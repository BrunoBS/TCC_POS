package br.com.brunobs.parse.json.element;

import java.lang.reflect.Array;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class JsonArray extends JsonTypeElementColecaoAbstract {

	private ParseElement jsonElement;

	public JsonArray(ParseVisitor element, UriInfo uriInfo) {
		super(element, uriInfo);
	}

	public void execute(Object object) {
		if (getType(object)) {
			InicioDoArray();
			int length = Array.getLength(object);
			for (int i = 0; i < length; ++i) {
				addArrayElement(Array.get(object, i), i < length - 1);
			}
			finalDoArray();
		} else {
			this.jsonElement.execute(object);
		}
	}

	private boolean getType(Object object) {
		return object.getClass().isArray();
	}

	public void nextElement(ParseElement jsonElement) {
		this.jsonElement = jsonElement;
	}

}
