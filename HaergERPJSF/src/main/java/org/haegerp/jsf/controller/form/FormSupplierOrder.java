package org.haegerp.jsf.controller.form;

import org.haegerp.entity.SupplierOrder;

/**
 * TODO!!
 * @author Wolf
 */
public class FormSupplierOrder {
    //Lieferants Name (Erforderlich)
    private String txtSupplierName;
    //Lieferantsbestellung: Datum der Bestellung (Erforderlich)
    private String txtOrderDate;
    //Lieferantsbestellung: Wann ist die Bestellung geschickt
    private String txtSendDate;
    //Lieferantsbestellung: Datum der Rechnung
    private String txtBillReceived;
    //Lieferantsbestellung: Datum 
    private String txtBillPaid;
    //Lieferantsbestellung Summe
    private String txtTotal;
    //Lieferantsbestellung Beschreibung
    private String txtDescription;
    
    private boolean disabled;
    
    //btnEditSave
    private String btnEditSave_Name;
    
    //btnCancel
    private String btnCancel_Name;
    private boolean btnCancel_Ajax;
    
    public FormSupplierOrder() {
    }
    
    public FormSupplierOrder(boolean disabled) {
        this.disabled = disabled;
        configureButtons(disabled);
    }
    
    public FormSupplierOrder(SupplierOrder supplierOrder, boolean disabled) {
        txtSupplierName = supplierOrder.getSupplier().getName();
        txtDescription = supplierOrder.getDescription();
        this.disabled = disabled;
        configureButtons(disabled);
    }
    
    private void configureButtons(boolean disabled) {
        if (disabled) {
            btnEditSave_Name = "Edit";
            btnCancel_Ajax = false;
            btnCancel_Name = "Back";
        } else {
            btnEditSave_Name = "Save";
            btnCancel_Ajax = true;
            btnCancel_Name = "Cancel";
        }
    }
}
