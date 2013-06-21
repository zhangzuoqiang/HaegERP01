package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;

public class Outstanding implements Serializable {

	private static final long serialVersionUID = 556096071267338464L;
	
	//Primary Key (Erforderlich)
	private long idOutstanding;
	//Die Bestellung, dass die Rechnung gehört
	private ClientBill clientBill;
	//Datum, dass die Rechnung erhalt wird (Erforderlich)
	private Date expireDate;
	//Datum, dass die Rechnung bezahlt wird
	private Date emailDate;
	
	/**
	 * Default Kontruktor
	 */
	public Outstanding() {
	}

	/**
	 * 
	 * @return idOutstanding - Primary Key (Erforderlich)
	 */
	public long getIdOutstanding() {
		return idOutstanding;
	}

	/**
	 * 
	 * @param idOutstanding Primary Key (Erforderlich)
	 */
	public void setIdOutstanding(long idOutstanding) {
		this.idOutstanding = idOutstanding;
	}

	/**
	 * 
	 * @return clientBill - Die Bestellung, dass die Rechnung gehört
	 */
	public ClientBill getClientBill() {
		return clientBill;
	}

	/**
	 * 
	 * @param clientBill Die Bestellung, dass die Rechnung gehört
	 */
	public void setClientBill(ClientBill clientBill) {
		this.clientBill = clientBill;
	}

	/**
	 * 
	 * @return expireDate - Datum, dass die Rechnung erhalt wird (Erforderlich)
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * 
	 * @param expireDate Datum, dass die Rechnung erhalt wird (Erforderlich)
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * 
	 * @return emailDate - Datum, dass die Rechnung bezahlt wird
	 */
	public Date getEmailDate() {
		return emailDate;
	}

	/**
	 * 
	 * @param emailDate Datum, dass die Rechnung bezahlt wird
	 */
	public void setEmailDate(Date emailDate) {
		this.emailDate = emailDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idOutstanding ^ (idOutstanding >>> 32));
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
		Outstanding other = (Outstanding) obj;
		if (idOutstanding != other.idOutstanding)
			return false;
		return true;
	}
	
}
