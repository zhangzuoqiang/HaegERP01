/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haegerp.jsf.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author Wolf
 */
public class Validator {
    
    private static String REGEX_INTEGER = "^\\d{0,}$";
    private static String REGEX_NUMBER = "^\\d{0,}\\,\\d{0,}|\\d{0,}\\.\\d{0,}|\\d{0,}$";
    
    public void validateInteger(String id, int maxLength) throws ValidatorException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        InputText it = (InputText)root.findComponent(id);
        
        String value = String.valueOf(it.getValue());
        
        Pattern pattern = Pattern.compile(REGEX_INTEGER);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches())
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value is wrong", "Value is wrong"));
        
        if (value.length() > maxLength)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value is too big", "Value is too big"));
        
        try {
            Long.valueOf(value);
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value is wrong", "Value is wrong"));
        }
        
    }
    
    public void validateNumber(String id, int maxIntLength, int maxRadLength, boolean negative, Float maxValue) throws ValidatorException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        InputText it = (InputText)root.findComponent(id);
        
        String value = String.valueOf(it.getValue());
        
        Pattern pattern = Pattern.compile(REGEX_NUMBER);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches())
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value is wrong", "Value is wrong"));
        
        value = value.replace(',', '.');
        
        String[] values = value.split("\\.");
        
        if (values[0].length() > maxIntLength)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Too many integer digits", "Too many integer digits"));
        
        if (values.length == 2 && values[1].length() > maxRadLength)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Too many fraction digits", "Too many fraction digits"));
        
        float parsedValue;
        
        try {
            parsedValue = Float.valueOf(value);
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value is wrong", "Value is wrong"));
        }
        
        if (maxValue != 0.0 && parsedValue > maxValue)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value is too big", "Value is too big"));
        
        if (!negative && parsedValue < 0)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value must not be negative", "Value must not be negative"));
        
    }
    
    public void validateComboBox(String id) throws ValidatorException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot root = facesContext.getViewRoot();
        SelectOneMenu som = (SelectOneMenu)root.findComponent(id);
        
        int itemValue = Integer.valueOf(String.valueOf(som.getValue()));
        
        if (itemValue == -1)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose a Category", "Choose a Category"));
    }
}
