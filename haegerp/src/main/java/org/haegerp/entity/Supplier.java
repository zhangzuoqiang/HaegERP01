package org.haegerp.entity;

import java.io.Serializable;

/**
 * Der Lieferant ist ein Geschäftspartner.<br/>
 * Zu ihnen kann man die Artikel kaufen.<br/>
 * 
 * @author Wolf
 *
 */
public class Supplier extends BusinessPartner implements Serializable {

	private static final long serialVersionUID = -7383190921739350244L;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Lieferant
	 */
	public Supplier() {
		super();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
