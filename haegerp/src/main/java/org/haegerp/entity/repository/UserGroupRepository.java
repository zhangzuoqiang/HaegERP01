package org.haegerp.entity.repository;

import org.haegerp.entity.UserGroup;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface UserGroupRepository extends MyRepository<UserGroup, Long> {

}
