package br.com.brunobs.parse.json.element;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import br.com.brunobs.parse.json.JsonVisitor;

public abstract class JsonTypeElementStringAbstract implements JsonElement {

	protected JsonVisitor element;
	public final static char[] HEX = "0123456789ABCDEF".toCharArray();

	public JsonTypeElementStringAbstract(JsonVisitor element) {
		this.element = element;
	}

	protected void stringValue(Object object) {
		this.element.add('"');
		CharacterIterator it = new StringCharacterIterator(object.toString());
		for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
			if (c == '"') {
				this.element.add("\\\"");
			} else if (c == '\\') {
				this.element.add("\\\\");
			} else if (c == '\b') {
				this.element.add("\\b");
			} else if (c == '\f') {
				this.element.add("\\f");
			} else if (c == '\n') {
				this.element.add("\\n");
			} else if (c == '\r') {
				this.element.add("\\r");
			} else if (c == '\t') {
				this.element.add("\\t");
			} else if (Character.isISOControl(c)) {
				unicode(c);
			} else {
				this.element.add(c);
			}
		}
		this.element.add('"');
	}

	private void unicode(char c) {
		this.element.add("\\u");
		int n = c;
		for (int i = 0; i < 4; ++i) {
			int digit = (n & 0xf000) >> 12;
			this.element.add(HEX[digit]);
			n <<= 4;
		}
	}

}
