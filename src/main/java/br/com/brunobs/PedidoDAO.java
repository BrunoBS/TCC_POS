package br.com.brunobs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

	private static final List<Pedido> pedidos;
	private static final List<Produto> produtos;
	private static final Cliente cliente;
	private static final Pedido pedido1;
	private static final Pedido pedido2;
	private static final Pedido pedido3;

	static {
		pedidos = new ArrayList<Pedido>();
		produtos = new ArrayList<Produto>();
		produtos.add(new Produto("COMPUTADOR", new BigDecimal(1400.55).setScale(2, RoundingMode.HALF_UP), 1, 1l));
		produtos.add(new Produto("IMPRESSORA", new BigDecimal(400.16).setScale(2, RoundingMode.HALF_UP), 1, 2l));
		produtos.add(new Produto("MONITOR", new BigDecimal(599.02).setScale(2, RoundingMode.HALF_UP), 2, 3l));
		cliente = new Cliente("Bruno", "443264-4446");
		pedido1 = new Pedido(1l, cliente, 01, produtos);
		pedidos.add(pedido1);
		pedido2 = new Pedido(2l, cliente, 02, produtos);
		pedidos.add(pedido2);
		pedido3 = new Pedido(3l, cliente, 03, produtos);
		pedidos.add(pedido3);

	}

	public static List<Pedido> getPedidos() {
		return pedidos;
	}

	public static List<Produto> getProdutos() {
		return produtos;
	}

	public static Cliente getCliente() {
		return cliente;
	}

	public static Pedido getPedido1() {
		return pedido1;
	}

	public static Pedido getPedido2() {
		return pedido2;
	}

	public static Pedido getPedido3() {
		return pedido3;
	}

}
