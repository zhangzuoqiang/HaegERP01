package org.haegerp.controller;

import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public boolean addNewArticle(ArticleCategory articleCategory, String color,
			String description, Long ean, String name, Float priceGross, Float priceSupplier,
			Float priceVat, Float sizeH, Float sizeL, Float sizeW, Long stock){
		
		Article article = new Article();
		try {
			article.setArticleCategory(articleCategory);
			article.setColor(color);
			article.setDescription(description);
			article.setEan(ean);
			article.setName(name);
			article.setPriceGross(priceGross);
			article.setPriceSupplier(priceSupplier);
			article.setPriceVat(priceVat);
			article.setSizeH(sizeH);
			article.setSizeL(sizeL);
			article.setSizeW(sizeW);
			article.setStock(stock);
			
			articleRepository.performNew(article);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		
		return true;
	}
}
