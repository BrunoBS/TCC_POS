package br.com.brunobs.parse.json.element;

import br.com.brunobs.parse.json.JsonVisitor;

public class JsonNull implements JsonElement {

	private JsonVisitor element;
	private JsonElement jsonElement;

	public JsonNull(JsonVisitor element) {
		this.element = element;
	}

	public void execute(Object object) {
		if (getType(object)) {
			this.element.add("null");
		} else {
			this.jsonElement.execute(object);
		}

	}

	private boolean getType(Object object) {
		return object == null;
	}

	public void nextElement(JsonElement jsonElement) {
		this.jsonElement = jsonElement;

	}
}
