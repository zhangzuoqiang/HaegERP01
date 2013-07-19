package org.haegerp.controller;

import org.haegerp.entity.Article;
import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
/**
 * Diese Klasse besitzt die Geschäftslogik von dem Artikel.
 * 
 * @author Wolf
 *
 */
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleCategoryRepository articleCategoryRepository;
	
	//Category
	private Long idArticleCategory = 0L;
	private Integer enableCategory = 0;
	
	//Fields
	//EAN
	private Long eanMin = 0L;
	private Long eanMax = 0L;
	private Integer enableEan = 0;
	
	//Name
	private String name = "";
	private Integer enableName = 0;
	
	//Price VAT
	private Float priceVat = 0.0F;
	private Integer enablePriceVat = 0;

	//Price Gross
	private Float priceGross = 0.0F;
	private Integer enablePriceGross = 0;

	//Price Supplier
	private Float priceSupplier = 0.0F;
	private Integer enablePriceSupplier = 0;

	//Stock
	private Integer stock = 0;
	private Integer enableStock = 0;
	
	//AllSwitch, FilterSwitch and CategorySwitch
	private Integer enableAll = 1;
	private Integer disableSearchFilters = 1;
	private Integer disableSearchCategory = 1;
	
	private int pageNumber;
	private Page<Article> page;
	
	public Page<Article> getPage() {
		return page;
	}

	public ArticleController() {
		pageNumber = 0;
	}
	
	public boolean getNextPage(int size) {
		if (page.hasNextPage()){
			pageNumber++;
			page = loadArticlesPage(size);
			return true;
		}
		else
			return false;
	}

	public boolean getPreviousPage(int size) {
		if (page.hasPreviousPage()){
			pageNumber--;
			page = loadArticlesPage(size);
			return true;
		}
		else
			return false;
	}
	
	public boolean getFirstPage(int size){
		pageNumber = 0;
		page = loadArticlesPage(size);
		return true;
	}
	
	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param page Nummer der Seite
	 * @param size Grösse der Seite
	 * @return Seite mit den Artikeln
	 */
	public Page<Article> loadArticlesPage(int size){
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = articleRepository.findWithFilters(enableCategory, idArticleCategory,
													disableSearchCategory, disableSearchFilters,
													enableEan, eanMin, eanMax,
													enableName, name,
													enablePriceVat, Double.parseDouble(priceVat.toString()),
													enablePriceGross, Double.parseDouble(priceGross.toString()),
													enablePriceSupplier, Double.parseDouble(priceSupplier.toString()),
													enableStock, stock,
													enableAll, pageRequest);
		return page;
	}
	
	public void setCategory(Long idArticleCategory, int size){
		if (idArticleCategory == -1){
			enableCategory = 0;
			if (enableEan + enableName + enablePriceGross + enablePriceSupplier + enablePriceVat + enableStock == 0)
				enableAll = 1;
			disableSearchCategory = 1;
		} else {
			this.idArticleCategory = idArticleCategory;
			enableCategory = 1;
			enableAll = 0;
			disableSearchCategory = 0;
			pageNumber = 0;
		}
	}
	
	public boolean setSearch(String value, int field, int size){
		enableEan = enableName = enablePriceGross = enablePriceSupplier = enablePriceVat = enableStock = 0;
		if (value.equals("")){
			disableSearchFilters = 1;
			if (enableCategory == 0)
				enableAll = 1;
		} else {
			try {
				switch (field) {
				case 0:
					enableEan = 1;
					eanMin = Long.parseLong(value);
					eanMin = eanMin * Math.round(Math.pow(10, 13 - value.length()));
					value = value + String.valueOf(Math.round(Math.pow(10, 13 - value.length()) -1));
					eanMax = Long.parseLong(value);
					break;
				
				case 1:
					enableName = 1;
					name = value;
					break;
				
				case 2:
					enablePriceVat = 1;
					priceVat = Float.parseFloat(value);
					break;
					
				case 3:
					enablePriceGross = 1;
					priceGross = Float.parseFloat(value);
					break;
					
				case 4:
					enablePriceSupplier = 1;
					priceSupplier = Float.parseFloat(value);
					break;
					
				case 5:
					enableStock = 1;
					stock = Integer.parseInt(value);
					break;
				}
				
				disableSearchFilters = 0;
				enableAll = 0;
				pageNumber = 0;
			} catch(Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		
		return true;
	}

	public void delete(Article article) {
		articleRepository.delete(article.getIdArticle());
	}

	public void saveArticle(Article article) {
		articleRepository.save(article);
	}
}
