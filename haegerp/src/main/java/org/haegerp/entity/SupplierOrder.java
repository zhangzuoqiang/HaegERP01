package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SupplierOrder implements Serializable {

	private static final long serialVersionUID = 5595958379021721485L;
	//Primary Key (Erforderlich)
	private long idSupplierOrder;
	//Lieferants Rechnung
	private SupplierBill supplierBill;
	//Lieferant (Erforderlich)
	private Supplier supplier;
	//Mitarbeiter, dass die Bestellung erstellt hat (Erforderlich)
	private Employee employee;
	//Datum, dass die Bestellung erstellt wird (Erforderlich)
	private Date orderDate;
	//Summe der Bestellung
	private float total;
	//Datum, dass der Mitarbeiter die Bestellung zu dem Lieferant schickt
	private Date sendDate;
	
	//Beschreibung der Bestellung
	private String description;
	//Details der Bestellung
	private Set<SupplierOrderDetail> supplierOrderDetail = new HashSet<SupplierOrderDetail>(0);
	
	public SupplierOrder() {
	}

	/**
	 * 
	 * @return idSupplierOrder - Primary Key (Erforderlich)
	 */
	public long getIdSupplierOrder() {
		return idSupplierOrder;
	}
	/**
	 * 
	 * @param idSupplierOrder Primary Key (Erforderlich)
	 */
	public void setIdSupplierOrder(long idSupplierOrder) {
		this.idSupplierOrder = idSupplierOrder;
	}

	/**
	 * 
	 * @return supplierBill - Lieferants Rechnung
	 */
	public SupplierBill getSupplierBill() {
		return supplierBill;
	}

	/**
	 * 
	 * @param supplierBill Lieferants Rechnung
	 */
	public void setSupplierBill(SupplierBill supplierBill) {
		this.supplierBill = supplierBill;
	}

	/**
	 * 
	 * @return supplier - Lieferant (Erforderlich)
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * 
	 * @param supplier Lieferant (Erforderlich)
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
	 * @return orderDate - Datum, dass die Bestellung erstellt wird (Erforderlich)
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * 
	 * @param orderDate Datum, dass die Bestellung erstellt wird (Erforderlich)
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * 
	 * @return sendDate - Datum, dass die Rechnung erhalt wird
	 */
	public Date getSendDate() {
		return sendDate;
	}

	/**
	 * 
	 * @param sendDate Datum, dass die Rechnung erhalt wird
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
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<SupplierOrderDetail> getSupplierOrderDetail() {
		return supplierOrderDetail;
	}

	/**
	 * 
	 * @param supplierOrderDetail
	 */
	public void setSupplierOrderDetail(Set<SupplierOrderDetail> supplierOrderDetail) {
		this.supplierOrderDetail = supplierOrderDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idSupplierOrder ^ (idSupplierOrder >>> 32));
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
		SupplierOrder other = (SupplierOrder) obj;
		if (idSupplierOrder != other.idSupplierOrder)
			return false;
		return true;
	}
}
