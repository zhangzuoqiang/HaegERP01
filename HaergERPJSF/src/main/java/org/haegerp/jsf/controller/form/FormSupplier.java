package org.haegerp.jsf.controller.form;

import org.haegerp.entity.Supplier;

/**
 * @author Wolf
 */
public class FormSupplier {
    //Geschäftspartners Name (Erforderlich)
    private String txtName;
    //Geschäftspartners Steuernummer (Erforderlich)
    private String txtTaxId;
    //Geschäftspartners Adresse (Erforderlich)
    private String txtAddress;
    //Geschäftspartners Postleitzahl (Erforderlich)
    private String txtZipCode;
    //Geschäftspartners Stadt (Erforderlich)
    private String txtCity;
    //Geschäftspartners Bundesland
    private String txtRegion;
    //Geschäftspartners Land (Erforderlich)
    private String txtCountry;
    //Geschäftspartners Email (Erforderlich)
    private String txtEmail;
    //Geschäftspartners Telefonnummer
    private String txtPhoneNumber;
    //Geschäftspartners Handynummer
    private String txtMobileNumber;
    //Geschäftspartners Faxnummer
    private String txtFaxNumber;
    //Geschäftspartners Beschreibung
    private String txtDescription;
    
    private boolean disabled;
    
    //btnEditSave
    private String btnEditSave_Name;
    
    //btnCancel
    private String btnCancel_Name;
    private boolean btnCancel_Ajax;
    
    public FormSupplier() {
    }
    
    public FormSupplier(boolean disabled) {
        this.disabled = disabled;
        configureButtons(disabled);
    }
    
    public FormSupplier(Supplier supplier, boolean disabled) {
        txtTaxId = String.valueOf(supplier.getTaxId());
        txtName = supplier.getName();
        txtAddress = supplier.getAddress();
        txtZipCode = supplier.getZipCode();
        txtCity = supplier.getCity();
        txtRegion = supplier.getRegion();
        txtCountry = supplier.getCountry();
        txtEmail = supplier.getEmail();
        txtPhoneNumber = supplier.getPhoneNumber();
        txtMobileNumber = supplier.getMobileNumber();
        txtFaxNumber = supplier.getFaxNumber();
        txtDescription = supplier.getDescription();
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
     * @return the txtName
     */
    public String getTxtName() {
        return txtName;
    }

    /**
     * @param txtName the txtName to set
     */
    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    /**
     * @return the txtTaxId
     */
    public String getTxtTaxId() {
        return txtTaxId;
    }

    /**
     * @param txtTaxId the txtTaxId to set
     */
    public void setTxtTaxId(String txtTaxId) {
        this.txtTaxId = txtTaxId;
    }

    /**
     * @return the txtAddress
     */
    public String getTxtAddress() {
        return txtAddress;
    }

    /**
     * @param txtAddress the txtAddress to set
     */
    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }

    /**
     * @return the txtZipCode
     */
    public String getTxtZipCode() {
        return txtZipCode;
    }

    /**
     * @param txtZipCode the txtZipCode to set
     */
    public void setTxtZipCode(String txtZipCode) {
        this.txtZipCode = txtZipCode;
    }

    /**
     * @return the txtCity
     */
    public String getTxtCity() {
        return txtCity;
    }

    /**
     * @param txtCity the txtCity to set
     */
    public void setTxtCity(String txtCity) {
        this.txtCity = txtCity;
    }

    /**
     * @return the txtRegion
     */
    public String getTxtRegion() {
        return txtRegion;
    }

    /**
     * @param txtRegion the txtRegion to set
     */
    public void setTxtRegion(String txtRegion) {
        this.txtRegion = txtRegion;
    }

    /**
     * @return the txtCountry
     */
    public String getTxtCountry() {
        return txtCountry;
    }

    /**
     * @param txtCountry the txtCountry to set
     */
    public void setTxtCountry(String txtCountry) {
        this.txtCountry = txtCountry;
    }

    /**
     * @return the txtEmail
     */
    public String getTxtEmail() {
        return txtEmail;
    }

    /**
     * @param txtEmail the txtEmail to set
     */
    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    /**
     * @return the txtPhoneNumber
     */
    public String getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    /**
     * @param txtPhoneNumber the txtPhoneNumber to set
     */
    public void setTxtPhoneNumber(String txtPhoneNumber) {
        this.txtPhoneNumber = txtPhoneNumber;
    }

    /**
     * @return the txtMobileNumber
     */
    public String getTxtMobileNumber() {
        return txtMobileNumber;
    }

    /**
     * @param txtMobileNumber the txtMobileNumber to set
     */
    public void setTxtMobileNumber(String txtMobileNumber) {
        this.txtMobileNumber = txtMobileNumber;
    }

    /**
     * @return the txtFaxNumber
     */
    public String getTxtFaxNumber() {
        return txtFaxNumber;
    }

    /**
     * @param txtFaxNumber the txtFaxNumber to set
     */
    public void setTxtFaxNumber(String txtFaxNumber) {
        this.txtFaxNumber = txtFaxNumber;
    }
}
