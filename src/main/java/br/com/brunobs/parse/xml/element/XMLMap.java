package br.com.brunobs.parse.xml.element;

import java.util.Iterator;
import java.util.Map;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;

public class XMLMap implements ParseElement {

	private ParseVisitor element;
	private ParseElement jsonElement;

	public XMLMap(ParseVisitor element) {
		this.element = element;
	}

	public void execute(Object object) {
		if (getType(object)) {
			getMap(object);
		} else {
			this.jsonElement.execute(object);
		}
	}

	public void nextElement(ParseElement jsonElement) {
		this.jsonElement = jsonElement;

	}

	private boolean getType(Object object) {
		return object instanceof Map;
	}

	private void getMap(Object objeto) {
		if (getType(objeto)) {
			Map map = (Map) objeto;
			String nomeElemento = this.element.getNomeElemento();
			this.element.tagInicial(nomeElemento);
			Iterator it = map.keySet().iterator();
			boolean firstField = true;
			while (it.hasNext()) {
				Object key = it.next();
				int len = this.element.getBuilder().length();
				this.element.add(key, map.get(key), firstField);
				if (len < this.element.getBuilder().length()) {
					firstField = false;
				}
			}
			this.element.tagFinal(nomeElemento);
		} else {
			this.jsonElement.execute(objeto);

		}
	}

}
