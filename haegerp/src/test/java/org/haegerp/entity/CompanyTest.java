package org.haegerp.entity;

import org.haegerp.entity.repository.CompanyRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.exception.LengthOverflowException;
import org.haegerp.session.EmployeeSession;
import org.haegerp.tools.Properties;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.TestCase;


/**
 * Dieses Test Suite wird Lieferantgeschäftspartner testen
 * 
 * @author Wolf
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/spring-data.xml")
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class CompanyTest extends TestCase {
	
    private static long COMPANY_ID = 1;
    
    private static boolean CHECK_SETUP = true;
    
    //Repositories
    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    @Before
    public void setUp() throws Exception {
    	super.setUp();
    	if (CHECK_SETUP)
    	{
    		CHECK_SETUP = false;
	    	EmployeeSession.setEmployee(employeeRepository.findOne(1L));
	    	if (!Properties.loadProperties()){
	    		fail("Failed to load Properties File.");
	    	}
    	}
    }
    
    
    /**
     * Die Unternehmen wird geändert und in der Datenbank gespeichert.
     */
    @Test
    public void test1UpdateCompany(){
    	try {
    		Company company = companyRepository.findOne(COMPANY_ID);
	        
    		company.setName(Properties.getProperty("UPDATE_COMPANY_NAME"));
    		company.setTaxId(Long.parseLong(Properties.getProperty("UPDATE_COMPANY_TAXID")));
    		company.setOwner(Properties.getProperty("UPDATE_COMPANY_OWNER"));
    		company.setSector(Properties.getProperty("UPDATE_COMPANY_SECTOR"));
    		company.setAddress(Properties.getProperty("UPDATE_COMPANY_ADDRESS"));
    		company.setZipCode(Properties.getProperty("UPDATE_COMPANY_ZIPCODE"));
    		company.setCity(Properties.getProperty("UPDATE_COMPANY_CITY"));
    		company.setRegion(Properties.getProperty("UPDATE_COMPANY_REGION"));
    		company.setCountry(Properties.getProperty("UPDATE_COMPANY_COUNTRY"));
    		company.setPhoneNumber(Properties.getProperty("UPDATE_COMPANY_PHONENUMBER"));
    		company.setFaxNumber(Properties.getProperty("UPDATE_COMPANY_FAXNUMBER"));
    		
    		companyRepository.save(company);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        company = companyRepository.findOne(COMPANY_ID);
	        
	        assertEquals(company.getName(), Properties.getProperty("UPDATE_COMPANY_NAME"));
	        assertEquals(company.getTaxId(), Long.parseLong(Properties.getProperty("UPDATE_COMPANY_TAXID")));
	        assertEquals(company.getOwner(), Properties.getProperty("UPDATE_COMPANY_OWNER"));
	        assertEquals(company.getSector(), Properties.getProperty("UPDATE_COMPANY_SECTOR"));
	        assertEquals(company.getAddress(), Properties.getProperty("UPDATE_COMPANY_ADDRESS"));
	        assertEquals(company.getZipCode(), Properties.getProperty("UPDATE_COMPANY_ZIPCODE"));
	        assertEquals(company.getCity(), Properties.getProperty("UPDATE_COMPANY_CITY"));
	        assertEquals(company.getRegion(), Properties.getProperty("UPDATE_COMPANY_REGION"));
	        assertEquals(company.getCountry(), Properties.getProperty("UPDATE_COMPANY_COUNTRY"));
	        assertEquals(company.getPhoneNumber(), Properties.getProperty("UPDATE_COMPANY_PHONENUMBER"));
	        assertEquals(company.getFaxNumber(), Properties.getProperty("UPDATE_COMPANY_FAXNUMBER"));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Fehlerprovokation Company
     * @throws Exception 
     */
    @Test(expected=LengthOverflowException.class)
    public void test2UpdateCompanyError() throws Exception{
        try {
        	Company company = companyRepository.findOne(COMPANY_ID);
	        
    		company.setName(Properties.getProperty("UPDATE_COMPANY_NAME_F"));
    		company.setTaxId(Long.parseLong(Properties.getProperty("UPDATE_COMPANY_TAXID_F")));
    		company.setOwner(Properties.getProperty("UPDATE_COMPANY_OWNER_F"));
    		company.setSector(Properties.getProperty("UPDATE_COMPANY_SECTOR_F"));
    		company.setAddress(Properties.getProperty("UPDATE_COMPANY_ADDRESS_F"));
    		company.setZipCode(Properties.getProperty("UPDATE_COMPANY_ZIPCODE_F"));
    		company.setCity(Properties.getProperty("UPDATE_COMPANY_CITY_F"));
    		company.setRegion(Properties.getProperty("UPDATE_COMPANY_REGION_F"));
    		company.setCountry(Properties.getProperty("UPDATE_COMPANY_COUNTRY_F"));
    		company.setPhoneNumber(Properties.getProperty("UPDATE_COMPANY_PHONENUMBER_F"));
    		company.setFaxNumber(Properties.getProperty("UPDATE_COMPANY_FAXNUMBER_F"));
    		
    		companyRepository.save(company);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        company = companyRepository.findOne(COMPANY_ID);
	        
	        assertEquals(company.getName(), Properties.getProperty("UPDATE_COMPANY_NAME"));
	        assertEquals(company.getTaxId(), Long.parseLong(Properties.getProperty("UPDATE_COMPANY_TAXID")));
	        assertEquals(company.getOwner(), Properties.getProperty("UPDATE_COMPANY_OWNER"));
	        assertEquals(company.getSector(), Properties.getProperty("UPDATE_COMPANY_SECTOR"));
	        assertEquals(company.getAddress(), Properties.getProperty("UPDATE_COMPANY_ADDRESS"));
	        assertEquals(company.getZipCode(), Properties.getProperty("UPDATE_COMPANY_ZIPCODE"));
	        assertEquals(company.getCity(), Properties.getProperty("UPDATE_COMPANY_CITY"));
	        assertEquals(company.getRegion(), Properties.getProperty("UPDATE_COMPANY_REGION"));
	        assertEquals(company.getCountry(), Properties.getProperty("UPDATE_COMPANY_COUNTRY"));
	        assertEquals(company.getPhoneNumber(), Properties.getProperty("UPDATE_COMPANY_PHONENUMBER"));
	        assertEquals(company.getFaxNumber(), Properties.getProperty("UPDATE_COMPANY_FAXNUMBER"));
	    } catch (Exception ex) {
	    	throw ex;
	    }
    }
}
