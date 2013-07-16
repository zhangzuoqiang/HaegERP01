package org.haegerp.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
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
	
	private static String REGEX_ONLY_NUMBERS="[0-9]*";
    private static String REGEX_FLOAT_VALUES="[0-9]*|([0-9]*.[0-9]{2})|([0-9]*.[0-9]{1})";
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleCategoryRepository articleCategoryRepository;
	
	private int pageNumber;
	private Page<Article> page;
	
	public Page<Article> getPage() {
		return page;
	}

	public ArticleController() {
		pageNumber = 0;
	}
	
	public void loadPage(int size){
		page = loadArticlesPage(size);
	}
	
	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param page Nummer der Seite
	 * @param size Grösse der Seite
	 * @return Seite mit den Artikeln
	 */
	private Page<Article> loadArticlesPage(int size){
		PageRequest pageRequest = new PageRequest(pageNumber, size);
		this.page = articleRepository.findAll(pageRequest);
		return page;
	}
	
	/**
	 * Das Argument wird analisiert und dann zu Float konvertiert
	 * 
	 * @param arg String mit einem Long Wert
	 * @return Long Wert
	 */
	public Long verifyDataLong(String arg){
		
		Pattern p;
		Matcher m;
		
		p = Pattern.compile(REGEX_ONLY_NUMBERS);
        m = p.matcher(arg);

        if (!m.matches() || arg.length() == 0)
            return null;
        else{
            try {
                return Long.parseLong(arg);
            } catch (Exception e) {
                return null;
            }
        }
	}
	
	/**
	 * Das Argument wird analisiert und dann zu Float konvertiert
	 * 
	 * @param arg String mit einem Float Wert
	 * @return Float Wert
	 */
	public Float verifyDataFloat(String arg){
		
		Pattern p = Pattern.compile(REGEX_FLOAT_VALUES);
		Matcher m = p.matcher(arg);

        if (!m.matches() || arg.length() == 0)
            return null;
        else{
            try {
                return Float.parseFloat(arg);
            } catch (Exception e) {
                return null;
            }
        }
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
	
	public boolean getArticlesByCategory(String categoryName, int size){
		//TODO: Finish function and Determine DI or Interface.
		pageNumber = 0;
		ArticleCategory articleCategory = articleCategoryRepository.findByName(categoryName);
		
		
		
		return true;
	}
}
