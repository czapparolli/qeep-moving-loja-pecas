package br.com.qm.loja.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.qm.loja.pojo.Peca;
import br.com.qm.loja.dao.*;

public class RelatorioDAO {

	private EntityManager manager;
	
	public RelatorioDAO(EntityManager manager) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pecas");
		this.manager = factory.createEntityManager();
	}
	
	public  void escritor() throws IOException {
		PecaDAO pecaDao = new PecaDAO(manager);
		VendaDAO vendaDao = new VendaDAO(manager);
		List<Peca> teste = pecaDao.fimEstoquePrograma();
		
		FileWriter writer = new FileWriter("arquivo.txt");
		PrintWriter gravarArq = new PrintWriter(writer);
		float total = vendaDao.acumuladoraTeste;
		//int quantidade = pecaDao.acumuladoraVenda(0);
		//pecaDao.fimEstoquePrograma();
		for (Peca str : teste) {
			gravarArq.printf(
					"\nCódigo de barras: %d Nome da peça: %s Quantidade: %d Valor de venda: R$ %.2f  \n\n",
					str.getCodigoDeBarras(), str.getNome(), str.getQuantidadeEmEstoque(), total
					);
			System.out.println("--------------------------------------------------");
			//gravarArq.printf("\nO total de vendas no dia de hoje foi R$ %.2f \n",total );
			
			//writer.write(str + System.lineSeparator());
		}
		writer.close();
		//writer.write(null);
	}

}
