package br.com.brunobs.parse.xml;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ParseVisitor;

public class XMLParseVisitor extends ParseVisitor {

	public XMLParseVisitor(UriInfo uriInfo) {
		super(uriInfo);
		this.chainParse = new XMLTypeCampo(this, uriInfo);
	}

	@Override
	public String visit(Object target) {
		return this.visit(target.getClass().getSimpleName().toLowerCase(), target);
	}

	@Override
	public String visit(String rootElemento, Object target) {
		setNomeElemento(rootElemento);
		this.rootElemento = rootElemento;
		this.primeiroElemento = true;
		tagInicial(rootElemento);
		parse(target);
		tagFinal(rootElemento);
		this.primeiroElemento = false;
		return this.builder.toString();
	}

	@Override
	public void add(Object key, Object value, boolean imprimeTags) {
		if (imprimeTags) {
			tagInicial(key);
		}
		parse(value);
		if (imprimeTags) {
			tagFinal(key);
		}

	}

	@Override
	public void add(Object key, Object value) {
		this.add(key, value, true);

	}

	@Override
	public void tagInicial(Object key) {
		this.builder.append("<");
		this.builder.append(key);
		this.builder.append(">");
	}

	@Override
	public void tagFinal(Object key) {
		this.builder.append("</");
		this.builder.append(key);
		this.builder.append(">");
	}

	@Override
	public void parse(Object object) {
		this.chainParse.getElement().execute(object);
	}

	@Override
	public void inicioObjeto() {

	}

	@Override
	public void endObject() {
	}
}