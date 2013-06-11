package org.haegerp.entity;

import java.io.Serializable;

/**
 * 
 * @author Wolf
 *
 */
public class Article implements Serializable{

	private static final long serialVersionUID = 5903001912808144518L;
	
	//Primary Key (Erforderlich - Automatisch)
	private int idArticle;
	
	//(Many-To-One) Kategorie, dass Artikel gehört (Erforderlich)
	private ArticleCategory articleCategory;
	
	//Artikels EAN (Erforderlich)
	private long ean;
	
	//Artikels Name (Erforderlich)
	private String name;
	
	//Artikels MwSt Preis
	private float priceVat;
	
	//Artikels Bruttopreis
	private float priceGross;
	
	//Artikels Lager
	private int stock;
	
	//Artikels Farbe
	private String color;
	
	//Artikels Höhegröße
	private float sizeH;
	
	//Artikels Längegröße
	private float sizeL;
	
	//Artikels Breitegröße
	private float sizeW;
	
	//Artikels Beschreibung
	private String description;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Artikel
	 */
	public Article() {
	}
	
	public int getIdArticle() {
		return idArticle;
	}
	
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	
	/**
	 * 
	 * @return articleCategory - Kategorie, dass Artikel gehört
	 */
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}
	
	/**
	 * 
	 * @param articleCategory Kategorie, dass Artikel gehört (Erforderlich)
	 */
	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}
	
	/**
	 * 
	 * @return ean - Artikels EAN
	 */
	public long getEan() {
		return ean;
	}
	
	/**
	 * 
	 * @param ean Artikels EAN (Erforderlich)
	 */
	public void setEan(long ean) {
		this.ean = ean;
	}
	
	/**
	 * 
	 * @return name - Artikels Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name Artikels Name (Erforderlich)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return priceVat - Artikels MwSt Preis
	 */
	public float getPriceVat() {
		return priceVat;
	}
	
	/**
	 * 
	 * @param priceVat Artikels MwSt Preis (Nicht Erforderlich)
	 */
	public void setPriceVat(float priceVat) {
		this.priceVat = priceVat;
	}
	
	/**
	 * 
	 * @return priceGross - Artikels Nettopreis
	 */
	public float getPriceGross() {
		return priceGross;
	}
	
	/**
	 * 
	 * @param priceGross Artikels Nettopreis (Nicht Erforderlich)
	 */
	public void setPriceGross(float priceGross) {
		this.priceGross = priceGross;
	}
	
	/**
	 * 
	 * @return stock - Artikels Lager
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * 
	 * @param stock Artikels Lager (Nicht Erforderlich)
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	/**
	 * 
	 * @return color - Artikels Farbe
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 
	 * @param color Artikels Farbe (Nicht Erforderlich)
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * 
	 * @return sizeH - Artikels Höhegröße
	 */
	public float getSizeH() {
		return sizeH;
	}
	
	/**
	 * 
	 * @param sizeH Artikels Höhegröße (Nicht Erforderlich)
	 */
	public void setSizeH(float sizeH) {
		this.sizeH = sizeH;
	}
	
	/**
	 * 
	 * @return sizeL - Artikels Längegröße
	 */
	public float getSizeL() {
		return sizeL;
	}
	
	/**
	 * 
	 * @param sizeL Artikels Längegröße (Nicht Erforderlich)
	 */
	public void setSizeL(float sizeL) {
		this.sizeL = sizeL;
	}
	
	/**
	 * 
	 * @return sizeW - Artikels Breitegröße
	 */
	public float getSizeW() {
		return sizeW;
	}
	/**
	 * 
	 * @param sizeW Artikels Breitegröße (Nicht Erforderlich)
	 */
	public void setSizeW(float sizeW) {
		this.sizeW = sizeW;
	}
	
	/**
	 * 
	 * @return description - Artikels Beschreibung
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @param description Artikels Beschreibung (Nicht Erforderlich)
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idArticle;
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
		Article other = (Article) obj;
		if (idArticle != other.idArticle)
			return false;
		return true;
	}
}
