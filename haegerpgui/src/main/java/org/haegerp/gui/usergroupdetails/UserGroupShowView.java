package org.haegerp.gui.usergroupdetails;

import org.haegerp.entity.Permission;
import org.haegerp.gui.UserGroupDetails;

public class UserGroupShowView implements UserGroupDetailsInterface {

	public void applyView(UserGroupDetails userGroupDetailsMenu) {
		userGroupDetailsMenu.btnCancel.setEnabled(true);
		userGroupDetailsMenu.btnCancel.setText("Exit");
		userGroupDetailsMenu.btnSaveEdit.setEnabled(true);
		userGroupDetailsMenu.btnSaveEdit.setText("Edit");
		
		userGroupDetailsMenu.txtCountEmployees.setValue(userGroupDetailsMenu.getUserGroup().getEmployees().size());
		userGroupDetailsMenu.txtName.setText(userGroupDetailsMenu.getUserGroup().getName());
		userGroupDetailsMenu.txtDescription.setText(userGroupDetailsMenu.getUserGroup().getDescription());
		
		userGroupDetailsMenu.loadLstPermission();
		
		for (Permission permission : userGroupDetailsMenu.getUserGroup().getPermissions()) {
			userGroupDetailsMenu.lstRevokedPermissionsModel.removeElement(permission.getModuleName());
			userGroupDetailsMenu.lstGrantedPermissionsModel.addElement(permission.getModuleName());
		}
		
		userGroupDetailsMenu.btnGrant.setEnabled(false);
		userGroupDetailsMenu.btnGrantAll.setEnabled(false);
		userGroupDetailsMenu.btnRevoke.setEnabled(false);
		userGroupDetailsMenu.btnRevokeAll.setEnabled(false);
		userGroupDetailsMenu.lstGrantedPermissions.setEnabled(false);
		userGroupDetailsMenu.lstRevokedPermissions.setEnabled(false);
		
		userGroupDetailsMenu.txtDescription.setEditable(false);
		userGroupDetailsMenu.txtCountEmployees.setEditable(false);
		userGroupDetailsMenu.txtName.setEditable(false);
	}

	public void btnSaveEdit(UserGroupDetails userGroupDetailsMenu) {
		userGroupDetailsMenu.setEditMode();
	}

	public void btnCancel(UserGroupDetails userGroupDetailsMenu) {
		userGroupDetailsMenu.setVisible(false);
	}

}
