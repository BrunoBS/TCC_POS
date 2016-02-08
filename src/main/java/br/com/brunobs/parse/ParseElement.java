package br.com.brunobs.parse;

public interface ParseElement {

	public void execute(Object object);

	public void nextElement(ParseElement jsonElement);
}
