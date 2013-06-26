package org.haegerp.entity;

import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.entity.repository.supplier.SupplierRepository;
import org.haegerp.exception.LengthOverflowException;
import org.haegerp.session.Session;
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
public class SupplierTest extends TestCase {
	
    private static long SUPPLIER_ID;
    
    private static boolean CHECK_SETUP = true;
    
    //Repositories
    @Autowired
    private SupplierRepository supplierRepo;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    @Before
    public void setUp() throws Exception {
    	super.setUp();
    	if (CHECK_SETUP)
    	{
    		CHECK_SETUP = false;
	    	Session.setEmployee(employeeRepository.findOne(1L));
    	}
    }
    
    /**
     * Ein Lieferant wird in die Datenbank erstellt
     */
    @Test
    public void test1InsertSupplier(){
        try {
	    	Supplier supplier = new Supplier();
	    	supplier.setName(Config.getProperty("INSERT_S_NAME"));
	    	supplier.setTaxId(Long.parseLong(Config.getProperty("INSERT_S_TAXID")));
	    	supplier.setAddress(Config.getProperty("INSERT_S_ADDRESS"));
	    	supplier.setZipCode(Config.getProperty("INSERT_S_ZIPCODE"));
	    	supplier.setCity(Config.getProperty("INSERT_S_CITY"));
	    	supplier.setRegion(Config.getProperty("INSERT_S_REGION"));
	    	supplier.setCountry(Config.getProperty("INSERT_S_COUNTRY"));
	    	supplier.setEmail(Config.getProperty("INSERT_S_EMAIL"));
	    	supplier.setPhoneNumber(Config.getProperty("INSERT_S_PHONENUMBER"));
	    	supplier.setMobileNumber(Config.getProperty("INSERT_S_MOBILENUMBER"));
	    	supplier.setFaxNumber(Config.getProperty("INSERT_S_FAXNUMBER"));
	    	supplier.setDescription(Config.getProperty("INSERT_S_DESCRIPTION"));
	    	
	    	supplier = supplierRepo.performNew(supplier);
	    	
	        //Die erstellt Artikelkategorie wird geprüft
	    	SUPPLIER_ID = supplier.getIdBusinessPartner();
	        supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	        assertEquals(supplier.getName(), Config.getProperty("INSERT_S_NAME"));
	        assertEquals(supplier.getTaxId(), Long.parseLong(Config.getProperty("INSERT_S_TAXID")));
	        assertEquals(supplier.getAddress(), Config.getProperty("INSERT_S_ADDRESS"));
	        assertEquals(supplier.getZipCode(), Config.getProperty("INSERT_S_ZIPCODE"));
	        assertEquals(supplier.getCity(), Config.getProperty("INSERT_S_CITY"));
	        assertEquals(supplier.getRegion(), Config.getProperty("INSERT_S_REGION"));
	        assertEquals(supplier.getCountry(), Config.getProperty("INSERT_S_COUNTRY"));
	        assertEquals(supplier.getEmail(), Config.getProperty("INSERT_S_EMAIL"));
	        assertEquals(supplier.getPhoneNumber(), Config.getProperty("INSERT_S_PHONENUMBER"));
	        assertEquals(supplier.getMobileNumber(), Config.getProperty("INSERT_S_MOBILENUMBER"));
	        assertEquals(supplier.getFaxNumber(), Config.getProperty("INSERT_S_FAXNUMBER"));
	        assertEquals(supplier.getDescription(), Config.getProperty("INSERT_S_DESCRIPTION"));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Der letzter Lieferant wird in die Datenbank ändert
     */
    @Test
    public void test2UpdateSupplier(){
    	try {
	        Supplier supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	    	supplier.setName(Config.getProperty("UPDATE_S_NAME"));
	    	supplier.setTaxId(Long.parseLong(Config.getProperty("UPDATE_S_TAXID")));
	    	supplier.setAddress(Config.getProperty("UPDATE_S_ADDRESS"));
	    	supplier.setZipCode(Config.getProperty("UPDATE_S_ZIPCODE"));
	    	supplier.setCity(Config.getProperty("UPDATE_S_CITY"));
	    	supplier.setRegion(Config.getProperty("UPDATE_S_REGION"));
	    	supplier.setCountry(Config.getProperty("UPDATE_S_COUNTRY"));
	    	supplier.setEmail(Config.getProperty("UPDATE_S_EMAIL"));
	    	supplier.setPhoneNumber(Config.getProperty("UPDATE_S_PHONENUMBER"));
	    	supplier.setMobileNumber(Config.getProperty("UPDATE_S_MOBILENUMBER"));
	    	supplier.setFaxNumber(Config.getProperty("UPDATE_S_FAXNUMBER"));
	    	supplier.setDescription(Config.getProperty("UPDATE_S_DESCRIPTION"));
	    	
	    	supplierRepo.performEdit(supplier);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	        assertEquals(supplier.getName(), Config.getProperty("UPDATE_S_NAME"));
	        assertEquals(supplier.getTaxId(), Long.parseLong(Config.getProperty("UPDATE_S_TAXID")));
	        assertEquals(supplier.getAddress(), Config.getProperty("UPDATE_S_ADDRESS"));
	        assertEquals(supplier.getZipCode(), Config.getProperty("UPDATE_S_ZIPCODE"));
	        assertEquals(supplier.getCity(), Config.getProperty("UPDATE_S_CITY"));
	        assertEquals(supplier.getRegion(), Config.getProperty("UPDATE_S_REGION"));
	        assertEquals(supplier.getCountry(), Config.getProperty("UPDATE_S_COUNTRY"));
	        assertEquals(supplier.getEmail(), Config.getProperty("UPDATE_S_EMAIL"));
	        assertEquals(supplier.getPhoneNumber(), Config.getProperty("UPDATE_S_PHONENUMBER"));
	        assertEquals(supplier.getMobileNumber(), Config.getProperty("UPDATE_S_MOBILENUMBER"));
	        assertEquals(supplier.getFaxNumber(), Config.getProperty("UPDATE_S_FAXNUMBER"));
	        assertEquals(supplier.getDescription(), Config.getProperty("UPDATE_S_DESCRIPTION"));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Der Liferant wird von der Datenbank gelöscht
     */
    @Test
    public void test3DeleteSupplier()
    {
    	try {
	        Supplier supplier = supplierRepo.findOne(SUPPLIER_ID);
	    	
	        supplierRepo.delete(supplier.getIdBusinessPartner());
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertNull(supplierRepo.findOne(SUPPLIER_ID));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Fehlerprovokation Supplier
     * @throws Exception 
     */
    @Test(expected=LengthOverflowException.class)
    public void test4InsertSupplierError() throws Exception{
        try {
	    	Supplier supplier = new Supplier();
	    	supplier.setName(Config.getProperty("INSERT_S_NAME_F"));
	    	supplier.setTaxId(Long.parseLong(Config.getProperty("INSERT_S_TAXID_F")));
	    	supplier.setAddress(Config.getProperty("INSERT_S_ADDRESS_F"));
	    	supplier.setZipCode(Config.getProperty("INSERT_S_ZIPCODE_F"));
	    	supplier.setCity(Config.getProperty("INSERT_S_CITY_F"));
	    	supplier.setRegion(Config.getProperty("INSERT_S_REGION_F"));
	    	supplier.setCountry(Config.getProperty("INSERT_S_COUNTRY_F"));
	    	supplier.setEmail(Config.getProperty("INSERT_S_EMAIL_F"));
	    	supplier.setPhoneNumber(Config.getProperty("INSERT_S_PHONENUMBER_F"));
	    	supplier.setMobileNumber(Config.getProperty("INSERT_S_MOBILENUMBER_F"));
	    	supplier.setFaxNumber(Config.getProperty("INSERT_S_FAXNUMBER_F"));
	    	supplier.setDescription(Config.getProperty("INSERT_S_DESCRIPTION_F"));
	    	
	    	supplier = supplierRepo.performNew(supplier);
	    	
	        //Die erstellt Artikelkategorie wird geprüft
	    	SUPPLIER_ID = supplier.getIdBusinessPartner();
	        supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	        assertEquals(supplier.getName(), Config.getProperty("INSERT_S_NAME"));
	        assertEquals(supplier.getTaxId(), Long.parseLong(Config.getProperty("INSERT_S_TAXID")));
	        assertEquals(supplier.getAddress(), Config.getProperty("INSERT_S_ADDRESS"));
	        assertEquals(supplier.getZipCode(), Config.getProperty("INSERT_S_ZIPCODE"));
	        assertEquals(supplier.getCity(), Config.getProperty("INSERT_S_CITY"));
	        assertEquals(supplier.getRegion(), Config.getProperty("INSERT_S_REGION"));
	        assertEquals(supplier.getCountry(), Config.getProperty("INSERT_S_COUNTRY"));
	        assertEquals(supplier.getEmail(), Config.getProperty("INSERT_S_EMAIL"));
	        assertEquals(supplier.getPhoneNumber(), Config.getProperty("INSERT_S_PHONENUMBER"));
	        assertEquals(supplier.getMobileNumber(), Config.getProperty("INSERT_S_MOBILENUMBER"));
	        assertEquals(supplier.getFaxNumber(), Config.getProperty("INSERT_S_FAXNUMBER"));
	        assertEquals(supplier.getDescription(), Config.getProperty("INSERT_S_DESCRIPTION"));
	    } catch (Exception ex) {
	    	throw ex;
	    }
    }
}
