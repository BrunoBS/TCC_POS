package br.com.brunobs.parse.json.element;

import br.com.brunobs.parse.json.JsonVisitor;

public class JsonNameClass extends JsonTypeElementStringAbstract {

	private JsonElement jsonElement;

	public JsonNameClass(JsonVisitor element) {
		super(element);
	}

	public void execute(Object objeto) {
		if (getType(objeto)) {
			stringValue(((Class) objeto).getName());
		} else {
			this.jsonElement.execute(objeto);
		}
	}

	public void nextElement(JsonElement jsonElement) {
		this.jsonElement = jsonElement;
	}

	private boolean getType(Object objeto) {
		return objeto instanceof Class;
	}
}
