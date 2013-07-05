package org.haegerp.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class ArticleController {
	
	private static String REGEX_ONLY_NUMBERS="[0-9]*";
    private static String REGEX_FLOAT_VALUES="[0-9]*|([0-9]*.[0-9]{2})|([0-9]*.[0-9]{1})";
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public ArticleController() {
		Page<Article> articlesPage = getPageArticles(0);
	}
	
	public Page<Article> getPageArticles(int page){
		PageRequest pageRequest = new PageRequest(page*10 + 1, page*10 + 10);
		return articleRepository.findAll(pageRequest);
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
	
}
