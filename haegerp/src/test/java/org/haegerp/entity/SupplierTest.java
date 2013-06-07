package org.haegerp.entity;

import org.haegerp.util.HibernateUtil;
import org.hibernate.Query;
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
    
    //Lieferant Felder
    //Erstellung
    private static String INSERT_NAME = "Food Supplier Inc.";
    private static long INSERT_TAXID = Long.parseLong("123456789012345");
    private static String INSERT_ADDRESS = "Maximillian Str. 304";
    private static String INSERT_ZIPCODE = "53333";
    private static String INSERT_CITY = "Bonn";
    private static String INSERT_REGION = "Nordrhein-Westfallen";
    private static String INSERT_COUNTRY = "Germany";
    private static String INSERT_EMAIL = "billing@food4all.de";
    private static String INSERT_PHONENUMBER = "+492281234567";
    private static String INSERT_MOBILENUMBER = "+4917612345678";
    private static String INSERT_FAXNUMBER = "492281234568";
    private static String INSERT_DESCRIPTION = "Best Supplier for Food";
    
    //Änderung
    private static String UPDATE_NAME = "Crazy Vegetables Inc.";
    private static long UPDATE_TAXID = Long.parseLong("543210987654321");
    private static String UPDATE_ADDRESS = "Konigswinter Str. 9";
    private static String UPDATE_ZIPCODE = "53392";
    private static String UPDATE_CITY = "Lisbon";
    private static String UPDATE_REGION = "Nordrhein-Westfallen";
    private static String UPDATE_COUNTRY = "Portugal";
    private static String UPDATE_EMAIL = "order@food4all.de";
    private static String UPDATE_PHONENUMBER = "+351211234567";
    private static String UPDATE_MOBILENUMBER = "+351911234567";
    private static String UPDATE_FAXNUMBER = "+351211234568";
    private static String UPDATE_DESCRIPTION = "Cheap Food Supplier";
    
    //Abfragen
    private static String QUERY_BY_ID = "FROM Supplier WHERE idBusinessPartner = ";
    
    /**
     * Ein Lieferant wird in die Datenbank erstellt
     */
    public void test1InsertSupplier(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
    	Supplier supplier = new Supplier();
    	supplier.setName(INSERT_NAME);
    	supplier.setTaxId(INSERT_TAXID);
    	supplier.setAddress(INSERT_ADDRESS);
    	supplier.setZipCode(INSERT_ZIPCODE);
    	supplier.setCity(INSERT_CITY);
    	supplier.setRegion(INSERT_REGION);
    	supplier.setCountry(INSERT_COUNTRY);
    	supplier.setEmail(INSERT_EMAIL);
    	supplier.setPhoneNumber(INSERT_PHONENUMBER);
    	supplier.setMobileNumber(INSERT_MOBILENUMBER);
    	supplier.setFaxNumber(INSERT_FAXNUMBER);
    	supplier.setDescription(INSERT_DESCRIPTION);
    	
    	session.beginTransaction();
    	session.save(supplier);
        session.getTransaction().commit();
        
        //Die erstellt Artikelkategorie wird geprüft
        session.beginTransaction();
        QUERY_BY_ID = QUERY_BY_ID + supplier.getIdBusinessPartner();
        Query query = session.createQuery(QUERY_BY_ID);
        session.getTransaction().commit();
        
        supplier = (Supplier) query.uniqueResult();
        
        assertEquals(supplier.getName(), INSERT_NAME);
        assertEquals(supplier.getTaxId(), INSERT_TAXID);
        assertEquals(supplier.getAddress(), INSERT_ADDRESS);
        assertEquals(supplier.getZipCode(), INSERT_ZIPCODE);
        assertEquals(supplier.getCity(), INSERT_CITY);
        assertEquals(supplier.getRegion(), INSERT_REGION);
        assertEquals(supplier.getCountry(), INSERT_COUNTRY);
        assertEquals(supplier.getEmail(), INSERT_EMAIL);
        assertEquals(supplier.getPhoneNumber(), INSERT_PHONENUMBER);
        assertEquals(supplier.getMobileNumber(), INSERT_MOBILENUMBER);
        assertEquals(supplier.getFaxNumber(), INSERT_FAXNUMBER);
        assertEquals(supplier.getDescription(), INSERT_DESCRIPTION);
    }
    
    /**
     * Der letzter Lieferant wird in die Datenbank ändert
     */
    public void test2UpdateSupplier(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(QUERY_BY_ID);
        session.getTransaction().commit();
        
        Supplier supplier = (Supplier) query.uniqueResult();
        
    	supplier.setName(UPDATE_NAME);
    	supplier.setTaxId(UPDATE_TAXID);
    	supplier.setAddress(UPDATE_ADDRESS);
    	supplier.setZipCode(UPDATE_ZIPCODE);
    	supplier.setCity(UPDATE_CITY);
    	supplier.setRegion(UPDATE_REGION);
    	supplier.setCountry(UPDATE_COUNTRY);
    	supplier.setEmail(UPDATE_EMAIL);
    	supplier.setPhoneNumber(UPDATE_PHONENUMBER);
    	supplier.setMobileNumber(UPDATE_MOBILENUMBER);
    	supplier.setFaxNumber(UPDATE_FAXNUMBER);
    	supplier.setDescription(UPDATE_DESCRIPTION);
    	
    	session.beginTransaction();
    	session.update(supplier);
        session.getTransaction().commit();
        
        //Die erstellt Artikelkategorie wird geprüft
        session.beginTransaction();
        query = session.createQuery(QUERY_BY_ID);
        session.getTransaction().commit();
        
        supplier = (Supplier) query.uniqueResult();
        
        assertEquals(supplier.getName(), UPDATE_NAME);
        assertEquals(supplier.getTaxId(), UPDATE_TAXID);
        assertEquals(supplier.getAddress(), UPDATE_ADDRESS);
        assertEquals(supplier.getZipCode(), UPDATE_ZIPCODE);
        assertEquals(supplier.getCity(), UPDATE_CITY);
        assertEquals(supplier.getRegion(), UPDATE_REGION);
        assertEquals(supplier.getCountry(), UPDATE_COUNTRY);
        assertEquals(supplier.getEmail(), UPDATE_EMAIL);
        assertEquals(supplier.getPhoneNumber(), UPDATE_PHONENUMBER);
        assertEquals(supplier.getMobileNumber(), UPDATE_MOBILENUMBER);
        assertEquals(supplier.getFaxNumber(), UPDATE_FAXNUMBER);
        assertEquals(supplier.getDescription(), UPDATE_DESCRIPTION);
    }
    
    /**
     * Der Liferant wird von der Datenbank gelöscht
     */
    public void test3DeleteSupplier()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        Query query = session.createQuery(QUERY_BY_ID);
        session.getTransaction().commit();
        
        Supplier supplier = (Supplier) query.uniqueResult();
    	
        session.beginTransaction();
        session.delete(supplier);
        session.getTransaction().commit();
        
        //Suchen noch einmal
        session.beginTransaction();
        query = session.createQuery(QUERY_BY_ID);
        session.getTransaction().commit();
        
        //keine Aufzeichnung gefunden
        assertTrue(query.list().isEmpty());
    }
}
