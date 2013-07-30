package org.haegerp.gui.clientdetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.entity.Client;
import org.haegerp.gui.ClientDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ClientNewView implements ClientDetailsInterface {

	public void applyView(ClientDetails clientDetailsMenu) {
		clientDetailsMenu.btnCancel.setEnabled(true);
		clientDetailsMenu.btnCancel.setText("Cancel");
		clientDetailsMenu.btnSaveEdit.setEnabled(true);
		clientDetailsMenu.btnSaveEdit.setText("Save");
		
		clientDetailsMenu.txtTaxID.setEditable(true);
		clientDetailsMenu.txtTaxID.setValue(null);
		clientDetailsMenu.txtName.setEditable(true);
		clientDetailsMenu.txtName.setText("");
		clientDetailsMenu.cbbCategory.setEnabled(true);
		clientDetailsMenu.cbbCategory.setSelectedIndex(0);
		clientDetailsMenu.txtAddress.setEditable(true);
		clientDetailsMenu.txtAddress.setText("");
		clientDetailsMenu.txtCity.setEditable(true);
		clientDetailsMenu.txtCity.setText("");
		clientDetailsMenu.txtRegion.setEditable(true);
		clientDetailsMenu.txtRegion.setText("");
		clientDetailsMenu.txtZipCode.setEditable(true);
		clientDetailsMenu.txtZipCode.setText("");
		clientDetailsMenu.txtCountry.setEditable(true);
		clientDetailsMenu.txtCountry.setText("");
		clientDetailsMenu.txtEmail.setEditable(true);
		clientDetailsMenu.txtEmail.setText("");
		clientDetailsMenu.txtPhoneNumber.setEditable(true);
		clientDetailsMenu.txtPhoneNumber.setText("");
		clientDetailsMenu.txtMobileNumber.setEditable(true);
		clientDetailsMenu.txtMobileNumber.setText("");
		clientDetailsMenu.txtFaxNumber.setEditable(true);
		clientDetailsMenu.txtFaxNumber.setText("");
		clientDetailsMenu.txtDescription.setEditable(true);
		clientDetailsMenu.txtDescription.setText("");
		
		clientDetailsMenu.setClient(new Client());
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
		clientDetailsMenu.setVisible(false);
	}

}
