package org.haegerp.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.ws.HaegerpWS;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@WebService(endpointInterface = "org.haegerp.ws.HaegerpWS")
/**
 * Implementierung von dem Web-Service
 * 
 * @author Wolf
 *
 */
public class HaegerpWSImpl implements HaegerpWS {

	private ArticleRepository articleRepository;
	private ArticleCategoryRepository articleCategoryRepository;
	
	@WebMethod
	@Transactional
	/**
	 * Ein Artikel wird erhalten
	 * 
	 * @param id ID des Artikel
	 */
	public Article getArticleById(@WebParam(name = "id") long id) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring-data.xml");
		articleRepository = (ArticleRepository) context.getBean("articleRepository");
		articleCategoryRepository = (ArticleCategoryRepository) context.getBean("articleCategoryRepository");
		
		Article article = articleRepository.findOne(id);
		
		ArticleCategory articleCategory = articleCategoryRepository.findOne(article.getArticleCategory().getIdArticleCategory());
		
		article.setArticleCategory(articleCategory);
		
		return article;
	}

}
