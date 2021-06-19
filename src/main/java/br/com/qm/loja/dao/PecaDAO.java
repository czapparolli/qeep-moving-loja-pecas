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

	public PecaDAO(EntityManager manager) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pecas");
		this.manager = factory.createEntityManager();
	}

	public void cadastraPeca(Scanner teclado) {

		Peca cadastra = new Peca();

		System.out.println("\nBem vindo ao menu de cadastrar pe�as, vamos come�ar...");
		System.out.print("\nDigite o n�mero do c�digo de barras da pe�a: ");
		int codigoDeBarras = teclado.nextInt();
		teclado.nextLine();
		System.out.print("\nDigite o nome da pe�a: ");
		String nomePeca = teclado.nextLine();
		System.out.print("\nDigite o modelo do carro para qual essa pe�a corresponde: ");
		String modeloCarro = teclado.nextLine();
		System.out.print("\nDigite o pre�o de custo da pe�a: ");
		float precoCusto = teclado.nextFloat();
		System.out.print("\nDigite o pre�o que voc� ir� vender a pe�a: ");
		float precoVenda = teclado.nextFloat();
		System.out.print("\nDigite quantas unidades dessa pe�a ser�o adicionadas ao estoque: ");
		int quantidadeEmEstoque = teclado.nextInt();
		teclado.nextLine();
		System.out.print("\nDigite a categoria correspondente desta pe�a (EX: Amortecedores, Freios e etc):  ");
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

		System.out.println("Pe�a cadastrada com sucesso !");
	}

	public void consultaPecaPorCodigo(Scanner teclado) {

		Peca consulta = new Peca();

		System.out.println("\nBem vindo ao menu de consultar pe�as, vamos come�ar...");
		System.out.print("\nDigite o n�mero do c�digo de barras da pe�a para consultar: ");
		int codigoDeBarras = teclado.nextInt();

		consulta = manager.find(Peca.class, codigoDeBarras);

		System.out.printf(
				"\nC�digo de barras: %d \nNome da pe�a: %s \nModelo do carro: %s \nPre�o de custo: R$ %.2f \nPre�o de venda: R$ %.2f \nQuantidade atual em estoque: %d \n",
				consulta.getCodigoDeBarras(), consulta.getNome(), consulta.getModeloCarro(), consulta.getPrecoCusto(),
				consulta.getPrecoVenda(), consulta.getQuantidadeEmEstoque(), consulta.getCategoria());

	}

	public List<Peca> litasTodasAsPecasEmEstoque() {

		// Peca consulta = new Peca();

		System.out.println("\nBem vindo ao menu de listagem de pe�as, vamos come�ar...");

		Query query = manager.createQuery("select p from Peca as p");

		// query.getResultList();

		List<Peca> pecas = query.getResultList();

		for (Peca consulta1 : pecas) {
			System.out.println("-------------------------------------------------");
			System.out.printf(
					"\nC�digo de barras: %d \nNome da pe�a: %s \nModelo do carro: %s \nPre�o de custo: R$ %.2f \nPre�o de venda: R$ %.2f \nQuantidade atual em estoque: %d \n\n",
					consulta1.getCodigoDeBarras(), consulta1.getNome(), consulta1.getModeloCarro(),
					consulta1.getPrecoCusto(), consulta1.getPrecoVenda(), consulta1.getQuantidadeEmEstoque(),
					consulta1.getCategoria());
		}

		return query.getResultList();
	}

	public List<Peca> listarPecasComEstoque() {
		Query query = manager.createQuery("select p from Peca as p");
		// where p.codigoDeBarras = 1
		// List<Peca> pecas =
		// query.setParameter("codigoDeBarras", 1);
		// query.setParameter("modeloCarro", "Onix");
		return query.getResultList();
	}

	public boolean alteraPeca(Peca loja) {

		Peca lojaDb = manager.find(Peca.class, loja.getCodigoDeBarras());
		lojaDb.setCodigoDeBarras(0);

		manager.getTransaction().begin();
		manager.merge(lojaDb);
		manager.getTransaction().commit();

		return true;
	}

}