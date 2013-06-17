package org.haegerp.entity;

import java.io.FileInputStream;
import java.util.Properties;

import org.haegerp.entity.repository.ClientCategoryRepository;
import org.haegerp.entity.repository.ClientRepository;
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
 * Dieses Test Suite wird Kunden und Kundenkategorie testen
 * 
 * @author Wolf
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/spring-data.xml")
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class Client_ClientCategoryTest extends TestCase {
    
    private static Properties properties = new Properties();
    
    @Autowired
    private ClientCategoryRepository clientCategoryRepo;
    
    @Autowired
    private ClientRepository clientRepository;
    
    private static long CLIENT_CATEGORY_ID;
    private static long CLIENT_ID;
    
    @Override
    @Before
    public void setUp() throws Exception {
    	super.setUp();
    	properties.load(new FileInputStream("./config.properties"));
    }
    
    /**
     * Eine Kundenkategorie wird in die Datenbank erstellt
     */
    @Test
    public void test1InsertClientCategory()
    {
        try {
	        ClientCategory clientCategory = new ClientCategory();
	        clientCategory.setName(properties.getProperty("INSERT_CC_NAME"));
	        clientCategory.setDescription(properties.getProperty("INSERT_CC_DESCRIPTION"));
	        
	        clientCategory = clientCategoryRepo.save(clientCategory);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        CLIENT_CATEGORY_ID = clientCategory.getIdClientCategory();
	        
	        clientCategory = clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	        
	        assertEquals(clientCategory.getName(), properties.getProperty("INSERT_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), properties.getProperty("INSERT_CC_DESCRIPTION"));
        } catch (Exception ex) {
        	ex.printStackTrace();
	    	fail(ex.getMessage());
        }
    }
    
    /**
     * Die letzte Kundenkategorie wird geändert
     */
    @Test
    public void test2UpdateClientCategory()
    {
    	try {
    		ClientCategory clientCategory = clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	    	
	        clientCategory.setName(properties.getProperty("UPDATE_CC_NAME"));
	        clientCategory.setDescription(properties.getProperty("UPDATE_CC_DESCRIPTION"));
	        
	        clientCategoryRepo.save(clientCategory);
	        
	        clientCategory = clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	        
	        assertEquals(clientCategory.getName(), properties.getProperty("UPDATE_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), properties.getProperty("UPDATE_CC_DESCRIPTION"));
    	} catch (Exception ex) {
        	ex.printStackTrace();
	    	fail(ex.getMessage());
        }
    }
    
    /**
     * Ein Kunden wird erstellt
     */
    @Test
    public void test3InsertClient(){
    	try {
	    	//Die Artikelkategorie wird geholt
	        ClientCategory clientCategory = (ClientCategory) clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	    	
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
	        
	        client = clientRepository.save(client);
	        
	        //Der erstellter Artikel wird geprüft
	        CLIENT_ID = client.getIdBusinessPartner();

	        client = clientRepository.findOne(CLIENT_ID);
	        
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
	    }
    }
    
    /**
     * Der letzter Kunde wird geändert
     */
    @Test
    public void test4UpdateClient(){
    	try {
	    	//Die Artikelkategorie wird geholt
	        Client client = clientRepository.findOne(CLIENT_ID);
	    	
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
	        
	        client = clientRepository.save(client);
	        
	        //Der geänderter Artikel wird geprüft
	        client = clientRepository.findOne(CLIENT_ID);
	        
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
	    }
    }
    
    /**
     * Der letzter Artikel wird gelöscht
     */
    @Test
    public void test5DeleteClient(){
    	try {
	        Client client = clientRepository.findOne(CLIENT_ID);
	    	
	        clientRepository.delete(client);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!clientRepository.exists(CLIENT_ID));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } 
    }
    
    /**
     * Die Kundenkategorie wird gelöscht
     */
    @Test
    public void test6DeleteArticleCategory()
    {
    	try {
	        ClientCategory clientCategory = clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	    	
	        clientCategoryRepo.delete(clientCategory);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!clientCategoryRepo.exists(CLIENT_CATEGORY_ID));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
}
