package org.haegerp.entity.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
	
	public <S extends T> S performNew(S entity);
	
	public <S extends T> S performEdit(S entity);
	
	public void delete(T entity);
	
	@Deprecated
	public <S extends T> S save(S entity);
}
