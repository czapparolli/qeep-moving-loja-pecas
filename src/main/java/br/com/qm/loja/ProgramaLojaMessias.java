package br.com.qm.loja;

import br.com.qm.loja.dao.PecaDAO;
import br.com.qm.loja.pojo.Peca;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;



public class ProgramaLojaMessias {
	
	Scanner teclado = new Scanner(System.in);
	
	private static EntityManager manager;
	
	public static void menuGestaoDePecas (Scanner teclado) {
		PecaDAO pecaDao = new PecaDAO(manager);
		
		
		int opcaoMenuGestaoDePecas = 0;

		do {
			System.out.println("\n------------------- Loja de Auto Pe�as Messias -------------------\n");
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
				
			case 6:
				pecaDao.listaPecaPorCategoria(teclado);
				break;
			default:
				System.out.println("Op��o inv�lida!");

			}
		} while (opcaoMenuGestaoDePecas != 10);

	}
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		PecaDAO pecaDao = new PecaDAO(manager);
		
		
	/*	List<Peca> pecas = lojaDao.listarPecas();
		for (Peca peca : pecas ) {
			System.out.println(peca);
		}
		
	}
}*/
		
		
		int opcaoMenuCadastroPrincipal = 0;

		do {
			System.out.println("\n------------------- Loja de Auto Pe�as Messias -------------------\n\n");
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
				// menuGerenciamentoDeVendas(teclado);
				break;

			default:
				System.out.println("Op��o inv�lida!");

			}
		} while (opcaoMenuCadastroPrincipal != 3);

	}
}

