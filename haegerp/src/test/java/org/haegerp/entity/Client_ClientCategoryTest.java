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
    
    Properties properties = new Properties();
    
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
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        ClientCategory clientCategory = new ClientCategory();
	        clientCategory.setName(properties.getProperty("INSERT_CC_NAME"));
	        clientCategory.setDescription(properties.getProperty("INSERT_CC_DESCRIPTION"));
	        
	        HibernateUtil.insert(clientCategory, session);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        QUERY_CC_BY_ID = QUERY_CC_BY_ID + clientCategory.getIdClientCategory();
	        
	        clientCategory = (ClientCategory) HibernateUtil.selectObject(QUERY_CC_BY_ID, session);
	        
	        assertEquals(clientCategory.getName(), properties.getProperty("INSERT_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), properties.getProperty("INSERT_CC_DESCRIPTION"));
        } catch (Exception ex) {
        	ex.printStackTrace();
	    	fail(ex.getMessage());
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
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
    		ClientCategory clientCategory = (ClientCategory) HibernateUtil.selectObject(QUERY_CC_BY_ID, session);
	    	
	        clientCategory.setName(properties.getProperty("UPDATE_CC_NAME"));
	        clientCategory.setDescription(properties.getProperty("UPDATE_CC_DESCRIPTION"));
	        
	        HibernateUtil.update(clientCategory, session);
	        
	        clientCategory = (ClientCategory) HibernateUtil.selectObject(QUERY_CC_BY_ID, session);
	        
	        assertEquals(clientCategory.getName(), properties.getProperty("UPDATE_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), properties.getProperty("UPDATE_CC_DESCRIPTION"));
    	} catch (Exception ex) {
        	ex.printStackTrace();
	    	fail(ex.getMessage());
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
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	    	//Die Artikelkategorie wird geholt
	        ClientCategory clientCategory = (ClientCategory) HibernateUtil.selectObject(QUERY_CC_BY_ID, session);
	    	
	        Client client = new Client();
	        
	        //Die Felder werden gefüllt
	        client.setClientCategory(clientCategory);
	        client.setAddress(properties.getProperty("INSERT_C_ADDRESS"));
	        client.setCity(properties.getProperty("INSERT_C_CITY"));
	        client.setCountry(properties.getProperty("INSERT_C_COUNTRY"));
	        client.setDescription(properties.getProperty("INSERT_C_DESCRIPTION"));
	        client.setEmail(properties.getProperty("INSERT_C_EMAIL"));
	        client.setFaxNumber(properties.getProperty("INSERT_C_FAXNUMBER"));
	        client.setMobileNumber(properties.getProperty("INSERT_C_MOBILENUMBER"));
	        client.setName(properties.getProperty("INSERT_C_NAME"));
	        client.setPhoneNumber(properties.getProperty("INSERT_C_PHONENUMBER"));
	        client.setRegion(properties.getProperty("INSERT_C_REGION"));
	        client.setTaxId(Long.parseLong(properties.getProperty("INSERT_C_TAXID")));
	        client.setZipCode(properties.getProperty("INSERT_C_ZIPCODE"));
	        
	        HibernateUtil.insert(client, session);
	        
	        //Der erstellter Artikel wird geprüft
	        QUERY_C_BY_ID = QUERY_C_BY_ID + client.getIdBusinessPartner();

	        client = (Client) HibernateUtil.selectObject(QUERY_C_BY_ID, session);
	        
	        assertEquals(client.getAddress(), properties.getProperty("INSERT_C_ADDRESS"));
	        assertEquals(client.getCity(), properties.getProperty("INSERT_C_CITY"));
	        assertEquals(client.getCountry(), properties.getProperty("INSERT_C_COUNTRY"));
	        assertEquals(client.getDescription(), properties.getProperty("INSERT_C_DESCRIPTION"));
	        assertEquals(client.getEmail(), properties.getProperty("INSERT_C_EMAIL"));
	        assertEquals(client.getFaxNumber(), properties.getProperty("INSERT_C_FAXNUMBER"));
	        assertEquals(client.getMobileNumber(), properties.getProperty("INSERT_C_MOBILENUMBER"));
	        assertEquals(client.getName(), properties.getProperty("INSERT_C_NAME"));
	        assertEquals(client.getPhoneNumber(), properties.getProperty("INSERT_C_PHONENUMBER"));
	        assertEquals(client.getRegion(), properties.getProperty("INSERT_C_REGION"));
	        assertEquals(client.getTaxId(), Long.parseLong(properties.getProperty("INSERT_C_TAXID")));
	        assertEquals(client.getZipCode(), properties.getProperty("INSERT_C_ZIPCODE"));
	        assertEquals(client.getClientCategory(), clientCategory);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
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
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	    	//Die Artikelkategorie wird geholt
	        Client client = (Client) HibernateUtil.selectObject(QUERY_C_BY_ID, session);
	    	
	        //Die Felder werden geändert
	        client.setAddress(properties.getProperty("UPDATE_C_ADDRESS"));
	        client.setCity(properties.getProperty("UPDATE_C_CITY"));
	        client.setCountry(properties.getProperty("UPDATE_C_COUNTRY"));
	        client.setDescription(properties.getProperty("UPDATE_C_DESCRIPTION"));
	        client.setEmail(properties.getProperty("UPDATE_C_EMAIL"));
	        client.setFaxNumber(properties.getProperty("UPDATE_C_FAXNUMBER"));
	        client.setMobileNumber(properties.getProperty("UPDATE_C_MOBILENUMBER"));
	        client.setName(properties.getProperty("UPDATE_C_NAME"));
	        client.setPhoneNumber(properties.getProperty("UPDATE_C_PHONENUMBER"));
	        client.setRegion(properties.getProperty("UPDATE_C_REGION"));
	        client.setTaxId(Long.parseLong(properties.getProperty("UPDATE_C_TAXID")));
	        client.setZipCode(properties.getProperty("UPDATE_C_ZIPCODE"));
	        
	        HibernateUtil.update(client, session);
	        
	        //Der geänderter Artikel wird geprüft
	        client = (Client) HibernateUtil.selectObject(QUERY_C_BY_ID, session);
	        
	        assertEquals(client.getAddress(), properties.getProperty("UPDATE_C_ADDRESS"));
	        assertEquals(client.getCity(), properties.getProperty("UPDATE_C_CITY"));
	        assertEquals(client.getCountry(), properties.getProperty("UPDATE_C_COUNTRY"));
	        assertEquals(client.getDescription(), properties.getProperty("UPDATE_C_DESCRIPTION"));
	        assertEquals(client.getEmail(), properties.getProperty("UPDATE_C_EMAIL"));
	        assertEquals(client.getFaxNumber(), properties.getProperty("UPDATE_C_FAXNUMBER"));
	        assertEquals(client.getMobileNumber(), properties.getProperty("UPDATE_C_MOBILENUMBER"));
	        assertEquals(client.getName(), properties.getProperty("UPDATE_C_NAME"));
	        assertEquals(client.getPhoneNumber(), properties.getProperty("UPDATE_C_PHONENUMBER"));
	        assertEquals(client.getRegion(), properties.getProperty("UPDATE_C_REGION"));
	        assertEquals(client.getTaxId(), Long.parseLong(properties.getProperty("UPDATE_C_TAXID")));
	        assertEquals(client.getZipCode(), properties.getProperty("UPDATE_C_ZIPCODE"));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
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
	    	fail(ex.getMessage());
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
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
}
