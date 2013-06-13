package org.haegerp.entity.repository;

import org.haegerp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ClientRepository extends JpaRepository<Client, Integer> {

	@Modifying
	@Transactional(propagation=Propagation.REQUIRED)
	public <S extends Client> S save(S entity);

}
