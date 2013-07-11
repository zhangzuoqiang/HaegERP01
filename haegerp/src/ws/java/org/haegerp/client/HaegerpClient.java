package org.haegerp.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.haegerp.entity.Article;
import org.haegerp.ws.HaegerpWS;

/**
 * Diese Klasse testet das Web-Service "HaegerpWS"
 * 
 * @author Wolf
 *
 */
public class HaegerpClient {
	
	public static void main(String[] args) throws Exception {
		 
		URL url = new URL("http://localhost:9999/ws/haegerp?wsdl");
	 
        QName qname = new QName("http://impl.ws.haegerp.org/", "HaegerpWSImplService");
 
        Service service = Service.create(url, qname);
 
        HaegerpWS haegerpWS = service.getPort(HaegerpWS.class);
 
        Article article = haegerpWS.getArticleById(0);
        
        System.out.println(article.getName());
	 
	    }
}
