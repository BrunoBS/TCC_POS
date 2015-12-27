package br.com.brunobs.parse.json.element;

import br.com.brunobs.parse.json.JsonVisitor;

public class JsonBoolean implements JsonElement {

	private JsonVisitor element;
	private JsonElement jsonElement;

	public JsonBoolean(JsonVisitor element) {
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
		return object instanceof Boolean;
	}

	public void nextElement(JsonElement jsonElement) {
		this.jsonElement = jsonElement;

	}
}
