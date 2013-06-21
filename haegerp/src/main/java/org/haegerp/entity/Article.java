package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.haegerp.exception.LengthOverflowException;

/**
 * Ein Artikel ist der Grund vom System.<br/>
 * Man kann ihn in einer Lieferantbestellung kaufen oder in einer Kundenbestellung verkaufen.<br/>
 * Ein Artikel muss zu einer Artikelkategorie gehören.<br/>
 * 
 * @author Wolf
 *
 */
public class Article implements Serializable{

	private static final long serialVersionUID = 5903001912808144518L;
	
	//Primary Key (Erforderlich - Automatisch)
	private long idArticle;
	
	//(Many-To-One) Kategorie, dass Artikel gehört (Erforderlich)
	private ArticleCategory articleCategory;
	
	//Artikels EAN (Erforderlich)
	private long ean;
	
	//Artikels Name (Erforderlich)
	private String name;
	
	//Artikels MwSt Preis (Erforderlich)
	private float priceVat;
	
	//Artikels Bruttopreis (Erforderlich)
	private float priceGross;
	
	//Artikels Liferantpreis (Erforderlich)
	private float priceSupplier;
	
	//Artikels Lager
	private long stock;
	
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
	
	//Welche Bestellung gehört den Artikel
	private Set<SupplierOrderDetail> supplierOrderDetail = new HashSet<SupplierOrderDetail>(0);
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Artikel
	 */
	public Article() {
	}
	
	/**
	 * 
	 * @return idArticle - Primary Key (Erforderlich - Automatisch)
	 */
	public long getIdArticle() {
		return idArticle;
	}
	
	/**
	 * 
	 * @param idArticle Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdArticle(long idArticle) {
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
	 * @throws LengthOverflowException 
	 */
	public void setEan(long ean) throws LengthOverflowException {
		if (ean > Long.MAX_VALUE || ean < 0L)
			throw new LengthOverflowException("EAN");
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
	 * @throws LengthOverflowException 
	 */
	public void setName(String name) throws LengthOverflowException {
		if (name.length() > 80)
			throw new LengthOverflowException("Name");
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
	 * @param priceVat Artikels MwSt Preis (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setPriceVat(float priceVat) throws LengthOverflowException {
		if (priceVat > 99.99F || priceVat < 0.0F)
			throw new LengthOverflowException("PriceVat");
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
	 * @param priceGross Artikels Nettopreis (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setPriceGross(float priceGross) throws LengthOverflowException {
		if (priceGross > 99999999999999999.99F)
			throw new LengthOverflowException("PriceGross");
		this.priceGross = priceGross;
	}

	/**
	 * 
	 * @return priceSupplier - Artikels Liferantpreis
	 */
	public float getPriceSupplier() {
		return priceSupplier;
	}

	/**
	 * 
	 * @param priceSupplier Artikels Liferantpreis (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setPriceSupplier(float priceSupplier) throws LengthOverflowException {
		if (priceSupplier > 99999999999999999.99F)
			throw new LengthOverflowException("PriceSupplier");
		this.priceSupplier = priceSupplier;
	}

	/**
	 * 
	 * @return stock - Artikels Lager
	 */
	public long getStock() {
		return stock;
	}
	
	/**
	 * 
	 * @param stock Artikels Lager (Nicht Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setStock(long stock) throws LengthOverflowException {
		if (stock > Long.MAX_VALUE || stock < Long.MIN_VALUE)
			throw new LengthOverflowException("Stock");
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
	 * @throws LengthOverflowException 
	 */
	public void setColor(String color) throws LengthOverflowException {
		if (color != null && color.length() > 30)
			throw new LengthOverflowException("Color");
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
	 * @throws LengthOverflowException 
	 */
	public void setSizeH(float sizeH) throws LengthOverflowException {
		if (sizeH > 99999999999999999.99F)
			throw new LengthOverflowException("SizeH");
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
	 * @throws LengthOverflowException 
	 */
	public void setSizeL(float sizeL) throws LengthOverflowException {
		if (sizeL > 99999999999999999.99F)
			throw new LengthOverflowException("SizeL");
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
	 * @throws LengthOverflowException 
	 */
	public void setSizeW(float sizeW) throws LengthOverflowException {
		if (sizeW > 99999999999999999.99F)
			throw new LengthOverflowException("sizeW");
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
	 * @throws LengthOverflowException 
	 */
	public void setDescription(String description) throws LengthOverflowException {
		if (description != null && description.length() > 256)
			throw new LengthOverflowException("description");
		this.description = description;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<SupplierOrderDetail> getSupplierOrderDetail() {
		return supplierOrderDetail;
	}

	/**
	 * 
	 * @param supplierOrderDetail
	 */
	public void setSupplierOrderDetail(Set<SupplierOrderDetail> supplierOrderDetail) {
		this.supplierOrderDetail = supplierOrderDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idArticle ^ (idArticle >>> 32));
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
