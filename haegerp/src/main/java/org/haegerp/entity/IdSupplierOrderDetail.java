package org.haegerp.entity;

import java.io.Serializable;

/**
 * 
 * 
 * @author Wolf
 *
 */
public class IdSupplierOrderDetail implements Serializable {

	private static final long serialVersionUID = -1680361542051086443L;

	//Das ID vor Bestellung
	private SupplierOrder supplierOrder;
	//Das ID vom Artikel
	private Article article;
	
	public IdSupplierOrderDetail() {
	}
	
	public IdSupplierOrderDetail(SupplierOrder supplierOrder, Article article) {
		this.supplierOrder = supplierOrder;
		this.article = article;
	}

	/**
	 * 
	 * @return idSupplierOrder - Holen den Lieferant Bestellung
	 */
	public SupplierOrder getSupplierOrder() {
		return supplierOrder;
	}

	/**
	 * 
	 * @param supplierOrder Stellen den Lieferant ein
	 */
	public void setSupplierOrder(SupplierOrder supplierOrder) {
		this.supplierOrder = supplierOrder;
	}

	/**
	 * 
	 * @return article - Holen den Artikel
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * 
	 * @param idArticle Stellen das ID ein
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
				+ ((supplierOrder == null) ? 0 : supplierOrder.hashCode());
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
		IdSupplierOrderDetail other = (IdSupplierOrderDetail) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (supplierOrder == null) {
			if (other.supplierOrder != null)
				return false;
		} else if (!supplierOrder.equals(other.supplierOrder))
			return false;
		return true;
	}
}
