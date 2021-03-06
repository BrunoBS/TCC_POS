package br.com.brunobs.parse.json.element;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class JsonCharacter extends JsonTypeElementStringAbstract {

	private ParseElement jsonElement;

	public JsonCharacter(ParseVisitor element) {
		super(element);
	}

	public void execute(Object objeto) {
		if (getType(objeto)) {
			stringValue(objeto);
		} else {
			this.jsonElement.execute(objeto);
		}
	}

	private boolean getType(Object objeto) {
		return objeto instanceof Character;
	}

	public void nextElement(ParseElement jsonElement) {
		this.jsonElement = jsonElement;
	}
}
