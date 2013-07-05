package org.haegerp.export;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.haegerp.entity.Article;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Exportation zur XML-Datei
 * 
 * @author Wolf
 *
 */
public class ExportXML implements Export {

	public boolean export(List<Article> articles) throws Exception{
		if (articles.isEmpty())
			return false;
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			//Root
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("haegerERP");
			doc.appendChild(rootElement);
	 
			for (Article article : articles) {
			
				//Article Nodes
				Element articleNode = doc.createElement("article");
				rootElement.appendChild(articleNode);
		 
				//Set IdArticle
				articleNode.setAttribute("id", String.valueOf(article.getIdArticle()));
				
				//Elements
				articleNode.appendChild(newNode(doc, "idArticleCategory", String.valueOf(article.getArticleCategory().getIdArticleCategory())));
				articleNode.appendChild(newNode(doc, "ean", String.valueOf(article.getEan())));
				articleNode.appendChild(newNode(doc, "name", article.getName()));
				articleNode.appendChild(newNode(doc, "priceVat", String.valueOf(article.getPriceVat())));
				articleNode.appendChild(newNode(doc, "priceGross", String.valueOf(article.getPriceGross())));
				articleNode.appendChild(newNode(doc, "priceSupplier", String.valueOf(article.getPriceSupplier())));
				articleNode.appendChild(newNode(doc, "stock", String.valueOf(article.getStock())));
				if (article.getColor() != null)
					articleNode.appendChild(newNode(doc, "color", article.getColor()));
				articleNode.appendChild(newNode(doc, "sizeH", String.valueOf(article.getSizeH())));
				articleNode.appendChild(newNode(doc, "sizeL", String.valueOf(article.getSizeL())));
				articleNode.appendChild(newNode(doc, "sizeW", String.valueOf(article.getSizeW())));
				if (article.getDescription() != null)
					articleNode.appendChild(newNode(doc, "description", article.getDescription()));
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			File file = new File("Exports");
			file.mkdirs();
			
			file = new File("Exports\\XML-Export " + String.valueOf((new Date()).getTime()) + ".xml");
			StreamResult result = new StreamResult(file);
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			transformer.transform(source, result);
			
			return true;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public Element newNode(Document doc, String nodeName, String nodeValue)
	{
		Element element = doc.createElement(nodeName);
		element.appendChild(doc.createTextNode(nodeValue));
		return element;
	}
}
