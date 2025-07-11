package com.joao.sistemafinanceiro.Menu;

import com.joao.sistemafinanceiro.Model.DocumentoFiscal;
import com.joao.sistemafinanceiro.Model.TituloCobranca;
import com.joao.sistemafinanceiro.Service.TituloCobrancaService;

import java.sql.Date;
import java.util.Scanner;

public class Menu_TituloCobranca{
	public static void executar(Scanner scanner){
		TituloCobrancaService tService = new TituloCobrancaService();
		int opcao;

		do{
			System.out.println("+--------------------------------------+");
			System.out.println("|             MENU TITULOS             |");
			System.out.println("+--------------------------------------+");
			System.out.printf("| %-36s |\n", "1 - Listar por Documento");
			System.out.printf("| %-36s |\n", "2 - Listar Todos Titulos");
			System.out.printf("| %-36s |\n", "3 - Editar Titulo");
			System.out.printf("| %-36s |\n", "4 - Remover Titulo");
			System.out.printf("| %-36s |\n", "0 - Voltar");
			System.out.println("+--------------------------------------+");
			System.out.printf("Escolha: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch(opcao){
				case 1 -> {
					System.out.printf("ID: ");
					String numero = scanner.nextLine();

					tService.consultarTodos(numero);
				}
				case 2 -> tService.buscarTodos();
				case 3 -> {
					TituloCobranca t = new TituloCobranca();
					DocumentoFiscal d = new DocumentoFiscal();

					System.out.printf("ID: ");
					t.setId(scanner.nextInt());
					scanner.nextLine();

					System.out.printf("Numero do Documento Referente: ");
					d.setNumero(scanner.nextLine());
					t.setDocumento(d);

					System.out.printf("Data de vencimento (yyyy-MM-dd): ");
					String dataStr = scanner.nextLine();
					Date dataEmissao = Date.valueOf(dataStr);
					t.setDataVencimento(dataEmissao);

					System.out.printf("Valor total: ");
					t.setValor(scanner.nextDouble());
					scanner.nextLine();

					tService.editar(t);
				}
				case 4 -> {
					System.out.printf("ID: ");
					int id = scanner.nextInt();
					scanner.nextLine();

					tService.excluir(id);
				}
				case 0 -> System.out.println("Voltando ao menu principal...");
				default -> System.out.println("Opcao invalida. ");
			}
		}while(opcao != 0);
	}
}
