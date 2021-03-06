package br.com.brunobs.parse.json.element;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class JsonNull implements ParseElement {

	private ParseVisitor element;
	private ParseElement jsonElement;

	public JsonNull(ParseVisitor element) {
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

	public void nextElement(ParseElement jsonElement) {
		this.jsonElement = jsonElement;

	}
}
