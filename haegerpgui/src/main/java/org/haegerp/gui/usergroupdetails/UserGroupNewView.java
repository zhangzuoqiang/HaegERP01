package org.haegerp.gui.usergroupdetails;

import java.util.Date;
import java.util.HashSet;

import javax.swing.JOptionPane;

import org.haegerp.entity.Permission;
import org.haegerp.entity.UserGroup;
import org.haegerp.gui.UserGroupDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserGroupNewView implements UserGroupDetailsInterface {

	public void applyView(UserGroupDetails userGroupDetailsMenu) {
		userGroupDetailsMenu.btnCancel.setEnabled(true);
		userGroupDetailsMenu.btnCancel.setText("Cancel");
		userGroupDetailsMenu.btnSaveEdit.setEnabled(true);
		userGroupDetailsMenu.btnSaveEdit.setText("Save");
		
		userGroupDetailsMenu.txtDescription.setEditable(true);
		userGroupDetailsMenu.txtDescription.setText("");
		
		userGroupDetailsMenu.txtCountEmployees.setEditable(false);
		userGroupDetailsMenu.txtCountEmployees.setValue(null);
		
		userGroupDetailsMenu.txtName.setEditable(true);
		userGroupDetailsMenu.txtName.setText("");
		
		userGroupDetailsMenu.loadLstPermission();
		
		userGroupDetailsMenu.btnGrant.setEnabled(true);
		userGroupDetailsMenu.btnGrantAll.setEnabled(true);
		userGroupDetailsMenu.btnRevoke.setEnabled(true);
		userGroupDetailsMenu.btnRevokeAll.setEnabled(true);
		userGroupDetailsMenu.lstGrantedPermissions.setEnabled(true);
		userGroupDetailsMenu.lstRevokedPermissions.setEnabled(true);
		
		userGroupDetailsMenu.setUserGroup(new UserGroup());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(UserGroupDetails userGroupDetailsMenu) {
		try {
			userGroupDetailsMenu.getUserGroup().setName(userGroupDetailsMenu.txtName.getText());
			
			userGroupDetailsMenu.getUserGroup().setDescription(userGroupDetailsMenu.txtDescription.getText());
			
			userGroupDetailsMenu.getUserGroup().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			userGroupDetailsMenu.getUserGroup().setLastModifiedDate(new Date());
			
			userGroupDetailsMenu.getUserGroup().setPermissions(new HashSet<Permission>(0));
			
			for (Permission permission : userGroupDetailsMenu.getPermissions()) {
				if (userGroupDetailsMenu.lstGrantedPermissionsModel.contains(permission.getModuleName())){
					userGroupDetailsMenu.getUserGroup().getPermissions().add(permission);
				}
			}
			
			userGroupDetailsMenu.setUserGroup(userGroupDetailsMenu.getUserGroupController().save(userGroupDetailsMenu.getUserGroup()));
			
			userGroupDetailsMenu.setShowMode();
			
			userGroupDetailsMenu.getUserGroupManagement().loadTable();
			userGroupDetailsMenu.getEmployeeManagement().loadCbbUserGroup();
			userGroupDetailsMenu.getEmployeeManagement().loadTable();
			userGroupDetailsMenu.getEmployeeDetails().loadCbbUserGroup();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(userGroupDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(UserGroupDetails userGroupDetailsMenu) {
		userGroupDetailsMenu.setVisible(false);
	}

}
