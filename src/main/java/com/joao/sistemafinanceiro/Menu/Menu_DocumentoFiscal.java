package com.joao.sistemafinanceiro.Menu;

import com.joao.sistemafinanceiro.Model.DocumentoFiscal;
import com.joao.sistemafinanceiro.Model.Parceiro;
import com.joao.sistemafinanceiro.Service.DocumentoFiscalService;

import java.sql.Date;
import java.util.Scanner;

public class Menu_DocumentoFiscal {
    public static void executar(Scanner scanner) {
        DocumentoFiscalService dService = new DocumentoFiscalService();
        int opcao;

        do {
            System.out.println("+--------------------------------------+");
            System.out.println("|         MENU DOCUMENTO FISCAL        |");
            System.out.println("+--------------------------------------+");
            System.out.printf("| %-36s |\n", "1 - Cadastrar Documento");
            System.out.printf("| %-36s |\n", "2 - Listar Documentos");
            System.out.printf("| %-36s |\n", "3 - Editar Documento");
            System.out.printf("| %-36s |\n", "4 - Remover Documento");
            System.out.printf("| %-36s |\n", "0 - Voltar");
            System.out.println("+--------------------------------------+");

            System.out.printf("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Parceiro emitente = new Parceiro();
                    Parceiro remetente = new Parceiro();

                    DocumentoFiscal d = new DocumentoFiscal();

                    System.out.printf("Tipo de Documento: ");
                    d.setTipo(scanner.nextLine());

                    System.out.printf("Numero: ");
                    d.setNumero(scanner.nextLine());

                    System.out.printf("CNPJ ou CPF do Emitente: ");
                    emitente.setDocumento(scanner.nextLine());
                    d.setEmitente(emitente);

                    System.out.printf("CNPJ ou CPF do Remente: ");
                    remetente.setDocumento(scanner.nextLine());
                    d.setRemetente(remetente);

                    System.out.printf("Data da Emissao (yyyy-MM-dd): ");
                    String dataStr = scanner.nextLine();
                    Date dataEmissao = Date.valueOf(dataStr);
                    d.setDataEmissao(dataEmissao);

                    System.out.printf("Valor total: ");
                    d.setValorTotal(scanner.nextDouble());
                    scanner.nextLine();

                    dService.salvar(d);
                }
                case 2 -> dService.consultarDocumentos();
                case 3 -> {
                    Parceiro emitente = new Parceiro();
                    Parceiro remetente = new Parceiro();

                    DocumentoFiscal d = new DocumentoFiscal();

                    System.out.printf("Tipo de Documento: ");
                    d.setTipo(scanner.nextLine());

                    System.out.printf("Numero: ");
                    d.setNumero(scanner.nextLine());

                    System.out.printf("CNPJ ou CPF do Emitente: ");
                    emitente.setDocumento(scanner.nextLine());
                    d.setEmitente(emitente);

                    System.out.printf("CNPJ ou CPF do Remente: ");
                    remetente.setDocumento(scanner.nextLine());
                    d.setRemetente(remetente);

                    System.out.printf("Data da Emissao (yyyy-MM-dd): ");
                    String dataStr = scanner.nextLine();
                    Date dataEmissao = Date.valueOf(dataStr);
                    d.setDataEmissao(dataEmissao);

                    System.out.printf("Valor total: ");
                    d.setValorTotal(scanner.nextDouble());
                    scanner.nextLine();

                    dService.atualizar(d);
                }
                case 4 -> {
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
