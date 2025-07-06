package com.joao.sistemafinanceiro.Menu;

import com.joao.sistemafinanceiro.Model.Parceiro;
import com.joao.sistemafinanceiro.Service.ParceiroService;

import java.util.Scanner;

/**
 * @author Joao
 * @version 1.0
 * @since Jul 5, 2025
 */
public class Menu_Parceiro {
    public static void executar(Scanner scanner) {
        ParceiroService pService = new ParceiroService();
        int opcao;

        do {
            System.out.println("+--------------------------------------+");
            System.out.println("|            MENU PARCEIROS            |");
            System.out.println("+--------------------------------------+");
            System.out.printf("| %-36s |\n", "1 - Cadastrar Parceiro");
            System.out.printf("| %-36s |\n", "2 - Listar Parceiro");
            System.out.printf("| %-36s |\n", "3 - Editar Parceiro");
            System.out.printf("| %-36s |\n", "4 - Remover Parceiro");
            System.out.printf("| %-36s |\n", "0 - Voltar");
            System.out.println("+--------------------------------------+");

            System.out.printf("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Parceiro p = new Parceiro();

                    System.out.printf("Nome: ");
                    p.setNome(scanner.nextLine());

                    System.out.printf("CNPJ/CPF: ");
                    p.setDocumento(scanner.nextLine());

                    System.out.printf("Tipo (Fornecedor/Cliente/Ambos): ");
                    p.setTipo(scanner.nextLine());

                    System.out.printf("Email: ");
                    p.setEmail(scanner.nextLine());

                    System.out.printf("Telefone: ");
                    p.setTelefone(scanner.nextLine());

                    pService.salvar(p);
                }
                case 2 -> pService.consultarTodos();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida. ");
            }
        } while (opcao != 0);
    }
}
