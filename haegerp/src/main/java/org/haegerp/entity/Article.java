package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.haegerp.exception.LengthOverflowException;

/**
 * Ein Artikel ist der Grund vom System.<br/>
 * Man kann ihn in einer Lieferantbestellung kaufen oder in einer Kundenbestellung verkaufen.<br/>
 * Ein Artikel muss zu einer Artikelkategorie gehören.<br/>
 * 
 * @author Fabio Codinha
 *
 */
@XmlRootElement
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
	//ID des Mitarbeiter, der erstellt hat order geändert
	private Long idEmployeeModify;
	//Datum von der letzten Änderung
	private Date  lastModifiedDate;
	
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
	@XmlAttribute(name="id")
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
	@XmlElement
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
	@XmlElement
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
	@XmlElement(name="article")
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
	@XmlElement
	public float getPriceVat() {
		return priceVat;
	}
        
        public float getPriceVatPercent() {
            return priceVat * 100;
        }
	
	/**
	 * 
	 * @param priceVat Artikels MwSt Preis (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setPriceVat(float priceVat) throws LengthOverflowException {
		if (priceVat > 1.0F || priceVat < 0.0F)
			throw new LengthOverflowException("PriceVat");
		this.priceVat = priceVat;
	}
	
	/**
	 * 
	 * @return priceGross - Artikels Nettopreis
	 */
	@XmlElement
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
	@XmlElement
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
	@XmlElement
	public long getStock() {
		return stock;
	}
	
	/**
	 * 
	 * @param stock Artikels Lager (Nicht Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setStock(Long stock) throws LengthOverflowException {
		if (stock != null)
		{
			if (stock > Long.MAX_VALUE || stock < Long.MIN_VALUE)
				throw new LengthOverflowException("Stock");
			this.stock = stock;
		} else {
			this.stock = 0;
		}
	}
	
	/**
	 * 
	 * @return color - Artikels Farbe
	 */
	@XmlElement
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
	@XmlElement
	public float getSizeH() {
		return sizeH;
	}
	
	/**
	 * 
	 * @param sizeH Artikels Höhegröße (Nicht Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setSizeH(Float sizeH) throws LengthOverflowException {
		if (sizeH != null)
		{
			if (sizeH > 99999999999999999.99F)
				throw new LengthOverflowException("SizeH");
			this.sizeH = sizeH;
		}
		else {
			this.sizeH = 0;
		}
	}
	
	/**
	 * 
	 * @return sizeL - Artikels Längegröße
	 */
	@XmlElement
	public float getSizeL() {
		return sizeL;
	}
	
	/**
	 * 
	 * @param sizeL Artikels Längegröße (Nicht Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setSizeL(Float sizeL) throws LengthOverflowException {
		if (sizeL != null)
		{
			if (sizeL > 99999999999999999.99F)
				throw new LengthOverflowException("SizeL");
			this.sizeL = sizeL;
		}
		else {
			this.sizeL = 0;
		}
	}
	
	/**
	 * 
	 * @return sizeW - Artikels Breitegröße
	 */
	@XmlElement
	public float getSizeW() {
		return sizeW;
	}
	/**
	 * 
	 * @param sizeW Artikels Breitegröße (Nicht Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setSizeW(Float sizeW) throws LengthOverflowException {
		if (sizeW != null)
		{
			if (sizeW > 99999999999999999.99F)
				throw new LengthOverflowException("SizeW");
			this.sizeW = sizeW;
		}
		else {
			this.sizeW = 0;
		}
	}
	
	/**
	 * 
	 * @return description - Artikels Beschreibung
	 */
	@XmlElement
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
	 * @return idEmployeeModify - ID des Mitarbeiter, der erstellt hat order geändert
	 */
	public Long getIdEmployeeModify() {
		return idEmployeeModify;
	}

	/**
	 * 
	 * @param idEmployeeModify ID des Mitarbeiter, der erstellt hat order geändert
	 */
	public void setIdEmployeeModify(Long idEmployeeModify) {
		this.idEmployeeModify = idEmployeeModify;
	}

	/**
	 * 
	 * @return lastModifiedDate - Datum von der letzten Änderung
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * 
	 * @param lastModifiedDate Datum von der letzten Änderung
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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
