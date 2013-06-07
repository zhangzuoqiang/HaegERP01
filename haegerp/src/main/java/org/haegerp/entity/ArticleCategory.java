package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ArticleCategory implements Serializable{

	private static final long serialVersionUID = 4064612947215250133L;
	
	private int idArticleCategory;
	private String name;
	private String description;
	private Set<Article> articles = new HashSet<Article>(0);
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Artikelkategorie
	 */
	public ArticleCategory() {
	}
	
	/**
	 * 
	 * @param idArticleCategory Primary Key (Erforderlich - Automatisch)
	 * @param name Name von der Artikelkategorie (Erforderlich)
	 * @param description Artikelkategorie Beschreibung
	 * @param articles Artikel, dass angeschlossen werden mit der Artikelkategorie
	 */
	public ArticleCategory(int idArticleCategory, String name,
			String description, Set<Article> articles) {
		this.idArticleCategory = idArticleCategory;
		this.name = name;
		this.description = description;
		this.articles = articles;
	}

	public int getIdArticleCategory() {
		return idArticleCategory;
	}

	public void setIdArticleCategory(int idArticleCategory) {
		this.idArticleCategory = idArticleCategory;
	}

	/**
	 * 
	 * @return name - Artikelkategorie Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name Artikelkategorie Name (Erforderlich)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return description - Artikelkategorie Beschreibung
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description Artikelkategorie Beschreibung
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return articles - Artikel, dass angeschlossen werden mit der Artikelkategorie
	 */
	public Set<Article> getArticles() {
		return articles;
	}

	/**
	 * 
	 * @param articles Artikel, dass angeschlossen werden mit der Artikelkategorie
	 */
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idArticleCategory;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleCategory other = (ArticleCategory) obj;
		if (idArticleCategory != other.idArticleCategory)
			return false;
		return true;
	}
}
