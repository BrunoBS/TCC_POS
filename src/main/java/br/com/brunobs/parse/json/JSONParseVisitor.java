package br.com.brunobs.parse.json;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ParseVisitor;
import br.com.brunobs.parse.json.element.JsonString;

public class JSONParseVisitor extends ParseVisitor {

	public JSONParseVisitor(UriInfo uriInfo) {
		super(uriInfo);
		this.chainParse = new JSONTypeCampo(this, uriInfo);
	}

	@Override
	public String visit(Object target) {
		parse(target);
		return this.builder.toString();
	}

	@Override
	public String visit(String rootElemento, Object target) {
		this.rootElemento = rootElemento;
		this.primeiroElemento = true;
		inicioObjeto();
		new JsonString(this).execute(this.rootElemento);
		add(':');
		parse(target);
		endObject();
		return this.builder.toString();
	}

	protected boolean addVirgula(boolean addVirgula) {
		if (!addVirgula) {
			add(',');
		} else {
			addVirgula = false;
		}
		return addVirgula;
	}

	@Override
	public void add(Object key, Object value, boolean addVirgula) {
		int start = this.builder.length();
		addVirgula(addVirgula);
		addAttribute(key);

		int len = this.builder.length();
		parse(value);
		if (len == this.builder.length()) {
			this.builder.delete(start, len);
		}
	}

	private void addAttribute(Object key) {
		this.builder.append("\"");
		this.builder.append(key);
		this.builder.append("\"");
		this.builder.append(":");
	}

	@Override
	public void inicioObjeto() {
		add('{');
	}

	@Override
	public void endObject() {
		add('}');
		this.primeiroElemento = false;
	}

	@Override
	public void parse(Object object) {
		this.chainParse.getElement().execute(object);

	}

	@Override
	public void add(Object key, Object value) {

	}

	@Override
	public void tagInicial(Object key) {

	}

	@Override
	public void tagFinal(Object key) {

	}

}