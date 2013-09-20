package org.haegerp.service;

import org.haegerp.entity.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von der Firma.
 * 
 * @author Fabio Codinha
 *
 */
@Service
@Transactional
public interface CompanyService {

	/**
	 * Die Details der Firma werden geholt
	 * @return Die Angaben zur Firma
	 */
	public Company getCompany();
	
	/**
	 * Die Details der Firma werden gespeichert
	 * @param company Die Firma
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Company save(Company company);

}
