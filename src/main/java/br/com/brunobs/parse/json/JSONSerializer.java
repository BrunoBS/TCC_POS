package br.com.brunobs.parse.json;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.Serializador;

public class JSONSerializer implements Serializador {

	private UriInfo uriInfo;

	public JSONSerializer(UriInfo uriInfo) {
		this.uriInfo = uriInfo;

	}

	public JSONSerializer() {
		this(null);
	}

	public String serialize(String rootName, Object target) {
		return new JSONParseVisitor(this.uriInfo).visit(rootName, target);
	}

	public String serialize(Object target) {
		return new JSONParseVisitor(this.uriInfo).visit(target);
	}

	public String prettyPrint(Object target) {
		return new JSONParseVisitor(this.uriInfo).visit(target);
	}

	public String prettyPrint(String rootName, Object target) {
		return new JSONParseVisitor(this.uriInfo).visit(rootName, target);
	}

}