package org.haegerp.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.ws.HaegerpWS;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementierung von dem Web-Service
 * 
 * @author Wolf
 *
 */
@WebService(endpointInterface = "org.haegerp.ws.HaegerpWS")
public class HaegerpWSImpl implements HaegerpWS {

	private ArticleRepository articleRepository;
	private ArticleCategoryRepository articleCategoryRepository;
	
	public HaegerpWSImpl() {
	}
	
	public HaegerpWSImpl(ArticleRepository articleRepository, ArticleCategoryRepository articleCategoryRepository) {
		this.articleRepository = articleRepository;
		this.articleCategoryRepository = articleCategoryRepository;
	}
	
	@WebMethod
	@Transactional
	/**
	 * Ein Artikel wird erhalten
	 * 
	 * @param id ID des Artikel
	 */
	public Article getArticleById(@WebParam(name = "id") long id) {
		Article article = articleRepository.findOne(id);
		
		ArticleCategory articleCategory = articleCategoryRepository.findOne(article.getArticleCategory().getIdArticleCategory());
		
		article.setArticleCategory(articleCategory);
		
		return article;
	}

}
