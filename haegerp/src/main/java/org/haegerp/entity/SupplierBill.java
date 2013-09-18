package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Wann der Lieferant die Rechnung schickt, erstellt man eine neue Liferantrechnung<br/>
 * 
 * @author Fabio Codinha
 *
 */
public class SupplierBill implements Serializable {

	private static final long serialVersionUID = -582360377641367019L;
	//Primary Key (Erforderlich)
	private long idSupplierBill;
	//Die Bestellung, dass die Rechnung gehört (Erforderlich)
	private SupplierOrder supplierOrder;
	//Datum, dass die Rechnung erhalt wird (Erforderlich)
	private Date receivedDate;
	//Datum, dass die Rechnung bezahlt wird
	private Date paidDate;
	//ID des Mitarbeiter, der erstellt hat order geändert
	private Long idEmployeeModify;
	//Datum von der letzten Änderung
	private Date  lastModifiedDate;
	
	public SupplierBill() {
	}

	/**
	 * 
	 * @return idSupplierBill - Primary Key (Erforderlich)
	 */
	public long getIdSupplierBill() {
		return idSupplierBill;
	}

	/**
	 * 
	 * @param idSupplierBill Primary Key (Erforderlich)
	 */
	public void setIdSupplierBill(long idSupplierBill) {
		this.idSupplierBill = idSupplierBill;
	}

	/**
	 * 
	 * @return supplierOrder - Die Bestellung, dass die Rechnung gehört
	 */
	public SupplierOrder getSupplierOrder() {
		return supplierOrder;
	}

	/**
	 * 
	 * @param supplierOrder Die Bestellung, dass die Rechnung gehört
	 */
	public void setSupplierOrder(SupplierOrder supplierOrder) {
		this.supplierOrder = supplierOrder;
	}

	/**
	 * 
	 * @return billedDate - Datum, dass die Rechnung bezahlt wird
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * 
	 * @param billedDate Datum, dass die Rechnung bezahlt wird
	 */
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	/**
	 * 
	 * @return paidDate - Datum, dass die Rechnung bezahlt wird
	 */
	public Date getPaidDate() {
		return paidDate;
	}

	/**
	 * 
	 * @param paidDate Datum, dass die Rechnung bezahlt wird
	 */
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
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
		result = prime * result + (int) (idSupplierBill ^ (idSupplierBill >>> 32));
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
		SupplierBill other = (SupplierBill) obj;
		if (idSupplierBill != other.idSupplierBill)
			return false;
		return true;
	}
}
