package br.com.brunobs.parse.json.element;

import java.util.Collection;
import java.util.Iterator;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.json.JsonVisitor;

public class JsonCollection extends JsonTypeElementColecaoAbstract {

	private JsonElement jsonElement;

	public JsonCollection(JsonVisitor element, UriInfo uriInfo) {
		super(element, uriInfo);
	}

	public void execute(Object objeto) {
		String url = this.uriInfo != null ? this.uriInfo.getRequestUri().getPath() : "Não foi localizado a URL";

		if (getType(objeto)) {
			if (!this.element.isElementoPai()) {

				if (this.uriInfo == null) {
					preencheDados(objeto);
				} else {
					stringValue(url);
				}
			} else {
				preencheDados(objeto);

			}
		} else {
			this.jsonElement.execute(objeto);
		}
	}

	private void preencheDados(Object objeto) {
		this.element.setElementoPai(false);
		Iterator<Collection> it = ((Collection) objeto).iterator();
		InicioDoArray();
		while (it.hasNext()) {
			addArrayElement(it.next(), it.hasNext());
		}
		finalDoArray();
	}

	public void nextElement(JsonElement jsonElement) {
		this.jsonElement = jsonElement;
	}

	private boolean getType(Object objeto) {
		return objeto instanceof Collection;
	}

}
