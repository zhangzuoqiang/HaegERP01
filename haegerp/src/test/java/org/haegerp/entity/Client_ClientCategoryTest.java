package org.haegerp.entity;

import org.haegerp.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * Dieses Test Suite wird Kunden und Kundenkategorie testen
 * 
 * @author Wolf
 *
 */
public class Client_ClientCategoryTest extends TestCase {
	/**
     * Ein Testfall wird erstellt
     *
     * @param testName name vom Testfall
     */
    public Client_ClientCategoryTest( String testName )
    {
        super( testName );
    }

    /**
     * @return Die Testfälle für testen
     */
    public static Test suite()
    {	
        return new TestSuite( Client_ClientCategoryTest.class );
    }
    
    //Kunde Felder
    //Erstellung
    private static String INSERT_C_NAME = "T-Systems";
    private static long INSERT_C_TAXID = Long.parseLong("123456789012345");
    private static String INSERT_C_ADDRESS = "Maximillian Str. 588";
    private static String INSERT_C_ZIPCODE = "53847";
    private static String INSERT_C_CITY = "Bonn";
    private static String INSERT_C_REGION = "Nordrhein-Westfallen";
    private static String INSERT_C_COUNTRY = "Germany";
    private static String INSERT_C_EMAIL = "payments@T-Systems.de";
    private static String INSERT_C_PHONENUMBER = "+492281231231";
    private static String INSERT_C_MOBILENUMBER = "+4917612312312";
    private static String INSERT_C_FAXNUMBER = "492281231232";
    private static String INSERT_C_DESCRIPTION = "Best Client";
    
    //Änderung
    private static String UPDATE_C_NAME = "Deutsche Telekom";
    private static long UPDATE_C_TAXID = Long.parseLong("543210987654321");
    private static String UPDATE_C_ADDRESS = "Konigswinter Str. 347";
    private static String UPDATE_C_ZIPCODE = "54812";
    private static String UPDATE_C_CITY = "Lisbon";
    private static String UPDATE_C_REGION = "Nordrhein-Westfallen";
    private static String UPDATE_C_COUNTRY = "Portugal";
    private static String UPDATE_C_EMAIL = "orders@T-Systems.de";
    private static String UPDATE_C_PHONENUMBER = "+351211234567";
    private static String UPDATE_C_MOBILENUMBER = "+351911234567";
    private static String UPDATE_C_FAXNUMBER = "+351211234568";
    private static String UPDATE_C_DESCRIPTION = "One of the best clients we have.";
    
    //Kundenkategorie Felder
    //Erstellung
    private static String INSERT_CC_NAME = "Snacks";
    private static String INSERT_CC_DESCRIPTION = "Twix, Snickers, Milka...";
    
    //Änderung
    private static String UPDATE_CC_NAME = "Meals";
    private static String UPDATE_CC_DESCRIPTION = "Real Food";
    
    //Abfragen
    //Kundenkategorie
    private static String QUERY_CC_BY_ID = "from ClientCategory where idClientCategory = ";
    
    //Kunde
    private static String QUERY_C_BY_ID = "from Client where idBusinessPartner = ";
    
    /**
     * Eine Kundenkategorie wird in die Datenbank erstellt
     */
    public void test1InsertClientCategory()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
	        ClientCategory clientCategory = new ClientCategory();
	        clientCategory.setName(INSERT_CC_NAME);
	        clientCategory.setDescription(INSERT_CC_DESCRIPTION);
	        
	        HibernateUtil.insert(clientCategory, session);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        QUERY_CC_BY_ID = QUERY_CC_BY_ID + clientCategory.getIdClientCategory();
	        
	        clientCategory = (ClientCategory) HibernateUtil.selectObject(QUERY_CC_BY_ID, session);
	        
	        assertEquals(clientCategory.getName(), INSERT_CC_NAME);
	        assertEquals(clientCategory.getDescription(), INSERT_CC_DESCRIPTION);
        } catch (Exception ex) {
        	ex.printStackTrace();
        } finally {
        	if (session.isOpen())
        		session.close();
        }
    }
    
    /**
     * Die letzte Kundenkategorie wird geändert
     */
    public void test2UpdateClientCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		ClientCategory clientCategory = (ClientCategory) HibernateUtil.selectList(QUERY_CC_BY_ID, session);
	    	
	        clientCategory.setName(UPDATE_CC_NAME);
	        clientCategory.setDescription(UPDATE_CC_DESCRIPTION);
	        
	        HibernateUtil.update(clientCategory, session);
	        
	        clientCategory = (ClientCategory) HibernateUtil.selectObject(QUERY_CC_BY_ID, session);
	        
	        assertEquals(clientCategory.getName(), UPDATE_CC_NAME);
	        assertEquals(clientCategory.getDescription(), UPDATE_CC_DESCRIPTION);
    	} catch (Exception ex) {
        	ex.printStackTrace();
        } finally {
        	if (session.isOpen())
        		session.close();
        }
    }
    
    /**
     * Ein Kunden wird erstellt
     */
    public void test3InsertClient(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	    	//Die Artikelkategorie wird geholt
	        ClientCategory clientCategory = (ClientCategory) HibernateUtil.selectObject(QUERY_CC_BY_ID, session);
	    	
	        Client client = new Client();
	        
	        //Die Felder werden gefüllt
	        client.setClientCategory(clientCategory);
	        client.setAddress(INSERT_C_ADDRESS);
	        client.setCity(INSERT_C_CITY);
	        client.setCountry(INSERT_C_COUNTRY);
	        client.setDescription(INSERT_C_DESCRIPTION);
	        client.setEmail(INSERT_C_EMAIL);
	        client.setFaxNumber(INSERT_C_FAXNUMBER);
	        client.setMobileNumber(INSERT_C_MOBILENUMBER);
	        client.setName(INSERT_C_NAME);
	        client.setPhoneNumber(INSERT_C_PHONENUMBER);
	        client.setRegion(INSERT_C_REGION);
	        client.setTaxId(INSERT_C_TAXID);
	        client.setZipCode(INSERT_C_ZIPCODE);
	        
	        HibernateUtil.insert(client, session);
	        
	        //Der erstellter Artikel wird geprüft
	        QUERY_C_BY_ID = QUERY_C_BY_ID + client.getIdBusinessPartner();

	        client = (Client) HibernateUtil.selectObject(QUERY_C_BY_ID, session);
	        
	        assertEquals(client.getAddress(), INSERT_C_ADDRESS);
	        assertEquals(client.getCity(), INSERT_C_CITY);
	        assertEquals(client.getCountry(), INSERT_C_COUNTRY);
	        assertEquals(client.getDescription(), INSERT_C_DESCRIPTION);
	        assertEquals(client.getEmail(), INSERT_C_EMAIL);
	        assertEquals(client.getFaxNumber(), INSERT_C_FAXNUMBER);
	        assertEquals(client.getMobileNumber(), INSERT_C_MOBILENUMBER);
	        assertEquals(client.getName(), INSERT_C_NAME);
	        assertEquals(client.getPhoneNumber(), INSERT_C_PHONENUMBER);
	        assertEquals(client.getRegion(), INSERT_C_REGION);
	        assertEquals(client.getTaxId(), INSERT_C_TAXID);
	        assertEquals(client.getZipCode(), INSERT_C_ZIPCODE);
	        assertEquals(client.getClientCategory(), clientCategory);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Der letzter Kunde wird geändert
     */
    public void test4UpdateClient(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	    	//Die Artikelkategorie wird geholt
	        Client client = (Client) HibernateUtil.selectObject(QUERY_C_BY_ID, session);
	    	
	        //Die Felder werden geändert
	        client.setAddress(UPDATE_C_ADDRESS);
	        client.setCity(UPDATE_C_CITY);
	        client.setCountry(UPDATE_C_COUNTRY);
	        client.setDescription(UPDATE_C_DESCRIPTION);
	        client.setEmail(UPDATE_C_EMAIL);
	        client.setFaxNumber(UPDATE_C_FAXNUMBER);
	        client.setMobileNumber(UPDATE_C_MOBILENUMBER);
	        client.setName(UPDATE_C_NAME);
	        client.setPhoneNumber(UPDATE_C_PHONENUMBER);
	        client.setRegion(UPDATE_C_REGION);
	        client.setTaxId(UPDATE_C_TAXID);
	        client.setZipCode(UPDATE_C_ZIPCODE);
	        
	        HibernateUtil.update(client, session);
	        
	        //Der geänderter Artikel wird geprüft
	        client = (Client) HibernateUtil.selectObject(QUERY_C_BY_ID, session);
	        
	        assertEquals(client.getAddress(), UPDATE_C_ADDRESS);
	        assertEquals(client.getCity(), UPDATE_C_CITY);
	        assertEquals(client.getCountry(), UPDATE_C_COUNTRY);
	        assertEquals(client.getDescription(), UPDATE_C_DESCRIPTION);
	        assertEquals(client.getEmail(), UPDATE_C_EMAIL);
	        assertEquals(client.getFaxNumber(), UPDATE_C_FAXNUMBER);
	        assertEquals(client.getMobileNumber(), UPDATE_C_MOBILENUMBER);
	        assertEquals(client.getName(), UPDATE_C_NAME);
	        assertEquals(client.getPhoneNumber(), UPDATE_C_PHONENUMBER);
	        assertEquals(client.getRegion(), UPDATE_C_REGION);
	        assertEquals(client.getTaxId(), UPDATE_C_TAXID);
	        assertEquals(client.getZipCode(), UPDATE_C_ZIPCODE);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Der letzter Artikel wird gelöscht
     */
    public void test5DeleteClient(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	        Client client = (Client) HibernateUtil.selectObject(QUERY_C_BY_ID, session);
	    	
	        HibernateUtil.delete(client, session);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(HibernateUtil.selectList(QUERY_C_BY_ID, session).isEmpty());
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Die Kundenkategorie wird gelöscht
     */
    public void test6DeleteArticleCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	        ClientCategory clientCategory = (ClientCategory) HibernateUtil.selectObject(QUERY_CC_BY_ID, session);
	    	
	        HibernateUtil.delete(clientCategory, session);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(HibernateUtil.selectList(QUERY_CC_BY_ID, session).isEmpty());
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
}
