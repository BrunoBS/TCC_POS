package br.com.brunobs.parse.xml.element;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class XMLNameClass extends XMLTypeElementStringAbstract {

	private ParseElement xmlElement;

	public XMLNameClass(ParseVisitor element) {
		super(element);
	}

	public void execute(Object objeto) {
		if (getType(objeto)) {
			stringValue(((Class) objeto).getName());
		} else {
			this.xmlElement.execute(objeto);
		}
	}

	public void nextElement(ParseElement xmlElement) {
		this.xmlElement = xmlElement;
	}

	private boolean getType(Object objeto) {
		return objeto instanceof Class;
	}

}
