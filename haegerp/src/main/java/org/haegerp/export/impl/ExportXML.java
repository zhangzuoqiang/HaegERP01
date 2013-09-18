package org.haegerp.export.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.haegerp.entity.Article;
import org.haegerp.export.Export;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Exportation zur XML-Datei
 * 
 * @author Fabio Codinha
 *
 */
@Service
public class ExportXML implements Export {

	public boolean export(List<Article> elements, String path) throws Exception{
		if (elements.isEmpty())
			return false;
		
		ExportList<Object> exportList = new ExportList<Object>();
		
		try {
			File file = new File(path);
			file.mkdirs();
			file = new File(path + "\\XML-Export_" + String.valueOf((new Date()).getTime()) + ".xml");
			
			for (Object obj : elements) {
				exportList.getValues().add(obj);
			}
			
			JAXBContext jaxbContext = JAXBContext.newInstance(ExportList.class, elements.get(0).getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			marshaller.marshal(exportList, file);
			
			return true;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * Ein neue Element für das XML-Dokument wird erstellt.
	 * 
	 * @param doc Welches XML-Dokument
	 * @param nodeName Name des Node
	 * @param nodeValue Wert des Node
	 * @return Ein Neue Element
	 */
	public Element newNode(Document doc, String nodeName, String nodeValue)
	{
		Element element = doc.createElement(nodeName);
		element.appendChild(doc.createTextNode(nodeValue));
		return element;
	}
}
