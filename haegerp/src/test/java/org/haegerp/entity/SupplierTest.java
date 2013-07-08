package org.haegerp.entity;

import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.entity.repository.supplier.SupplierRepository;
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
	    	EmployeeSession.setEmployee(employeeRepository.findOne(1L));
	    	if (!Properties.loadProperties()){
	    		fail("Failed to load Properties File.");
	    	}
    	}
    }
    
    /**
     * Ein Lieferant wird in die Datenbank erstellt
     */
    @Test
    public void test1InsertSupplier(){
        try {
	    	Supplier supplier = new Supplier();
	    	supplier.setName(Properties.getProperty("INSERT_S_NAME"));
	    	supplier.setTaxId(Long.parseLong(Properties.getProperty("INSERT_S_TAXID")));
	    	supplier.setAddress(Properties.getProperty("INSERT_S_ADDRESS"));
	    	supplier.setZipCode(Properties.getProperty("INSERT_S_ZIPCODE"));
	    	supplier.setCity(Properties.getProperty("INSERT_S_CITY"));
	    	supplier.setRegion(Properties.getProperty("INSERT_S_REGION"));
	    	supplier.setCountry(Properties.getProperty("INSERT_S_COUNTRY"));
	    	supplier.setEmail(Properties.getProperty("INSERT_S_EMAIL"));
	    	supplier.setPhoneNumber(Properties.getProperty("INSERT_S_PHONENUMBER"));
	    	supplier.setMobileNumber(Properties.getProperty("INSERT_S_MOBILENUMBER"));
	    	supplier.setFaxNumber(Properties.getProperty("INSERT_S_FAXNUMBER"));
	    	supplier.setDescription(Properties.getProperty("INSERT_S_DESCRIPTION"));
	    	
	    	supplier = supplierRepo.save(supplier);
	    	
	        //Die erstellt Artikelkategorie wird geprüft
	    	SUPPLIER_ID = supplier.getIdBusinessPartner();
	        supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	        assertEquals(supplier.getName(), Properties.getProperty("INSERT_S_NAME"));
	        assertEquals(supplier.getTaxId(), Long.parseLong(Properties.getProperty("INSERT_S_TAXID")));
	        assertEquals(supplier.getAddress(), Properties.getProperty("INSERT_S_ADDRESS"));
	        assertEquals(supplier.getZipCode(), Properties.getProperty("INSERT_S_ZIPCODE"));
	        assertEquals(supplier.getCity(), Properties.getProperty("INSERT_S_CITY"));
	        assertEquals(supplier.getRegion(), Properties.getProperty("INSERT_S_REGION"));
	        assertEquals(supplier.getCountry(), Properties.getProperty("INSERT_S_COUNTRY"));
	        assertEquals(supplier.getEmail(), Properties.getProperty("INSERT_S_EMAIL"));
	        assertEquals(supplier.getPhoneNumber(), Properties.getProperty("INSERT_S_PHONENUMBER"));
	        assertEquals(supplier.getMobileNumber(), Properties.getProperty("INSERT_S_MOBILENUMBER"));
	        assertEquals(supplier.getFaxNumber(), Properties.getProperty("INSERT_S_FAXNUMBER"));
	        assertEquals(supplier.getDescription(), Properties.getProperty("INSERT_S_DESCRIPTION"));
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
	        
	    	supplier.setName(Properties.getProperty("UPDATE_S_NAME"));
	    	supplier.setTaxId(Long.parseLong(Properties.getProperty("UPDATE_S_TAXID")));
	    	supplier.setAddress(Properties.getProperty("UPDATE_S_ADDRESS"));
	    	supplier.setZipCode(Properties.getProperty("UPDATE_S_ZIPCODE"));
	    	supplier.setCity(Properties.getProperty("UPDATE_S_CITY"));
	    	supplier.setRegion(Properties.getProperty("UPDATE_S_REGION"));
	    	supplier.setCountry(Properties.getProperty("UPDATE_S_COUNTRY"));
	    	supplier.setEmail(Properties.getProperty("UPDATE_S_EMAIL"));
	    	supplier.setPhoneNumber(Properties.getProperty("UPDATE_S_PHONENUMBER"));
	    	supplier.setMobileNumber(Properties.getProperty("UPDATE_S_MOBILENUMBER"));
	    	supplier.setFaxNumber(Properties.getProperty("UPDATE_S_FAXNUMBER"));
	    	supplier.setDescription(Properties.getProperty("UPDATE_S_DESCRIPTION"));
	    	
	    	supplierRepo.save(supplier);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	        assertEquals(supplier.getName(), Properties.getProperty("UPDATE_S_NAME"));
	        assertEquals(supplier.getTaxId(), Long.parseLong(Properties.getProperty("UPDATE_S_TAXID")));
	        assertEquals(supplier.getAddress(), Properties.getProperty("UPDATE_S_ADDRESS"));
	        assertEquals(supplier.getZipCode(), Properties.getProperty("UPDATE_S_ZIPCODE"));
	        assertEquals(supplier.getCity(), Properties.getProperty("UPDATE_S_CITY"));
	        assertEquals(supplier.getRegion(), Properties.getProperty("UPDATE_S_REGION"));
	        assertEquals(supplier.getCountry(), Properties.getProperty("UPDATE_S_COUNTRY"));
	        assertEquals(supplier.getEmail(), Properties.getProperty("UPDATE_S_EMAIL"));
	        assertEquals(supplier.getPhoneNumber(), Properties.getProperty("UPDATE_S_PHONENUMBER"));
	        assertEquals(supplier.getMobileNumber(), Properties.getProperty("UPDATE_S_MOBILENUMBER"));
	        assertEquals(supplier.getFaxNumber(), Properties.getProperty("UPDATE_S_FAXNUMBER"));
	        assertEquals(supplier.getDescription(), Properties.getProperty("UPDATE_S_DESCRIPTION"));
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
	    	supplier.setName(Properties.getProperty("INSERT_S_NAME_F"));
	    	supplier.setTaxId(Long.parseLong(Properties.getProperty("INSERT_S_TAXID_F")));
	    	supplier.setAddress(Properties.getProperty("INSERT_S_ADDRESS_F"));
	    	supplier.setZipCode(Properties.getProperty("INSERT_S_ZIPCODE_F"));
	    	supplier.setCity(Properties.getProperty("INSERT_S_CITY_F"));
	    	supplier.setRegion(Properties.getProperty("INSERT_S_REGION_F"));
	    	supplier.setCountry(Properties.getProperty("INSERT_S_COUNTRY_F"));
	    	supplier.setEmail(Properties.getProperty("INSERT_S_EMAIL_F"));
	    	supplier.setPhoneNumber(Properties.getProperty("INSERT_S_PHONENUMBER_F"));
	    	supplier.setMobileNumber(Properties.getProperty("INSERT_S_MOBILENUMBER_F"));
	    	supplier.setFaxNumber(Properties.getProperty("INSERT_S_FAXNUMBER_F"));
	    	supplier.setDescription(Properties.getProperty("INSERT_S_DESCRIPTION_F"));
	    	
	    	supplier = supplierRepo.save(supplier);
	    	
	        //Die erstellt Artikelkategorie wird geprüft
	    	SUPPLIER_ID = supplier.getIdBusinessPartner();
	        supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	        assertEquals(supplier.getName(), Properties.getProperty("INSERT_S_NAME"));
	        assertEquals(supplier.getTaxId(), Long.parseLong(Properties.getProperty("INSERT_S_TAXID")));
	        assertEquals(supplier.getAddress(), Properties.getProperty("INSERT_S_ADDRESS"));
	        assertEquals(supplier.getZipCode(), Properties.getProperty("INSERT_S_ZIPCODE"));
	        assertEquals(supplier.getCity(), Properties.getProperty("INSERT_S_CITY"));
	        assertEquals(supplier.getRegion(), Properties.getProperty("INSERT_S_REGION"));
	        assertEquals(supplier.getCountry(), Properties.getProperty("INSERT_S_COUNTRY"));
	        assertEquals(supplier.getEmail(), Properties.getProperty("INSERT_S_EMAIL"));
	        assertEquals(supplier.getPhoneNumber(), Properties.getProperty("INSERT_S_PHONENUMBER"));
	        assertEquals(supplier.getMobileNumber(), Properties.getProperty("INSERT_S_MOBILENUMBER"));
	        assertEquals(supplier.getFaxNumber(), Properties.getProperty("INSERT_S_FAXNUMBER"));
	        assertEquals(supplier.getDescription(), Properties.getProperty("INSERT_S_DESCRIPTION"));
	    } catch (Exception ex) {
	    	throw ex;
	    }
    }
}
