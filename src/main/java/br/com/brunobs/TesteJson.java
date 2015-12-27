package br.com.brunobs;

import br.com.brunobs.parse.json.JSONSerializer;

public class TesteJson {
	public static void main(String[] args) {

		JSONSerializer serial = new JSONSerializer();
		serial = new JSONSerializer();
		String retorno = serial.serialize(PedidoDAO.getPedidos());
		System.out.println(retorno);
	}
}