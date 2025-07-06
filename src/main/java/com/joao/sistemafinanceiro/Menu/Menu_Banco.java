package com.joao.sistemafinanceiro.Menu;

import com.joao.sistemafinanceiro.Model.Banco;
import com.joao.sistemafinanceiro.Service.BancoService;

import java.util.Scanner;

/**
 * @author Joao
 * @version 1.0
 * @since Jul 5, 2025
 */
public class Menu_Banco {

    public static void executar(Scanner scanner) {
        BancoService bService = new BancoService();
        int opcao;

        do {
            System.out.println("+--------------------------------------+");
            System.out.println("|              MENU BANCO              |");
            System.out.println("+--------------------------------------+");
            System.out.printf("| %-36s |\n", "1 - Cadastrar Banco");
            System.out.printf("| %-36s |\n", "2 - Listar Bancos");
            System.out.printf("| %-36s |\n", "3 - Editar Banco");
            System.out.printf("| %-36s |\n", "4 - Remover Banco");
            System.out.printf("| %-36s |\n", "0 - Voltar");
            System.out.println("+--------------------------------------+");

            System.out.printf("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Banco b = new Banco();

                    System.out.printf("Nome do banco: ");
                    b.setNome(scanner.nextLine());

                    System.out.printf("Agencia: ");
                    b.setAgencia(scanner.nextLine());

                    System.out.printf("Conta: ");
                    b.setConta(scanner.nextLine());

                    System.out.printf("Tipo: ");
                    b.setTipo(scanner.nextLine());

                    System.out.printf("Saldo atual: ");
                    b.setSaldo(scanner.nextDouble());
                    scanner.nextLine();

                    bService.salvar(b);
                }
                case 2 -> bService.consultarTodos();
                case 3 -> {
                    Banco b = new Banco();

                    System.out.printf("Nome do banco: ");
                    b.setNome(scanner.nextLine());

                    System.out.printf("Agencia: ");
                    b.setAgencia(scanner.nextLine());

                    System.out.printf("Conta: ");
                    b.setConta(scanner.nextLine());

                    System.out.printf("Tipo: ");
                    b.setTipo(scanner.nextLine());

                    System.out.printf("Saldo atual: ");
                    b.setSaldo(scanner.nextDouble());
                    scanner.nextLine();

                    bService.atualizar(b);
                }
                case 4 -> {
                    System.out.printf("Agencia: ");
                    String agencia = scanner.nextLine();

                    System.out.printf("Conta: ");
                    String conta = scanner.nextLine();

                    bService.excluir(agencia, conta);
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida. ");
            }
        } while (opcao != 0);
    }
}
