package br.com.brunobs.parse.xml.element;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ParseVisitor;

public abstract class XMLTypeElementColecaoAbstract extends XMLTypeElementStringAbstract {

	protected ParseVisitor element;
	protected UriInfo uriInfo;

	public XMLTypeElementColecaoAbstract(ParseVisitor element, UriInfo uriInfo) {
		super(element);
		this.uriInfo = uriInfo;
		this.element = element;
	}

	protected void addArrayElement(Object object) {
		this.element.parse(object);

	}

}
