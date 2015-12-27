package br.com.brunobs;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	private Long id = 1l;

	private String nome;
	private BigDecimal valor;
	private Integer quantidade;

	public Produto(String nome, BigDecimal valor, Integer quantidade, Long id) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.id = id;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	public Long getId() {
		return this.id;
	}

	public Produto() {
		super();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
