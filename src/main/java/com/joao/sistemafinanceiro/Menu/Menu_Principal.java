package com.joao.sistemafinanceiro.Menu;

import java.util.Scanner;

/**
 * @author Joao
 * @version 1.0
 * @since Jul 3, 2025
 */
public class Menu_Principal {

    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("+---------------------------+");
            System.out.println("| %-3 | %-14 |");
            
            System.out.println("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
            }
        }
        scanner.close();
    }
}
