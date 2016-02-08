package br.com.brunobs.parse.xml.element;

import java.math.BigDecimal;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class XMLNumber implements ParseElement {

	private ParseVisitor element;
	private ParseElement xmlElement;

	public XMLNumber(ParseVisitor element) {
		this.element = element;
	}

	private boolean getType(Object object) {
		return object instanceof Number;
	}

	public void execute(Object object) {
		if (getType(object)) {
			getNumber(object);
		} else {
			this.xmlElement.execute(object);
		}

	}

	public void nextElement(ParseElement xmlElement) {
		this.xmlElement = xmlElement;
	}

	private void getNumber(Object object) {
		Number nnumber = (Number) object;
		if (nnumber != null && valida(nnumber)) {
			String numberString = nnumber.toString();
			if (numberString.indexOf('.') > 0 && numberString.indexOf('e') < 0 && numberString.indexOf('E') < 0) {
				while (numberString.endsWith("0")) {
					numberString = numberString.substring(0, numberString.length() - 1);
				}
				if (numberString.endsWith(".")) {
					numberString = numberString.substring(0, numberString.length() - 1);
				}
			}
			if (nnumber instanceof BigDecimal || nnumber instanceof Double || nnumber instanceof Float) {
				BigDecimal a = new BigDecimal(numberString);
				a = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				this.element.add(a);
			} else {
				this.element.add(nnumber);
			}

		} else {
			this.element.add("0");
		}
	}

	private boolean valida(Object o) {
		if (o != null) {
			if (o instanceof Double) {
				if (((Double) o).isInfinite() || ((Double) o).isNaN()) {
					return false;
				}
			} else if (o instanceof Float) {
				if (((Float) o).isInfinite() || ((Float) o).isNaN()) {
					return false;
				}
			}
		} else if (o instanceof Long) {
			return true;
		}

		return true;
	}
}