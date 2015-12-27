package br.com.brunobs.parse.json.element;

public interface JsonElement {

	public void execute(Object object);

	public void nextElement(JsonElement jsonElement);
}
