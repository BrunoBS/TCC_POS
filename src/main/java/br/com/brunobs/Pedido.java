package br.com.brunobs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

public class Pedido11 {
	@Id
	private Long id;
	private Cliente cliente;
	private Integer numero;
	private List<Produto> produtos = new ArrayList<Produto>();

	public Pedido() {
		super();
	}

	@Override
	public String toString() {
		return this.cliente.getNome();
	}

	public Pedido(Long id, Cliente cliente, Integer numero, List<Produto> produtos) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.numero = numero;
		this.produtos = produtos;
	}

	public Long getId() {
		return this.id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void addProdutos(Produto produto) {
		this.produtos.add(produto);
	}

}
