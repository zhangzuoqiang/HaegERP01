package org.haegerp.entity;

import org.haegerp.Properties;
import org.haegerp.entity.repository.client.ClientCategoryRepository;
import org.haegerp.entity.repository.client.ClientRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.exception.LengthOverflowException;
import org.haegerp.session.EmployeeSession;
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
    
    @Autowired
    private ClientCategoryRepository clientCategoryRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    private static long CLIENT_CATEGORY_ID;
    private static long CLIENT_ID;
    private static boolean CHECK_SETUP = true;
    
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
     * Eine Kundenkategorie wird in die Datenbank erstellt
     */
    @Test
    public void test1InsertClientCategory()
    {
        try {
	        ClientCategory clientCategory = new ClientCategory();
	        clientCategory.setName(Properties.getProperty("INSERT_CC_NAME"));
	        clientCategory.setDescription(Properties.getProperty("INSERT_CC_DESCRIPTION"));
	        
	        clientCategory = clientCategoryRepository.save(clientCategory);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        CLIENT_CATEGORY_ID = clientCategory.getIdClientCategory();
	        
	        clientCategory = clientCategoryRepository.findOne(CLIENT_CATEGORY_ID);
	        
	        assertEquals(clientCategory.getName(), Properties.getProperty("INSERT_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), Properties.getProperty("INSERT_CC_DESCRIPTION"));
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
    		ClientCategory clientCategory = clientCategoryRepository.findOne(CLIENT_CATEGORY_ID);
	    	
	        clientCategory.setName(Properties.getProperty("UPDATE_CC_NAME"));
	        clientCategory.setDescription(Properties.getProperty("UPDATE_CC_DESCRIPTION"));
	        
	        clientCategoryRepository.save(clientCategory);
	        
	        clientCategory = clientCategoryRepository.findOne(CLIENT_CATEGORY_ID);
	        
	        assertEquals(clientCategory.getName(), Properties.getProperty("UPDATE_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), Properties.getProperty("UPDATE_CC_DESCRIPTION"));
    	} catch (Exception ex) {
        	ex.printStackTrace();
	    	fail(ex.getMessage());
        }
    }
    
    /**
     * Ein Kunde wird erstellt
     */
    @Test
    public void test3InsertClient(){
    	try {
	    	//Die Artikelkategorie wird geholt
	        ClientCategory clientCategory = (ClientCategory) clientCategoryRepository.findOne(CLIENT_CATEGORY_ID);
	    	
	        Client client = new Client();
	        
	        //Die Felder werden gefüllt
	        client.setClientCategory(clientCategory);
	        client.setAddress(Properties.getProperty("INSERT_C_ADDRESS"));
	        client.setCity(Properties.getProperty("INSERT_C_CITY"));
	        client.setCountry(Properties.getProperty("INSERT_C_COUNTRY"));
	        client.setDescription(Properties.getProperty("INSERT_C_DESCRIPTION"));
	        client.setEmail(Properties.getProperty("INSERT_C_EMAIL"));
	        client.setFaxNumber(Properties.getProperty("INSERT_C_FAXNUMBER"));
	        client.setMobileNumber(Properties.getProperty("INSERT_C_MOBILENUMBER"));
	        client.setName(Properties.getProperty("INSERT_C_NAME"));
	        client.setPhoneNumber(Properties.getProperty("INSERT_C_PHONENUMBER"));
	        client.setRegion(Properties.getProperty("INSERT_C_REGION"));
	        client.setTaxId(Long.parseLong(Properties.getProperty("INSERT_C_TAXID")));
	        client.setZipCode(Properties.getProperty("INSERT_C_ZIPCODE"));
	        
	        client = clientRepository.save(client);
	        
	        //Der erstellter Artikel wird geprüft
	        CLIENT_ID = client.getIdBusinessPartner();

	        client = clientRepository.findOne(CLIENT_ID);
	        
	        assertEquals(client.getAddress(), Properties.getProperty("INSERT_C_ADDRESS"));
	        assertEquals(client.getCity(), Properties.getProperty("INSERT_C_CITY"));
	        assertEquals(client.getCountry(), Properties.getProperty("INSERT_C_COUNTRY"));
	        assertEquals(client.getDescription(), Properties.getProperty("INSERT_C_DESCRIPTION"));
	        assertEquals(client.getEmail(), Properties.getProperty("INSERT_C_EMAIL"));
	        assertEquals(client.getFaxNumber(), Properties.getProperty("INSERT_C_FAXNUMBER"));
	        assertEquals(client.getMobileNumber(), Properties.getProperty("INSERT_C_MOBILENUMBER"));
	        assertEquals(client.getName(), Properties.getProperty("INSERT_C_NAME"));
	        assertEquals(client.getPhoneNumber(), Properties.getProperty("INSERT_C_PHONENUMBER"));
	        assertEquals(client.getRegion(), Properties.getProperty("INSERT_C_REGION"));
	        assertEquals(client.getTaxId(), Long.parseLong(Properties.getProperty("INSERT_C_TAXID")));
	        assertEquals(client.getZipCode(), Properties.getProperty("INSERT_C_ZIPCODE"));
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
	        client.setAddress(Properties.getProperty("UPDATE_C_ADDRESS"));
	        client.setCity(Properties.getProperty("UPDATE_C_CITY"));
	        client.setCountry(Properties.getProperty("UPDATE_C_COUNTRY"));
	        client.setDescription(Properties.getProperty("UPDATE_C_DESCRIPTION"));
	        client.setEmail(Properties.getProperty("UPDATE_C_EMAIL"));
	        client.setFaxNumber(Properties.getProperty("UPDATE_C_FAXNUMBER"));
	        client.setMobileNumber(Properties.getProperty("UPDATE_C_MOBILENUMBER"));
	        client.setName(Properties.getProperty("UPDATE_C_NAME"));
	        client.setPhoneNumber(Properties.getProperty("UPDATE_C_PHONENUMBER"));
	        client.setRegion(Properties.getProperty("UPDATE_C_REGION"));
	        client.setTaxId(Long.parseLong(Properties.getProperty("UPDATE_C_TAXID")));
	        client.setZipCode(Properties.getProperty("UPDATE_C_ZIPCODE"));
	        
	        client = clientRepository.save(client);
	        
	        //Der geänderter Artikel wird geprüft
	        client = clientRepository.findOne(CLIENT_ID);
	        
	        assertEquals(client.getAddress(), Properties.getProperty("UPDATE_C_ADDRESS"));
	        assertEquals(client.getCity(), Properties.getProperty("UPDATE_C_CITY"));
	        assertEquals(client.getCountry(), Properties.getProperty("UPDATE_C_COUNTRY"));
	        assertEquals(client.getDescription(), Properties.getProperty("UPDATE_C_DESCRIPTION"));
	        assertEquals(client.getEmail(), Properties.getProperty("UPDATE_C_EMAIL"));
	        assertEquals(client.getFaxNumber(), Properties.getProperty("UPDATE_C_FAXNUMBER"));
	        assertEquals(client.getMobileNumber(), Properties.getProperty("UPDATE_C_MOBILENUMBER"));
	        assertEquals(client.getName(), Properties.getProperty("UPDATE_C_NAME"));
	        assertEquals(client.getPhoneNumber(), Properties.getProperty("UPDATE_C_PHONENUMBER"));
	        assertEquals(client.getRegion(), Properties.getProperty("UPDATE_C_REGION"));
	        assertEquals(client.getTaxId(), Long.parseLong(Properties.getProperty("UPDATE_C_TAXID")));
	        assertEquals(client.getZipCode(), Properties.getProperty("UPDATE_C_ZIPCODE"));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Der letzter Kunde wird gelöscht
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
    public void test6DeleteClientCategory()
    {
    	try {
	        ClientCategory clientCategory = clientCategoryRepository.findOne(CLIENT_CATEGORY_ID);
	    	
	        clientCategoryRepository.delete(clientCategory);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!clientCategoryRepository.exists(CLIENT_CATEGORY_ID));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Fehlerprovokation ClientCategory
     * @throws Exception 
     */
    @Test(expected=LengthOverflowException.class)
    public void test7InsertClientCategoryError() throws Exception
    {
    	try {
	        ClientCategory clientCategory = new ClientCategory();
	        clientCategory.setName(Properties.getProperty("INSERT_CC_NAME_F"));
	        clientCategory.setDescription(Properties.getProperty("INSERT_CC_DESCRIPTION_F"));
	        
	        clientCategory = clientCategoryRepository.save(clientCategory);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        CLIENT_CATEGORY_ID = clientCategory.getIdClientCategory();
	        
	        clientCategory = clientCategoryRepository.findOne(CLIENT_CATEGORY_ID);
	        
	        assertEquals(clientCategory.getName(), Properties.getProperty("INSERT_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), Properties.getProperty("INSERT_CC_DESCRIPTION"));
        } catch (Exception ex) {
        	throw ex;
        }
    }
    
    /**
     * Fehlerprovokation Client
     * @throws Exception 
     */
    @Test(expected=LengthOverflowException.class)
    public void test8InsertClientError() throws Exception
    {
    	try {
	    	//Die Artikelkategorie wird geholt
	        ClientCategory clientCategory = (ClientCategory) clientCategoryRepository.findOne(CLIENT_CATEGORY_ID);
	    	
	        Client client = new Client();
	        
	        //Die Felder werden gefüllt
	        client.setClientCategory(clientCategory);
	        client.setAddress(Properties.getProperty("INSERT_C_ADDRESS_F"));
	        client.setCity(Properties.getProperty("INSERT_C_CITY_F"));
	        client.setCountry(Properties.getProperty("INSERT_C_COUNTRY_F"));
	        client.setDescription(Properties.getProperty("INSERT_C_DESCRIPTION_F"));
	        client.setEmail(Properties.getProperty("INSERT_C_EMAIL_F"));
	        client.setFaxNumber(Properties.getProperty("INSERT_C_FAXNUMBER_F"));
	        client.setMobileNumber(Properties.getProperty("INSERT_C_MOBILENUMBER_F"));
	        client.setName(Properties.getProperty("INSERT_C_NAME_F"));
	        client.setPhoneNumber(Properties.getProperty("INSERT_C_PHONENUMBER_F"));
	        client.setRegion(Properties.getProperty("INSERT_C_REGION_F"));
	        client.setTaxId(Long.parseLong(Properties.getProperty("INSERT_C_TAXID_F")));
	        client.setZipCode(Properties.getProperty("INSERT_C_ZIPCODE_F"));
	        
	        client = clientRepository.save(client);
	        
	        //Der erstellter Artikel wird geprüft
	        CLIENT_ID = client.getIdBusinessPartner();

	        client = clientRepository.findOne(CLIENT_ID);
	        
	        assertEquals(client.getAddress(), Properties.getProperty("INSERT_C_ADDRESS"));
	        assertEquals(client.getCity(), Properties.getProperty("INSERT_C_CITY"));
	        assertEquals(client.getCountry(), Properties.getProperty("INSERT_C_COUNTRY"));
	        assertEquals(client.getDescription(), Properties.getProperty("INSERT_C_DESCRIPTION"));
	        assertEquals(client.getEmail(), Properties.getProperty("INSERT_C_EMAIL"));
	        assertEquals(client.getFaxNumber(), Properties.getProperty("INSERT_C_FAXNUMBER"));
	        assertEquals(client.getMobileNumber(), Properties.getProperty("INSERT_C_MOBILENUMBER"));
	        assertEquals(client.getName(), Properties.getProperty("INSERT_C_NAME"));
	        assertEquals(client.getPhoneNumber(), Properties.getProperty("INSERT_C_PHONENUMBER"));
	        assertEquals(client.getRegion(), Properties.getProperty("INSERT_C_REGION"));
	        assertEquals(client.getTaxId(), Long.parseLong(Properties.getProperty("INSERT_C_TAXID")));
	        assertEquals(client.getZipCode(), Properties.getProperty("INSERT_C_ZIPCODE"));
	        assertEquals(client.getClientCategory(), clientCategory);
	    } catch (Exception ex) {
	    	throw ex;
	    }
    }
}
