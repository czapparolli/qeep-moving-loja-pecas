package br.com.qm.loja.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.qm.loja.pojo.Peca;

public class VendaDAO {

	private EntityManager manager;
	private float acumuladoraTeste = 0;
	 

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

		do {
			try {

				System.out.println("\nBem vindo ao menu de venda, vamos começar...");
				System.out.print("\nDigite o número do código de barras da peça: ");
				codigoDeBarras = teclado.nextInt();
				venda = manager.find(Peca.class, codigoDeBarras);

			} catch (Exception e) {
				System.err.println("\nVenda não computada, não existe o código de barras informado... Tente novamente !");
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

			atualizaEstoque =  venda.getQuantidadeEmEstoque()  - quantidade;

			venda.setQuantidadeEmEstoque(atualizaEstoque);

			manager.getTransaction().begin();
			manager.merge(venda);
			manager.getTransaction().commit();


			acumuladoraTeste = acumuladoraTeste + (venda.getPrecoVenda() * quantidade);
			
			
			for (Peca consulta : relatorio) {
				System.out.println("-------------------------------------------------");
				System.out.printf(
						"\nCódigo de barras: %d \nNome da peça: %s \nModelo do carro: %s \nPreço de custo: R$ %.2f \nPreço de venda: R$ %.2f \nQuantidade atual em estoque: %d \n\n",
						consulta.getCodigoDeBarras(), consulta.getNome(), consulta.getModeloCarro(),
						consulta.getPrecoCusto(), consulta.getPrecoVenda(), consulta.getQuantidadeEmEstoque(),
						consulta.getCategoria());
			}

		} while (parada != 1);
	}
	

}
