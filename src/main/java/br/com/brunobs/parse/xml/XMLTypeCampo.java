package br.com.brunobs.parse.xml;

import javax.ws.rs.core.UriInfo;

import br.com.brunobs.parse.ChainParse;
import br.com.brunobs.parse.ParseElement;
import br.com.brunobs.parse.ParseVisitor;
import br.com.brunobs.parse.json.element.JsonNull;
import br.com.brunobs.parse.xml.element.XMLArray;
import br.com.brunobs.parse.xml.element.XMLBean;
import br.com.brunobs.parse.xml.element.XMLBoolean;
import br.com.brunobs.parse.xml.element.XMLCharacter;
import br.com.brunobs.parse.xml.element.XMLCollection;
import br.com.brunobs.parse.xml.element.XMLDate;
import br.com.brunobs.parse.xml.element.XMLMap;
import br.com.brunobs.parse.xml.element.XMLNameClass;
import br.com.brunobs.parse.xml.element.XMLNumber;
import br.com.brunobs.parse.xml.element.XMLString;

public class XMLTypeCampo implements ChainParse {

	private ParseElement xmlNull;
	private ParseElement xmlNameClass;
	private ParseElement xmlBoolean;
	private ParseElement xmlString;
	private ParseElement xmlCharacter;
	private ParseElement xmlMap;
	private ParseElement xmlNumber;
	private ParseElement xmlArray;
	private ParseElement xmlDate;
	private ParseElement xmlCollection;
	private ParseElement xmlBean;

	public XMLTypeCampo(ParseVisitor xmlVisitor) {
		this(xmlVisitor, null);
	}

	public XMLTypeCampo(ParseVisitor xmlVisitor, UriInfo info) {
		this.xmlNull = new JsonNull(xmlVisitor);
		this.xmlNameClass = new XMLNameClass(xmlVisitor);
		this.xmlBoolean = new XMLBoolean(xmlVisitor);
		this.xmlString = new XMLString(xmlVisitor);
		this.xmlCharacter = new XMLCharacter(xmlVisitor);
		this.xmlMap = new XMLMap(xmlVisitor);
		this.xmlNumber = new XMLNumber(xmlVisitor);
		this.xmlArray = new XMLArray(xmlVisitor, info);
		this.xmlDate = new XMLDate(xmlVisitor);
		this.xmlCollection = new XMLCollection(xmlVisitor, info);
		this.xmlBean = new XMLBean(xmlVisitor);

		this.xmlNull.nextElement(this.xmlNameClass);
		this.xmlNameClass.nextElement(this.xmlBoolean);
		this.xmlBoolean.nextElement(this.xmlString);
		this.xmlString.nextElement(this.xmlCharacter);
		this.xmlCharacter.nextElement(this.xmlMap);
		this.xmlMap.nextElement(this.xmlNumber);
		this.xmlNumber.nextElement(this.xmlArray);
		this.xmlArray.nextElement(this.xmlDate);
		this.xmlDate.nextElement(this.xmlCollection);
		this.xmlCollection.nextElement(this.xmlBean);
		this.xmlBean.nextElement(null);
	}

	public ParseElement getElement() {
		return this.xmlNull;
	}
}
