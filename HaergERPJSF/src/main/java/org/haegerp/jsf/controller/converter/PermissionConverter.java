package org.haegerp.jsf.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.haegerp.entity.Permission;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

/**
 * Dieses Converter ist für die Component "p:pickList" von PrimeFaces
 * 
 * @author Fábio Codinha
 */
@FacesConverter(forClass = Permission.class, value = "permissionConverter")
public class PermissionConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent component, String value) {
        PickList  p = (PickList) component;
        DualListModel dl = (DualListModel) p.getValue();
        return dl.getSource().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent component, Object value) {
        PickList  p = (PickList) component;
        DualListModel dl = (DualListModel) p.getValue();
        return String.valueOf(dl.getSource().indexOf(value));
    }
    
}
