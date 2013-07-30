package org.haegerp.gui.divisiondetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.gui.DivisionDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DivisionEditView implements DivisionDetailsInterface {

	public void applyView(DivisionDetails divisionDetailsMenu) {
		divisionDetailsMenu.btnCancel.setEnabled(true);
		divisionDetailsMenu.btnCancel.setText("Cancel");
		divisionDetailsMenu.btnSaveEdit.setEnabled(true);
		divisionDetailsMenu.btnSaveEdit.setText("Save");
		
		divisionDetailsMenu.txtDescription.setEditable(true);
		divisionDetailsMenu.txtCountArticles.setEditable(false);
		divisionDetailsMenu.txtName.setEditable(true);
		
		divisionDetailsMenu.txtCountArticles.setValue(divisionDetailsMenu.getDivision().getEmployees().size());
		divisionDetailsMenu.txtName.setText(divisionDetailsMenu.getDivision().getName());
		divisionDetailsMenu.txtDescription.setText(divisionDetailsMenu.getDivision().getDescription());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(DivisionDetails divisionDetailsMenu) {
		try {
			divisionDetailsMenu.getDivision().setName(divisionDetailsMenu.txtName.getText());
			
			divisionDetailsMenu.getDivision().setDescription(divisionDetailsMenu.txtDescription.getText());
			
			divisionDetailsMenu.getDivision().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			divisionDetailsMenu.getDivision().setLastModifiedDate(new Date());
			
			divisionDetailsMenu.setDivision(divisionDetailsMenu.getDivisionController().save(divisionDetailsMenu.getDivision()));
			
			divisionDetailsMenu.setShowMode();
			
			divisionDetailsMenu.getDivisionManagement().loadTable();
			//TODO EmployeManagement & Details
//			divisionDetailsMenu.getEmployeeManagement().loadCbbCategory();
//			divisionDetailsMenu.getEmployeeManagement().loadTable();
//			divisionDetailsMenu.getEmployeeDetails().loadCbbCategory();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(divisionDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(DivisionDetails divisionDetailsMenu) {
		divisionDetailsMenu.setShowMode();
	}

}
