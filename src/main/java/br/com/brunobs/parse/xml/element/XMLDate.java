package br.com.brunobs.parse.xml.element;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class XMLDate extends XMLTypeElementStringAbstract {

	private ParseElement xmlElement;
	private SimpleDateFormat format;

	public XMLDate(ParseVisitor element, SimpleDateFormat format) {
		super(element);
		this.format = format;
	}

	public XMLDate(ParseVisitor element) {
		super(element);
		this.format = new SimpleDateFormat("dd/MM/yyyy");

	}

	public void execute(Object object) {
		if (getType(object)) {
			Date date = ((Date) object);
			stringValue(this.format.format(date));
		} else {
			this.xmlElement.execute(object);
		}
	}

	public void nextElement(ParseElement xmlElement) {
		this.xmlElement = xmlElement;
	}

	private boolean getType(Object object) {
		return object instanceof Date;
	}

}
