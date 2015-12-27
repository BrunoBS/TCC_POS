package br.com.brunobs.parse.csv;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.persistence.Id;
import javax.ws.rs.core.UriInfo;

public class CSVParse {

	private final UriInfo uriInfo;

	public CSVParse(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}

	public String toCSV(Object obj) {
		String retorno = "";
		Class<?> clazz = obj.getClass();
		if (obj instanceof Collection<?>) {
			Collection<?> c = (Collection<?>) obj;
			if (c != null && !c.isEmpty()) {
				for (Object object : c) {
					retorno += getCabecalhado(object.getClass());
					break;
				}
			} else {
				return "";
			}
		} else {
			retorno += getCabecalhado(clazz);
		}

		if (obj instanceof Collection<?>) {
			Collection<?> c = (Collection<?>) obj;
			for (Object object : c) {
				retorno += getCorpo(object, object.getClass());
			}
		} else {
			retorno += getCorpo(obj, clazz);
		}
		return retorno;
	}

	private String getCorpo(Object obj, Class clazz) {
		String retorno = "";
		for (Field field : clazz.getDeclaredFields()) {
			try {
				field.setAccessible(true);
				if (Collection.class.isAssignableFrom(field.getType())) {

					retorno += pathRecurso(obj) + "/" + field.getName() + ",";
				} else {
					retorno += field.get(obj) + ",";
				}
			} catch (Exception e) {
				retorno += ",";
			}
		}
		retorno = retorno.substring(0, retorno.length() - 1);
		retorno += "\n";
		return retorno;
	}

	private Object pathRecurso(Object obj) throws IllegalArgumentException, IllegalAccessException {
		for (Field field : obj.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Id.class)) {
				field.setAccessible(true);
				if (this.uriInfo.getRequestUri().getPath().endsWith("/")) {
					return this.uriInfo.getRequestUri().getPath() + field.get(obj);
				}
			}
		}
		return this.uriInfo.getRequestUri().getPath();

	}

	private String getCabecalhado(Class clazz) {
		String retorno = "";
		for (Field filed : clazz.getDeclaredFields()) {
			retorno += filed.getName() + ",";
		}
		retorno = retorno.substring(0, retorno.length() - 1);
		retorno += "\n";
		return retorno;
	}
}
