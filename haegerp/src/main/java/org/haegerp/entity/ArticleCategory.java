package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ArticleCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4064612947215250133L;
	
	private int idArticleCategory;
	private String name;
	private Set<Article> articles = new HashSet<Article>(0);
	
	public ArticleCategory() {
	}
	
	public ArticleCategory(String name) {
		this.name = name;
	}

	public ArticleCategory(int idArticleCategory, String name, Set<Article> articles) {
		this.idArticleCategory = idArticleCategory;
		this.name = name;
		this.articles = articles;
	}

	public int getIdArticleCategory() {
		return idArticleCategory;
	}

	public void setIdArticleCategory(int idArticleCategory) {
		this.idArticleCategory = idArticleCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Article> getArticle() {
		return articles;
	}

	public void setArticle(Set<Article> articles) {
		this.articles = articles;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
