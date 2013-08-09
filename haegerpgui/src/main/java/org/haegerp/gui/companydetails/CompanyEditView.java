package org.haegerp.gui.companydetails;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.gui.CompanyDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CompanyEditView implements CompanyDetailsInterface {

	public void applyView(CompanyDetails companyDetailsMenu) {
		companyDetailsMenu.btnCancel.setEnabled(true);
		companyDetailsMenu.btnCancel.setText("Cancel");
		companyDetailsMenu.btnSaveEdit.setEnabled(true);
		companyDetailsMenu.btnSaveEdit.setText("Save");
		
		companyDetailsMenu.txtTaxID.setEditable(true);
		companyDetailsMenu.txtName.setEditable(true);
		companyDetailsMenu.txtOwner.setEditable(true);
		companyDetailsMenu.txtSector.setEditable(true);
		companyDetailsMenu.txtAddress.setEditable(true);
		companyDetailsMenu.txtCity.setEditable(true);
		companyDetailsMenu.txtRegion.setEditable(true);
		companyDetailsMenu.txtZipCode.setEditable(true);
		companyDetailsMenu.txtCountry.setEditable(true);
		companyDetailsMenu.txtEmail.setEditable(true);
		companyDetailsMenu.txtPhoneNumber.setEditable(true);
		companyDetailsMenu.txtFaxNumber.setEditable(true);
		
		companyDetailsMenu.txtTaxID.setValue(companyDetailsMenu.getCompany().getTaxId());
		companyDetailsMenu.txtName.setText(companyDetailsMenu.getCompany().getName());
		companyDetailsMenu.txtOwner.setText(companyDetailsMenu.getCompany().getOwner());
		companyDetailsMenu.txtSector.setText(companyDetailsMenu.getCompany().getSector());
		companyDetailsMenu.txtAddress.setText(companyDetailsMenu.getCompany().getAddress());
		companyDetailsMenu.txtCity.setText(companyDetailsMenu.getCompany().getCity());
		companyDetailsMenu.txtRegion.setText(companyDetailsMenu.getCompany().getRegion());
		companyDetailsMenu.txtZipCode.setText(companyDetailsMenu.getCompany().getZipCode());
		companyDetailsMenu.txtCountry.setText(companyDetailsMenu.getCompany().getCountry());
		companyDetailsMenu.txtPhoneNumber.setText(companyDetailsMenu.getCompany().getPhoneNumber());
		companyDetailsMenu.txtFaxNumber.setText(companyDetailsMenu.getCompany().getFaxNumber());
		companyDetailsMenu.txtLastModified.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(companyDetailsMenu.getCompany().getLastModifiedDate()));
		companyDetailsMenu.txtLastModifiedUser.setText(companyDetailsMenu.getEmployeeController().getEmployeeName(companyDetailsMenu.getCompany().getIdEmployeeModify()));
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(CompanyDetails companyDetailsMenu) {
		try {
			companyDetailsMenu.getCompany().setTaxId((Long) companyDetailsMenu.txtTaxID.getValue());
			companyDetailsMenu.getCompany().setName(companyDetailsMenu.txtName.getText());
			companyDetailsMenu.getCompany().setOwner(companyDetailsMenu.txtOwner.getText());
			companyDetailsMenu.getCompany().setSector(companyDetailsMenu.txtSector.getText());
			
			companyDetailsMenu.getCompany().setAddress(companyDetailsMenu.txtAddress.getText());
			companyDetailsMenu.getCompany().setCity(companyDetailsMenu.txtCity.getText());
			companyDetailsMenu.getCompany().setRegion(companyDetailsMenu.txtRegion.getText());
			companyDetailsMenu.getCompany().setZipCode(companyDetailsMenu.txtZipCode.getText());
			companyDetailsMenu.getCompany().setCountry(companyDetailsMenu.txtCountry.getText());
			
			companyDetailsMenu.getCompany().setEmail(companyDetailsMenu.txtEmail.getText());
			companyDetailsMenu.getCompany().setPhoneNumber(companyDetailsMenu.txtPhoneNumber.getText());
			companyDetailsMenu.getCompany().setFaxNumber(companyDetailsMenu.txtFaxNumber.getText());
			
			companyDetailsMenu.getCompany().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			companyDetailsMenu.getCompany().setLastModifiedDate(new Date());
			
			companyDetailsMenu.setCompany(companyDetailsMenu.getCompanyController().save(companyDetailsMenu.getCompany()));
			
			companyDetailsMenu.setShowMode();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(companyDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(CompanyDetails companyDetailsMenu) {
		companyDetailsMenu.setShowMode();
	}

}
