package org.haegerp.gui.employeedetails;

import org.haegerp.gui.EmployeeDetails;

public class EmployeeShowView implements EmployeeDetailsInterface {

	public void applyView(EmployeeDetails employeeDetailsMenu) {
		employeeDetailsMenu.btnCancel.setEnabled(true);
		employeeDetailsMenu.btnCancel.setText("Exit");
		employeeDetailsMenu.btnSaveEdit.setEnabled(true);
		employeeDetailsMenu.btnSaveEdit.setText("Edit");
		
		employeeDetailsMenu.cbbDivision.setSelectedItem(employeeDetailsMenu.getEmployee().getDivision().getName());
		employeeDetailsMenu.cbbUserGroup.setSelectedItem(employeeDetailsMenu.getEmployee().getUserGroup().getName());
		employeeDetailsMenu.cbbSalaryCategory.setSelectedItem(employeeDetailsMenu.getEmployee().getSalaryCategory().getSalaryFrom() + " - " + employeeDetailsMenu.getEmployee().getSalaryCategory().getSalaryTo());
		
		employeeDetailsMenu.cbbSalaryCategory.setEnabled(false);
		employeeDetailsMenu.cbbUserGroup.setEnabled(false);
		employeeDetailsMenu.cbbDivision.setEnabled(false);
		
		employeeDetailsMenu.txtAddress.setText(employeeDetailsMenu.getEmployee().getAddress());
		employeeDetailsMenu.txtCity.setText(employeeDetailsMenu.getEmployee().getCity());
		employeeDetailsMenu.txtCountry.setText(employeeDetailsMenu.getEmployee().getCountry());
		employeeDetailsMenu.txtEmail.setText(employeeDetailsMenu.getEmployee().getEmail());
		employeeDetailsMenu.txtIdCard.setValue(employeeDetailsMenu.getEmployee().getIdCard());
		employeeDetailsMenu.txtMobileNumber.setText(employeeDetailsMenu.getEmployee().getMobileNumber());
		employeeDetailsMenu.txtName.setText(employeeDetailsMenu.getEmployee().getName());
		employeeDetailsMenu.txtPassword.setText("");
		employeeDetailsMenu.txtPasswordConfirm.setText("");
		employeeDetailsMenu.txtPhoneNumber.setText(employeeDetailsMenu.getEmployee().getPhoneNumber());
		employeeDetailsMenu.txtRegion.setText(employeeDetailsMenu.getEmployee().getRegion());
		employeeDetailsMenu.txtUsername.setText(employeeDetailsMenu.getEmployee().getUsername());
		employeeDetailsMenu.txtZipCode.setText(employeeDetailsMenu.getEmployee().getZipCode());
		
		employeeDetailsMenu.txtAddress.setEditable(false);
		employeeDetailsMenu.txtCity.setEditable(false);
		employeeDetailsMenu.txtCountry.setEditable(false);
		employeeDetailsMenu.txtEmail.setEditable(false);
		employeeDetailsMenu.txtIdCard.setEditable(false);
		employeeDetailsMenu.txtMobileNumber.setEditable(false);
		employeeDetailsMenu.txtName.setEditable(false);
		employeeDetailsMenu.txtPassword.setEditable(false);
		employeeDetailsMenu.txtPasswordConfirm.setEditable(false);
		employeeDetailsMenu.txtPhoneNumber.setEditable(false);
		employeeDetailsMenu.txtRegion.setEditable(false);
		employeeDetailsMenu.txtUsername.setEditable(false);
		employeeDetailsMenu.txtZipCode.setEditable(false);
	}

	public void btnSaveEdit(EmployeeDetails employeeDetailsMenu) {
		employeeDetailsMenu.setEditMode();
	}

	public void btnCancel(EmployeeDetails employeeDetailsMenu) {
		employeeDetailsMenu.setVisible(false);
	}

}
