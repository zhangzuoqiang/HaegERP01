package org.haegerp.controller;

import org.haegerp.entity.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von dem Kunden.
 * 
 * @author Wolf
 *
 */
@Service
@Transactional
public interface CompanyController {

	/**
	 * Die Angaben zur Firma werden geholt
	 * @return Die Angaben zur Firma
	 */
	public Company getCompany();
	
	/**
	 * Die Angaben zur Firma werden gespeichert
	 * @param company Der Lieferant
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Company save(Company company);

}
