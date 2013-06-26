package org.haegerp.entity;

import org.haegerp.entity.repository.client.ClientCategoryRepository;
import org.haegerp.entity.repository.client.ClientRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
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
    private ClientCategoryRepository clientCategoryRepo;
    
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
	    	Session.setEmployee(employeeRepository.findOne(1L));
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
	        clientCategory.setName(Config.getProperty("INSERT_CC_NAME"));
	        clientCategory.setDescription(Config.getProperty("INSERT_CC_DESCRIPTION"));
	        
	        clientCategory = clientCategoryRepo.performNew(clientCategory);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        CLIENT_CATEGORY_ID = clientCategory.getIdClientCategory();
	        
	        clientCategory = clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	        
	        assertEquals(clientCategory.getName(), Config.getProperty("INSERT_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), Config.getProperty("INSERT_CC_DESCRIPTION"));
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
	    	
	        clientCategory.setName(Config.getProperty("UPDATE_CC_NAME"));
	        clientCategory.setDescription(Config.getProperty("UPDATE_CC_DESCRIPTION"));
	        
	        clientCategoryRepo.performEdit(clientCategory);
	        
	        clientCategory = clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	        
	        assertEquals(clientCategory.getName(), Config.getProperty("UPDATE_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), Config.getProperty("UPDATE_CC_DESCRIPTION"));
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
	        ClientCategory clientCategory = (ClientCategory) clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	    	
	        Client client = new Client();
	        
	        //Die Felder werden gefüllt
	        client.setClientCategory(clientCategory);
	        client.setAddress(Config.getProperty("INSERT_C_ADDRESS"));
	        client.setCity(Config.getProperty("INSERT_C_CITY"));
	        client.setCountry(Config.getProperty("INSERT_C_COUNTRY"));
	        client.setDescription(Config.getProperty("INSERT_C_DESCRIPTION"));
	        client.setEmail(Config.getProperty("INSERT_C_EMAIL"));
	        client.setFaxNumber(Config.getProperty("INSERT_C_FAXNUMBER"));
	        client.setMobileNumber(Config.getProperty("INSERT_C_MOBILENUMBER"));
	        client.setName(Config.getProperty("INSERT_C_NAME"));
	        client.setPhoneNumber(Config.getProperty("INSERT_C_PHONENUMBER"));
	        client.setRegion(Config.getProperty("INSERT_C_REGION"));
	        client.setTaxId(Long.parseLong(Config.getProperty("INSERT_C_TAXID")));
	        client.setZipCode(Config.getProperty("INSERT_C_ZIPCODE"));
	        
	        client = clientRepository.performNew(client);
	        
	        //Der erstellter Artikel wird geprüft
	        CLIENT_ID = client.getIdBusinessPartner();

	        client = clientRepository.findOne(CLIENT_ID);
	        
	        assertEquals(client.getAddress(), Config.getProperty("INSERT_C_ADDRESS"));
	        assertEquals(client.getCity(), Config.getProperty("INSERT_C_CITY"));
	        assertEquals(client.getCountry(), Config.getProperty("INSERT_C_COUNTRY"));
	        assertEquals(client.getDescription(), Config.getProperty("INSERT_C_DESCRIPTION"));
	        assertEquals(client.getEmail(), Config.getProperty("INSERT_C_EMAIL"));
	        assertEquals(client.getFaxNumber(), Config.getProperty("INSERT_C_FAXNUMBER"));
	        assertEquals(client.getMobileNumber(), Config.getProperty("INSERT_C_MOBILENUMBER"));
	        assertEquals(client.getName(), Config.getProperty("INSERT_C_NAME"));
	        assertEquals(client.getPhoneNumber(), Config.getProperty("INSERT_C_PHONENUMBER"));
	        assertEquals(client.getRegion(), Config.getProperty("INSERT_C_REGION"));
	        assertEquals(client.getTaxId(), Long.parseLong(Config.getProperty("INSERT_C_TAXID")));
	        assertEquals(client.getZipCode(), Config.getProperty("INSERT_C_ZIPCODE"));
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
	        client.setAddress(Config.getProperty("UPDATE_C_ADDRESS"));
	        client.setCity(Config.getProperty("UPDATE_C_CITY"));
	        client.setCountry(Config.getProperty("UPDATE_C_COUNTRY"));
	        client.setDescription(Config.getProperty("UPDATE_C_DESCRIPTION"));
	        client.setEmail(Config.getProperty("UPDATE_C_EMAIL"));
	        client.setFaxNumber(Config.getProperty("UPDATE_C_FAXNUMBER"));
	        client.setMobileNumber(Config.getProperty("UPDATE_C_MOBILENUMBER"));
	        client.setName(Config.getProperty("UPDATE_C_NAME"));
	        client.setPhoneNumber(Config.getProperty("UPDATE_C_PHONENUMBER"));
	        client.setRegion(Config.getProperty("UPDATE_C_REGION"));
	        client.setTaxId(Long.parseLong(Config.getProperty("UPDATE_C_TAXID")));
	        client.setZipCode(Config.getProperty("UPDATE_C_ZIPCODE"));
	        
	        client = clientRepository.performEdit(client);
	        
	        //Der geänderter Artikel wird geprüft
	        client = clientRepository.findOne(CLIENT_ID);
	        
	        assertEquals(client.getAddress(), Config.getProperty("UPDATE_C_ADDRESS"));
	        assertEquals(client.getCity(), Config.getProperty("UPDATE_C_CITY"));
	        assertEquals(client.getCountry(), Config.getProperty("UPDATE_C_COUNTRY"));
	        assertEquals(client.getDescription(), Config.getProperty("UPDATE_C_DESCRIPTION"));
	        assertEquals(client.getEmail(), Config.getProperty("UPDATE_C_EMAIL"));
	        assertEquals(client.getFaxNumber(), Config.getProperty("UPDATE_C_FAXNUMBER"));
	        assertEquals(client.getMobileNumber(), Config.getProperty("UPDATE_C_MOBILENUMBER"));
	        assertEquals(client.getName(), Config.getProperty("UPDATE_C_NAME"));
	        assertEquals(client.getPhoneNumber(), Config.getProperty("UPDATE_C_PHONENUMBER"));
	        assertEquals(client.getRegion(), Config.getProperty("UPDATE_C_REGION"));
	        assertEquals(client.getTaxId(), Long.parseLong(Config.getProperty("UPDATE_C_TAXID")));
	        assertEquals(client.getZipCode(), Config.getProperty("UPDATE_C_ZIPCODE"));
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
	        ClientCategory clientCategory = clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	    	
	        clientCategoryRepo.delete(clientCategory);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!clientCategoryRepo.exists(CLIENT_CATEGORY_ID));
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
	        clientCategory.setName(Config.getProperty("INSERT_CC_NAME_F"));
	        clientCategory.setDescription(Config.getProperty("INSERT_CC_DESCRIPTION_F"));
	        
	        clientCategory = clientCategoryRepo.performNew(clientCategory);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        CLIENT_CATEGORY_ID = clientCategory.getIdClientCategory();
	        
	        clientCategory = clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	        
	        assertEquals(clientCategory.getName(), Config.getProperty("INSERT_CC_NAME"));
	        assertEquals(clientCategory.getDescription(), Config.getProperty("INSERT_CC_DESCRIPTION"));
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
	        ClientCategory clientCategory = (ClientCategory) clientCategoryRepo.findOne(CLIENT_CATEGORY_ID);
	    	
	        Client client = new Client();
	        
	        //Die Felder werden gefüllt
	        client.setClientCategory(clientCategory);
	        client.setAddress(Config.getProperty("INSERT_C_ADDRESS_F"));
	        client.setCity(Config.getProperty("INSERT_C_CITY_F"));
	        client.setCountry(Config.getProperty("INSERT_C_COUNTRY_F"));
	        client.setDescription(Config.getProperty("INSERT_C_DESCRIPTION_F"));
	        client.setEmail(Config.getProperty("INSERT_C_EMAIL_F"));
	        client.setFaxNumber(Config.getProperty("INSERT_C_FAXNUMBER_F"));
	        client.setMobileNumber(Config.getProperty("INSERT_C_MOBILENUMBER_F"));
	        client.setName(Config.getProperty("INSERT_C_NAME_F"));
	        client.setPhoneNumber(Config.getProperty("INSERT_C_PHONENUMBER_F"));
	        client.setRegion(Config.getProperty("INSERT_C_REGION_F"));
	        client.setTaxId(Long.parseLong(Config.getProperty("INSERT_C_TAXID_F")));
	        client.setZipCode(Config.getProperty("INSERT_C_ZIPCODE_F"));
	        
	        client = clientRepository.performNew(client);
	        
	        //Der erstellter Artikel wird geprüft
	        CLIENT_ID = client.getIdBusinessPartner();

	        client = clientRepository.findOne(CLIENT_ID);
	        
	        assertEquals(client.getAddress(), Config.getProperty("INSERT_C_ADDRESS"));
	        assertEquals(client.getCity(), Config.getProperty("INSERT_C_CITY"));
	        assertEquals(client.getCountry(), Config.getProperty("INSERT_C_COUNTRY"));
	        assertEquals(client.getDescription(), Config.getProperty("INSERT_C_DESCRIPTION"));
	        assertEquals(client.getEmail(), Config.getProperty("INSERT_C_EMAIL"));
	        assertEquals(client.getFaxNumber(), Config.getProperty("INSERT_C_FAXNUMBER"));
	        assertEquals(client.getMobileNumber(), Config.getProperty("INSERT_C_MOBILENUMBER"));
	        assertEquals(client.getName(), Config.getProperty("INSERT_C_NAME"));
	        assertEquals(client.getPhoneNumber(), Config.getProperty("INSERT_C_PHONENUMBER"));
	        assertEquals(client.getRegion(), Config.getProperty("INSERT_C_REGION"));
	        assertEquals(client.getTaxId(), Long.parseLong(Config.getProperty("INSERT_C_TAXID")));
	        assertEquals(client.getZipCode(), Config.getProperty("INSERT_C_ZIPCODE"));
	        assertEquals(client.getClientCategory(), clientCategory);
	    } catch (Exception ex) {
	    	throw ex;
	    }
    }
}
