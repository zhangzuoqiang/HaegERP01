package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import org.haegerp.controller.ArticleController;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
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
	private EmployeeRepository employeeRepository;
	//Category
	private Long idArticleCategory = 0L;
	private Integer enableCategory = 0;
	
	//Search
	private String search = "";
	private Integer enableSearch = 0;
	
	//AllSwitch, FilterSwitch and CategorySwitch
	private Integer enableAll = 1;
	private Integer disableSearchFilters = 1;
	private Integer disableSearchCategory = 1;
	
	private int pageNumber;
	private Page<Article> page;
	
        @Override
	public Page<Article> getPage() {
		return page;
	}

	public ArticleControllerImpl() {
		pageNumber = 0;
	}
	
        @Override
	public boolean getNextPage(int size) {
		if (page.hasNextPage()){
			pageNumber++;
			page = loadPage(size);
			return true;
		}
		else
			return false;
	}

        @Override
	public boolean getPreviousPage(int size) {
		if (page.hasPreviousPage()){
			pageNumber--;
			page = loadPage(size);
			return true;
		}
		else
			return false;
	}
	
        @Override
	public boolean getFirstPage(int size){
		pageNumber = 0;
		page = loadPage(size);
		return true;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public Object[][] loadTableRows(int size) {
            Page<Article> articlePage = this.loadPage(size);
            Object[][] rows = new Object[articlePage.getContent().size()][10];
            for (int i = 0; i < articlePage.getContent().size(); i++) {
        	Article article = articlePage.getContent().get(i);
        	
                rows[i][0] = article.getIdArticle();
        	rows[i][1] = article.getEan();
        	rows[i][2] = article.getName();
        	rows[i][3] = article.getArticleCategory().getName();
                rows[i][4] = (article.getPriceVat() * 100) + " %";
                rows[i][5] = article.getPriceGross() + " €";
                rows[i][6] = article.getPriceSupplier() + " €";
                rows[i][7] = article.getStock();
                rows[i][8] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(article.getLastModifiedDate());
                rows[i][9] = employeeRepository.findOne(article.getIdEmployeeModify()).getName();
            }
        
            return rows;
	}
	
        @Override
	public void setCategory(Long idArticleCategory, int size){
		if (idArticleCategory == -1){
			enableCategory = 0;
			if (enableSearch == 0)
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
	
        @Override
	public void setSearch(String value, int size){
		enableSearch = 0;
		if (value.equals("")){
                    disableSearchFilters = 1;
                    if (enableCategory == 0)
                        enableAll = 1;
		} else {
                    try {
                        enableSearch = 1;
                        search = value;
                        disableSearchFilters = 0;
                        enableAll = 0;
                        pageNumber = 0;
                    } catch(Exception ex) {
                        ex.printStackTrace(System.err);
                    }
		}
	}

        @Override
	public void delete(Article article) {
		articleRepository.delete(article.getIdArticle());
	}

	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public Article save(Article article) {
		Article savedArticle = articleRepository.save(article);
		return savedArticle;
	}

	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public Page<Article> loadPage(int size) {
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = articleRepository.findWithFilters(
                        enableCategory, idArticleCategory,
                        disableSearchCategory, disableSearchFilters,
                        enableSearch, search,
                        enableAll, pageRequest);
		return page;
	}

        @Override
	public void deleteAllArticleFromCategory(ArticleCategory articleCategory) {
		articleRepository.deleteInBatch(articleCategory.getArticles());
		articleCategory.setArticles(new HashSet<Article>(0));
	}

	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public Object[][] loadAllTableRows() {
		List<Article> list = this.loadAllArticles();
        Object[][] rows = new Object[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);

            rows[i][0] = article.getIdArticle();
            rows[i][1] = article.getEan();
            rows[i][2] = article.getName();
            rows[i][3] = article.getArticleCategory().getName();
        }
        
        return rows;
	}

	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public List<Article> loadAllArticles() {
		return articleRepository.findAll();
	}

	@Transactional(propagation=Propagation.REQUIRED)
        @Override
	public Article getArticleById(long id) {
		return articleRepository.findOne(id);
	}
}
