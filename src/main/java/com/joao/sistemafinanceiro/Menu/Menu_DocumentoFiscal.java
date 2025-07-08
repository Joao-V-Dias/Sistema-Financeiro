package com.joao.sistemafinanceiro.Menu;

import com.joao.sistemafinanceiro.Model.DocumentoFiscal;
import com.joao.sistemafinanceiro.Model.Parceiro;
import com.joao.sistemafinanceiro.Model.TituloCobranca;
import com.joao.sistemafinanceiro.Service.DocumentoFiscalService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu_DocumentoFiscal {
    public static void executar(Scanner scanner) {
        DocumentoFiscalService dService = new DocumentoFiscalService();
        int opcao;

        do {
            System.out.println("+--------------------------------------+");
            System.out.println("|         MENU DOCUMENTO FISCAL        |");
            System.out.println("+--------------------------------------+");
            System.out.printf("| %-36s |\n", "1 - Emitir Documento");
            System.out.printf("| %-36s |\n", "2 - Receber Documento");
            System.out.printf("| %-36s |\n", "3 - Listar Documentos");
            System.out.printf("| %-36s |\n", "4 - Editar Documento");
            System.out.printf("| %-36s |\n", "5 - Remover Documento");
            System.out.printf("| %-36s |\n", "0 - Voltar");
            System.out.println("+--------------------------------------+");

            System.out.printf("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Parceiro cliente = new Parceiro();
                    Parceiro fornecedor = new Parceiro();
                    List<TituloCobranca> lstT = new ArrayList<>();

                    DocumentoFiscal d = new DocumentoFiscal();

                    System.out.printf("Tipo de Documento: ");
                    d.setTipo(scanner.nextLine());

                    System.out.printf("Numero: ");
                    d.setNumero(scanner.nextLine());

                    System.out.printf("CNPJ ou CPF do Cliente: ");
                    cliente.setDocumento(scanner.nextLine());
                    d.setCliente(cliente);

                    fornecedor.setDocumento("11.222.543/0001-00");
                    d.setFornecedor(fornecedor);

                    System.out.printf("Data da Emissao (yyyy-MM-dd): ");
                    String dataStr = scanner.nextLine();
                    Date dataEmissao = Date.valueOf(dataStr);
                    d.setDataEmissao(dataEmissao);

                    System.out.printf("Quantidade de pagamentos: ");
                    int qtdPagamentos = scanner.nextInt();
                    scanner.nextLine();

                    double valorTotal = 0.0;

                    for (int i = 0; i < qtdPagamentos; i++) {
                        TituloCobranca t = new TituloCobranca();
                        t.setDocumento(d);

                        System.out.printf("Data de vencimento (yyyy-MM-dd): ");
                        String dataStrT = scanner.nextLine();
                        Date dataEmissaoT = Date.valueOf(dataStrT);
                        t.setDataVencimento(dataEmissaoT);

                        System.out.printf("Valor: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();
                        t.setValor(valor);

                        valorTotal+= valor;
                        lstT.add(t);
                    }

                    d.setTitulos(lstT);

                    dService.salvar(d);
                }
                case 2 -> {
                    Parceiro cliente = new Parceiro();
                    Parceiro fornecedor = new Parceiro();

                    DocumentoFiscal d = new DocumentoFiscal();

                    System.out.printf("Tipo de Documento: ");
                    d.setTipo(scanner.nextLine());

                    System.out.printf("Numero: ");
                    d.setNumero(scanner.nextLine());

                    cliente.setDocumento("11.222.543/0001-00");
                    d.setCliente(cliente);

                    System.out.printf("CNPJ ou CPF do Fornecedor: ");
                    fornecedor.setDocumento(scanner.nextLine());
                    d.setFornecedor(fornecedor);

                    System.out.printf("Data da Emissao (yyyy-MM-dd): ");
                    String dataStr = scanner.nextLine();
                    Date dataEmissao = Date.valueOf(dataStr);
                    d.setDataEmissao(dataEmissao);

                    System.out.printf("Valor total: ");
                    d.setValorTotal(scanner.nextDouble());
                    scanner.nextLine();

                    dService.salvar(d);
                }
                case 3 -> dService.consultarDocumentos();
                case 4 -> {
                    Parceiro cliente = new Parceiro();
                    Parceiro fornecedor = new Parceiro();

                    DocumentoFiscal d = new DocumentoFiscal();

                    System.out.printf("Tipo de Documento: ");
                    d.setTipo(scanner.nextLine());

                    System.out.printf("Numero: ");
                    d.setNumero(scanner.nextLine());

                    System.out.printf("CNPJ ou CPF do Cliente: ");
                    cliente.setDocumento(scanner.nextLine());
                    d.setCliente(cliente);

                    System.out.printf("CNPJ ou CPF do Remente: ");
                    fornecedor.setDocumento(scanner.nextLine());
                    d.setFornecedor(fornecedor);

                    System.out.printf("Data da Emissao (yyyy-MM-dd): ");
                    String dataStr = scanner.nextLine();
                    Date dataEmissao = Date.valueOf(dataStr);
                    d.setDataEmissao(dataEmissao);

                    System.out.printf("Valor total: ");
                    d.setValorTotal(scanner.nextDouble());
                    scanner.nextLine();

                    dService.atualizar(d);
                }
                case 5 -> {
                    System.out.printf("Numero: ");
                    String numero = scanner.nextLine();

                    dService.excluir(numero);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida. ");
            }
        } while (opcao != 0);
    }
}
