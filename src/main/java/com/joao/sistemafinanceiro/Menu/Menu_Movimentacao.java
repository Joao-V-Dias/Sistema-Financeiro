package com.joao.sistemafinanceiro.Menu;

import com.joao.sistemafinanceiro.Model.*;
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
            System.out.printf("| %-36s |\n", "1 - Pagar");
            System.out.printf("| %-36s |\n", "2 - Receber");
            System.out.printf("| %-36s |\n", "3 - Listar Movimentos");
            System.out.printf("| %-36s |\n", "4 - Consultar Movimento por ID");
            System.out.printf("| %-36s |\n", "5 - Editar Movimento");
            System.out.printf("| %-36s |\n", "6 - Remover Movimento");
            System.out.printf("| %-36s |\n", "0 - Voltar");
            System.out.println("+--------------------------------------+");

            System.out.printf("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Movimentacao m = new Movimentacao();
                    Banco b = new Banco();
                    TituloCobranca t = new TituloCobranca();

                    System.out.print("Agencia do Banco: ");
                    b.setAgencia(scanner.nextLine());

                    System.out.print("Conta do Banco: ");
                    b.setConta(scanner.nextLine());
                    m.setBanco(b);

                    System.out.print("ID do Título de Cobrança: ");
                    t.setId(scanner.nextInt());
                    scanner.nextLine();
                    m.setTituloCobranca(t);

                    m.setTipo("RECEBER");

                    System.out.print("Observacao: ");
                    m.setObservacao(scanner.nextLine());

                    mService.salvar(m);
                }

                case 2 -> {
                    Movimentacao m = new Movimentacao();
                    Banco b = new Banco();
                    TituloCobranca t = new TituloCobranca();

                    System.out.print("Agencia do Banco: ");
                    b.setAgencia(scanner.nextLine());

                    System.out.print("Conta do Banco: ");
                    b.setConta(scanner.nextLine());
                    m.setBanco(b);

                    System.out.print("ID do Título de Cobrança: ");
                    t.setId(scanner.nextInt());
                    scanner.nextLine();
                    m.setTituloCobranca(t);

                    m.setTipo("PAGAR");

                    System.out.print("Observacao: ");
                    m.setObservacao(scanner.nextLine());

                    mService.salvar(m);
                }

                case 3 -> mService.consultarTodos();

                case 4 -> {
                    System.out.print("ID da movimentação: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    mService.consultarPorId(id);
                }

                case 5 -> {
                    Movimentacao m = new Movimentacao();
                    Banco b = new Banco();
                    TituloCobranca t = new TituloCobranca();

                    System.out.print("ID da Movimentação: ");
                    m.setId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Agencia do Banco: ");
                    b.setAgencia(scanner.nextLine());

                    System.out.print("Conta do Banco: ");
                    b.setConta(scanner.nextLine());
                    m.setBanco(b);

                    System.out.print("ID do Título de Cobrança: ");
                    t.setId(scanner.nextInt());
                    scanner.nextLine();
                    m.setTituloCobranca(t);

                    System.out.print("Tipo (PAGAR/RECEBER): ");
                    m.setTipo(scanner.nextLine());

                    System.out.print("Observacao: ");
                    m.setObservacao(scanner.nextLine());

                    mService.atualizar(m);
                }

                case 6 -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    mService.excluir(id);
                }

                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }
}
