package br.com.qm.loja.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.qm.loja.pojo.Peca;

public class VendaDAO {

	private EntityManager manager;
	float acumuladoraTeste = 0;

	List<Peca> relatorio = new ArrayList();

	public VendaDAO(EntityManager manager) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pecas");
		this.manager = factory.createEntityManager();
	}

	public void realizaVenda(Scanner teclado) {
		int atualizaEstoque = 0;
		int parada = 0;
		int codigoDeBarras = 0;
		int quantidade = 0;
		Peca venda = new Peca();
		PecaDAO pecaDao = new PecaDAO(manager);

		do {
			try {

				System.out.println("\nBem vindo ao menu de venda, vamos começar...");
				System.out.print("\nDigite o número do código de barras da peça: ");
				codigoDeBarras = teclado.nextInt();
				venda = manager.find(Peca.class, codigoDeBarras);

			} catch (Exception e) {
				System.err
						.println("\nVenda não computada, não existe o código de barras informado... Tente novamente !");
			}

			System.out.print("\nDigite quantas unidades desta peça serão vendidas:  ");
			quantidade = teclado.nextInt();

			if (quantidade > venda.getQuantidadeEmEstoque() || quantidade <= 0) {
				System.err.println("\nQuantidade de peças inválidas com o estoque atual... Tente novamente");
				break;

			}

			System.out.println("\nDeseja inserir outro produto? Digite [0] para continuar e [1] para encerrar");
			parada = teclado.nextInt();

			venda = manager.find(Peca.class, codigoDeBarras);

			venda.getQuantidadeEmEstoque();

			atualizaEstoque = venda.getQuantidadeEmEstoque() - quantidade;
			
			venda.setQuantidadeEmEstoque(atualizaEstoque);

			manager.getTransaction().begin();
			manager.merge(venda);
			manager.getTransaction().commit();

			acumuladoraTeste = acumuladoraTeste + (venda.getPrecoVenda() * quantidade);
			
			

		} while (parada != 1);
	}

	public void escritor() throws IOException {
		PecaDAO pecaDao = new PecaDAO(manager);

		VendaDAO vendaDao = new VendaDAO(manager);
		List<Peca> teste = pecaDao.fimEstoquePrograma();
		List<Peca> fim = pecaDao.fimEstoquePrograma();
		List<Peca> inicio = pecaDao.inicioEstoquePrograma();

		FileWriter writer = new FileWriter("Relatório de vendas diário.txt");
		PrintWriter gravarArq = new PrintWriter(writer);

		

		int quantidade = 2;

		/*
		 * for (Peca consultaTeste : inicio) {
		 * 
		 * quantidade = consultaTeste.getQuantidadeEmEstoque(); }
		 * 
		 * for (Peca consultaTeste2 : fim) {
		 * 
		 * quantidade = quantidade - consultaTeste2.getQuantidadeEmEstoque(); }
		 */
		
		gravarArq.println(
				"---------------------------------------------------------------------------------------------------------------------------------------\n");
		for (Peca str : teste) {
			
			gravarArq.printf("Código de barras: %d | Nome da peça: %s | Quantidade: %d | Valor de venda: R$ %.2f\n",
					str.getCodigoDeBarras(), str.getNome(), str.getQuantidadeEmEstoque(), str.getPrecoVenda());

			gravarArq.println(
					"\n---------------------------------------------------------------------------------------------------------------------------------------\n");

		}
		gravarArq.printf("\nO total de vendas no dia de hoje foi R$ %.2f \n", acumuladoraTeste);
		writer.close();
		System.out.println("\nRelatório diário gerado com sucesso, verifique o arquivo TXT na pasta do programa");
	}

}
