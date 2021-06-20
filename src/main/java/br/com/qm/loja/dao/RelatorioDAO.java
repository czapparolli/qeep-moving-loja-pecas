package br.com.qm.loja.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
		List<Peca> teste = new ArrayList();
		FileWriter writer = new FileWriter("arquivo.txt");
		//writer.write(null);
	}

}
