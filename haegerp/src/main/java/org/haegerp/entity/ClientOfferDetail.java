package org.haegerp.entity;

import java.io.Serializable;

import org.haegerp.exception.LengthOverflowException;

/**
 * Eine Bestellung kann viele Artikel haben.<br/>
 * Diese Klasse repräsentiert jede Linie von einer Kundebestellung.
 * 
 * @author Wolf
 *
 */
public class ClientOfferDetail implements Serializable {

	private static final long serialVersionUID = -6290432093260950762L;

	//Primary Key (Erforderlich)
	private ClientOfferDetailPK clientOfferDetailPK;
	//Quantität
	private long quantity;
	//Prozent Rabatt
	private float discount;
	//Artikel Summe
	private float totalArticle;
	
	/**
	 * Default Konstruktor
	 */
	public ClientOfferDetail() {
	}

	/**
	 * 
	 * @return clientOfferDetailPK - Primary Key (Erforderlich)
	 */
	public ClientOfferDetailPK getClientOfferDetailPK() {
		return clientOfferDetailPK;
	}

	/**
	 * 
	 * @param clientOfferDetailPK Primary Key (Erforderlich)
	 */
	public void setClientOfferDetailPK(ClientOfferDetailPK clientOfferDetailPK) {
		this.clientOfferDetailPK = clientOfferDetailPK;
	}

	/**
	 * 
	 * @return quantity - Quantität
	 */
	public long getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity Quantität
	 * @throws LengthOverflowException 
	 */
	public void setQuantity(long quantity) throws LengthOverflowException {
		if (quantity > Long.MAX_VALUE || quantity < 1L)
			throw new LengthOverflowException("Quantity");
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return discount - Prozent Rabatt
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * 
	 * @param discount Prozent Rabatt
	 * @throws LengthOverflowException
	 */
	public void setDiscount(float discount) throws LengthOverflowException {
		if (discount >= 100F || discount < 0.0F)
			throw new LengthOverflowException("Discount");
		this.discount = discount;
	}

	/**
	 * 
	 * @return totalArticle - Artikel Summe
	 */
	public float getTotalArticle() {
		return totalArticle;
	}

	/**
	 * 
	 * @param totalArticle Artikel Summe
	 * @throws LengthOverflowException 
	 */
	public void setTotalArticle(float totalArticle) throws LengthOverflowException {
		if (totalArticle >= Float.MAX_VALUE || totalArticle < 0.0F)
			throw new LengthOverflowException("TotalArticle");
		this.totalArticle = totalArticle;
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
				+ ((clientOfferDetailPK == null) ? 0 : clientOfferDetailPK
						.hashCode());
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
		ClientOfferDetail other = (ClientOfferDetail) obj;
		if (clientOfferDetailPK == null) {
			if (other.clientOfferDetailPK != null)
				return false;
		} else if (!clientOfferDetailPK.equals(other.clientOfferDetailPK))
			return false;
		return true;
	}
}
