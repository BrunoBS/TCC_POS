package br.com.brunobs.parse.xml.element;

import java.util.Collection;
import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class XMLCollection extends XMLTypeElementColecaoAbstract {

	private ParseElement xmlElement;
	private String url;

	public XMLCollection(ParseVisitor element, UriInfo uriInfo) {
		super(element, uriInfo);
		this.url = this.uriInfo != null ? this.uriInfo.getRequestUri().getPath() : "Não foi localizado a URL";
	}

	public void execute(Object objeto) {

		if (getType(objeto)) {
			if (!this.element.isPrimeiroElemento()) {
				// == E NAO != TODO FAZER A REGRA PARA GERAR A URL> NÃO SEI
				// COMO!

				if (this.uriInfo != null) {
					preencheDados(objeto);
				} else {
					this.element.parse(this.url);
				}
			} else {
				preencheDados(objeto);

			}
		} else {
			this.xmlElement.execute(objeto);
		}
	}

	private void preencheDados(Object objeto) {
		this.element.setPrimeiroElemento(false);
		Iterator<Collection> it = ((Collection) objeto).iterator();
		while (it.hasNext()) {
			this.element.parse(it.next());
		}
		this.element.getBuilder().toString();
	}

	public void nextElement(ParseElement xmlElement) {
		this.xmlElement = xmlElement;
	}

	private boolean getType(Object objeto) {
		return objeto instanceof Collection;
	}
}