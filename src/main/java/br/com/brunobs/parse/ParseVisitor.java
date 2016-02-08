package br.com.brunobs.parse;

import javax.ws.rs.core.UriInfo;

public abstract class ParseVisitor {
	protected StringBuffer builder;
	protected ChainParse chainParse;

	protected boolean primeiroElemento;
	private UriInfo uriInfo;
	private String nomeElemento;
	protected String rootElemento;

	public String getNomeElemento() {
		return this.nomeElemento;
	}

	public void setNomeElemento(String nomeElemento) {
		this.nomeElemento = nomeElemento;
	}

	public UriInfo getUriInfo() {
		return this.uriInfo;
	}

	public ParseVisitor(UriInfo uriInfo) {
		this.primeiroElemento = true;
		this.builder = new StringBuffer();
	}

	public StringBuffer getBuilder() {
		return this.builder;
	}

	public boolean isPrimeiroElemento() {
		return this.primeiroElemento;
	}

	public void setPrimeiroElemento(boolean isElementoPai) {
		this.primeiroElemento = isElementoPai;
	}

	public ParseVisitor() {
		this.primeiroElemento = true;
		this.builder = new StringBuffer();

	}

	public abstract String visit(Object target);

	public abstract String visit(String rootElemento, Object target);

	public void add(Object value) {
		this.builder.append(value);
	}

	public abstract void add(Object key, Object value, boolean acao);

	public abstract void add(Object key, Object value);

	public abstract void parse(Object object);

	public abstract void tagInicial(Object key);

	public abstract void tagFinal(Object key);

	public abstract void inicioObjeto();

	public abstract void endObject();

}