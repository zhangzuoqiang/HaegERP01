package org.haegerp.gui.clientcategorydetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.gui.ClientCategoryDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ClientCategoryEditView implements ClientCategoryDetailsInterface {

	public void applyView(ClientCategoryDetails clientCategoryDetailsMenu) {
		clientCategoryDetailsMenu.btnCancel.setEnabled(true);
		clientCategoryDetailsMenu.btnCancel.setText("Cancel");
		clientCategoryDetailsMenu.btnSaveEdit.setEnabled(true);
		clientCategoryDetailsMenu.btnSaveEdit.setText("Save");
		
		clientCategoryDetailsMenu.txtDescription.setEditable(true);
		clientCategoryDetailsMenu.txtCountClients.setEditable(false);
		clientCategoryDetailsMenu.txtName.setEditable(true);
		
		clientCategoryDetailsMenu.txtCountClients.setValue(clientCategoryDetailsMenu.getClientCategory().getClients().size());
		clientCategoryDetailsMenu.txtName.setText(clientCategoryDetailsMenu.getClientCategory().getName());
		clientCategoryDetailsMenu.txtDescription.setText(clientCategoryDetailsMenu.getClientCategory().getDescription());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ClientCategoryDetails clientCategoryDetailsMenu) {
		try {
			clientCategoryDetailsMenu.getClientCategory().setName(clientCategoryDetailsMenu.txtName.getText());
			
			clientCategoryDetailsMenu.getClientCategory().setDescription(clientCategoryDetailsMenu.txtDescription.getText());
			
			clientCategoryDetailsMenu.getClientCategory().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			clientCategoryDetailsMenu.getClientCategory().setLastModifiedDate(new Date());
			
			clientCategoryDetailsMenu.setClientCategory(clientCategoryDetailsMenu.getClientCategoryController().save(clientCategoryDetailsMenu.getClientCategory()));
			
			clientCategoryDetailsMenu.setShowMode();
			
			clientCategoryDetailsMenu.getClientManagement().loadCbbCategory();
			clientCategoryDetailsMenu.getClientManagement().loadTable();
			clientCategoryDetailsMenu.getClientDetails().loadCbbCategory();
			clientCategoryDetailsMenu.getClientCategoryManagement().loadTable();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(clientCategoryDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(ClientCategoryDetails clientCategoryDetailsMenu) {
		clientCategoryDetailsMenu.setShowMode();
	}

}
