package org.haegerp.jsf.controller.form;

import org.haegerp.entity.Article;

/**
 *
 * @author Fabio Codinha
 */
public class FormArticle {
    //Artikels EAN (Erforderlich)

    private String txtEan;
    //Artikels Name (Erforderlich)
    private String txtName;
    //ID-Kategorie des Artikel
    private int cbValue;
    //Artikels MwSt Preis (Erforderlich)
    private String txtPriceVat;
    //Artikels Bruttopreis (Erforderlich)
    private String txtPriceGross;
    //Artikels Liferantpreis (Erforderlich)
    private String txtPriceSupplier;
    //Artikels Lager
    private String txtStock;
    //Artikels Farbe
    private String txtColor;
    //Artikels Höhegröße
    private String txtSizeH;
    //Artikels Längegröße
    private String txtSizeL;
    //Artikels Breitegröße
    private String txtSizeW;
    //Artikels Beschreibung
    private String txtDescription;
    private boolean disabled;
    //btnEditSave
    private String btnEditSave_Name;
    //btnCancel
    private String btnCancel_Name;
    private boolean btnCancel_Ajax;

    public FormArticle() {
    }

    /**
     * Erstellung Modus
     *
     * @param disabled
     */
    public FormArticle(boolean disabled) {
        this.disabled = disabled;
        configureButtons(disabled);
    }

    /**
     * Bei Änderung oder Schau Modus
     *
     * @param article Artikel
     * @param disabled True - Schau Modus; False - Änderung Modus
     */
    public FormArticle(Article article, boolean disabled) {
        txtEan = String.valueOf(article.getEan());
        txtName = article.getName();
        cbValue = Integer.valueOf(String.valueOf(article.getArticleCategory().getIdArticleCategory()));
        txtPriceVat = String.valueOf(article.getPriceVat() * 100);
        txtPriceGross = String.valueOf(article.getPriceGross());
        txtPriceSupplier = String.valueOf(article.getPriceSupplier());
        txtStock = String.valueOf(article.getStock());
        txtColor = article.getColor();
        txtSizeH = String.valueOf(article.getSizeH());
        txtSizeL = String.valueOf(article.getSizeL());
        txtSizeW = String.valueOf(article.getSizeW());
        txtDescription = article.getDescription();
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
     * @return the txtEan
     */
    public String getTxtEan() {
        return txtEan;
    }

    /**
     * @param txtEan the txtEan to set
     */
    public void setTxtEan(String txtEan) {
        this.txtEan = txtEan;
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
     * @return the txtPriceVat
     */
    public String getTxtPriceVat() {
        return txtPriceVat;
    }

    /**
     * @param txtPriceVat the txtPriceVat to set
     */
    public void setTxtPriceVat(String txtPriceVat) {
        this.txtPriceVat = txtPriceVat;
    }

    /**
     * @return the txtPriceGross
     */
    public String getTxtPriceGross() {
        return txtPriceGross;
    }

    /**
     * @param txtPriceGross the txtPriceGross to set
     */
    public void setTxtPriceGross(String txtPriceGross) {
        this.txtPriceGross = txtPriceGross;
    }

    /**
     * @return the txtPriceSupplier
     */
    public String getTxtPriceSupplier() {
        return txtPriceSupplier;
    }

    /**
     * @param txtPriceSupplier the txtPriceSupplier to set
     */
    public void setTxtPriceSupplier(String txtPriceSupplier) {
        this.txtPriceSupplier = txtPriceSupplier;
    }

    /**
     * @return the txtStock
     */
    public String getTxtStock() {
        return txtStock;
    }

    /**
     * @param txtStock the txtStock to set
     */
    public void setTxtStock(String txtStock) {
        this.txtStock = txtStock;
    }

    /**
     * @return the txtColor
     */
    public String getTxtColor() {
        return txtColor;
    }

    /**
     * @param txtColor the txtColor to set
     */
    public void setTxtColor(String txtColor) {
        this.txtColor = txtColor;
    }

    /**
     * @return the txtSizeH
     */
    public String getTxtSizeH() {
        return txtSizeH;
    }

    /**
     * @param txtSizeH the txtSizeH to set
     */
    public void setTxtSizeH(String txtSizeH) {
        this.txtSizeH = txtSizeH;
    }

    /**
     * @return the txtSizeL
     */
    public String getTxtSizeL() {
        return txtSizeL;
    }

    /**
     * @param txtSizeL the txtSizeL to set
     */
    public void setTxtSizeL(String txtSizeL) {
        this.txtSizeL = txtSizeL;
    }

    /**
     * @return the txtSizeW
     */
    public String getTxtSizeW() {
        return txtSizeW;
    }

    /**
     * @param txtSizeW the txtSizeW to set
     */
    public void setTxtSizeW(String txtSizeW) {
        this.txtSizeW = txtSizeW;
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
     * @return the cbValue
     */
    public int getCbValue() {
        return cbValue;
    }

    /**
     * @param cbValue the cbValue to set
     */
    public void setCbValue(int cbValue) {
        this.cbValue = cbValue;
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
}
