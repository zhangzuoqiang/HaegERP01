package org.haegerp.gui.salarycategorydetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.gui.SalaryCategoryDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SalaryCategoryEditView implements SalaryCategoryDetailsInterface {

	public void applyView(SalaryCategoryDetails salaryCategoryDetailsMenu) {
		salaryCategoryDetailsMenu.btnCancel.setEnabled(true);
		salaryCategoryDetailsMenu.btnCancel.setText("Cancel");
		salaryCategoryDetailsMenu.btnSaveEdit.setEnabled(true);
		salaryCategoryDetailsMenu.btnSaveEdit.setText("Save");
		
		salaryCategoryDetailsMenu.txtDescription.setEditable(true);
		salaryCategoryDetailsMenu.txtCountArticles.setEditable(false);
		salaryCategoryDetailsMenu.txtSalaryFrom.setEditable(true);
		salaryCategoryDetailsMenu.txtSalaryTo.setEditable(true);
		
		salaryCategoryDetailsMenu.txtCountArticles.setValue(salaryCategoryDetailsMenu.getSalaryCategory().getEmployees().size());
		salaryCategoryDetailsMenu.txtSalaryFrom.setValue(salaryCategoryDetailsMenu.getSalaryCategory().getSalaryFrom());
		salaryCategoryDetailsMenu.txtSalaryTo.setValue(salaryCategoryDetailsMenu.getSalaryCategory().getSalaryTo());
		salaryCategoryDetailsMenu.txtDescription.setText(salaryCategoryDetailsMenu.getSalaryCategory().getDescription());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(SalaryCategoryDetails salaryCategoryDetailsMenu) {
		try {
			salaryCategoryDetailsMenu.getSalaryCategory().setSalaryFrom((Float) salaryCategoryDetailsMenu.txtSalaryFrom.getValue());
			salaryCategoryDetailsMenu.getSalaryCategory().setSalaryTo((Float) salaryCategoryDetailsMenu.txtSalaryTo.getValue());
			
			salaryCategoryDetailsMenu.getSalaryCategory().setDescription(salaryCategoryDetailsMenu.txtDescription.getText());
			
			salaryCategoryDetailsMenu.getSalaryCategory().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			salaryCategoryDetailsMenu.getSalaryCategory().setLastModifiedDate(new Date());
			
			salaryCategoryDetailsMenu.setSalaryCategory(salaryCategoryDetailsMenu.getSalaryCategoryController().save(salaryCategoryDetailsMenu.getSalaryCategory()));
			
			salaryCategoryDetailsMenu.setShowMode();
			
			salaryCategoryDetailsMenu.getSalaryCategoryManagement().loadTable();
			//TODO EmployeManagement & Details
//			salaryCategoryDetailsMenu.getEmployeeManagement().loadCbbCategory();
//			salaryCategoryDetailsMenu.getEmployeeManagement().loadTable();
//			salaryCategoryDetailsMenu.getEmployeeDetails().loadCbbCategory();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(salaryCategoryDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(SalaryCategoryDetails salaryCategoryDetailsMenu) {
		salaryCategoryDetailsMenu.setShowMode();
	}

}
