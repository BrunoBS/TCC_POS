package br.com.brunobs.parse;


public interface Serializador {
	public String serialize(String rootName, Object target);

	public String serialize(Object target);

	public String prettyPrint(Object target);

	public String prettyPrint(String rootName, Object target);

}
