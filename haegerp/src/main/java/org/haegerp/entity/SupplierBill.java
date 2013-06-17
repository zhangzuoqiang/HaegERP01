package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Wann der Lieferant die Rechnung schickt, erstellt man eine neue Liferantrechnung<br/>
 * 
 * @author Wolf
 *
 */
public class SupplierBill implements Serializable {

	private static final long serialVersionUID = -582360377641367019L;
	//Primary Key (Erforderlich)
	private long idSupplierBill;
	//Bestallung der Rechnung
	private SupplierOrder supplierOrder;
	//Datum, dass die Rechnung erhalt wird (Erforderlich)
	private Date receivedDate;
	//Datum, dass die Rechnung bezahlt wird
	private Date paidDate;
	
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
	 * @return supplierOrder - Bestallung der Rechnung
	 */
	public SupplierOrder getSupplierOrder() {
		return supplierOrder;
	}

	/**
	 * 
	 * @param supplierOrder Bestallung der Rechnung
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
