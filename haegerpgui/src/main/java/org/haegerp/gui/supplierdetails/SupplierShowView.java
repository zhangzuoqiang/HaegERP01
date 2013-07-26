package org.haegerp.gui.supplierdetails;

import org.haegerp.gui.SupplierDetails;

public class SupplierShowView implements SupplierDetailsInterface {

	public void applyView(SupplierDetails supplierDetailsMenu) {
		supplierDetailsMenu.btnCancel.setEnabled(true);
		supplierDetailsMenu.btnCancel.setText("Exit");
		supplierDetailsMenu.btnSaveEdit.setEnabled(true);
		supplierDetailsMenu.btnSaveEdit.setText("Edit");
		
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
		
		supplierDetailsMenu.txtTaxID.setEditable(false);
		supplierDetailsMenu.txtName.setEditable(false);
		supplierDetailsMenu.txtAddress.setEditable(false);
		supplierDetailsMenu.txtCity.setEditable(false);
		supplierDetailsMenu.txtRegion.setEditable(false);
		supplierDetailsMenu.txtZipCode.setEditable(false);
		supplierDetailsMenu.txtCountry.setEditable(false);
		supplierDetailsMenu.txtEmail.setEditable(false);
		supplierDetailsMenu.txtPhoneNumber.setEditable(false);
		supplierDetailsMenu.txtMobileNumber.setEditable(false);
		supplierDetailsMenu.txtFaxNumber.setEditable(false);
		supplierDetailsMenu.txtDescription.setEditable(false);
	}

	public void btnSaveEdit(SupplierDetails supplierDetailsMenu) {
		supplierDetailsMenu.setEditMode();
	}

	public void btnCancel(SupplierDetails supplierDetailsMenu) {
		supplierDetailsMenu.setVisible(false);
	}

}
