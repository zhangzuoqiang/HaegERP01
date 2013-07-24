package org.haegerp.gui.clientcategorydetails;

import org.haegerp.gui.ClientCategoryDetails;

public class ClientCategoryShowView implements ClientCategoryDetailsInterface {

	public void applyView(ClientCategoryDetails clientCategoryDetailsMenu) {
		clientCategoryDetailsMenu.btnCancel.setEnabled(true);
		clientCategoryDetailsMenu.btnCancel.setText("Exit");
		clientCategoryDetailsMenu.btnSaveEdit.setEnabled(true);
		clientCategoryDetailsMenu.btnSaveEdit.setText("Edit");
		
		clientCategoryDetailsMenu.txtCountClients.setValue(clientCategoryDetailsMenu.getClientCategory().getClients().size());
		clientCategoryDetailsMenu.txtName.setText(clientCategoryDetailsMenu.getClientCategory().getName());
		clientCategoryDetailsMenu.txtDescription.setText(clientCategoryDetailsMenu.getClientCategory().getDescription());
		
		clientCategoryDetailsMenu.txtDescription.setEditable(false);
		clientCategoryDetailsMenu.txtCountClients.setEditable(false);
		clientCategoryDetailsMenu.txtName.setEditable(false);
	}

	public void btnSaveEdit(ClientCategoryDetails clientCategoryDetailsMenu) {
		clientCategoryDetailsMenu.setEditMode();
	}

	public void btnCancel(ClientCategoryDetails clientCategoryDetailsMenu) {
		clientCategoryDetailsMenu.setVisible(false);
	}

}
