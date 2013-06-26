package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.haegerp.exception.LengthOverflowException;

/**
 * Wann ein Kunde Artikel kaufen möchte, dann muss man ein Bestellung machen und schickt sie zu dem Kunde.
 * 
 * @author Wolf
 *
 */
public class ClientOffer implements Serializable {

	private static final long serialVersionUID = 2375144900992929307L;

	//Primary Key (Erforderlich)
	private long idClientOffer;
	//Rechnung des Kunde
	private ClientBill clientBill;
	//Kunde (Erforderlich)
	private Client client;
	//Mitarbeiter, dass die Bestellung erstellt hat (Erforderlich)
	private Employee employee;
	//Datum, dass die Bestellung erstellt wird (Erforderlich)
	private Date offerDate;
	//Summe der Bestellung
	private float total;
	//Datum, dass der Mitarbeiter die Bestellung zu dem Kunde schickt
	private Date sendDate;
	//Beschreibung der Bestellung
	private String description;
	//Details / Artikel der Bestellung
	private Set<ClientOfferDetail> clientOfferDetail = new HashSet<ClientOfferDetail>(0);
	
	/**
	 * Default Konstruktor
	 */
	public ClientOffer() {
	}

	/**
	 * 
	 * @return idClientOffer - Primary Key (Erforderlich)
	 */
	public long getIdClientOffer() {
		return idClientOffer;
	}

	/**
	 * 
	 * @param idClientOffer Primary Key (Erforderlich)
	 */
	public void setIdClientOffer(long idClientOffer) {
		this.idClientOffer = idClientOffer;
	}

	/**
	 * 
	 * @return clientBill - Rechnung des Kunde
	 */
	public ClientBill getClientBill() {
		return clientBill;
	}

	/**
	 * 
	 * @param clientBill Rechnung des Kunde
	 */
	public void setClientBill(ClientBill clientBill) {
		this.clientBill = clientBill;
	}

	/**
	 * 
	 * @return client - Kunde (Erforderlich)
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * 
	 * @param client Kunde (Erforderlich)
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * 
	 * @return employee - Mitarbeiter, dass die Bestellung erstellt hat (Erforderlich)
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * 
	 * @param employee Mitarbeiter, dass die Bestellung erstellt hat (Erforderlich)
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * 
	 * @return offerDate - Datum, dass die Bestellung erstellt wird (Erforderlich)
	 */
	public Date getOfferDate() {
		return offerDate;
	}

	/**
	 * 
	 * @param offerDate Datum, dass die Bestellung erstellt wird (Erforderlich)
	 */
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}

	/**
	 * 
	 * @return total - Summe der Bestellung
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * 
	 * @param total Summe der Bestellung
	 * @throws LengthOverflowException 
	 */
	public void setTotal(Float total) throws LengthOverflowException {
		if (total != null) {
			if (total > Float.MAX_VALUE)
				throw new LengthOverflowException("Total");
			this.total = total;
		} else {
			this.total = 0;
		}
	}

	/**
	 * 
	 * @return sendDate - Datum, dass der Mitarbeiter die Bestellung zu dem Kunde schickt
	 */
	public Date getSendDate() {
		return sendDate;
	}

	/**
	 * 
	 * @param sendDate Datum, dass der Mitarbeiter die Bestellung zu dem Kunde schickt
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * 
	 * @return description - Beschreibung der Bestellung
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description Beschreibung der Bestellung
	 * @throws LengthOverflowException 
	 */
	public void setDescription(String description) throws LengthOverflowException {
		if (description != null && description.length() > 256)
			throw new LengthOverflowException("Description");
		this.description = description;
	}

	/**
	 * 
	 * @return clientOfferDetail - Details / Artikel der Bestellung
	 */
	public Set<ClientOfferDetail> getClientOfferDetail() {
		return clientOfferDetail;
	}

	/**
	 * 
	 * @param clientOfferDetail Details / Artikel der Bestellung
	 */
	public void setClientOfferDetail(Set<ClientOfferDetail> clientOfferDetail) {
		this.clientOfferDetail = clientOfferDetail;
	}
	
	/**
	 * Summe des Angebot
	 */
	public void calculateTotal() {
		this.total = 0;
		for (ClientOfferDetail clientOfferDetail : this.clientOfferDetail) {
			this.total = this.total + clientOfferDetail.getTotalArticle();
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idClientOffer ^ (idClientOffer >>> 32));
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
		ClientOffer other = (ClientOffer) obj;
		if (idClientOffer != other.idClientOffer)
			return false;
		return true;
	}
}
