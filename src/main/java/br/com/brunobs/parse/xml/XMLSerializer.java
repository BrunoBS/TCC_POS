package br.com.brunobs.parse.xml;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.Serializador;

public class XMLSerializer implements Serializador {

	private UriInfo uriInfo;

	public XMLSerializer(UriInfo uriInfo) {
		this.uriInfo = uriInfo;

	}

	public XMLSerializer() {
		this(null);
	}

	public String serialize(String rootName, Object target) {
		return new XMLParseVisitor(this.uriInfo).visit(rootName, target);
	}

	public String serialize(Object target) {
		return new XMLParseVisitor(this.uriInfo).visit(target);
	}

	public String prettyPrint(Object target) {
		return new XMLParseVisitor(this.uriInfo).visit(target);
	}

	public String prettyPrint(String rootName, Object target) {
		return new XMLParseVisitor(this.uriInfo).visit(rootName, target);
	}

}