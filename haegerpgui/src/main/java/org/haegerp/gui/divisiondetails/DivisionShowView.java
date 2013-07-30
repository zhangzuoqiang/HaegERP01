package org.haegerp.gui.divisiondetails;

import org.haegerp.gui.DivisionDetails;

public class DivisionShowView implements DivisionDetailsInterface {

	public void applyView(DivisionDetails divisionDetailsMenu) {
		divisionDetailsMenu.btnCancel.setEnabled(true);
		divisionDetailsMenu.btnCancel.setText("Exit");
		divisionDetailsMenu.btnSaveEdit.setEnabled(true);
		divisionDetailsMenu.btnSaveEdit.setText("Edit");
		
		divisionDetailsMenu.txtCountArticles.setValue(divisionDetailsMenu.getDivision().getEmployees().size());
		divisionDetailsMenu.txtName.setText(divisionDetailsMenu.getDivision().getName());
		divisionDetailsMenu.txtDescription.setText(divisionDetailsMenu.getDivision().getDescription());
		
		divisionDetailsMenu.txtDescription.setEditable(false);
		divisionDetailsMenu.txtCountArticles.setEditable(false);
		divisionDetailsMenu.txtName.setEditable(false);
	}

	public void btnSaveEdit(DivisionDetails divisionDetailsMenu) {
		divisionDetailsMenu.setEditMode();
	}

	public void btnCancel(DivisionDetails divisionDetailsMenu) {
		divisionDetailsMenu.setVisible(false);
	}

}
