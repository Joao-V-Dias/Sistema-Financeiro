package com.joao.sistemafinanceiro.Menu;

import java.util.Scanner;

/**
 * @author Joao
 * @version 1.0
 * @since Jul 3, 2025
 */
public class Menu_Principal{

	public static void executar(){
		Scanner scanner = new Scanner(System.in);
		int opcao = -1;

		while(opcao != 0){
			System.out.println("+--------------------------------------+");
			System.out.println("|         SISTEMA FINANCEIRO           |");
			System.out.println("+--------------------------------------+");
			System.out.printf("| %-36s |\n", "1 - Parceiros");
			System.out.printf("| %-36s |\n", "2 - Movimento");
			System.out.printf("| %-36s |\n", "3 - Banco");
			System.out.printf("| %-36s |\n", "4 - Documentos Fiscais");
			System.out.printf("| %-36s |\n", "5 - Titulos de Cobranca");
			System.out.printf("| %-36s |\n", "0 - Sair");
			System.out.println("+--------------------------------------+");
			System.out.printf("Escolha: ");
			opcao = scanner.nextInt();

			switch(opcao){
				case 1 -> Menu_Parceiro.executar(scanner);
				case 2 -> Menu_Movimentacao.executar(scanner);
				case 3 -> Menu_Banco.executar(scanner);
				case 4 -> Menu_DocumentoFiscal.executar(scanner);
				case 5 -> Menu_TituloCobranca.executar(scanner);
				case 0 -> System.out.println("Encerrando o sistema... ");
				default -> System.out.println("Opcao invalida. ");
			}
		}
		scanner.close();
	}
}
