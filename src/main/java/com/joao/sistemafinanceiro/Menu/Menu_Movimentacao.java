package com.joao.sistemafinanceiro.Menu;

import com.joao.sistemafinanceiro.Model.Banco;
import com.joao.sistemafinanceiro.Model.DocumentoFiscal;
import com.joao.sistemafinanceiro.Model.Movimentacao;
import com.joao.sistemafinanceiro.Service.MovimentacaoService;

import java.util.Scanner;

public class Menu_Movimentacao {
    public static void executar(Scanner scanner) {
        MovimentacaoService mService = new MovimentacaoService();
        int opcao;

        do {
            System.out.println("+--------------------------------------+");
            System.out.println("|           MENU MOVIMENTACAO          |");
            System.out.println("+--------------------------------------+");
            System.out.printf("| %-36s |\n", "1 - Gerar Movimento");
            System.out.printf("| %-36s |\n", "2 - Listar Movimentos");
            System.out.printf("| %-36s |\n", "2 - Consultar Movimento por ID");
            System.out.printf("| %-36s |\n", "3 - Editar Movimento");
            System.out.printf("| %-36s |\n", "4 - Remover Movimento");
            System.out.printf("| %-36s |\n", "0 - Voltar");
            System.out.println("+--------------------------------------+");

            System.out.printf("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Movimentacao m = new Movimentacao();
                    Banco b = new Banco();
                    DocumentoFiscal d = new DocumentoFiscal();

                    System.out.printf("Agencia do Banco: ");
                    b.setAgencia(scanner.nextLine());

                    System.out.printf("Conta do Banco: ");
                    b.setConta(scanner.nextLine());

                    m.setBanco(b);

                    System.out.printf("Documento: ");
                    d.setNumero(scanner.nextLine());

                    m.setDocumento(d);

                    System.out.printf("Tipo (PAGAR/RECEBER): ");
                    m.setTipo(scanner.nextLine());

                    System.out.println("Valor: ");
                    m.setValor(scanner.nextDouble());

                    System.out.println("Observacao: ");
                    m.setObservacao(scanner.nextLine());

                    mService.salvar(m);
                }
                case 2 -> mService.consultarTodos();
                case 3 -> {
                    Movimentacao m = new Movimentacao();
                    Banco b = new Banco();
                    DocumentoFiscal d = new DocumentoFiscal();

                    System.out.printf("ID: ");
                    m.setId(scanner.nextInt());

                    System.out.printf("Agencia do Banco: ");
                    b.setAgencia(scanner.nextLine());

                    System.out.printf("Conta do Banco: ");
                    b.setConta(scanner.nextLine());

                    m.setBanco(b);

                    System.out.printf("Documento: ");
                    d.setNumero(scanner.nextLine());

                    m.setDocumento(d);

                    System.out.printf("Tipo (PAGAR/RECEBER): ");
                    m.setTipo(scanner.nextLine());

                    System.out.println("Valor: ");
                    m.setValor(scanner.nextDouble());

                    System.out.println("Observacao: ");
                    m.setObservacao(scanner.nextLine());
                }
                case 4 -> {
                    System.out.printf("ID: ");
                    int id = scanner.nextInt();

                    mService.excluir(id);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida. ");
            }
        } while (opcao != 0);
    }
}
