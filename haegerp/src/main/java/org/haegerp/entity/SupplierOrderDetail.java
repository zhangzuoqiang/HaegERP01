package org.haegerp.entity;

import java.io.Serializable;

public class SupplierOrderDetail implements Serializable {

	private static final long serialVersionUID = -203112193435018380L;
	
	//Primary Key (Erforderlich)
	private IdSupplierOrderDetail idSupplierOrderDetail;
	//Stückpreis
	private float price;
	//Quantität
	private long quantity;
	//Artikels MwSt Preis
	private float priceVat;
	//Prozent Rabatt
	private float discount;
	//Artikel Summe
	private float articleTotal;
	
	public SupplierOrderDetail() {
	}

	/**
	 * 
	 * @return idSupplierOrderDetail - Primary Key (Erforderlich)
	 */
	public IdSupplierOrderDetail getIdSupplierOrderDetail() {
		return idSupplierOrderDetail;
	}

	/**
	 * 
	 * @param idSupplierOrderDetail Primary Key (Erforderlich)
	 */
	public void setIdSupplierOrderDetail(IdSupplierOrderDetail idSupplierOrderDetail) {
		this.idSupplierOrderDetail = idSupplierOrderDetail;
	}

	/**
	 * 
	 * @return price - Holen den Preis
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price Stellen den Preis
	 */
	public void setPrice(float price) {
		this.price = price;
		updateArticleTotal();
	}

	/**
	 * 
	 * @return Holen die Quantität
	 */
	public long getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity Stellen die Quantität
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
		updateArticleTotal();
	}

	/**
	 * 
	 * @return Holen den Artikels MwSt Preis
	 */
	public float getPriceVat() {
		return priceVat;
	}

	/**
	 * 
	 * @param priceVat Stellen Artikels MwSt Preis
	 */
	public void setPriceVat(float priceVat) {
		this.priceVat = priceVat;
		updateArticleTotal();
	}

	/**
	 * 
	 * @return Holen den Rabatt
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * 
	 * @param discount Stellen den Rabatt
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
		updateArticleTotal();
	}

	/**
	 * 
	 * @return Holen die Summe
	 */
	public float getArticleTotal() {
		return articleTotal;
	}

	/**
	 * 
	 * @param articleTotal Stellen die Summe
	 */
	public void setArticleTotal(float articleTotal) {
		this.articleTotal = articleTotal;
	}
	
	/**
	 * Die Summe wird aktualisiert
	 */
	public void updateArticleTotal(){
		float percentVat = (this.priceVat/100) + 1;
		float percentDiscount = this.discount/100;
		this.articleTotal = this.price * percentVat * this.quantity * percentDiscount;
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
				+ ((idSupplierOrderDetail == null) ? 0 : idSupplierOrderDetail
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
		SupplierOrderDetail other = (SupplierOrderDetail) obj;
		if (idSupplierOrderDetail == null) {
			if (other.idSupplierOrderDetail != null)
				return false;
		} else if (!idSupplierOrderDetail.equals(other.idSupplierOrderDetail))
			return false;
		return true;
	}
}
