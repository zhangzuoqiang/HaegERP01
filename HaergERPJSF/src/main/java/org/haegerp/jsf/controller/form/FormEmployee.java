package org.haegerp.jsf.controller.form;

import org.haegerp.entity.Employee;

/**
 * @author Fabio Codinha
 */
public class FormEmployee {
    //Name vom Mitarbeiter (Erforderlich)

    private String txtName;
    //Ausweisnummer vom Mitarbeiter (Erforderlich)
    private String txtIdCard;
    //(Many-To-One) Kategorie, dass Kunde gehört (Erforderlich)
    private long cbSalaryCategory;
    //(Many-To-One) Division, dass Mitarbeiter gehört (Erforderlich)
    private long cbDivision;
    //(Many-To-One) Benutzergruppe, dass Mitarbeiter gehört (Erforderlich)
    private long cbUserGroup;
    //Adresse vom Mitarbeiter (Erforderlich)
    private String txtAddress;
    //Postleitzahl von der Adresse des Mitarbeiter (Erforderlich)
    private String txtZipCode;
    //Stadt von der Adresse des Mitarbeiter (Erforderlich)
    private String txtCity;
    //Bundesland von der Adresse des Mitarbeiter
    private String txtRegion;
    //Land vom Mitarbeiter (Erforderlich)
    private String txtCountry;
    //Email vom Mitarbeiter (Erforderlich)
    private String txtEmail;
    //Telefonnummer vom Mitarbeiter
    private String txtPhoneNumber;
    //Handynummer vom Mitarbeiter
    private String txtMobileNumber;
    //Benutzername (Erforderlich)
    private String txtUsername;
    //Kenntwort (Erforderlich)
    private String txtPassword;
    //Kenntwort Bestätigung (Erforderlich)
    private String txtConfirmPassword;
    private boolean disabled;
    //Muss der Benutzer ein kennwort einfügen?
    private boolean required;
    //btnEditSave
    private String btnEditSave_Name;
    //btnCancel
    private String btnCancel_Name;
    private boolean btnCancel_Ajax;

    public FormEmployee() {
    }

    /**
     * Erstellung Modus
     *
     * @param disabled
     * @param required Muss der Benutzer ein kennwort einfügen?
     */
    public FormEmployee(boolean disabled, boolean required) {
        this.disabled = disabled;
        this.required = required;
        configureButtons(disabled);
    }
    
    /**
     * Bei Änderung oder Schau Modus
     *
     * @param employee Mitarbeiter
     * @param disabled True - Schau Modus; False - Änderung Modus
     * @param required Muss der Benutzer ein kennwort einfügen?
     */
    public FormEmployee(Employee employee, boolean disabled, boolean required) {
        txtIdCard = String.valueOf(employee.getIdCard());
        txtName = employee.getName();
        txtUsername = employee.getUsername();
        cbDivision = Integer.valueOf(String.valueOf(employee.getDivision().getIdDivision()));
        cbSalaryCategory = Integer.valueOf(String.valueOf(employee.getSalaryCategory().getIdSalaryCategory()));
        cbUserGroup = Integer.valueOf(String.valueOf(employee.getUserGroup().getIdUserGroup()));
        txtAddress = employee.getAddress();
        txtZipCode = employee.getZipCode();
        txtCity = employee.getCity();
        txtRegion = employee.getRegion();
        txtCountry = employee.getCountry();
        txtEmail = employee.getEmail();
        txtPhoneNumber = employee.getPhoneNumber();
        txtMobileNumber = employee.getMobileNumber();
        this.disabled = disabled;
        this.required = required;
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
     * @return the txtIdCard
     */
    public String getTxtIdCard() {
        return txtIdCard;
    }

    /**
     * @param txtIdCard the txtIdCard to set
     */
    public void setTxtIdCard(String txtIdCard) {
        this.txtIdCard = txtIdCard;
    }

    /**
     * @return the cbSalaryCategory
     */
    public long getCbSalaryCategory() {
        return cbSalaryCategory;
    }

    /**
     * @param cbSalaryCategory the cbSalaryCategory to set
     */
    public void setCbSalaryCategory(long cbSalaryCategory) {
        this.cbSalaryCategory = cbSalaryCategory;
    }

    /**
     * @return the cbDivision
     */
    public long getCbDivision() {
        return cbDivision;
    }

    /**
     * @param cbDivision the cbDivision to set
     */
    public void setCbDivision(long cbDivision) {
        this.cbDivision = cbDivision;
    }

    /**
     * @return the cbUserGroup
     */
    public long getCbUserGroup() {
        return cbUserGroup;
    }

    /**
     * @param cbUserGroup the cbUserGroup to set
     */
    public void setCbUserGroup(long cbUserGroup) {
        this.cbUserGroup = cbUserGroup;
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
     * @return the txtUsername
     */
    public String getTxtUsername() {
        return txtUsername;
    }

    /**
     * @param txtUsername the txtUsername to set
     */
    public void setTxtUsername(String txtUsername) {
        this.txtUsername = txtUsername;
    }

    /**
     * @return the txtPassword
     */
    public String getTxtPassword() {
        return txtPassword;
    }

    /**
     * @param txtPassword the txtPassword to set
     */
    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    /**
     * @return the txtConfirmPassword
     */
    public String getTxtConfirmPassword() {
        return txtConfirmPassword;
    }

    /**
     * @param txtConfirmPassword the txtConfirmPassword to set
     */
    public void setTxtConfirmPassword(String txtConfirmPassword) {
        this.txtConfirmPassword = txtConfirmPassword;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }
}
