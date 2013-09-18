package org.haegerp.jsf.controller.form;

import org.haegerp.entity.SalaryCategory;

/**
 *
 * @author Fabio Codinha
 */
public class FormSalaryCategory {
    //Gehalt von X (Erforderlich)
    private String txtSalaryFrom;
    //... Bis Y (Erforderlich)
    private String txtSalaryTo;
    //Beschreibung der Kundenkategorie
    private String txtDescription;
    
    private boolean disabled;
    
    //btnEditSave
    private String btnEditSave_Name;
    
    //btnCancel
    private String btnCancel_Name;
    private boolean btnCancel_Ajax;
    
    public FormSalaryCategory() {
    }
    
    /**
     * Erstellung Modus
     *
     * @param disabled
     */
    public FormSalaryCategory(boolean disabled) {
        this.disabled = disabled;
        configureButtons(disabled);
    }
    
    /**
     * Bei Änderung oder Schau Modus
     *
     * @param salaryCategory Gehaltkategorie
     * @param disabled True - Schau Modus; False - Änderung Modus
     */
    public FormSalaryCategory(SalaryCategory salaryCategory, boolean disabled) {
        txtSalaryFrom = String.valueOf(salaryCategory.getSalaryFrom());
        txtSalaryTo = String.valueOf(salaryCategory.getSalaryTo());
        txtDescription = salaryCategory.getDescription();
        this.disabled = disabled;
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
     * @return the txtSalaryFrom
     */
    public String getTxtSalaryFrom() {
        return txtSalaryFrom;
    }

    /**
     * @param txtSalaryFrom the txtSalaryFrom to set
     */
    public void setTxtSalaryFrom(String txtSalaryFrom) {
        this.txtSalaryFrom = txtSalaryFrom;
    }

    /**
     * @return the txtSalaryTo
     */
    public String getTxtSalaryTo() {
        return txtSalaryTo;
    }

    /**
     * @param txtSalaryTo the txtSalaryTo to set
     */
    public void setTxtSalaryTo(String txtSalaryTo) {
        this.txtSalaryTo = txtSalaryTo;
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

}
