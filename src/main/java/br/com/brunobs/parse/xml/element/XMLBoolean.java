package br.com.brunobs.parse.xml.element;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class XMLBoolean implements ParseElement {

	private ParseVisitor element;
	private ParseElement xmlElement;

	public XMLBoolean(ParseVisitor element) {
		this.element = element;
	}

	public void execute(Object object) {
		if (getType(object)) {
			Boolean obj = (Boolean) object;
			this.element.add(obj.toString());
		} else {
			this.xmlElement.execute(object);
		}
	}

	private boolean getType(Object object) {
		return object instanceof Boolean;
	}

	public void nextElement(ParseElement xmlElement) {
		this.xmlElement = xmlElement;

	}
}
