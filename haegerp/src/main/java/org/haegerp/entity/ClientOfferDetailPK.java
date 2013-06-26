package org.haegerp.entity;

import java.io.Serializable;

/**
 * Die Linien von einer Bestellung haben ein Composite Primary Key.<br/>
 * Das heisst, dass eine ein Bestellung und eine Artikelversion haben muss.<br/>
 * 
 * @author Wolf
 *
 */
public class ClientOfferDetailPK implements Serializable {

	private static final long serialVersionUID = -4041650833759046030L;

	//Bestellung, dass die Linie gehört
	private ClientOffer clientOffer;
	//Artikel Version
	private ArticleHistory articleHistory;
	
	/**
	 * Default Konstruktor
	 */
	public ClientOfferDetailPK() {
	}
	
	/**
	 * 
	 * @param clientOffer Bestellung, dass die Linie gehört
	 * @param articleHistory Artikel Version
	 */
	public ClientOfferDetailPK(ClientOffer clientOffer, ArticleHistory articleHistory) {
		this.clientOffer = clientOffer;
		this.articleHistory = articleHistory;
	}



	/**
	 * 
	 * @return clientOffer - Bestellung, dass die Linie gehört
	 */
	public ClientOffer getClientOffer() {
		return clientOffer;
	}

	/**
	 * 
	 * @param clientOffer Bestellung, dass die Linie gehört
	 */
	public void setClientOffer(ClientOffer clientOffer) {
		this.clientOffer = clientOffer;
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
				+ ((clientOffer == null) ? 0 : clientOffer.hashCode());
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
		ClientOfferDetailPK other = (ClientOfferDetailPK) obj;
		if (articleHistory == null) {
			if (other.articleHistory != null)
				return false;
		} else if (!articleHistory.equals(other.articleHistory))
			return false;
		if (clientOffer == null) {
			if (other.clientOffer != null)
				return false;
		} else if (!clientOffer.equals(other.clientOffer))
			return false;
		return true;
	}
}
