package br.com.brunobs.parse.json.element;

import br.com.brunobs.parse.json.JsonVisitor;

public class JsonCharacter extends JsonTypeElementStringAbstract {

	private JsonElement jsonElement;

	public JsonCharacter(JsonVisitor element) {
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

	public void nextElement(JsonElement jsonElement) {
		this.jsonElement = jsonElement;
	}
}
