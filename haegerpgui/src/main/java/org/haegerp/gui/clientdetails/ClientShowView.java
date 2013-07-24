package org.haegerp.gui.clientdetails;

import org.haegerp.gui.ClientDetails;

public class ClientShowView implements ClientDetailsInterface {

	public void applyView(ClientDetails clientDetailsMenu) {
		clientDetailsMenu.btnCancel.setEnabled(true);
		clientDetailsMenu.btnCancel.setText("Exit");
		clientDetailsMenu.btnSaveEdit.setEnabled(true);
		clientDetailsMenu.btnSaveEdit.setText("Edit");
		
		clientDetailsMenu.txtTaxID.setValue(clientDetailsMenu.getClient().getTaxId());
		clientDetailsMenu.txtName.setText(clientDetailsMenu.getClient().getName());
		clientDetailsMenu.cbbCategory.setSelectedItem(clientDetailsMenu.getClient().getClientCategory().getName());
		clientDetailsMenu.txtAddress.setText(clientDetailsMenu.getClient().getAddress());
		clientDetailsMenu.txtCity.setText(clientDetailsMenu.getClient().getCity());
		clientDetailsMenu.txtRegion.setText(clientDetailsMenu.getClient().getRegion());
		clientDetailsMenu.txtZipCode.setText(clientDetailsMenu.getClient().getZipCode());
		clientDetailsMenu.txtCountry.setText(clientDetailsMenu.getClient().getCountry());
		clientDetailsMenu.txtEmail.setText(clientDetailsMenu.getClient().getEmail());
		clientDetailsMenu.txtPhoneNumber.setText(clientDetailsMenu.getClient().getPhoneNumber());
		clientDetailsMenu.txtMobileNumber.setText(clientDetailsMenu.getClient().getMobileNumber());
		clientDetailsMenu.txtFaxNumber.setText(clientDetailsMenu.getClient().getFaxNumber());
		clientDetailsMenu.txtDescription.setText(clientDetailsMenu.getClient().getDescription());
		
		clientDetailsMenu.txtTaxID.setEditable(false);
		clientDetailsMenu.txtName.setEditable(false);
		clientDetailsMenu.cbbCategory.setEnabled(false);
		clientDetailsMenu.txtAddress.setEditable(false);
		clientDetailsMenu.txtCity.setEditable(false);
		clientDetailsMenu.txtRegion.setEditable(false);
		clientDetailsMenu.txtZipCode.setEditable(false);
		clientDetailsMenu.txtCountry.setEditable(false);
		clientDetailsMenu.txtEmail.setEditable(false);
		clientDetailsMenu.txtPhoneNumber.setEditable(false);
		clientDetailsMenu.txtMobileNumber.setEditable(false);
		clientDetailsMenu.txtFaxNumber.setEditable(false);
		clientDetailsMenu.txtDescription.setEditable(false);
	}

	public void btnSaveEdit(ClientDetails clientDetailsMenu) {
		clientDetailsMenu.setEditMode();
	}

	public void btnCancel(ClientDetails clientDetailsMenu) {
		clientDetailsMenu.setVisible(false);
	}

}
