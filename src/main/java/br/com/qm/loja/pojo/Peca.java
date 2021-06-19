package br.com.qm.loja.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




@Entity
public class Peca {
	@Id
	@Column(name ="codigo_de_barras")
	private int codigoDeBarras;
	
	@Column(name ="nome", nullable = false, length = 50)
	private String nome;
	
	@Column(name ="modelo_carro", nullable = false)
	private String modeloCarro;
	
	@Column(name ="preco_custo", nullable = false)
	private float precoCusto;
	
	@Column(name ="preco_venda", nullable = false)
	private float precoVenda;
	
	@Column(name ="quantidade_em_estoque", nullable = false)
	private int quantidadeEmEstoque;
	
	@Column(name ="categoria", nullable = false)
	private String categoria;
	
	public Peca(int codigoDeBarras, String nome, String modeloCarro, float precoCusto, float precoVenda,
			int quantidadeEmEstoque, String categoria) {
		this.codigoDeBarras = codigoDeBarras;
		this.nome = nome;
		this.modeloCarro = modeloCarro;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
		this.categoria = categoria;
	}
	
	public Peca (){
	}

	public int getCodigoDeBarras() {
		return codigoDeBarras;
	}


	public void setCodigoDeBarras(int codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getModeloCarro() {
		return modeloCarro;
	}


	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}


	public float getPrecoCusto() {
		return precoCusto;
	}


	public void setPrecoCusto(float precoCusto) {
		this.precoCusto = precoCusto;
	}


	public float getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}


	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}


	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Peca [codigoDeBarras=" + codigoDeBarras + ", nome=" + nome + ", modeloCarro=" + modeloCarro
				+ ", precoCusto=" + precoCusto + ", precoVenda=" + precoVenda + ", quantidadeEmEstoque="
				+ quantidadeEmEstoque + ", categoria=" + categoria + "]";
	}
	
	
}
	