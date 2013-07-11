package org.haegerp.entity;

import java.io.Serializable;

/**
 * Die ID von der Artikelhistorie ist Kompost, und für jede Kompost ID muss man eine Klasse erstellen.
 * 
 * @author Wolf
 *
 */
public class ArticleHistoryPK implements Serializable {
	
	private static final long serialVersionUID = 7009602054952700253L;
	
	//Nummer der Version
	private long idArticleHistory;
	//Welcher Artikel gehört diese Version
	private Article article;
	
	/**
	 * Default Konstruktor
	 */
	public ArticleHistoryPK() {
	}
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue ArticleHistoryPK
	 * 
	 * @param idArticleHistory Nummer der Version
	 * @param article Welcher Artikel gehört diese Version
	 */
	public ArticleHistoryPK(long idArticleHistory, Article article) {
		super();
		this.idArticleHistory = idArticleHistory;
		this.article = article;
	}

	/**
	 * 
	 * @return idArticleHistory - Nummer der Version
	 */
	public long getIdArticleHistory() {
		return idArticleHistory;
	}

	/**
	 * 
	 * @param idArticleHistory Nummer der Version
	 */
	public void setIdArticleHistory(long idArticleHistory) {
		this.idArticleHistory = idArticleHistory;
	}

	/**
	 * 
	 * @return article - Welcher Artikel gehört diese Version
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * 
	 * @param article Welcher Artikel gehört diese Version
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result
				+ (int) (idArticleHistory ^ (idArticleHistory >>> 32));
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
		ArticleHistoryPK other = (ArticleHistoryPK) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (idArticleHistory != other.idArticleHistory)
			return false;
		return true;
	}
	
	
}
