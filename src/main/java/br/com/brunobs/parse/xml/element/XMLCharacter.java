package br.com.brunobs.parse.xml.element;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class XMLCharacter extends XMLTypeElementStringAbstract {

	private ParseElement xmlElement;

	public XMLCharacter(ParseVisitor element) {
		super(element);
	}

	public void execute(Object objeto) {
		if (getType(objeto)) {
			stringValue(objeto);
		} else {
			this.xmlElement.execute(objeto);
		}
	}

	private boolean getType(Object objeto) {
		return objeto instanceof Character;
	}

	public void nextElement(ParseElement xmlElement) {
		this.xmlElement = xmlElement;
	}
}
