package br.com.brunobarbosa.json;

import javax.ws.rs.core.UriInfo;

import org.junit.Test;

import br.com.brunobs.PedidoDAO;
import br.com.brunobs.parse.json.JSONSerializer;

public class RepresentacaoTest {

	private final UriInfo uriInfo = null;

	@Test
	public void test() {
		JSONSerializer serial = new JSONSerializer(this.uriInfo);
		System.out.println(serial.serialize("pedidos", PedidoDAO.getPedidos()));
	}

}
