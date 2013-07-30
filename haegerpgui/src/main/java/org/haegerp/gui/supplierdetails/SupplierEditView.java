package org.haegerp.gui.supplierdetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.gui.SupplierDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SupplierEditView implements SupplierDetailsInterface {

	public void applyView(SupplierDetails supplierDetailsMenu) {
		supplierDetailsMenu.btnCancel.setEnabled(true);
		supplierDetailsMenu.btnCancel.setText("Cancel");
		supplierDetailsMenu.btnSaveEdit.setEnabled(true);
		supplierDetailsMenu.btnSaveEdit.setText("Save");
		
		supplierDetailsMenu.txtTaxID.setEditable(true);
		supplierDetailsMenu.txtName.setEditable(true);
		supplierDetailsMenu.txtAddress.setEditable(true);
		supplierDetailsMenu.txtCity.setEditable(true);
		supplierDetailsMenu.txtRegion.setEditable(true);
		supplierDetailsMenu.txtZipCode.setEditable(true);
		supplierDetailsMenu.txtCountry.setEditable(true);
		supplierDetailsMenu.txtEmail.setEditable(true);
		supplierDetailsMenu.txtPhoneNumber.setEditable(true);
		supplierDetailsMenu.txtMobileNumber.setEditable(true);
		supplierDetailsMenu.txtFaxNumber.setEditable(true);
		supplierDetailsMenu.txtDescription.setEditable(true);
		
		supplierDetailsMenu.txtTaxID.setValue(supplierDetailsMenu.getSupplier().getTaxId());
		supplierDetailsMenu.txtName.setText(supplierDetailsMenu.getSupplier().getName());
		supplierDetailsMenu.txtAddress.setText(supplierDetailsMenu.getSupplier().getAddress());
		supplierDetailsMenu.txtCity.setText(supplierDetailsMenu.getSupplier().getCity());
		supplierDetailsMenu.txtRegion.setText(supplierDetailsMenu.getSupplier().getRegion());
		supplierDetailsMenu.txtZipCode.setText(supplierDetailsMenu.getSupplier().getZipCode());
		supplierDetailsMenu.txtCountry.setText(supplierDetailsMenu.getSupplier().getCountry());
		supplierDetailsMenu.txtEmail.setText(supplierDetailsMenu.getSupplier().getEmail());
		supplierDetailsMenu.txtPhoneNumber.setText(supplierDetailsMenu.getSupplier().getPhoneNumber());
		supplierDetailsMenu.txtMobileNumber.setText(supplierDetailsMenu.getSupplier().getMobileNumber());
		supplierDetailsMenu.txtFaxNumber.setText(supplierDetailsMenu.getSupplier().getFaxNumber());
		supplierDetailsMenu.txtDescription.setText(supplierDetailsMenu.getSupplier().getDescription());
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
			
			supplierDetailsMenu.getSupplierManagement().loadTable();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(supplierDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(SupplierDetails supplierDetailsMenu) {
		supplierDetailsMenu.setShowMode();
	}

}
