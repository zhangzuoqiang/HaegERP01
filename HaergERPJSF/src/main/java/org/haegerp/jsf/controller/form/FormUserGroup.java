package org.haegerp.jsf.controller.form;

import java.util.ArrayList;
import java.util.List;
import org.haegerp.entity.Permission;
import org.haegerp.entity.UserGroup;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Fabio Codinha
 */
public class FormUserGroup {
    //Name der Benutzergruppe (Erforderlich)

    private String txtName;
    //Beschreibung der Benutzergruppe
    private String txtDescription;
    //PickList Source
    private List<Permission> source = new ArrayList<Permission>();
    //PickList Destination
    private List<Permission> destination = new ArrayList<Permission>();
    //PickList DualList
    private DualListModel<Permission> permissions;
    private boolean disabled;
    //btnEditSave
    private String btnEditSave_Name;
    //btnCancel
    private String btnCancel_Name;
    private boolean btnCancel_Ajax;

    public FormUserGroup() {
    }

    /**
     * Erstellung Modus
     * @param disabled
     * @param allPermissions Alle Erlaubnise
     */
    public FormUserGroup(boolean disabled, List<Permission> allPermissions) {
        this.source = allPermissions;
        this.destination = new ArrayList<Permission>();
        this.permissions = new DualListModel<Permission>(this.source, this.destination);

        this.disabled = disabled;
        configureButtons(disabled);
    }

    /**
     * Bei Änderung oder Schau Modus
     * @param userGroup Benutzergruppe
     * @param disabled True - Schau Modus; False - Änderung Modus
     * @param allPermissions Alle Erlaubnise
     */
    public FormUserGroup(UserGroup userGroup, boolean disabled, List<Permission> allPermissions) {
        txtName = userGroup.getName();
        txtDescription = userGroup.getDescription();

        this.source = allPermissions;
        this.destination = new ArrayList<Permission>();

        for (Permission permission : userGroup.getPermissions()) {
            this.source.remove(permission);
            this.destination.add(permission);
        }

        this.permissions = new DualListModel<Permission>(this.source, this.destination);

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
     * @return the source
     */
    public List<Permission> getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(List<Permission> source) {
        this.source = source;
    }

    /**
     * @return the destination
     */
    public List<Permission> getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(List<Permission> destination) {
        this.destination = destination;
    }

    /**
     * @return the permissions
     */
    public DualListModel<Permission> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(DualListModel<Permission> permissions) {
        this.permissions = permissions;
    }
}
