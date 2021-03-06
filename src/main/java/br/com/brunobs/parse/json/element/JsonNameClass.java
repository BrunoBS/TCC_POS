package br.com.brunobs.parse.json.element;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class JsonNameClass extends JsonTypeElementStringAbstract {

	private ParseElement jsonElement;

	public JsonNameClass(ParseVisitor element) {
		super(element);
	}

	public void execute(Object objeto) {
		if (getType(objeto)) {
			stringValue(((Class) objeto).getName());
		} else {
			this.jsonElement.execute(objeto);
		}
	}

	public void nextElement(ParseElement jsonElement) {
		this.jsonElement = jsonElement;
	}

	private boolean getType(Object objeto) {
		return objeto instanceof Class;
	}
}
