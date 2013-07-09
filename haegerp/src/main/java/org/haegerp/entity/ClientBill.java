package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Wann der Mitarbeiter die Rechnung schickt, erstellt man eine neue Kunderechnung<br/>
 * 
 * @author Wolf
 *
 */
public class ClientBill implements Serializable {

	private static final long serialVersionUID = -368241591722288663L;

	//Primary Key (Erforderlich)
	private long idClientBill;
	//Die Bestellung, dass die Rechnung gehört
	private ClientOffer clientOffer;
	//Wenn eine Rechnung eine Outstanding Bezahlung hat, dann outstanding != null
	private Outstanding outstanding;
	//Datum, dass die Rechnung ausgestellt wird (Erforderlich)
	private Date billedDate;
	//Datum, dass die Rechnung bezahlt wird
	private Date paidDate;
	//ID des Mitarbeiter, der erstellt hat order geändert
	private Long idEmployeeModify;
	//Datum von der letzten Änderung
	private Date  lastModifiedDate;
	
	/**
	 * Default Konstruktor
	 */
	public ClientBill() {
	}

	/**
	 * 
	 * @return idClientBill - Primary Key (Erforderlich)
	 */
	public long getIdClientBill() {
		return idClientBill;
	}

	/**
	 * 
	 * @param idClientBill Primary Key (Erforderlich)
	 */
	public void setIdClientBill(long idClientBill) {
		this.idClientBill = idClientBill;
	}

	/**
	 * 
	 * @return clientOffer - Die Bestellung, dass die Rechnung gehört
	 */
	public ClientOffer getClientOffer() {
		return clientOffer;
	}

	/**
	 * 
	 * @param clientOffer Die Bestellung, dass die Rechnung gehört
	 */
	public void setClientOffer(ClientOffer clientOffer) {
		this.clientOffer = clientOffer;
	}

	/**
	 * 
	 * @return outstanding - Wenn eine Rechnung eine Outstanding Bezahlung hat, dann outstanding != null
	 */
	public Outstanding getOutstanding() {
		return outstanding;
	}

	/**
	 * 
	 * @param outstanding Wenn eine Rechnung eine Outstanding Bezahlung hat, dann outstanding != null
	 */
	public void setOutstanding(Outstanding outstanding) {
		this.outstanding = outstanding;
	}

	/**
	 * 
	 * @return billedDate - Datum, dass die Rechnung ausgestellt wird (Erforderlich)
	 */
	public Date getBilledDate() {
		return billedDate;
	}

	/**
	 * 
	 * @param billedDate Datum, dass die Rechnung ausgestellt wird (Erforderlich)
	 */
	public void setBilledDate(Date billedDate) {
		this.billedDate = billedDate;
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
		result = prime * result + (int) (idClientBill ^ (idClientBill >>> 32));
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
		ClientBill other = (ClientBill) obj;
		if (idClientBill != other.idClientBill)
			return false;
		return true;
	}
	
}
