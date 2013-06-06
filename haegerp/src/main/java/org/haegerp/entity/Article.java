package org.haegerp.entity;

import java.io.Serializable;

public class Article implements Serializable{

	private static final long serialVersionUID = 5903001912808144518L;
	
	private int idArticle;
	private ArticleCategory articleCategory;
	private long ean;
	private String name;
	private float priceVat;
	private float priceGross;
	private int stock;
	private String color;
	private float sizeH;
	private float sizeL;
	private float sizeW;
	private String description;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Artikel
	 */
	public Article() {
	}
	
	/**
	 * Konstruktor, dass benutzt wird vom Hibernate
	 * 
	 * @param idArticle Primary Key (Erforderlich - Automatisch)
	 * @param articleCategory Kategorie, dass Artikel gehört (Erforderlich)
	 * @param ean Artikels EAN (Erforderlich)
	 * @param name Artikels Name (Erforderlich)
	 * @param priceVat Artikels MwSt Preis
	 * @param priceGross Artikels Nettopreis
	 * @param stock Artikels Lager
	 * @param color Artikels Farbe
	 * @param sizeH Artikels Höhegröße
	 * @param sizeL Artikels Längegröße
	 * @param sizeW Artikels Breitegröße
	 * @param description Artikels Beschreibung
	 */
	public Article(int idArticle, ArticleCategory articleCategory, long ean,
			String name, float priceVat, float priceGross, int stock,
			String color, float sizeH, float sizeL, float sizeW,
			String description) {
		this.idArticle = idArticle;
		this.articleCategory = articleCategory;
		this.ean = ean;
		this.name = name;
		this.priceVat = priceVat;
		this.priceGross = priceGross;
		this.stock = stock;
		this.color = color;
		this.sizeH = sizeH;
		this.sizeL = sizeL;
		this.sizeW = sizeW;
		this.description = description;
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
	
	
}
