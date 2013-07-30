package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.haegerp.controller.ArticleController;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public final class ArticleControllerImpl implements ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleCategoryRepository articleCategoryRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	//Category
	private Long idArticleCategory = 0L;
	private Integer enableCategory = 0;
	
	//Fields
	//EAN
	private String ean = "";
	private Integer enableEan = 0;
	
	//Name
	private String name = "";
	private Integer enableName = 0;
	
	//Price VAT
	private String priceVat = "";
	private Integer enablePriceVat = 0;

	//Price Gross
	private String priceGross = "";
	private Integer enablePriceGross = 0;

	//Price Supplier
	private String priceSupplier = "";
	private Integer enablePriceSupplier = 0;

	//Stock
	private String stock = "";
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

	public ArticleControllerImpl() {
		pageNumber = 0;
	}
	
	public boolean getNextPage(int size) {
		if (page.hasNextPage()){
			pageNumber++;
			page = loadPage(size);
			return true;
		}
		else
			return false;
	}

	public boolean getPreviousPage(int size) {
		if (page.hasPreviousPage()){
			pageNumber--;
			page = loadPage(size);
			return true;
		}
		else
			return false;
	}
	
	public boolean getFirstPage(int size){
		pageNumber = 0;
		page = loadPage(size);
		return true;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int size) {
		Page<Article> page = this.loadPage(size);
        Object[][] rows = new Object[page.getContent().size()][9];
        for (int i = 0; i < page.getContent().size(); i++) {
        	Article article = page.getContent().get(i);
        	
        	rows[i][0] = article.getEan();
        	rows[i][1] = article.getName();
        	rows[i][2] = article.getArticleCategory().getName();
			rows[i][3] = (article.getPriceVat() * 100) + " %";
			rows[i][4] = article.getPriceGross() + " €";
			rows[i][5] = article.getPriceSupplier() + " €";
			rows[i][6] = article.getStock();
			rows[i][7] = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(article.getLastModifiedDate());
			rows[i][8] = employeeRepository.findOne(article.getIdEmployeeModify()).getName();
		}
        
        return rows;
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
	
	public void setSearch(String value, int field, int size){
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
					ean = value;
					break;
				
				case 1:
					enableName = 1;
					name = value;
					break;
				
				case 2:
					enablePriceVat = 1;
					value = value.replace('.', ',');
					priceVat = value;
					break;
					
				case 3:
					enablePriceGross = 1;
					value = value.replace('.', ',');
					priceGross = value;
					break;
					
				case 4:
					enablePriceSupplier = 1;
					value = value.replace('.', ',');
					priceSupplier = value;
					break;
					
				case 5:
					enableStock = 1;
					stock = value;
					break;
				}
				
				disableSearchFilters = 0;
				enableAll = 0;
				pageNumber = 0;
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void delete(Article article) {
		articleRepository.delete(article.getIdArticle());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Article save(Article article) {
		Article savedArticle = articleRepository.save(article);
		return savedArticle;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Page<Article> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = articleRepository.findWithFilters(enableCategory, idArticleCategory,
													disableSearchCategory, disableSearchFilters,
													enableEan, ean,
													enableName, name,
													enablePriceVat, priceVat,
													enablePriceGross, priceGross,
													enablePriceSupplier, priceSupplier,
													enableStock, stock,
													enableAll, pageRequest);
		return page;
	}

	public void deleteAllArticleFromCategory(ArticleCategory articleCategory) {
		articleRepository.deleteInBatch(articleCategory.getArticles());
		articleCategory.setArticles(new HashSet<Article>(0));
	}
}
