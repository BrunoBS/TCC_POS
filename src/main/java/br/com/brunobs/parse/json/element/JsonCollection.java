package br.com.brunobs.parse.json.element;

import java.util.Collection;
import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class JsonCollection extends JsonTypeElementColecaoAbstract {

	private ParseElement jsonElement;
	private String url;

	public JsonCollection(ParseVisitor element, UriInfo uriInfo) {
		super(element, uriInfo);
		this.url = this.uriInfo != null ? this.uriInfo.getRequestUri().getPath() : "N�o foi localizado a URL";
	}

	public void execute(Object objeto) {

		if (getType(objeto)) {
			if (!this.element.isPrimeiroElemento()) {
				// == E NAO != TODO FAZER A REGRA PARA GERAR A URL> N�O SEI
				// COMO!

				if (this.uriInfo != null) {
					preencheDados(objeto);
				} else {
					stringValue(this.url);
				}
			} else {
				preencheDados(objeto);

			}
		} else {
			this.jsonElement.execute(objeto);
		}
	}

	private void preencheDados(Object objeto) {
		this.element.setPrimeiroElemento(false);
		Iterator<Collection> it = ((Collection) objeto).iterator();
		InicioDoArray();
		while (it.hasNext()) {
			addArrayElement(it.next(), it.hasNext());
		}
		finalDoArray();
	}

	public void nextElement(ParseElement jsonElement) {
		this.jsonElement = jsonElement;
	}

	private boolean getType(Object objeto) {
		return objeto instanceof Collection;
	}

}
