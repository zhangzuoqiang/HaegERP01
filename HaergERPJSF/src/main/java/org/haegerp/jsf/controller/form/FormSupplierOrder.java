package org.haegerp.jsf.controller.form;

import java.text.SimpleDateFormat;
import org.haegerp.entity.SupplierOrder;

/**
 *
 * @author Wolf
 */
public class FormSupplierOrder {
    //Lieferanten Name

    private String txtSupplierName;
    //Der Benutzer kann den Lieferanten nochmal wählen
    private boolean disabledSupplier;
    //Kunden Name
    private String txtEmployeeName;
    //Lieferantsbestellung: Datum der Bestellung (Erforderlich)
    private String txtOrderDate;
    //Lieferantsbestellung: Wann ist die Bestellung geschickt
    private String txtSendDate;
    //Der Benutzer kann die Bestellung auf geschickt ändern
    private boolean disabledSendDate;
    //Lieferantsbestellung: Datum der Rechnung
    private String txtBillReceived;
    //Kann der Benutzer die Datum der Rechnung speichern?
    private boolean disableBillReceived;
    //Lieferantsbestellung: Datum 
    private String txtBillPaid;
    //Kann der Benutzer die Datum der Rechnung Zahlung speichern?
    private boolean disableBillPaid;
    //Lieferantsbestellung Summe
    private String txtTotal;
    //Lieferantsbestellung Beschreibung
    private String txtDescription;
    //Artikel der Bestellung
    private Object[][] tblArticles;
    //Kann der Benutzer die Artikel ändern?
    private boolean disableArticle;
    private boolean disabled;
    //btnEditSave
    private String btnEditSave_Name;
    //btnCancel
    private String btnCancel_Name;
    private boolean btnCancel_Ajax;
    //Wählender der Artikeln
    //Die Artikel
    private Object[][] tblChooserArticles;
    private String txtSearchArticle;
    //Wählender der Lieferanten
    //Inhalt der Tabelle
    private Object[][] tblChooserSuppliers;
    private String txtSearchSupplier;

    public FormSupplierOrder() {
    }

    public FormSupplierOrder(boolean disabled) {
        this.disabled = disabled;
        if (!disabled) {
            disableArticle = false;
            disabledSupplier = false;
            disableBillPaid = true;
            disableBillReceived = true;
            disabledSendDate = true;
        } else {
            disableArticle = true;
            disabledSupplier = true;
            disableBillPaid = true;
            disableBillReceived = true;
            disabledSendDate = true;
        }
        configureButtons(disabled);
    }

    public FormSupplierOrder(SupplierOrder supplierOrder, boolean disabled) {
        txtSupplierName = supplierOrder.getSupplier().getName();
        txtEmployeeName = supplierOrder.getEmployee().getName();
        if (supplierOrder.getOrderDate() != null) {
            txtOrderDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrder.getOrderDate());
        } else {
            txtOrderDate = "";
        }

        if (supplierOrder.getSendDate() != null) {
            txtSendDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrder.getSendDate());
            disabledSupplier = true;
            disabledSendDate = true;
        } else {
            txtSendDate = "Order not sent";
            disabledSupplier = false;
            disabledSendDate = false;
        }

        if (supplierOrder.getSupplierBill() != null) {
            if (supplierOrder.getSupplierBill().getReceivedDate() != null) {
                txtBillReceived = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrder.getSupplierBill().getReceivedDate());
                disableBillReceived = true;
                disableArticle = true;
            } else {
                txtBillReceived = "Bill not Received";
                disableBillReceived = false;
                disableArticle = false;
            }

            if (supplierOrder.getSupplierBill().getPaidDate() != null) {
                txtBillPaid = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrder.getSupplierBill().getPaidDate());
                disableBillPaid = true;
            } else {
                txtBillPaid = "Bill not Paid";
                disableBillPaid = false;
            }
        } else {
            txtBillReceived = "Bill not Received";
            txtBillPaid = "Bill not Paid";
            if (disabledSendDate) {
                disableBillReceived = false;
            }
        }
        txtTotal = String.valueOf(supplierOrder.getTotal());

        txtDescription = supplierOrder.getDescription();
        this.disabled = disabled;
        if (disabled) {
            disableArticle = true;
            disabledSupplier = true;
            disableBillPaid = true;
            disableBillReceived = true;
            disabledSendDate = true;
        }
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

    /**
     * @return the txtSupplierName
     */
    public String getTxtSupplierName() {
        return txtSupplierName;
    }

    /**
     * @param txtSupplierName the txtSupplierName to set
     */
    public void setTxtSupplierName(String txtSupplierName) {
        this.txtSupplierName = txtSupplierName;
    }

    /**
     * @return the txtOrderDate
     */
    public String getTxtOrderDate() {
        return txtOrderDate;
    }

    /**
     * @param txtOrderDate the txtOrderDate to set
     */
    public void setTxtOrderDate(String txtOrderDate) {
        this.txtOrderDate = txtOrderDate;
    }

    /**
     * @return the txtSendDate
     */
    public String getTxtSendDate() {
        return txtSendDate;
    }

    /**
     * @param txtSendDate the txtSendDate to set
     */
    public void setTxtSendDate(String txtSendDate) {
        this.txtSendDate = txtSendDate;
    }

    /**
     * @return the txtBillReceived
     */
    public String getTxtBillReceived() {
        return txtBillReceived;
    }

    /**
     * @param txtBillReceived the txtBillReceived to set
     */
    public void setTxtBillReceived(String txtBillReceived) {
        this.txtBillReceived = txtBillReceived;
    }

    /**
     * @return the txtBillPaid
     */
    public String getTxtBillPaid() {
        return txtBillPaid;
    }

    /**
     * @param txtBillPaid the txtBillPaid to set
     */
    public void setTxtBillPaid(String txtBillPaid) {
        this.txtBillPaid = txtBillPaid;
    }

    /**
     * @return the txtTotal
     */
    public String getTxtTotal() {
        return txtTotal;
    }

    /**
     * @param txtTotal the txtTotal to set
     */
    public void setTxtTotal(String txtTotal) {
        this.txtTotal = txtTotal;
    }

    /**
     * @return the txtDescription
     */
    public String getTxtDescription() {
        return txtDescription;
    }

    /**
     * @param txtDescription the txtDescription to set
     */
    public void setTxtDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    /**
     * @return the disabled
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * @return the btnEditSave_Name
     */
    public String getBtnEditSave_Name() {
        return btnEditSave_Name;
    }

    /**
     * @param btnEditSave_Name the btnEditSave_Name to set
     */
    public void setBtnEditSave_Name(String btnEditSave_Name) {
        this.btnEditSave_Name = btnEditSave_Name;
    }

    /**
     * @return the btnCancel_Name
     */
    public String getBtnCancel_Name() {
        return btnCancel_Name;
    }

    /**
     * @param btnCancel_Name the btnCancel_Name to set
     */
    public void setBtnCancel_Name(String btnCancel_Name) {
        this.btnCancel_Name = btnCancel_Name;
    }

    /**
     * @return the btnCancel_Ajax
     */
    public boolean isBtnCancel_Ajax() {
        return btnCancel_Ajax;
    }

    /**
     * @param btnCancel_Ajax the btnCancel_Ajax to set
     */
    public void setBtnCancel_Ajax(boolean btnCancel_Ajax) {
        this.btnCancel_Ajax = btnCancel_Ajax;
    }

    /**
     * @return the tblArticles
     */
    public Object[][] getTblArticles() {
        return tblArticles;
    }

    /**
     * @param tblArticles the tblArticles to set
     */
    public void setTblArticles(Object[][] tblArticles) {
        this.tblArticles = tblArticles;
    }

    /**
     * @return the txtEmployeeName
     */
    public String getTxtEmployeeName() {
        return txtEmployeeName;
    }

    /**
     * @param txtEmployeeName the txtEmployeeName to set
     */
    public void setTxtEmployeeName(String txtEmployeeName) {
        this.txtEmployeeName = txtEmployeeName;
    }

    /**
     * @return the tblChooserArticles
     */
    public Object[][] getTblChooserArticles() {
        return tblChooserArticles;
    }

    /**
     * @param tblChooserArticles the tblChooserArticles to set
     */
    public void setTblChooserArticles(Object[][] tblChooserArticles) {
        this.tblChooserArticles = tblChooserArticles;
    }

    /**
     * @return the txtSearchArticle
     */
    public String getTxtSearchArticle() {
        return txtSearchArticle;
    }

    /**
     * @param txtSearchArticle the txtSearchArticle to set
     */
    public void setTxtSearchArticle(String txtSearchArticle) {
        this.txtSearchArticle = txtSearchArticle;
    }

    /**
     * @return the tblChooserSuppliers
     */
    public Object[][] getTblChooserSuppliers() {
        return tblChooserSuppliers;
    }

    /**
     * @param tblChooserSuppliers the tblChooserSuppliers to set
     */
    public void setTblChooserSuppliers(Object[][] tblChooserSuppliers) {
        this.tblChooserSuppliers = tblChooserSuppliers;
    }

    /**
     * @return the txtSearchSupplier
     */
    public String getTxtSearchSupplier() {
        return txtSearchSupplier;
    }

    /**
     * @param txtSearchSupplier the txtSearchSupplier to set
     */
    public void setTxtSearchSupplier(String txtSearchSupplier) {
        this.txtSearchSupplier = txtSearchSupplier;
    }

    /**
     * @return the disabledSupplier
     */
    public boolean isDisabledSupplier() {
        return disabledSupplier;
    }

    /**
     * @param disabledSupplier the disabledSupplier to set
     */
    public void setDisabledSupplier(boolean disabledSupplier) {
        this.disabledSupplier = disabledSupplier;
    }

    /**
     * @return the disabledSendDate
     */
    public boolean isDisabledSendDate() {
        return disabledSendDate;
    }

    /**
     * @param disabledSendDate the disabledSendDate to set
     */
    public void setDisabledSendDate(boolean disabledSendDate) {
        this.disabledSendDate = disabledSendDate;
    }

    /**
     * @return the disableBillReceived
     */
    public boolean isDisableBillReceived() {
        return disableBillReceived;
    }

    /**
     * @param disableBillReceived the disableBillReceived to set
     */
    public void setDisableBillReceived(boolean disableBillReceived) {
        this.disableBillReceived = disableBillReceived;
    }

    /**
     * @return the disableBillPaid
     */
    public boolean isDisableBillPaid() {
        return disableBillPaid;
    }

    /**
     * @param disableBillPaid the disableBillPaid to set
     */
    public void setDisableBillPaid(boolean disableBillPaid) {
        this.disableBillPaid = disableBillPaid;
    }

    /**
     * @return the disableArticle
     */
    public boolean isDisableArticle() {
        return disableArticle;
    }

    /**
     * @param disableArticle the disableArticle to set
     */
    public void setDisableArticle(boolean disableArticle) {
        this.disableArticle = disableArticle;
    }
}
