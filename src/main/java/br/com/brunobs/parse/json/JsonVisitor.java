package br.com.brunobs.parse.json;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.json.element.JsonString;

public class JsonVisitor {
	private StringBuffer builder;
	private TypeCampoJson campo;
	private boolean isElementoPai;

	public StringBuffer getBuilder() {
		return this.builder;
	}

	public boolean isElementoPai() {
		return this.isElementoPai;
	}

	public void setElementoPai(boolean isElementoPai) {
		this.isElementoPai = isElementoPai;
	}

	protected JsonVisitor() {
		this.isElementoPai = true;
		this.builder = new StringBuffer();
		this.campo = new TypeCampoJson(this);
	}

	protected JsonVisitor(UriInfo uriInfo) {
		this.isElementoPai = true;
		this.builder = new StringBuffer();
		this.campo = new TypeCampoJson(this, uriInfo);
	}

	public String visit(Object target) {
		json(target);
		return this.builder.toString();
	}

	public String visit(String rootName, Object target) {
		this.isElementoPai = true;
		inicioObjeto();
		new JsonString(this).execute(rootName);
		add(':');
		json(target);
		endObject();
		return this.builder.toString();
	}

	public void json(Object object) {
		this.campo.getJsonElement().execute(object);

	}

	protected boolean addVirgula(boolean addVirgula) {
		if (!addVirgula) {
			add(',');
		} else {
			addVirgula = false;
		}
		return addVirgula;
	}

	public void inicioObjeto() {
		add('{');
	}

	public void endObject() {
		add('}');
		this.isElementoPai = false;
	}

	public void add(char c) {
		this.builder.append(c);
	}

	public void add(Object value) {
		this.builder.append(value);
	}

	public void add(Object key, Object value, boolean addVirgula) {
		int start = this.builder.length();
		addVirgula(addVirgula);
		addAttribute(key);

		int len = this.builder.length();
		json(value);
		if (len == this.builder.length()) {
			this.builder.delete(start, len);
		}
	}

	private void addAttribute(Object key) {
		System.out.println(key);
		this.builder.append("\"");
		this.builder.append(key);
		this.builder.append("\"");
		this.builder.append(":");
	}

}