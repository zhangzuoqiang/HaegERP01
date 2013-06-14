package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ArticleCategory implements Serializable{

	private static final long serialVersionUID = 4064612947215250133L;
	
	//Primary Key (Erforderlich - Automatisch)
	private long idArticleCategory;
	
	//Name von der Artikelkategorie (Erforderlich)
	private String name;
	
	//Artikelkategorie Beschreibung
	private String description;
	
	//(One-To-Many) Artikel, dass angeschlossen werden mit der Artikelkategorie
	private Set<Article> articles = new HashSet<Article>(0);
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Artikelkategorie
	 */
	public ArticleCategory() {
	}

	/**
	 * 
	 * @return idArticleCategory - Primary Key (Erforderlich - Automatisch)
	 */
	public long getIdArticleCategory() {
		return idArticleCategory;
	}

	/**
	 * 
	 * @param idArticleCategory Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdArticleCategory(long idArticleCategory) {
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
		result = prime * result + (int) (idArticleCategory ^ (idArticleCategory >>> 32));
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
