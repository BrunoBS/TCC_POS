package br.com.brunobs.parse.json.element;

import java.util.Iterator;
import java.util.Map;

import br.com.brunobs.parse.json.JsonVisitor;

public class JsonMap implements JsonElement {

	private JsonVisitor element;
	private JsonElement jsonElement;

	public JsonMap(JsonVisitor element) {
		this.element = element;
	}

	public void execute(Object object) {
		if (getType(object)) {
			getMap(object);
		} else {
			this.jsonElement.execute(object);
		}
	}

	public void nextElement(JsonElement jsonElement) {
		this.jsonElement = jsonElement;

	}

	private boolean getType(Object object) {
		return object instanceof Map;
	}

	private void getMap(Object object) {
		Map map = (Map) object;
		this.element.inicioObjeto();
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
		this.element.endObject();
	}

}
