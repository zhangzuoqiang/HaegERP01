package org.haegerp.gui.clientdetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.gui.ClientDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ClientEditView implements ClientDetailsInterface {

	public void applyView(ClientDetails clientDetailsMenu) {
		clientDetailsMenu.btnCancel.setEnabled(true);
		clientDetailsMenu.btnCancel.setText("Cancel");
		clientDetailsMenu.btnSaveEdit.setEnabled(true);
		clientDetailsMenu.btnSaveEdit.setText("Save");
		
		clientDetailsMenu.txtTaxID.setEditable(true);
		clientDetailsMenu.txtName.setEditable(true);
		clientDetailsMenu.cbbCategory.setEnabled(true);
		clientDetailsMenu.txtAddress.setEditable(true);
		clientDetailsMenu.txtCity.setEditable(true);
		clientDetailsMenu.txtRegion.setEditable(true);
		clientDetailsMenu.txtZipCode.setEditable(true);
		clientDetailsMenu.txtCountry.setEditable(true);
		clientDetailsMenu.txtEmail.setEditable(true);
		clientDetailsMenu.txtPhoneNumber.setEditable(true);
		clientDetailsMenu.txtMobileNumber.setEditable(true);
		clientDetailsMenu.txtFaxNumber.setEditable(true);
		clientDetailsMenu.txtDescription.setEditable(true);
		
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
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ClientDetails clientDetailsMenu) {
		try {
			clientDetailsMenu.getClient().setTaxId((Long) clientDetailsMenu.txtTaxID.getValue());
			clientDetailsMenu.getClient().setName(clientDetailsMenu.txtName.getText());
			clientDetailsMenu.getClient().setClientCategory(clientDetailsMenu.getCategories().get(clientDetailsMenu.cbbCategory.getSelectedIndex()-1));
			
			clientDetailsMenu.getClient().setAddress(clientDetailsMenu.txtAddress.getText());
			clientDetailsMenu.getClient().setCity(clientDetailsMenu.txtCity.getText());
			clientDetailsMenu.getClient().setRegion(clientDetailsMenu.txtRegion.getText());
			clientDetailsMenu.getClient().setZipCode(clientDetailsMenu.txtZipCode.getText());
			clientDetailsMenu.getClient().setCountry(clientDetailsMenu.txtCountry.getText());
			
			clientDetailsMenu.getClient().setEmail(clientDetailsMenu.txtEmail.getText());
			clientDetailsMenu.getClient().setPhoneNumber(clientDetailsMenu.txtPhoneNumber.getText());
			clientDetailsMenu.getClient().setMobileNumber(clientDetailsMenu.txtMobileNumber.getText());
			clientDetailsMenu.getClient().setFaxNumber(clientDetailsMenu.txtFaxNumber.getText());
			
			clientDetailsMenu.getClient().setDescription(clientDetailsMenu.txtDescription.getText());
			
			clientDetailsMenu.getClient().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			clientDetailsMenu.getClient().setLastModifiedDate(new Date());
			
			clientDetailsMenu.setClient(clientDetailsMenu.getClientController().save(clientDetailsMenu.getClient()));
			
			clientDetailsMenu.setShowMode();
			
			clientDetailsMenu.getClientCategoryManagement().loadTable();
			clientDetailsMenu.getClientManagement().loadTable();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(clientDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(ClientDetails clientDetailsMenu) {
		clientDetailsMenu.setShowMode();
	}

}
