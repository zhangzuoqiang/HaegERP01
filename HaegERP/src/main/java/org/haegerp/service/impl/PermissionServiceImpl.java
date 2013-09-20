package org.haegerp.service.impl;

import java.util.List;

import org.haegerp.service.PermissionService;
import org.haegerp.entity.Permission;
import org.haegerp.entity.repository.employee.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
