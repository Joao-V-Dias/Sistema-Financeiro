CREATE DATABASE bd_financeiro;
USE bd_financeiro;
-- DROP DATABASE bd_financeiro;

CREATE TABLE banco(
    nome VARCHAR(40) NOT NULL, 
    agencia VARCHAR(12) NOT NULL,
    conta VARCHAR(20) NOT NULL,
    tipo VARCHAR(13) NOT NULL,
    saldo DOUBLE NOT NULL DEFAULT 0.00,
    PRIMARY KEY (agencia, conta)
);

CREATE TABLE parceiro(
    documento VARCHAR(18) NOT NULL PRIMARY KEY,  
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(12) NOT NULL,
    email VARCHAR(50), 
    telefone VARCHAR(20)
);

CREATE TABLE documento_fiscal(
	numero VARCHAR(20) NOT NULL PRIMARY KEY,
    tipo VARCHAR(8) NOT NULL,
    cliente VARCHAR(18) NOT NULL,
    fornecedor VARCHAR(18) NOT NULL,
    data_emissao DATE NOT NULL,
    valor_total DOUBLE NOT NULL,
    FOREIGN KEY (cliente) REFERENCES parceiro(documento) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fornecedor) REFERENCES parceiro(documento) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE titulo_cobranca(
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_documento VARCHAR(20) NOT NULL,
    data_vencimento DATE NOT NULL,
    valor DOUBLE NOT NULL,
    status VARCHAR(8) NOT NULL DEFAULT 'ABERTO',
    FOREIGN KEY (numero_documento) REFERENCES documento_fiscal(numero) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE movimentacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    agencia VARCHAR(12) NOT NULL,
    conta VARCHAR(20) NOT NULL,
    titulo_cobranca INT NOT NULL,
    tipo VARCHAR(8) NOT NULL, 
    data_operacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observacao VARCHAR(100),
    FOREIGN KEY (agencia, conta) REFERENCES banco(agencia, conta) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (titulo_cobranca) REFERENCES titulo_cobranca(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- CADASTRO DA EMPRESA
INSERT INTO parceiro (documento, nome, tipo, email, telefone)
VALUES ('11.222.543/0001-00', 'Minha Empresa Ltda', 'MATRIZ', 'contato@minhaempresa.com', '11988887777');

-- TRIGGER
DELIMITER $$
CREATE TRIGGER trg_atualiza_saldo
AFTER INSERT ON movimentacao
FOR EACH ROW
BEGIN
	DECLARE pago VARCHAR(20);
    
    SELECT status INTO pago FROM titulo_cobranca WHERE id = NEW.titulo_cobranca;

	IF pago != 'PAGO' THEN
		IF NEW.tipo = 'RECEBER' THEN
			UPDATE banco
			SET saldo = saldo + (
				SELECT valor 
				FROM titulo_cobranca 
				WHERE id = NEW.titulo_cobranca
			)
			WHERE agencia = NEW.agencia AND conta = NEW.conta;

		ELSEIF NEW.tipo = 'PAGAR' THEN
			UPDATE banco
			SET saldo = saldo - (
				SELECT valor 
				FROM titulo_cobranca 
				WHERE id = NEW.titulo_cobranca
			)
			WHERE agencia = NEW.agencia AND conta = NEW.conta;
		END IF;
		UPDATE titulo_cobranca SET status = 'PAGO' WHERE id = NEW.titulo_cobranca;
	END IF;
END$$
DELIMITER ;


-- View
CREATE VIEW vw_movimentacao AS
SELECT
    m.id AS id_movimentacao,
    m.data_operacao,
    m.tipo AS tipo_movimentacao,
    b.nome AS banco,
    tc.id AS id_titulo,
    tc.data_vencimento,
    tc.status AS status_titulo,
    df.numero AS numero_documento,
    df.tipo AS tipo_documento,
    df.valor_total,
    cli.nome AS nome_cliente,
    forn.nome AS nome_fornecedor
FROM movimentacao m
JOIN banco b 
    ON m.agencia = b.agencia AND m.conta = b.conta
JOIN titulo_cobranca tc 
    ON m.titulo_cobranca = tc.id
JOIN documento_fiscal df 
    ON tc.numero_documento = df.numero
JOIN parceiro cli 
    ON df.cliente = cli.documento
JOIN parceiro forn 
    ON df.fornecedor = forn.documento;

CREATE VIEW vw_documentos_com_pessoas AS
SELECT 
    cli.nome AS nome_cliente,
    forn.nome AS nome_fornecedor,
    df.*
FROM 
    documento_fiscal df
JOIN parceiro cli ON df.cliente = cli.documento
JOIN parceiro forn ON df.fornecedor = forn.documento;

CREATE USER 'joao'@'localhost' IDENTIFIED BY '12345';
GRANT ALL PRIVILEGES ON bd_financeiro.* TO 'joao'@'localhost';

-- SQL DE TESTE
INSERT INTO banco (nome, agencia, conta, tipo, saldo) VALUES
('Banco do Brasil', '1234', '001122-3', 'Corrente', 1500.00),
('Caixa Economica', '5678', '004455-6', 'Poupanca', 3000.50),
('Bradesco', '4321', '007788-9', 'Corrente', 500.75),
('Itau', '8765', '009900-1', 'Poupanca', 8000.00),
('Nubank', '0001', '012345-6', 'Corrente', 250.00);

INSERT INTO parceiro (nome, documento, tipo, email, telefone) VALUES
('Maria Silva', '123.456.789-00', 'CLIENTE', 'maria@email.com', '11999999999'),
('Joao Souza', '11.222.333/0001-00', 'FORNECEDOR', 'joao@empresa.com', '1133334444'),
('Ana Pereira', '987.654.321-00', 'CLIENTE', 'ana.pereira@gmail.com', '11988887777'),
('Empresa XYZ', '22.333.444/0001-55', 'FORNECEDOR', 'contato@xyz.com.br', '1144445555'),
('Carlos Lima', '555.666.777-88', 'AMBOS', 'carlos.lima@exemplo.com', '1177776666');


INSERT INTO documento_fiscal (numero, tipo, cliente, fornecedor, data_emissao, valor_total) VALUES
('NF001', 'NFe', '123.456.789-00', '11.222.333/0001-00', '2025-07-01', 1200.00),
('NF002', 'NFe', '11.222.333/0001-00', '123.456.789-00', '2025-07-03', 890.50),
('NF003', 'NFe', '123.456.789-00', '11.222.333/0001-00', '2025-07-05', 450.75);

INSERT INTO titulo_cobranca (numero_documento, data_vencimento, valor, status) VALUES
("NF001", '2025-07-10', 1200.00, 'ABERTO'),
("NF001", '2025-07-15', 890.50, 'PAGO'),
("NF001", '2025-07-20', 450.75, 'ABERTO'),
("NF003", '2025-07-25', 620.00, 'CANC'),
("NF002", '2025-08-01', 3100.00, 'ABERTO');

INSERT INTO movimentacao (agencia, conta, titulo_cobranca, tipo, observacao) VALUES
('1234', '001122-3', 1, 'PAGAR', 'Pagamento referente ao título 1'),
('1234', '001122-3', 2, 'RECEBER', 'Recebimento referente ao título 2'),
('5678', '004455-6', 3, 'PAGAR', 'Pagamento referente ao título 3'),
('5678', '004455-6', 5, 'RECEBER', 'Recebimento referente ao título 5'),
('4321', '007788-9', 4, 'PAGAR', 'Cancelamento referente ao título 4'),
('0001', '012345-6', 5, 'RECEBER', 'Recebimento extra no Nubank');
