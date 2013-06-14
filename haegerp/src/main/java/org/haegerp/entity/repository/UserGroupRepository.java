package org.haegerp.entity.repository;

import org.haegerp.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	@Modifying
	@Transactional
	public <S extends UserGroup> S save(S entity);
}
