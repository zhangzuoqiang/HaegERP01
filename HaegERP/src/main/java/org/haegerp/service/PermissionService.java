package org.haegerp.service;

import java.util.List;

import org.haegerp.entity.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface PermissionService {
	
	/**
	 * Bekommt alle Kategorien in einer Liste.
	 * @return Liste mit den Erlaubnisen
	 */
	public List<Permission> getAllPermissions();
	
}
