package org.haegerp.entity;

import java.io.Serializable;

/**
 * Die Linien von einer Bestellung haben ein Composite Primary Key.<br/>
 * Das heisst, dass eine ein Bestellung und eine Artikelversion haben muss.<br/>
 * 
 * @author Fabio Codinha
 *
 */
public class SupplierOrderDetailPK implements Serializable {

	private static final long serialVersionUID = 4907577054930356369L;

	//Bestellung, dass die Linie gehört
	private SupplierOrder supplierOrder;
	//Artikel Version
	private ArticleHistory articleHistory;
	//ID des Mitarbeiter, der erstellt hat order geändert
	
	/**
	 * Default Kontruktor
	 */
	public SupplierOrderDetailPK() {
	}
	
	/**
	 * Konstruktor mit keinen Parametern.
	 * Ideal für eine neue Linie Primary Key.
	 * 
	 * @param supplierOrder Bestellung, dass die Linie gehört
	 * @param articleHistory Artikel Version
	 */
	public SupplierOrderDetailPK(SupplierOrder supplierOrder,
			ArticleHistory articleHistory) {
		this.supplierOrder = supplierOrder;
		this.articleHistory = articleHistory;
	}

	/**
	 * 
	 * @return supplierOrder - Bestellung, dass die Linie gehört
	 */
	public SupplierOrder getSupplierOrder() {
		return supplierOrder;
	}

	/**
	 * 
	 * @param supplierOrder Bestellung, dass die Linie gehört
	 */
	public void setSupplierOrder(SupplierOrder supplierOrder) {
		this.supplierOrder = supplierOrder;
	}

	/**
	 * 
	 * @return articleHistory - Artikel Version
	 */
	public ArticleHistory getArticleHistory() {
		return articleHistory;
	}

	/**
	 * 
	 * @param articleHistory Artikel Version
	 */
	public void setArticleHistory(ArticleHistory articleHistory) {
		this.articleHistory = articleHistory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articleHistory == null) ? 0 : articleHistory.hashCode());
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
		SupplierOrderDetailPK other = (SupplierOrderDetailPK) obj;
		if (articleHistory == null) {
			if (other.articleHistory != null)
				return false;
		} else if (!articleHistory.equals(other.articleHistory))
			return false;
		if (supplierOrder == null) {
			if (other.supplierOrder != null)
				return false;
		} else if (!supplierOrder.equals(other.supplierOrder))
			return false;
		return true;
	}
}
