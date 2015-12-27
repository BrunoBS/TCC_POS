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

		if (getType(objeto)) {
			if (!this.element.isElementoPai() && this.uriInfo != null) {
				stringValue(this.uriInfo.getRequestUri().getPath());

			} else {

				if (this.element.isElementoPai()) {
					this.element.setElementoPai(false);
					Iterator it = ((Collection) objeto).iterator();
					InicioDoArray();
					while (it.hasNext()) {
						addArrayElement(it.next(), it.hasNext());
					}
					finalDoArray();
				} else {

					addArrayElement(this.uriInfo.getRequestUri().getPath(), false);
				}

			}
		} else {
			this.jsonElement.execute(objeto);
		}
	}

	public void nextElement(JsonElement jsonElement) {
		this.jsonElement = jsonElement;
	}

	private boolean getType(Object objeto) {
		return objeto instanceof Collection;
	}

}
