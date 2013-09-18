package org.haegerp.jsf.controller.form;

import java.text.SimpleDateFormat;
import org.haegerp.entity.ClientOffer;

/**
 *
 * @author Fabio Codinha
 */
public class FormClientOffer {
    //Kunden Name
    private String txtClientName;
    //Der Benutzer kann den Kunden nochmal wählen
    private boolean disabledClient;
    //Benutzer Name
    private String txtEmployeeName;
    //Kundenangebot: Datum des Antegots (Erforderlich)
    private String txtOrderDate;
    //Kundenangebot: Wann ist das Angebot geschickt
    private String txtSendDate;
    //Der Benutzer kann das Angebot auf geschickt ändern
    private boolean disabledSendDate;
    //Kundenangebot: Datum der Rechnung
    private String txtBilled;
    //Kann der Benutzer die Datum der Rechnung speichern?
    private boolean disableBilled;
    //Kundenangebot: Datum 
    private String txtBillPaid;
    //Kann der Benutzer die Datum der Rechnung Zahlung speichern?
    private boolean disableBillPaid;
    //Kundenangebot Summe
    private String txtTotal;
    //Kundenangebot Beschreibung
    private String txtDescription;
    //Artikel des Angebots
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
    //Wählend der Kunden
    //Inhalt der Tabelle
    private Object[][] tblChooserClients;
    private String txtSearchClient;

    public FormClientOffer() {
    }

    /**
     * Erstellung Modus
     *
     * @param disabled
     */
    public FormClientOffer(boolean disabled) {
        this.disabled = disabled;
        if (!disabled) {
            disableArticle = false;
            disabledClient = false;
            disableBillPaid = true;
            disableBilled = true;
            disabledSendDate = true;
        } else {
            disableArticle = true;
            disabledClient = true;
            disableBillPaid = true;
            disableBilled = true;
            disabledSendDate = true;
        }
        configureButtons(disabled);
    }

    
    /**
     * Bei Änderung oder Schau Modus
     *
     * @param clientOffer Kundenangebot
     * @param disabled True - Schau Modus; False - Änderung Modus
     */
    public FormClientOffer(ClientOffer clientOffer, boolean disabled) {
        txtClientName = clientOffer.getClient().getName();
        txtEmployeeName = clientOffer.getEmployee().getName();
        if (clientOffer.getOfferDate() != null) {
            txtOrderDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOffer.getOfferDate());
        } else {
            txtOrderDate = "";
        }

        if (clientOffer.getSendDate() != null) {
            txtSendDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOffer.getSendDate());
            disabledClient = true;
            disabledSendDate = true;
        } else {
            txtSendDate = "Offer not sent";
            disabledClient = false;
            disabledSendDate = false;
        }

        if (clientOffer.getClientBill() != null) {
            if (clientOffer.getClientBill().getBilledDate() != null) {
                txtBilled = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOffer.getClientBill().getBilledDate());
                disableBilled = true;
                disableArticle = true;
            } else {
                txtBilled = "Not yet Billed";
                disableBilled = false;
                disableArticle = false;
            }

            if (clientOffer.getClientBill().getPaidDate() != null) {
                txtBillPaid = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOffer.getClientBill().getPaidDate());
                disableBillPaid = true;
            } else {
                txtBillPaid = "Bill not Paid";
                disableBillPaid = false;
            }
        } else {
            txtBilled = "Not yet Billed";
            txtBillPaid = "Bill not Paid";
            if (disabledSendDate) {
                disableBilled = false;
                disableBillPaid = true;
            } else {
                disableBilled = true;
                disableBillPaid = true;
            }
        }
        txtTotal = String.valueOf(clientOffer.getTotal());

        txtDescription = clientOffer.getDescription();
        this.disabled = disabled;
        if (disabled) {
            disableArticle = true;
            disabledClient = true;
            disableBillPaid = true;
            disableBilled = true;
            disabledSendDate = true;
        }
        configureButtons(disabled);
    }

    /**
     * Die Knöpfe wird vorbereitet
     *
     * @param disabled True - Schau Modus; False - Änderung oder Erstellung
     * Modus
     */
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
     * @return the txtClientName
     */
    public String getTxtClientName() {
        return txtClientName;
    }

    /**
     * @param txtClientName the txtClientName to set
     */
    public void setTxtClientName(String txtClientName) {
        this.txtClientName = txtClientName;
    }

    /**
     * @return the disabledClient
     */
    public boolean isDisabledClient() {
        return disabledClient;
    }

    /**
     * @param disabledClient the disabledClient to set
     */
    public void setDisabledClient(boolean disabledClient) {
        this.disabledClient = disabledClient;
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
     * @return the tblChooserClients
     */
    public Object[][] getTblChooserClients() {
        return tblChooserClients;
    }

    /**
     * @param tblChooserClients the tblChooserClients to set
     */
    public void setTblChooserClients(Object[][] tblChooserClients) {
        this.tblChooserClients = tblChooserClients;
    }

    /**
     * @return the txtSearchClient
     */
    public String getTxtSearchClient() {
        return txtSearchClient;
    }

    /**
     * @param txtSearchClient the txtSearchClient to set
     */
    public void setTxtSearchClient(String txtSearchClient) {
        this.txtSearchClient = txtSearchClient;
    }

    /**
     * @return the txtBilled
     */
    public String getTxtBilled() {
        return txtBilled;
    }

    /**
     * @param txtBilled the txtBilled to set
     */
    public void setTxtBilled(String txtBilled) {
        this.txtBilled = txtBilled;
    }

    /**
     * @return the disableBilled
     */
    public boolean isDisableBilled() {
        return disableBilled;
    }

    /**
     * @param disableBilled the disableBilled to set
     */
    public void setDisableBilled(boolean disableBilled) {
        this.disableBilled = disableBilled;
    }

}
