package br.com.brunobs.parse.xml.element;

import java.lang.reflect.Array;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class XMLArray extends XMLTypeElementColecaoAbstract {
	private ParseElement xmlElement;

	public XMLArray(ParseVisitor element, UriInfo uriInfo) {
		super(element, uriInfo);
	}

	public void execute(Object object) {
		String nomeElemento = this.element.getNomeElemento();
		if (getType(object)) {
			this.element.tagInicial(nomeElemento);
			int length = Array.getLength(object);
			for (int i = 0; i < length; ++i) {
				addArrayElement(Array.get(object, i));
			}
			this.element.tagFinal(nomeElemento);
		} else {
			this.xmlElement.execute(object);
		}
	}

	private boolean getType(Object object) {
		return object.getClass().isArray();
	}

	public void nextElement(ParseElement jsonElement) {
		this.xmlElement = jsonElement;
	}

}
