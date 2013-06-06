package org.haegerp.entity;

import java.io.Serializable;

public class Article implements Serializable{

	/**
	 * 
	 */
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
	
	public Article() {
	}
	
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
	
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}
	
	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}
	
	public long getEan() {
		return ean;
	}
	
	public void setEan(long ean) {
		this.ean = ean;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getPriceVat() {
		return priceVat;
	}
	
	public void setPriceVat(float priceVat) {
		this.priceVat = priceVat;
	}
	
	public float getPriceGross() {
		return priceGross;
	}
	
	public void setPriceGross(float priceGross) {
		this.priceGross = priceGross;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public float getSizeH() {
		return sizeH;
	}
	
	public void setSizeH(float sizeH) {
		this.sizeH = sizeH;
	}
	
	public float getSizeL() {
		return sizeL;
	}
	
	public void setSizeL(float sizeL) {
		this.sizeL = sizeL;
	}
	
	public float getSizeW() {
		return sizeW;
	}
	
	public void setSizeW(float sizeW) {
		this.sizeW = sizeW;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
