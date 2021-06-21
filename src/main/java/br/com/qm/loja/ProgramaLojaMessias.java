package br.com.qm.loja;

import br.com.qm.loja.dao.*;
import br.com.qm.loja.pojo.Peca;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

public class ProgramaLojaMessias {

	Scanner teclado = new Scanner(System.in);

	private static EntityManager manager;

	public static void menuGestaoDePecas(Scanner teclado) {
		PecaDAO pecaDao = new PecaDAO(manager);

		int opcaoMenuGestaoDePecas = 0;

		do {
			System.out.println("\n------------------- Menu de gest�o e controle de estoque -------------------\n");
			System.out.println("[1] - cadastrar uma nova pe�a");
			System.out.println("[2] - consultar uma pe�a pelo seu c�digo de barras");
			System.out.println("[3] - listar todas as pe�as em estoque");
			System.out.println("[4] - listar todas as pe�as come�adas com o nome come�ado por um determinado texto");
			System.out.println("[5] - listar todas as pe�as para um determinado modelo de carro (o carro dever� ser lido)");
			System.out.println("[6] - listar todas as pe�as de uma determinada categoria");
			System.out.println("[7] - remover uma pe�a do estoque");
			System.out.println("[8] - voltar para o menu principal");
			System.out.print("\nDigite a op��o: ");
			opcaoMenuGestaoDePecas = teclado.nextInt();

			switch (opcaoMenuGestaoDePecas) {
			case 1:
				pecaDao.cadastraPeca(teclado);
				break;
			case 2:
				pecaDao.consultaPecaPorCodigo(teclado);
				break;
			case 3:
				pecaDao.listaTodasAsPecasEmEstoque();
				break;
			case 4:
				pecaDao.listaPecaPorLetra(teclado);
				break;
			case 5:
				pecaDao.listaTodasAsPecasPorModelo(teclado);
				break;

			case 6:
				pecaDao.listaPecaPorCategoria(teclado);
				break;
			case 7:
				pecaDao.removePecaPorCodigo(teclado);
				break;
			case 8:
				System.out.println("\nVoltando para o menu principal...\n");
				break;
			default:
				System.out.println("Op��o inv�lida!");

			}
		} while (opcaoMenuGestaoDePecas != 8);

	}

	public static void menuVendas(Scanner teclado) throws IOException {
		PecaDAO pecaDao = new PecaDAO(manager);
		VendaDAO vendaDao = new VendaDAO(manager);
		RelatorioDAO relatorioDao = new RelatorioDAO(manager);

		int opcaoMenuGestaoVendas = 0;

		do {
			System.out.println("\n------------------- Loja de Auto Pe�as Messias -------------------\n");
			System.out.println("[1] - cadastrar uma nova venda");
			System.out.println("[2] - relat�rio di�rio de venda");
			System.out.println("[3] - voltar para o menu principal");
			System.out.print("\nDigite a op��o: ");
			opcaoMenuGestaoVendas = teclado.nextInt();

			switch (opcaoMenuGestaoVendas) {
			case 1:
				vendaDao.realizaVenda(teclado);
				break;
			case 2:
				vendaDao.escritor();
				break;
			case 3:
				System.out.println("\nVoltando para o menu principal...\n");
				break;
			default:
				System.out.println("Op��o inv�lida!");

			}
		} while (opcaoMenuGestaoVendas != 3);

	}

	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		PecaDAO pecaDao = new PecaDAO(manager);
		VendaDAO vendaDao = new VendaDAO(manager);

		int opcaoMenuCadastroPrincipal = 0;

		do {
			System.out.println("------------------- Loja de Auto Pe�as Messias -------------------\n");
			System.out.println("[1] - Gest�o de pe�as");
			System.out.println("[2] - Gerenciamento de vendas");
			System.out.println("[3] - Para encerrar o programa");
			System.out.print("\nDigite a op��o: ");
			opcaoMenuCadastroPrincipal = teclado.nextInt();

			switch (opcaoMenuCadastroPrincipal) {
			case 1:
				menuGestaoDePecas(teclado);
				break;
			case 2:
				menuVendas(teclado);
				break;
			case 3:
				System.out.println("\nPrograma encerrado com sucesso... At� mais !");
				break;
			default:
				System.out.println("Op��o inv�lida!");

			}
		} while (opcaoMenuCadastroPrincipal != 3);

	}
}
