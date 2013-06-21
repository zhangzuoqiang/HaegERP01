package org.haegerp.entity;

import java.io.Serializable;

import org.haegerp.exception.LengthOverflowException;

/**
 * Eine Bestellung kann viele Artikel haben.<br/>
 * Diese Klasse repräsentiert jede Linie von einer Bestellung.
 * 
 * @author Wolf
 *
 */
public class SupplierOrderDetail implements Serializable {

	private static final long serialVersionUID = -203112193435018380L;
	
	//Primary Key (Erforderlich)
	private SupplierOrderDetailPK supplierOrderDetailPK;
	//Quantität
	private long quantity;
	//Prozent Rabatt
	private float discount;
	//Artikel Summe
	private float totalArticle;
	
	public SupplierOrderDetail() {
	}

	/**
	 * 
	 * @return idSupplierOrderDetail - Primary Key (Erforderlich)
	 */
	public SupplierOrderDetailPK getSupplierOrderDetailPK() {
		return supplierOrderDetailPK;
	}

	/**
	 * 
	 * @param idSupplierOrderDetail Primary Key (Erforderlich)
	 */
	public void setSupplierOrderDetailPK(SupplierOrderDetailPK supplierOrderDetailPK) {
		this.supplierOrderDetailPK = supplierOrderDetailPK;
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
	 * @throws LengthOverflowException 
	 */
	public void setQuantity(long quantity) throws LengthOverflowException {
		if (quantity > Long.MAX_VALUE || quantity < 1L)
			throw new LengthOverflowException("Quantity");
		this.quantity = quantity;
		ArticleTotalCalculation();
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
	 * @throws LengthOverflowException 
	 */
	public void setDiscount(float discount) throws LengthOverflowException {
		if (discount >= 100F || discount < 0.0F)
			throw new LengthOverflowException("Discount");
		this.discount = discount;
		ArticleTotalCalculation();
	}

	/**
	 * 
	 * @return Holen die Summe
	 */
	public float getTotalArticle() {
		return totalArticle;
	}

	/**
	 * 
	 * @param articleTotal Stellen die Summe
	 * @throws LengthOverflowException 
	 */
	public void setTotalArticle(float totalArticle) throws LengthOverflowException {
		if (totalArticle >= Float.MAX_VALUE || totalArticle < 0.0F)
			throw new LengthOverflowException("TotalArticle");
		this.totalArticle = totalArticle;
	}
	
	/**
	 * Die Summe wird ausgerechnt
	 */
	public void ArticleTotalCalculation(){
		this.totalArticle = (float) (Math.floor((this.quantity 
				* (1 - (this.discount/100)) 
				* supplierOrderDetailPK.getArticleHistory().getPriceSupplier())*100)/100);
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
				+ ((supplierOrderDetailPK == null) ? 0 : supplierOrderDetailPK
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
		if (supplierOrderDetailPK == null) {
			if (other.supplierOrderDetailPK != null)
				return false;
		} else if (!supplierOrderDetailPK.equals(other.supplierOrderDetailPK))
			return false;
		return true;
	}
}
