package br.com.brunobs.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.brunobs.Pedido;
import br.com.brunobs.PedidoDAO;
import br.com.brunobs.Produto;
import br.com.brunobs.produces.NomeElemento;

@Path("/")
public class PedidoService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pedidos")
	@NomeElemento(nome = "pedidos")
	public List<Pedido> findPedidos() {
		return PedidoDAO.getPedidos();
	}

	@GET
	@Path("pedido/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	@NomeElemento(nome = "pedido")
	public Pedido findPedido(@PathParam("value") int value) {
		return PedidoDAO.getPedidos().get(value);
	}

	@GET
	@Path("pedido/{value}/produtos/")
	@Produces(MediaType.APPLICATION_JSON)
	@NomeElemento(nome = "produtos")
	public List<Produto> findProdutos(@PathParam("value") int value) {
		return PedidoDAO.getPedidos().get(value).getProdutos();
	}

	@GET
	@Path("pedido/{pedido}/produto/{produto}")
	@Produces(MediaType.APPLICATION_JSON)
	@NomeElemento(nome = "produto")
	public Produto findProduto(@PathParam("pedido") int pedido, @PathParam("produto") int produto) {
		return PedidoDAO.getPedidos().get(pedido).getProdutos().get(produto);
	}
}