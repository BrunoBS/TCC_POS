package br.com.brunobs.parse.json.element;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseSet;
import br.com.brunobs.parse.ParseVisitor;

public class JsonBean implements ParseElement {

	private ParseVisitor element;

	public JsonBean(ParseVisitor element) {
		this.element = element;
	}

	private ParseSet visits = new ParseSet(Collections.EMPTY_SET);

	public void nextElement(ParseElement jsonElement) {
	}

	public void execute(Object bean) {
		if (!this.visits.contains(bean)) {
			this.visits = new ParseSet(this.visits);
			this.visits.add(bean);
			this.element.inicioObjeto();
			try {
				Class klass = bean.getClass();

				Method[] methods = klass.getDeclaredMethods();
				boolean firstField = true;
				for (int i = 0; i < methods.length; i += 1) {
					Method method = methods[i];
					if (Modifier.isPublic(method.getModifiers())) {
						String name = method.getName();
						String key = "";
						boolean invoke = false;
						if (name.startsWith("get")) {
							invoke = true;
							key = name.substring(3);
						} else if (name.startsWith("is")) {
							key = name.substring(2);
							invoke = true;
						}
						if (key.length() > 0) {
							key = String.valueOf(key.charAt(0)).toLowerCase() + key.substring(1);
						}

						if (invoke) {
							Object value = method.invoke(bean, (Object[]) null);

							if (!this.visits.contains(value)) {
								this.element.add(key, value, firstField);
								firstField = false;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			this.element.endObject();
			this.visits = (ParseSet) this.visits.getParent();
		}
	}

}
