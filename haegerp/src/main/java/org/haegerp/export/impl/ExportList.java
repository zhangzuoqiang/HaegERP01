package org.haegerp.export.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Das Ziel von diese Klasse ist:
 * Für jedes Dokument kann man mehr als 1 Artikel exportiert werden.
 * 
 * @author Fabio Codinha
 *
 * @param <T>
 */
@XmlRootElement
public class ExportList<T> {
	
	private List<T> values = new ArrayList<T>();
	
	@XmlAnyElement(lax=true)
	public List<T> getValues(){
		return values;
	}
}
