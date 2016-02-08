package br.com.brunobs.parse.xml.element;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;

import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseSet;
import br.com.brunobs.parse.ParseVisitor;

public class XMLBean implements ParseElement {

	private ParseVisitor element;

	public XMLBean(ParseVisitor element) {
		this.element = element;
	}

	private ParseSet visits = new ParseSet(Collections.EMPTY_SET);

	public void nextElement(ParseElement xmlElement) {

	}

	public void execute(Object bean) {
		Boolean escreverTag = true;
		if (!this.visits.contains(bean)) {
			this.visits = new ParseSet(this.visits);
			this.visits.add(bean);
			Class klass = bean.getClass();
			if (!this.element.getNomeElemento().equals(klass.getSimpleName().toLowerCase())) {
				this.element.tagInicial(klass.getSimpleName().toLowerCase());
			} else {
				escreverTag = false;
			}
			try {

				Method[] methods = klass.getDeclaredMethods();

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
								// System.err.println(this.element.getBuilder().toString());
								this.element.setNomeElemento(key);
								this.element.setPrimeiroElemento(false);
								this.element.add(key, value);

							}
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
			if (escreverTag)
				this.element.tagFinal(klass.getSimpleName().toLowerCase());
			this.visits = (ParseSet) this.visits.getParent();
		}
	}

}
