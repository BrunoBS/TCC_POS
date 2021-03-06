package br.com.brunobs.parse.json.element;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ParseVisitor;

public abstract class JsonTypeElementColecaoAbstract extends JsonTypeElementStringAbstract {

	protected ParseVisitor element;
	protected UriInfo uriInfo;

	public JsonTypeElementColecaoAbstract(ParseVisitor element, UriInfo uriInfo) {
		super(element);
		this.uriInfo = uriInfo;
		this.element = element;
	}

	protected void addArrayElement(Object object, boolean isLast) {
		int len = this.element.getBuilder().length();
		this.element.parse(object);
		if (len < this.element.getBuilder().length()) {
			if (isLast)
				this.element.add(',');
		}
	}

	protected void InicioDoArray() {
		this.element.add('[');
	}

	protected void finalDoArray() {
		this.element.add(']');
	}

}
