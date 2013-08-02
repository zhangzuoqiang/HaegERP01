package org.haegerp.gui.divisiondetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.entity.Division;
import org.haegerp.gui.DivisionDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DivisionNewView implements DivisionDetailsInterface {

	public void applyView(DivisionDetails divisionDetailsMenu) {
		divisionDetailsMenu.btnCancel.setEnabled(true);
		divisionDetailsMenu.btnCancel.setText("Cancel");
		divisionDetailsMenu.btnSaveEdit.setEnabled(true);
		divisionDetailsMenu.btnSaveEdit.setText("Save");
		
		divisionDetailsMenu.txtDescription.setEditable(true);
		divisionDetailsMenu.txtDescription.setText("");
		
		divisionDetailsMenu.txtCountArticles.setEditable(false);
		divisionDetailsMenu.txtCountArticles.setValue(null);
		
		divisionDetailsMenu.txtName.setEditable(true);
		divisionDetailsMenu.txtName.setText("");
		
		divisionDetailsMenu.setDivision(new Division());
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
			
			divisionDetailsMenu.getEmployeeManagement().loadCbbDivision();
			divisionDetailsMenu.getEmployeeManagement().loadTable();
			divisionDetailsMenu.getEmployeeDetails().loadCbbDivision();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(divisionDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(DivisionDetails divisionDetailsMenu) {
		divisionDetailsMenu.setVisible(false);
	}

}
