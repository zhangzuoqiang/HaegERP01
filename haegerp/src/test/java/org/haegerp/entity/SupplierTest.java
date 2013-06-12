package org.haegerp.entity;

import java.io.FileInputStream;
import java.util.Properties;

import org.haegerp.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * Dieses Test Suite wird Lieferantgeschäftspartner testen
 * 
 * @author Wolf
 *
 */
public class SupplierTest extends TestCase {
	/**
     * Ein Testfall wird erstellt
     *
     * @param testName name vom Testfall
     */
    public SupplierTest( String testName )
    {
        super( testName );
    }

    /**
     * @return Die Testfälle für testen
     */
    public static Test suite()
    {	
        return new TestSuite( SupplierTest.class );
    }
    
    Properties properties = new Properties();
    
    //Abfragen
    private static String QUERY_BY_ID = "FROM Supplier WHERE idBusinessPartner = ";
    
    /**
     * Ein Lieferant wird in die Datenbank erstellt
     */
    public void test1InsertSupplier(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
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
	    	
	    	HibernateUtil.insert(supplier, session);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	    	QUERY_BY_ID = QUERY_BY_ID + supplier.getIdBusinessPartner();
	        supplier = (Supplier) HibernateUtil.selectObject(QUERY_BY_ID, session);
	        
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
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Der letzter Lieferant wird in die Datenbank ändert
     */
    public void test2UpdateSupplier(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        Supplier supplier = (Supplier) HibernateUtil.selectObject(QUERY_BY_ID, session);
	        
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
	    	
	    	HibernateUtil.update(supplier, session);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        supplier = (Supplier) HibernateUtil.selectObject(QUERY_BY_ID, session);
	        
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
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Der Liferant wird von der Datenbank gelöscht
     */
    public void test3DeleteSupplier()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	        Supplier supplier = (Supplier) HibernateUtil.selectObject(QUERY_BY_ID, session);
	    	
	        HibernateUtil.delete(supplier, session);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(HibernateUtil.selectList(QUERY_BY_ID, session).isEmpty());
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
}
