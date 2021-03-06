package br.com.brunobs.parse.json.element;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class JsonDate extends JsonTypeElementStringAbstract {

	private ParseElement jsonElement;
	private SimpleDateFormat format;

	public JsonDate(ParseVisitor element, SimpleDateFormat format) {
		super(element);
		this.format = format;
	}

	public JsonDate(ParseVisitor element) {
		super(element);
		this.format = new SimpleDateFormat("dd/MM/yyyy");

	}

	public void execute(Object object) {
		if (getType(object)) {
			Date date = ((Date) object);
			stringValue(this.format.format(date));
		} else {
			this.jsonElement.execute(object);
		}
	}

	public void nextElement(ParseElement jsonElement) {
		this.jsonElement = jsonElement;
	}

	private boolean getType(Object object) {
		return object instanceof Date;
	}

}
