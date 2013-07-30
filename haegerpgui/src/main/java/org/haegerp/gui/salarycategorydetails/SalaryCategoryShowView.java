package org.haegerp.gui.salarycategorydetails;

import org.haegerp.gui.SalaryCategoryDetails;

public class SalaryCategoryShowView implements SalaryCategoryDetailsInterface {

	public void applyView(SalaryCategoryDetails salaryCategoryDetailsMenu) {
		salaryCategoryDetailsMenu.btnCancel.setEnabled(true);
		salaryCategoryDetailsMenu.btnCancel.setText("Exit");
		salaryCategoryDetailsMenu.btnSaveEdit.setEnabled(true);
		salaryCategoryDetailsMenu.btnSaveEdit.setText("Edit");
		
		salaryCategoryDetailsMenu.txtCountArticles.setValue(salaryCategoryDetailsMenu.getSalaryCategory().getEmployees().size());
		salaryCategoryDetailsMenu.txtSalaryFrom.setValue(salaryCategoryDetailsMenu.getSalaryCategory().getSalaryFrom());
		salaryCategoryDetailsMenu.txtSalaryTo.setValue(salaryCategoryDetailsMenu.getSalaryCategory().getSalaryTo());
		salaryCategoryDetailsMenu.txtDescription.setText(salaryCategoryDetailsMenu.getSalaryCategory().getDescription());
		
		salaryCategoryDetailsMenu.txtDescription.setEditable(false);
		salaryCategoryDetailsMenu.txtCountArticles.setEditable(false);
		salaryCategoryDetailsMenu.txtSalaryFrom.setEditable(false);
		salaryCategoryDetailsMenu.txtSalaryTo.setEditable(false);
	}

	public void btnSaveEdit(SalaryCategoryDetails salaryCategoryDetailsMenu) {
		salaryCategoryDetailsMenu.setEditMode();
	}

	public void btnCancel(SalaryCategoryDetails salaryCategoryDetailsMenu) {
		salaryCategoryDetailsMenu.setVisible(false);
	}

}
