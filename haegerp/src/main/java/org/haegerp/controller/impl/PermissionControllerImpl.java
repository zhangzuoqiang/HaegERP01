package org.haegerp.controller.impl;

import java.util.List;

import org.haegerp.controller.PermissionController;
import org.haegerp.entity.Permission;
import org.haegerp.entity.repository.employee.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionControllerImpl implements PermissionController {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public List<Permission> getAllPermissions() {
		return permissionRepository.findAll();
	}

}
