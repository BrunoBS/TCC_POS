package br.com.brunobs.parse.json.element;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class JsonBoolean implements ParseElement {

	private ParseVisitor element;
	private ParseElement jsonElement;

	public JsonBoolean(ParseVisitor element) {
		this.element = element;
	}

	public void execute(Object object) {
		if (getType(object)) {
			Boolean obj = (Boolean) object;
			this.element.add(obj.toString());
		} else {
			this.jsonElement.execute(object);
		}
	}

	private boolean getType(Object object) {
		return object instanceof Boolean;
	}

	public void nextElement(ParseElement jsonElement) {
		this.jsonElement = jsonElement;

	}
}
