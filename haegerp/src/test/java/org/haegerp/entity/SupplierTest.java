package org.haegerp.entity;

import java.io.FileInputStream;
import java.util.Properties;

import org.haegerp.entity.repository.SupplierRepository;
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
	
    private Properties properties = new Properties();
    
    private static int SUPPLIER_ID;
    
    //Repositories
    @Autowired
    private SupplierRepository supplierRepo;
    
    /**
     * Ein Lieferant wird in die Datenbank erstellt
     */
    @Test
    public void test1InsertSupplier(){
        try {
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	    	Supplier supplier = new Supplier();
	    	supplier.setName(properties.getProperty("INSERT_NAME"));
	    	supplier.setTaxId(Long.parseLong(properties.getProperty("INSERT_TAXID")));
	    	supplier.setAddress(properties.getProperty("INSERT_ADDRESS"));
	    	supplier.setZipCode(properties.getProperty("INSERT_ZIPCODE"));
	    	supplier.setCity(properties.getProperty("INSERT_CITY"));
	    	supplier.setRegion(properties.getProperty("INSERT_REGION"));
	    	supplier.setCountry(properties.getProperty("INSERT_COUNTRY"));
	    	supplier.setEmail(properties.getProperty("INSERT_EMAIL"));
	    	supplier.setPhoneNumber(properties.getProperty("INSERT_PHONENUMBER"));
	    	supplier.setMobileNumber(properties.getProperty("INSERT_MOBILENUMBER"));
	    	supplier.setFaxNumber(properties.getProperty("INSERT_FAXNUMBER"));
	    	supplier.setDescription(properties.getProperty("INSERT_DESCRIPTION"));
	    	
	    	supplier = supplierRepo.save(supplier);
	    	
	        //Die erstellt Artikelkategorie wird geprüft
	    	SUPPLIER_ID = supplier.getIdBusinessPartner();
	        supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	        assertEquals(supplier.getName(), properties.getProperty("INSERT_NAME"));
	        assertEquals(supplier.getTaxId(), Long.parseLong(properties.getProperty("INSERT_TAXID")));
	        assertEquals(supplier.getAddress(), properties.getProperty("INSERT_ADDRESS"));
	        assertEquals(supplier.getZipCode(), properties.getProperty("INSERT_ZIPCODE"));
	        assertEquals(supplier.getCity(), properties.getProperty("INSERT_CITY"));
	        assertEquals(supplier.getRegion(), properties.getProperty("INSERT_REGION"));
	        assertEquals(supplier.getCountry(), properties.getProperty("INSERT_COUNTRY"));
	        assertEquals(supplier.getEmail(), properties.getProperty("INSERT_EMAIL"));
	        assertEquals(supplier.getPhoneNumber(), properties.getProperty("INSERT_PHONENUMBER"));
	        assertEquals(supplier.getMobileNumber(), properties.getProperty("INSERT_MOBILENUMBER"));
	        assertEquals(supplier.getFaxNumber(), properties.getProperty("INSERT_FAXNUMBER"));
	        assertEquals(supplier.getDescription(), properties.getProperty("INSERT_DESCRIPTION"));
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
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        Supplier supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	    	supplier.setName(properties.getProperty("UPDATE_NAME"));
	    	supplier.setTaxId(Long.parseLong(properties.getProperty("UPDATE_TAXID")));
	    	supplier.setAddress(properties.getProperty("UPDATE_ADDRESS"));
	    	supplier.setZipCode(properties.getProperty("UPDATE_ZIPCODE"));
	    	supplier.setCity(properties.getProperty("UPDATE_CITY"));
	    	supplier.setRegion(properties.getProperty("UPDATE_REGION"));
	    	supplier.setCountry(properties.getProperty("UPDATE_COUNTRY"));
	    	supplier.setEmail(properties.getProperty("UPDATE_EMAIL"));
	    	supplier.setPhoneNumber(properties.getProperty("UPDATE_PHONENUMBER"));
	    	supplier.setMobileNumber(properties.getProperty("UPDATE_MOBILENUMBER"));
	    	supplier.setFaxNumber(properties.getProperty("UPDATE_FAXNUMBER"));
	    	supplier.setDescription(properties.getProperty("UPDATE_DESCRIPTION"));
	    	
	    	supplierRepo.save(supplier);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        supplier = supplierRepo.findOne(SUPPLIER_ID);
	        
	        assertEquals(supplier.getName(), properties.getProperty("UPDATE_NAME"));
	        assertEquals(supplier.getTaxId(), Long.parseLong(properties.getProperty("UPDATE_TAXID")));
	        assertEquals(supplier.getAddress(), properties.getProperty("UPDATE_ADDRESS"));
	        assertEquals(supplier.getZipCode(), properties.getProperty("UPDATE_ZIPCODE"));
	        assertEquals(supplier.getCity(), properties.getProperty("UPDATE_CITY"));
	        assertEquals(supplier.getRegion(), properties.getProperty("UPDATE_REGION"));
	        assertEquals(supplier.getCountry(), properties.getProperty("UPDATE_COUNTRY"));
	        assertEquals(supplier.getEmail(), properties.getProperty("UPDATE_EMAIL"));
	        assertEquals(supplier.getPhoneNumber(), properties.getProperty("UPDATE_PHONENUMBER"));
	        assertEquals(supplier.getMobileNumber(), properties.getProperty("UPDATE_MOBILENUMBER"));
	        assertEquals(supplier.getFaxNumber(), properties.getProperty("UPDATE_FAXNUMBER"));
	        assertEquals(supplier.getDescription(), properties.getProperty("UPDATE_DESCRIPTION"));
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
}
