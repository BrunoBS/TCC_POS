package br.com.brunobs.parse.json;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ChainParse;
import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;
import br.com.brunobs.parse.json.element.JsonArray;
import br.com.brunobs.parse.json.element.JsonBean;
import br.com.brunobs.parse.json.element.JsonBoolean;
import br.com.brunobs.parse.json.element.JsonCharacter;
import br.com.brunobs.parse.json.element.JsonCollection;
import br.com.brunobs.parse.json.element.JsonDate;
import br.com.brunobs.parse.json.element.JsonMap;
import br.com.brunobs.parse.json.element.JsonNameClass;
import br.com.brunobs.parse.json.element.JsonNull;
import br.com.brunobs.parse.json.element.JsonNumber;
import br.com.brunobs.parse.json.element.JsonString;

public class JSONTypeCampo implements ChainParse {

	private ParseElement jsonNull;
	private ParseElement JsoNameClass;
	private ParseElement jsonBoolean;
	private ParseElement jsonString;
	private ParseElement jsonCharacter;
	private ParseElement jsonMap;
	private ParseElement jsonNumber;
	private ParseElement jsonArray;
	private ParseElement jsonDate;
	private ParseElement jsonCollection;
	private ParseElement jsonBean;

	public JSONTypeCampo(ParseVisitor jsonVisitor) {
		this(jsonVisitor, null);
	}

	public JSONTypeCampo(ParseVisitor jsonVisitor, UriInfo info) {
		this.jsonNull = new JsonNull(jsonVisitor);
		this.JsoNameClass = new JsonNameClass(jsonVisitor);
		this.jsonBoolean = new JsonBoolean(jsonVisitor);
		this.jsonString = new JsonString(jsonVisitor);
		this.jsonCharacter = new JsonCharacter(jsonVisitor);
		this.jsonMap = new JsonMap(jsonVisitor);
		this.jsonNumber = new JsonNumber(jsonVisitor);
		this.jsonArray = new JsonArray(jsonVisitor, info);
		this.jsonDate = new JsonDate(jsonVisitor);
		this.jsonCollection = new JsonCollection(jsonVisitor, info);
		this.jsonBean = new JsonBean(jsonVisitor);

		this.jsonNull.nextElement(this.JsoNameClass);
		this.JsoNameClass.nextElement(this.jsonBoolean);
		this.jsonBoolean.nextElement(this.jsonString);
		this.jsonString.nextElement(this.jsonCharacter);
		this.jsonCharacter.nextElement(this.jsonMap);
		this.jsonMap.nextElement(this.jsonNumber);
		this.jsonNumber.nextElement(this.jsonArray);
		this.jsonArray.nextElement(this.jsonDate);
		this.jsonDate.nextElement(this.jsonCollection);
		this.jsonCollection.nextElement(this.jsonBean);
		this.jsonBean.nextElement(null);
	}

	public ParseElement getElement() {
		return this.jsonNull;
	}
}
