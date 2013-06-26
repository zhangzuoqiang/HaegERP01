package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.haegerp.exception.LengthOverflowException;

/**
 * ArticleHistory ist eine Versionierung von dem Artikel.<br/>
 * Wann ein Artikel geändert wird, dann die alte Version wird auch in heir speischern<br/>
 * 
 * @author Wolf
 *
 */
public class ArticleHistory implements Serializable {

	private static final long serialVersionUID = 315503569000796490L;

	//Primary Key (Erforderlich)
	private ArticleHistoryPK articleHistoryPK;
	//Name der Kategorie, dass der Artikel von diese Version gehört (Erforderlich)
	private String articleCategory;
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
	//Liferantbestellungen, dass diese Version gehört
	private Set<SupplierOrderDetail> supplierOrderDetails = new HashSet<SupplierOrderDetail>(0);
	//Kundenbestellungen, dass diese Version gehört
	private Set<ClientOfferDetail> clientOfferDetails = new HashSet<ClientOfferDetail>(0);
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Artikelversion
	 */
	public ArticleHistory() {
	}

	/**
	 * 
	 * @return articleHistoryPK - Primary Key (Erforderlich)
	 */
	public ArticleHistoryPK getArticleHistoryPK() {
		return articleHistoryPK;
	}

	/**
	 * 
	 * @param articleHistoryPK Primary Key (Erforderlich)
	 */
	public void setArticleHistoryPK(ArticleHistoryPK articleHistoryPK) {
		this.articleHistoryPK = articleHistoryPK;
	}

	/**
	 * 
	 * @return articleCategory - Kategorie, dass der Artikel von diese Version gehört (Erforderlich)
	 */
	public String getArticleCategory() {
		return articleCategory;
	}

	/**
	 * 
	 * @param articleCategory Kategorie, dass der Artikel von diese Version gehört (Erforderlich)
	 */
	public void setArticleCategory(String articleCategory) {
		this.articleCategory = articleCategory;
	}

	/**
	 * 
	 * @return ean - Artikels EAN (Erforderlich)
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
	 * @return name - Artikels Name (Erforderlich)
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
	 * @param priceVat Artikels MwSt Preis
	 * @throws LengthOverflowException 
	 */
	public void setPriceVat(float priceVat) throws LengthOverflowException {
		if (priceVat > 99.99F || priceVat < 0.0F)
			throw new LengthOverflowException("PriceVat");
		this.priceVat = priceVat;
	}

	/**
	 * 
	 * @return priceGross - Artikels Bruttopreis
	 */
	public float getPriceGross() {
		return priceGross;
	}

	/**
	 * 
	 * @param priceGross Artikels Bruttopreis
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
	 * @param priceSupplier Artikels Liferantpreis
	 * @throws LengthOverflowException 
	 */
	public void setPriceSupplier(float priceSupplier) throws LengthOverflowException {
		if (priceGross > 99999999999999999.99F)
			throw new LengthOverflowException("PriceGross");
		this.priceSupplier = priceSupplier;
	}
	
	/**
	 * 
	 * @return supplierOrderDetails - Bestellungen, dass diese Version gehört
	 */
	public Set<SupplierOrderDetail> getSupplierOrderDetails() {
		return supplierOrderDetails;
	}

	/**
	 * 
	 * @param supplierOrderDetails Bestellungen, dass diese Version gehört
	 */
	public void setSupplierOrderDetails(
			Set<SupplierOrderDetail> supplierOrderDetails) {
		this.supplierOrderDetails = supplierOrderDetails;
	}
	
	/**
	 * 
	 * @return clientOfferDetails - Kundenbestellungen, dass diese Version gehört
	 */
	public Set<ClientOfferDetail> getClientOfferDetails() {
		return clientOfferDetails;
	}

	/**
	 * 
	 * @param clientOfferDetails Kundenbestellungen, dass diese Version gehört
	 */
	public void setClientOfferDetails(Set<ClientOfferDetail> clientOfferDetails) {
		this.clientOfferDetails = clientOfferDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((articleHistoryPK == null) ? 0 : articleHistoryPK.hashCode());
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
		ArticleHistory other = (ArticleHistory) obj;
		if (articleHistoryPK == null) {
			if (other.articleHistoryPK != null)
				return false;
		} else if (!articleHistoryPK.equals(other.articleHistoryPK))
			return false;
		return true;
	}
	
	
}
