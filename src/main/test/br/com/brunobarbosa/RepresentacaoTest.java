package br.com.brunobarbosa;

import org.junit.Test;

import br.com.brunobs.PedidoDAO;
import br.com.brunobs.parse.Serializador;
import br.com.brunobs.parse.json.JSONSerializer;
import br.com.brunobs.parse.xml.XMLSerializer;

public class RepresentacaoTest {

	@Test
	public void testJSONProdutos() {
		JSONSerializer serial = new JSONSerializer(null);
		imprime(serial);

	}

	@Test
	public void testXMLProdutos() {
		XMLSerializer serial = new XMLSerializer(null);
		imprime(serial);

	}

	private void imprime(Serializador serializador) {
		System.out.println(serializador.serialize("clientes", PedidoDAO.getProdutos()));
	}
}