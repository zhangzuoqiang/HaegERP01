package org.haegerp.gui.employeedetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.entity.Employee;
import org.haegerp.gui.EmployeeDetails;
import org.haegerp.session.EmployeeSession;
import org.haegerp.tools.MD5Digest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class EmployeeNewView implements EmployeeDetailsInterface {

	public void applyView(EmployeeDetails employeeDetailsMenu) {
		employeeDetailsMenu.btnCancel.setEnabled(true);
		employeeDetailsMenu.btnCancel.setText("Cancel");
		employeeDetailsMenu.btnSaveEdit.setEnabled(true);
		employeeDetailsMenu.btnSaveEdit.setText("Save");
		
		employeeDetailsMenu.cbbSalaryCategory.setEnabled(true);
		employeeDetailsMenu.cbbUserGroup.setEnabled(true);
		employeeDetailsMenu.cbbDivision.setEnabled(true);
		
		employeeDetailsMenu.txtAddress.setEditable(true);
		employeeDetailsMenu.txtCity.setEditable(true);
		employeeDetailsMenu.txtCountry.setEditable(true);
		employeeDetailsMenu.txtEmail.setEditable(true);
		employeeDetailsMenu.txtIdCard.setEditable(true);
		employeeDetailsMenu.txtMobileNumber.setEditable(true);
		employeeDetailsMenu.txtName.setEditable(true);
		employeeDetailsMenu.txtPassword.setEditable(true);
		employeeDetailsMenu.txtPasswordConfirm.setEditable(true);
		employeeDetailsMenu.txtPhoneNumber.setEditable(true);
		employeeDetailsMenu.txtRegion.setEditable(true);
		employeeDetailsMenu.txtUsername.setEditable(true);
		employeeDetailsMenu.txtZipCode.setEditable(true);
		
		employeeDetailsMenu.cbbDivision.setSelectedIndex(0);
		employeeDetailsMenu.cbbUserGroup.setSelectedIndex(0);
		employeeDetailsMenu.cbbSalaryCategory.setSelectedIndex(0);
		
		employeeDetailsMenu.txtAddress.setText("");
		employeeDetailsMenu.txtCity.setText("");
		employeeDetailsMenu.txtCountry.setText("");
		employeeDetailsMenu.txtEmail.setText("");
		employeeDetailsMenu.txtIdCard.setValue(null);
		employeeDetailsMenu.txtMobileNumber.setText("");
		employeeDetailsMenu.txtName.setText("");
		employeeDetailsMenu.txtPassword.setText("");
		employeeDetailsMenu.txtPasswordConfirm.setText("");
		employeeDetailsMenu.txtPhoneNumber.setText("");
		employeeDetailsMenu.txtRegion.setText("");
		employeeDetailsMenu.txtUsername.setText("");
		employeeDetailsMenu.txtZipCode.setText("");
		
		employeeDetailsMenu.setEmployee(new Employee());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(EmployeeDetails employeeDetailsMenu) {
		try {
			employeeDetailsMenu.getEmployee().setIdCard((Long) employeeDetailsMenu.txtIdCard.getValue());
			employeeDetailsMenu.getEmployee().setName(employeeDetailsMenu.txtName.getText());
			
			employeeDetailsMenu.getEmployee().setSalaryCategory(employeeDetailsMenu.getSalaryCategories().get(employeeDetailsMenu.cbbSalaryCategory.getSelectedIndex()-1));
			employeeDetailsMenu.getEmployee().setDivision(employeeDetailsMenu.getDivisions().get(employeeDetailsMenu.cbbDivision.getSelectedIndex()-1));
			employeeDetailsMenu.getEmployee().setUserGroup(employeeDetailsMenu.getUserGroups().get(employeeDetailsMenu.cbbUserGroup.getSelectedIndex()-1));
			
			employeeDetailsMenu.getEmployee().setUsername(employeeDetailsMenu.txtUsername.getText());
			
			if (employeeDetailsMenu.txtPassword.getPassword().length > 0)
				employeeDetailsMenu.getEmployee().setPassword(MD5Digest.toMD5(employeeDetailsMenu.txtPassword.getPassword()));
			
			employeeDetailsMenu.getEmployee().setAddress(employeeDetailsMenu.txtAddress.getText());
			employeeDetailsMenu.getEmployee().setZipCode(employeeDetailsMenu.txtZipCode.getText());
			employeeDetailsMenu.getEmployee().setCity(employeeDetailsMenu.txtCity.getText());
			employeeDetailsMenu.getEmployee().setRegion(employeeDetailsMenu.txtRegion.getText());
			employeeDetailsMenu.getEmployee().setCountry(employeeDetailsMenu.txtCountry.getText());
			employeeDetailsMenu.getEmployee().setEmail(employeeDetailsMenu.txtEmail.getText());
			employeeDetailsMenu.getEmployee().setPhoneNumber(employeeDetailsMenu.txtPhoneNumber.getText());
			employeeDetailsMenu.getEmployee().setMobileNumber(employeeDetailsMenu.txtMobileNumber.getText());
			
			employeeDetailsMenu.getEmployee().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			employeeDetailsMenu.getEmployee().setLastModifiedDate(new Date());
			
			employeeDetailsMenu.setEmployee(employeeDetailsMenu.getEmployeeController().save(employeeDetailsMenu.getEmployee()));
			
			employeeDetailsMenu.setShowMode();
			
			employeeDetailsMenu.getEmployeeManagement().loadTable();
			employeeDetailsMenu.getSalaryCategoryManagement().loadTable();
			employeeDetailsMenu.getUserGroupManagement().loadTable();
			employeeDetailsMenu.getDivisionManagement().loadTable();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(employeeDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(EmployeeDetails employeeDetailsMenu) {
		employeeDetailsMenu.setVisible(false);
	}

}
