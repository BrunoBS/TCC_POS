package br.com.brunobs.parse.xml.element;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class XMLNull implements ParseElement {

	private ParseVisitor element;
	private ParseElement xmlElement;

	public XMLNull(ParseVisitor element) {
		this.element = element;
	}

	public void execute(Object object) {
		if (getType(object)) {
			this.element.add("null");
		} else {
			this.xmlElement.execute(object);
		}

	}

	private boolean getType(Object object) {
		return object == null;
	}

	public void nextElement(ParseElement xmlElement) {
		this.xmlElement = xmlElement;

	}
}
