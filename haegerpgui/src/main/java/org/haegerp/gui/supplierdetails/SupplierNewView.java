package org.haegerp.gui.supplierdetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.entity.Supplier;
import org.haegerp.gui.SupplierDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SupplierNewView implements SupplierDetailsInterface {

	public void applyView(SupplierDetails supplierDetailsMenu) {
		supplierDetailsMenu.btnCancel.setEnabled(true);
		supplierDetailsMenu.btnCancel.setText("Cancel");
		supplierDetailsMenu.btnSaveEdit.setEnabled(true);
		supplierDetailsMenu.btnSaveEdit.setText("Save");
		
		supplierDetailsMenu.txtTaxID.setEditable(true);
		supplierDetailsMenu.txtTaxID.setValue(null);
		supplierDetailsMenu.txtName.setEditable(true);
		supplierDetailsMenu.txtName.setText("");
		supplierDetailsMenu.txtAddress.setEditable(true);
		supplierDetailsMenu.txtAddress.setText("");
		supplierDetailsMenu.txtCity.setEditable(true);
		supplierDetailsMenu.txtCity.setText("");
		supplierDetailsMenu.txtRegion.setEditable(true);
		supplierDetailsMenu.txtRegion.setText("");
		supplierDetailsMenu.txtZipCode.setEditable(true);
		supplierDetailsMenu.txtZipCode.setText("");
		supplierDetailsMenu.txtCountry.setEditable(true);
		supplierDetailsMenu.txtCountry.setText("");
		supplierDetailsMenu.txtEmail.setEditable(true);
		supplierDetailsMenu.txtEmail.setText("");
		supplierDetailsMenu.txtPhoneNumber.setEditable(true);
		supplierDetailsMenu.txtPhoneNumber.setText("");
		supplierDetailsMenu.txtMobileNumber.setEditable(true);
		supplierDetailsMenu.txtMobileNumber.setText("");
		supplierDetailsMenu.txtFaxNumber.setEditable(true);
		supplierDetailsMenu.txtFaxNumber.setText("");
		supplierDetailsMenu.txtDescription.setEditable(true);
		supplierDetailsMenu.txtDescription.setText("");
		
		supplierDetailsMenu.setSupplier(new Supplier());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(SupplierDetails supplierDetailsMenu) {
		try {
			supplierDetailsMenu.getSupplier().setTaxId((Long) supplierDetailsMenu.txtTaxID.getValue());
			supplierDetailsMenu.getSupplier().setName(supplierDetailsMenu.txtName.getText());
			
			supplierDetailsMenu.getSupplier().setAddress(supplierDetailsMenu.txtAddress.getText());
			supplierDetailsMenu.getSupplier().setCity(supplierDetailsMenu.txtCity.getText());
			supplierDetailsMenu.getSupplier().setRegion(supplierDetailsMenu.txtRegion.getText());
			supplierDetailsMenu.getSupplier().setZipCode(supplierDetailsMenu.txtZipCode.getText());
			supplierDetailsMenu.getSupplier().setCountry(supplierDetailsMenu.txtCountry.getText());
			
			supplierDetailsMenu.getSupplier().setEmail(supplierDetailsMenu.txtEmail.getText());
			supplierDetailsMenu.getSupplier().setPhoneNumber(supplierDetailsMenu.txtPhoneNumber.getText());
			supplierDetailsMenu.getSupplier().setMobileNumber(supplierDetailsMenu.txtMobileNumber.getText());
			supplierDetailsMenu.getSupplier().setFaxNumber(supplierDetailsMenu.txtFaxNumber.getText());
			
			supplierDetailsMenu.getSupplier().setDescription(supplierDetailsMenu.txtDescription.getText());
			
			supplierDetailsMenu.getSupplier().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			supplierDetailsMenu.getSupplier().setLastModifiedDate(new Date());
			
			supplierDetailsMenu.setSupplier(supplierDetailsMenu.getSupplierController().save(supplierDetailsMenu.getSupplier()));
			
			supplierDetailsMenu.setShowMode();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(supplierDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(SupplierDetails supplierDetailsMenu) {
		supplierDetailsMenu.setVisible(false);
	}

}
