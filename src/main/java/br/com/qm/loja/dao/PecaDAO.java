package br.com.qm.loja.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.qm.loja.pojo.Peca;

public class PecaDAO {

	private EntityManager manager;
	
	private List<Peca> relatorioInicio = new ArrayList();
	private List<Peca> relatorioFim = new ArrayList();

	public PecaDAO(EntityManager manager) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pecas");
		this.manager = factory.createEntityManager();
	}

	public void cadastraPeca(Scanner teclado) {

		Peca cadastra = new Peca();

		System.out.println("\nBem vindo ao menu de cadastrar peças, vamos começar...");
		System.out.print("\nDigite o número do código de barras da peça: ");
		int codigoDeBarras = teclado.nextInt();
		teclado.nextLine();
		System.out.print("\nDigite o nome da peça: ");
		String nomePeca = teclado.nextLine();
		System.out.print("\nDigite o modelo do carro para qual essa peça corresponde: ");
		String modeloCarro = teclado.nextLine();
		System.out.print("\nDigite o preço de custo da peça: ");
		float precoCusto = teclado.nextFloat();
		System.out.print("\nDigite o preço que você irá vender a peça: ");
		float precoVenda = teclado.nextFloat();
		System.out.print("\nDigite quantas unidades dessa peça serão adicionadas ao estoque: ");
		int quantidadeEmEstoque = teclado.nextInt();
		teclado.nextLine();
		System.out.print("\nDigite a categoria correspondente desta peça (EX: Amortecedores, Freios e etc):  ");
		String categoria = teclado.nextLine();

		cadastra.setCodigoDeBarras(codigoDeBarras);
		cadastra.setNome(nomePeca);
		cadastra.setModeloCarro(modeloCarro);
		cadastra.setPrecoCusto(precoCusto);
		cadastra.setPrecoVenda(precoVenda);
		cadastra.setQuantidadeEmEstoque(quantidadeEmEstoque);
		cadastra.setCategoria(categoria);

		manager.getTransaction().begin();
		manager.persist(cadastra);
		manager.getTransaction().commit();

		System.out.println("Peça cadastrada com sucesso !");
		manager.close();
	}

	public void consultaPecaPorCodigo(Scanner teclado) {

		Peca consulta = new Peca();

		System.out.println("\nBem vindo ao menu de consultar peças, vamos começar...");
		System.out.print("\nDigite o número do código de barras da peça para consultar: ");
		int codigoDeBarras = teclado.nextInt();

		consulta = manager.find(Peca.class, codigoDeBarras);

		System.out.printf(
				"\nCódigo de barras: %d \nNome da peça: %s \nModelo do carro: %s \nPreço de custo: R$ %.2f \nPreço de venda: R$ %.2f \nQuantidade atual em estoque: %d \n",
				consulta.getCodigoDeBarras(), consulta.getNome(), consulta.getModeloCarro(), consulta.getPrecoCusto(),
				consulta.getPrecoVenda(), consulta.getQuantidadeEmEstoque(), consulta.getCategoria());
		manager.close();
	}

	public List<Peca> listaTodasAsPecasEmEstoque() {


		System.out.println("\nBem vindo ao menu de listagem de peças, vamos começar...");

		Query query = manager.createQuery("select p from Peca as p where p.quantidadeEmEstoque > 0");

		// query.getResultList();

		List<Peca> relatorioInicio = query.getResultList();

		for (Peca consulta1 : relatorioInicio) {
			System.out.println("-------------------------------------------------");
			System.out.printf(
					"\nCódigo de barras: %d \nNome da peça: %s \nModelo do carro: %s \nPreço de custo: R$ %.2f \nPreço de venda: R$ %.2f \nQuantidade atual em estoque: %d \nCategoria %s \n\n",
					consulta1.getCodigoDeBarras(), consulta1.getNome(), consulta1.getModeloCarro(),
					consulta1.getPrecoCusto(), consulta1.getPrecoVenda(), consulta1.getQuantidadeEmEstoque(),
					consulta1.getCategoria());
		}
		return query.getResultList();
	}

	public List<Peca> listaPecaPorLetra(Scanner teclado) {
		teclado.nextLine();

		System.out.println("\nBem vindo ao menu de consultar peças, vamos começar...");
		System.out.print("\nDigite uma letra para listar todas as peças em estoque começadas por ela: ");
		String input = teclado.nextLine();

		Query query = manager.createQuery("select p from Peca as p where p.nome = :nome");
		query.setParameter("nome", input);
		// '"+input+"'%");

		List<Peca> pecas = query.getResultList();

		for (Peca consulta : pecas) {
			System.out.println("-------------------------------------------------");
			System.out.printf(
					"\nCódigo de barras: %d \nNome da peça: %s \nModelo do carro: %s \nPreço de custo: R$ %.2f \nPreço de venda: R$ %.2f \nQuantidade atual em estoque: %d \n\n",
					consulta.getCodigoDeBarras(), consulta.getNome(), consulta.getModeloCarro(),
					consulta.getPrecoCusto(), consulta.getPrecoVenda(), consulta.getQuantidadeEmEstoque(),
					consulta.getCategoria());
		}
		manager.close();
		return query.getResultList();
		

	}

	public List<Peca> listaPecaPorCategoria(Scanner teclado) {
		teclado.nextLine();

		System.out.println("\nBem vindo ao menu de consultar peças, vamos começar...");
		System.out.print("\nDigite uma categoria para listar todas as peças dela:  ");
		String input = teclado.nextLine();

		Query query = manager.createQuery("select p from Peca as p where p.categoria = :categoria");
		query.setParameter("categoria", input);
		// input + "%"

		List<Peca> pecas = query.getResultList();

		if (pecas.equals(null)) {
			System.out.println("é nulo");
		} else {
			for (Peca consulta : pecas) {
				System.out.println("-------------------------------------------------");
				System.out.printf(
						"\nCódigo de barras: %d \nNome da peça: %s \nModelo do carro: %s \nPreço de custo: R$ %.2f \nPreço de venda: R$ %.2f \nQuantidade atual em estoque: %d \nCategoria %s \n\n",
						consulta.getCodigoDeBarras(), consulta.getNome(), consulta.getModeloCarro(),
						consulta.getPrecoCusto(), consulta.getPrecoVenda(), consulta.getQuantidadeEmEstoque(),
						consulta.getCategoria());
			}
		}
		manager.close();

		return query.getResultList();

	}

	public boolean removePecaPorCodigo(Scanner teclado) {

		System.out.println("\nBem vindo ao menu de consultar peças, vamos começar...");
		System.out.print("\nDigite o número do código de barras da peça para consultar: ");
		int codigoDeBarras = teclado.nextInt();

		Peca remove = manager.find(Peca.class, codigoDeBarras);

		if (remove == null) {
			System.out.printf("\nNão foi encontrado nenhuma peça de codigo de barras %i, tente novamente",
					codigoDeBarras);
			return false;
		}
		manager.getTransaction().begin();
		manager.remove(remove);
		manager.getTransaction().commit();
		System.out.printf("Peça %s removida com sucesso\n", remove.getNome());
		manager.close();
		return true;

	}

	public List<Peca> inicioEstoquePrograma() {

		Peca inicio = new Peca();
		Query query = manager.createQuery("select p from Peca as p order by p.codigoDeBarras");
		
		List<Peca> relatorioFim = query.getResultList();
		for (Peca consulta : relatorioFim ) {
			System.out.println("-------------------------------------------------");
			System.out.printf(
					"\nCódigo de barras: %d \nNome da peça: %s \nModelo do carro: %s \nPreço de custo: R$ %.2f \nPreço de venda: R$ %.2f \nQuantidade atual em estoque: %d \n\n",
					consulta.getCodigoDeBarras(), consulta.getNome(), consulta.getModeloCarro(),
					consulta.getPrecoCusto(), consulta.getPrecoVenda(), consulta.getQuantidadeEmEstoque(),
					consulta.getCategoria());
		}
		return query.getResultList();

	}

	public List<Peca> fimEstoquePrograma() {

		Peca fim = new Peca();
		Query query = manager.createQuery("select p from Peca as p order by p.codigoDeBarras");
		
		List<Peca> pecas = query.getResultList();
		for (Peca consulta : pecas ) {
			System.out.println("-------------------------------------------------");
			System.out.printf(
					"\nCódigo de barras: %d \nNome da peça: %s \nModelo do carro: %s \nPreço de custo: R$ %.2f \nPreço de venda: R$ %.2f \nQuantidade atual em estoque: %d \n\n",
					consulta.getCodigoDeBarras(), consulta.getNome(), consulta.getModeloCarro(),
					consulta.getPrecoCusto(), consulta.getPrecoVenda(), consulta.getQuantidadeEmEstoque(),
					consulta.getCategoria());
		}
		return query.getResultList();

	}

}
