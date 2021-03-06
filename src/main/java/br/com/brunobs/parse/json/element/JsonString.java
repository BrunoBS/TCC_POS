package br.com.brunobs.parse.json.element;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class JsonString extends JsonTypeElementStringAbstract {

	private ParseElement jsonElement;

	public JsonString(ParseVisitor element) {
		super(element);
	}

	public void execute(Object objeto) {
		if (getType(objeto)) {
			stringValue(objeto);
		} else {
			this.jsonElement.execute(objeto);
		}
	}

	public void nextElement(ParseElement jsonElement) {
		this.jsonElement = jsonElement;
	}

	private boolean getType(Object objeto) {
		return objeto instanceof String;
	}
}
