package org.haegerp.gui.companydetails;

import java.text.SimpleDateFormat;

import org.haegerp.gui.CompanyDetails;

public class CompanyShowView implements CompanyDetailsInterface {

	public void applyView(CompanyDetails companyDetailsMenu) {
		companyDetailsMenu.btnCancel.setEnabled(true);
		companyDetailsMenu.btnCancel.setText("Exit");
		companyDetailsMenu.btnSaveEdit.setEnabled(true);
		companyDetailsMenu.btnSaveEdit.setText("Edit");
		
		companyDetailsMenu.txtTaxID.setValue(companyDetailsMenu.getCompany().getTaxId());
		companyDetailsMenu.txtName.setText(companyDetailsMenu.getCompany().getName());
		companyDetailsMenu.txtOwner.setText(companyDetailsMenu.getCompany().getOwner());
		companyDetailsMenu.txtSector.setText(companyDetailsMenu.getCompany().getSector());
		companyDetailsMenu.txtAddress.setText(companyDetailsMenu.getCompany().getAddress());
		companyDetailsMenu.txtCity.setText(companyDetailsMenu.getCompany().getCity());
		companyDetailsMenu.txtRegion.setText(companyDetailsMenu.getCompany().getRegion());
		companyDetailsMenu.txtZipCode.setText(companyDetailsMenu.getCompany().getZipCode());
		companyDetailsMenu.txtCountry.setText(companyDetailsMenu.getCompany().getCountry());
		companyDetailsMenu.txtEmail.setText(companyDetailsMenu.getCompany().getEmail());
		companyDetailsMenu.txtPhoneNumber.setText(companyDetailsMenu.getCompany().getPhoneNumber());
		companyDetailsMenu.txtFaxNumber.setText(companyDetailsMenu.getCompany().getFaxNumber());
                if (companyDetailsMenu.getCompany().getLastModifiedDate() == null)
                    companyDetailsMenu.txtLastModified.setText("");
                else
                    companyDetailsMenu.txtLastModified.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(companyDetailsMenu.getCompany().getLastModifiedDate()));
                if (companyDetailsMenu.getCompany().getIdEmployeeModify() == null)
                    companyDetailsMenu.txtLastModifiedUser.setText("");
                else
                    companyDetailsMenu.txtLastModifiedUser.setText(companyDetailsMenu.getEmployeeController().getEmployeeName(companyDetailsMenu.getCompany().getIdEmployeeModify()));
		
		companyDetailsMenu.txtTaxID.setEditable(false);
		companyDetailsMenu.txtName.setEditable(false);
		companyDetailsMenu.txtOwner.setEditable(false);
		companyDetailsMenu.txtSector.setEditable(false);
		companyDetailsMenu.txtAddress.setEditable(false);
		companyDetailsMenu.txtCity.setEditable(false);
		companyDetailsMenu.txtRegion.setEditable(false);
		companyDetailsMenu.txtZipCode.setEditable(false);
		companyDetailsMenu.txtCountry.setEditable(false);
		companyDetailsMenu.txtEmail.setEditable(false);
		companyDetailsMenu.txtPhoneNumber.setEditable(false);
		companyDetailsMenu.txtFaxNumber.setEditable(false);
	}

	public void btnSaveEdit(CompanyDetails companyDetailsMenu) {
		companyDetailsMenu.setEditMode();
	}

	public void btnCancel(CompanyDetails companyDetailsMenu) {
		companyDetailsMenu.setVisible(false);
	}

}
