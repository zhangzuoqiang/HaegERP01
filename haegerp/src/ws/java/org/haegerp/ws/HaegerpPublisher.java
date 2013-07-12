package org.haegerp.ws;

import javax.xml.ws.Endpoint;

import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.ws.impl.HaegerpWSImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Das Web-Service wird publiziert
 * 
 * @author Wolf
 *
 */

@Component
public class HaegerpPublisher {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-data.xml");
		
		ArticleRepository articleRepository = (ArticleRepository)context.getBean("articleRepository");
		ArticleCategoryRepository articleCategoryRepository = (ArticleCategoryRepository)context.getBean("articleCategoryRepository");
		
		Endpoint.publish("http://localhost:9999/ws/haegerp", new HaegerpWSImpl(articleRepository, articleCategoryRepository));
		
	}
	
	
}
