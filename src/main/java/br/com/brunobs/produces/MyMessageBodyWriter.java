package br.com.brunobs.produces;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import br.com.brunobs.parse.csv.CSVParse;
import br.com.brunobs.parse.json.JSONSerializer;

import com.thoughtworks.xstream.XStream;

@Provider
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
public class MyMessageBodyWriter implements MessageBodyWriter<Object> {

	private final UriInfo uriInfo;

	public MyMessageBodyWriter(@Context UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}

	public long getSize(Object object, Class classe, Type type, Annotation[] annotation, MediaType mediaType) {
		return -1;
	}

	public boolean isWriteable(Class classe, Type type, Annotation[] annotation, MediaType mediaType) {
		return true;
	}

	public void writeTo(Object obj, Class classe, Type type, Annotation[] annotation, MediaType mediaType, MultivaluedMap multivaluedMap,
			OutputStream entityStream) throws IOException, WebApplicationException {
		String retorno = "";
		DataOutputStream dos = new DataOutputStream(entityStream);

		if (MediaType.APPLICATION_JSON.equals(mediaType.toString())) {
			JSONSerializer serial = new JSONSerializer(this.uriInfo);
			String nomeDoElemento = type.getTypeName();
			for (Annotation anotacao : annotation) {
				if (anotacao instanceof NomeElemento) {
					nomeDoElemento = ((NomeElemento) anotacao).nome();
				}
			}

			retorno = serial.serialize(nomeDoElemento, obj).trim();
		}
		if (MediaType.APPLICATION_XML.equals(mediaType.toString())) {
			retorno = new XStream().toXML(obj).trim();
		}
		if (MediaType.TEXT_PLAIN.equals(mediaType.toString())) {
			retorno = new CSVParse(this.uriInfo).toCSV(obj);
		}

		dos.writeBytes(retorno);
	}
}