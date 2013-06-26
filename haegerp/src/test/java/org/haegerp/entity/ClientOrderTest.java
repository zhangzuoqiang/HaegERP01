package org.haegerp.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.article.ArticleHistoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.entity.repository.client.ClientBillRepository;
import org.haegerp.entity.repository.client.ClientCategoryRepository;
import org.haegerp.entity.repository.client.ClientOfferDetailRepository;
import org.haegerp.entity.repository.client.ClientOfferRepository;
import org.haegerp.entity.repository.client.ClientRepository;
import org.haegerp.entity.repository.client.OutstandingRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.exception.LengthOverflowException;
import org.haegerp.session.Session;
import org.junit.After;
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
 * Dieses Test Suite wird Artikel und Artikelkategorie testen
 * 
 * @author Wolf
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/spring-data.xml")
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class ClientOrderTest extends TestCase {
	
    private static long ARTICLE1_ID;
    private static long ARTICLE2_ID;
    
    private static long ARTICLE_CATEGORY_ID;
    
    private static long CLIENT_CATEGORY_ID;
    private static long CLIENT_ID;
    
    private static long CLIENT_OFFER_ID;
    private static long CLIENT_BILL_ID;
    private static long CLIENT_OUTSTANDING_ID;
    
    private static boolean CHECK_SETUP = true;
    private static boolean CHECK_ERASE = false;
    
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;
    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientCategoryRepository clientCategoryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClientOfferRepository clientOfferRepository;
    @Autowired
    private ClientOfferDetailRepository clientOfferDetailRepository;
    @Autowired
    private ClientBillRepository clientBillRepository;
    @Autowired
    private OutstandingRepository outstandingRepository;
    
    @Override
    @Before
    public void setUp() throws Exception {
    	if (CHECK_SETUP)
    	{
    		CHECK_SETUP = false;
    		
	    	Session.setEmployee(employeeRepository.findOne(1L));
	    	
	    	//Die Artikel werden erstellt
	    	//Kategorie
	    	ArticleCategory articleCategory = new ArticleCategory();
	        articleCategory.setName(Config.getProperty("INSERT_AC_NAME"));
	        articleCategory.setDescription(Config.getProperty("INSERT_AC_DESCRIPTION"));
	        
	        articleCategory = articleCategoryRepository.performNew(articleCategory);
	        
	        ARTICLE_CATEGORY_ID = articleCategory.getIdArticleCategory();
	        
	        //Artikel eins
	        //Erstellung
	        Article article = new Article();
	        article.setArticleCategory(articleCategory);
	        article.setArticleCategory(articleCategory);
	        article.setColor(Config.getProperty("UPDATE_A_COLOR"));
	        article.setDescription(Config.getProperty("UPDATE_A_DESCRIPTION"));
	        article.setEan(Long.parseLong(Config.getProperty("UPDATE_A_EAN")));
	        article.setName(Config.getProperty("UPDATE_A_NAME"));
	        article.setPriceGross(Float.parseFloat(Config.getProperty("UPDATE_A_PRICEGROSS")));
	        article.setPriceSupplier(Float.parseFloat(Config.getProperty("UPDATE_A_PRICESUPPLIER")));
	        article.setPriceVat(Float.parseFloat(Config.getProperty("UPDATE_A_PRICEVAT")));
	        article.setSizeH(Float.parseFloat(Config.getProperty("UPDATE_A_SIZEH")));
	        article.setSizeL(Float.parseFloat(Config.getProperty("UPDATE_A_SIZEL")));
	        article.setSizeW(Float.parseFloat(Config.getProperty("UPDATE_A_SIZEW")));
	        article.setStock(Long.parseLong(Config.getProperty("UPDATE_A_STOCK")));
	        
	        article = articleRepository.performNew(article);
	        ARTICLE1_ID = article.getIdArticle();
	        
	        //Die Artikelversion wird kontrolliert
	        articleRepository.createArticleHistory(article);
	        
	        //Artikel Eins wird geändert
	        article = articleRepository.findOne(ARTICLE1_ID);
	        article.setColor(Config.getProperty("INSERT_A_COLOR"));
	        article.setDescription(Config.getProperty("INSERT_A_DESCRIPTION"));
	        article.setEan(Long.parseLong(Config.getProperty("INSERT_A_EAN")));
	        article.setName(Config.getProperty("INSERT_A_NAME"));
	        article.setPriceGross(Float.parseFloat(Config.getProperty("INSERT_A_PRICEGROSS")));
	        article.setPriceSupplier(Float.parseFloat(Config.getProperty("INSERT_A_PRICESUPPLIER")));
	        article.setPriceVat(Float.parseFloat(Config.getProperty("INSERT_A_PRICEVAT")));
	        article.setSizeH(Float.parseFloat(Config.getProperty("INSERT_A_SIZEH")));
	        article.setSizeL(Float.parseFloat(Config.getProperty("INSERT_A_SIZEL")));
	        article.setSizeW(Float.parseFloat(Config.getProperty("INSERT_A_SIZEW")));
	        article.setStock(Long.parseLong(Config.getProperty("INSERT_A_STOCK")));
	        
	        article = articleRepository.performEdit(article);
	        
	        //Die Artikelversion wird kontrolliert
	        articleRepository.createArticleHistory(article);
	        
	        //Artikel zwei
	        article = new Article();
	        article.setArticleCategory(articleCategory);
	        article.setColor(Config.getProperty("UPDATE_A_COLOR"));
	        article.setDescription(Config.getProperty("UPDATE_A_DESCRIPTION"));
	        article.setEan(Long.parseLong(Config.getProperty("UPDATE_A_EAN")));
	        article.setName(Config.getProperty("UPDATE_A_NAME"));
	        article.setPriceGross(Float.parseFloat(Config.getProperty("UPDATE_A_PRICEGROSS")));
	        article.setPriceSupplier(Float.parseFloat(Config.getProperty("UPDATE_A_PRICESUPPLIER")));
	        article.setPriceVat(Float.parseFloat(Config.getProperty("UPDATE_A_PRICEVAT")));
	        article.setSizeH(Float.parseFloat(Config.getProperty("UPDATE_A_SIZEH")));
	        article.setSizeL(Float.parseFloat(Config.getProperty("UPDATE_A_SIZEL")));
	        article.setSizeW(Float.parseFloat(Config.getProperty("UPDATE_A_SIZEW")));
	        article.setStock(Long.parseLong(Config.getProperty("UPDATE_A_STOCK")));
	        
	        article = articleRepository.performNew(article);
	        ARTICLE2_ID = article.getIdArticle();
	        
	        //Die Artikelversion wird kontrolliert
	        articleRepository.createArticleHistory(article);
	        
	        //Der Kunde wird erstellt
	        //Kategorie
	        ClientCategory clientCategory = new ClientCategory();
	        clientCategory.setName(Config.getProperty("INSERT_CC_NAME"));
	        clientCategory.setDescription("INSERT_CC_DESCRIPTION");
	        
	        clientCategory = clientCategoryRepository.performNew(clientCategory);
	        CLIENT_CATEGORY_ID = clientCategory.getIdClientCategory();
	        
	        //Kunde
	        Client client = new Client();
	        client.setClientCategory(clientCategory);
	        client.setName(Config.getProperty("INSERT_C_NAME"));
	        client.setTaxId(Long.parseLong(Config.getProperty("INSERT_C_TAXID")));
	        client.setAddress(Config.getProperty("INSERT_C_ADDRESS"));
	        client.setZipCode(Config.getProperty("INSERT_C_ZIPCODE"));
	        client.setCity(Config.getProperty("INSERT_C_CITY"));
	        client.setRegion(Config.getProperty("INSERT_C_REGION"));
	        client.setCountry(Config.getProperty("INSERT_C_COUNTRY"));
	        client.setEmail(Config.getProperty("INSERT_C_EMAIL"));
	        client.setPhoneNumber(Config.getProperty("INSERT_C_PHONENUMBER"));
	        client.setMobileNumber(Config.getProperty("INSERT_C_MOBILENUMBER"));
	        client.setFaxNumber(Config.getProperty("INSERT_C_FAXNUMBER"));
	        client.setDescription(Config.getProperty("INSERT_C_DESCRIPTION"));
	    	
	        client = clientRepository.performNew(client);
	    	CLIENT_ID = client.getIdBusinessPartner();
	    	
	        //Mitarbeiter
	    	Session.setEmployee(employeeRepository.findOne(1L));
    	}
        
    }
    
    @Override
    @After
    public void tearDown() throws Exception {
    	if (CHECK_ERASE)
    	{
	    	//Die Artikel werden gelöscht
	    	Article article = articleRepository.findOne(ARTICLE1_ID);
	        articleRepository.delete(article);
	        article = articleRepository.findOne(ARTICLE2_ID);
	        articleRepository.delete(article);
	        
	        articleHistoryRepository.deleteAllVersionsOfArticle(ARTICLE1_ID);
	        articleHistoryRepository.deleteAllVersionsOfArticle(ARTICLE2_ID);
	        
	        //Die Artikelkategorie wird gelöscht
	        ArticleCategory articleCategory = articleCategoryRepository.findOne(ARTICLE_CATEGORY_ID);
	        articleCategoryRepository.delete(articleCategory);
	        
	        //Der Kunde wird gelöscht
	        Client client = clientRepository.findOne(CLIENT_ID);
	        clientRepository.delete(client.getIdBusinessPartner());
	        
	        //Gehaltkategorie
	        ClientCategory clientCategory = clientCategoryRepository.findOne(CLIENT_CATEGORY_ID);
	        clientCategoryRepository.delete(clientCategory);
	        
	        CHECK_ERASE = false;
    	}
    }
    
    /**
     * Ein Angebot wird erstellt
     */
    @Test
    public void test01InsertClientOffer(){
    	try {
    		Client client = clientRepository.findOne(CLIENT_ID);
    		//DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		//Die Felder werden gefüllt
    		ClientOffer clientOffer = new ClientOffer();
    		clientOffer.setDescription(Config.getProperty("INSERT_CO_DESCRIPTION"));
    		clientOffer.setOfferDate(new Date());
    		clientOffer.setEmployee(Session.getEmployee());
    		clientOffer.setClient(client);
    		clientOffer.setTotal(0.0F);
    		
    		clientOffer = clientOfferRepository.performNew(clientOffer);
    		
    		CLIENT_OFFER_ID = clientOffer.getIdClientOffer();
    		
    		//Das Angebot für den Kunde wird geprüft
    		clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
    		
    		assertEquals(clientOffer.getDescription(), Config.getProperty("INSERT_CO_DESCRIPTION"));
	        assertEquals(clientOffer.getEmployee(), Session.getEmployee());
	        assertEquals(clientOffer.getClient(), client);
	        assertEquals(clientOffer.getTotal(), 0.0F);
    		
    		//Hinzufügen den ersten Artikel
    		Article article = articleRepository.findOne(ARTICLE1_ID);
    		ArticleHistory articleHistory = articleHistoryRepository.findOne(new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(ARTICLE1_ID), article));
    		
    		ClientOfferDetail clientOfferDetail = new ClientOfferDetail();
    		clientOfferDetail.setClientOfferDetailPK(new ClientOfferDetailPK(clientOffer, articleHistory));
    		
    		clientOfferDetail.setDiscount(Float.parseFloat(Config.getProperty("INSERT_COD_A1_DISCOUNT")));
    		clientOfferDetail.setQuantity(Long.parseLong(Config.getProperty("INSERT_COD_A1_QUANTITY")));
    		
    		clientOfferDetail = clientOfferDetailRepository.performNew(clientOfferDetail);
    		
    		clientOffer.getClientOfferDetail().add(clientOfferDetail);
    		
    		//Hinzufügen den zweiten Artikel
    		article = articleRepository.findOne(ARTICLE2_ID);
    		articleHistory = articleHistoryRepository.findOne(new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(ARTICLE2_ID), article));
    		
    		clientOfferDetail = new ClientOfferDetail();
    		clientOfferDetail.setClientOfferDetailPK(new ClientOfferDetailPK(clientOffer, articleHistory));
    		
    		clientOfferDetail.setDiscount(Float.parseFloat(Config.getProperty("INSERT_COD_A2_DISCOUNT")));
    		clientOfferDetail.setQuantity(Long.parseLong(Config.getProperty("INSERT_COD_A2_QUANTITY")));
    		
    		clientOfferDetail = clientOfferDetailRepository.performNew(clientOfferDetail);
    		
    		clientOffer.getClientOfferDetail().add(clientOfferDetail);
    		clientOffer.calculateTotal();
    		
    		//Die Artikel wird geprüft
    		clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
    		
    		float articleTotal;
    		float discount;
    		long quantity;
    		
    		for (ClientOfferDetail orderArticle : clientOffer.getClientOfferDetail()) {
				if (orderArticle.getClientOfferDetailPK().getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == ARTICLE1_ID){
		    		discount = Float.parseFloat(Config.getProperty("INSERT_COD_A1_DISCOUNT"));
		    		quantity = Long.parseLong(Config.getProperty("INSERT_COD_A1_QUANTITY"));
				} else {
					discount = Float.parseFloat(Config.getProperty("INSERT_COD_A2_DISCOUNT"));
		    		quantity = Long.parseLong(Config.getProperty("INSERT_COD_A2_QUANTITY"));
				}
				
				articleTotal = (float) Math.floor(
						orderArticle.getClientOfferDetailPK().getArticleHistory().getPriceGross()
						* (1 + (orderArticle.getClientOfferDetailPK().getArticleHistory().getPriceVat() / 100))
						* orderArticle.getQuantity()
						* (1 - (orderArticle.getDiscount() / 100))*100)/100;

				assertEquals(orderArticle.getTotalArticle(), articleTotal);
				assertEquals(orderArticle.getDiscount(), discount);
	    		assertEquals(orderArticle.getQuantity(), quantity);
			}
    		
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
    }
    
    /**
     * Ein Angebot wird geändert
     */
    @Test
    public void test02UpdateClientOffer(){
	try {
    		Client client = clientRepository.findOne(CLIENT_ID);
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		//Die Felder werden gefüllt
    		ClientOffer clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
    		clientOffer.setSendDate(dateFormat.parse(Config.getProperty("UPDATE_CO_SENDDATE")));
    		clientOffer.setDescription(Config.getProperty("UPDATE_CO_DESCRIPTION"));
    		clientOffer.setEmployee(Session.getEmployee());
    		clientOffer.setClient(client);
    		
    		clientOffer = clientOfferRepository.performEdit(clientOffer);
    		
    		//Das Angebot für den Kunde wird geprüft
    		clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
    		
    		assertEquals(clientOffer.getSendDate(), dateFormat.parse(Config.getProperty("UPDATE_CO_SENDDATE")));
    		assertEquals(clientOffer.getDescription(), Config.getProperty("UPDATE_CO_DESCRIPTION"));
	        assertEquals(clientOffer.getEmployee(), Session.getEmployee());
	        assertEquals(clientOffer.getClient(), client);
    		
    		//Änderung die Artikel
    		for (ClientOfferDetail orderArticle : clientOffer.getClientOfferDetail()) {
				if (orderArticle.getClientOfferDetailPK().getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == ARTICLE1_ID){
		    		orderArticle.setDiscount(Float.parseFloat(Config.getProperty("UPDATE_COD_A1_DISCOUNT")));
		    		orderArticle.setQuantity(Long.parseLong(Config.getProperty("UPDATE_COD_A1_QUANTITY")));
				} else {
					orderArticle.setDiscount(Float.parseFloat(Config.getProperty("UPDATE_COD_A2_DISCOUNT")));
		    		orderArticle.setQuantity(Long.parseLong(Config.getProperty("UPDATE_COD_A2_QUANTITY")));
				}
				clientOfferDetailRepository.performEdit(orderArticle);
    		}
    		clientOffer.calculateTotal();
    		
    		//Die Artikel wird geprüft
    		clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
    		
    		float articleTotal;
    		float discount;
    		long quantity;
    		
    		for (ClientOfferDetail orderArticle : clientOffer.getClientOfferDetail()) {
				if (orderArticle.getClientOfferDetailPK().getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == ARTICLE1_ID){
		    		discount = Float.parseFloat(Config.getProperty("UPDATE_COD_A1_DISCOUNT"));
		    		quantity = Long.parseLong(Config.getProperty("UPDATE_COD_A1_QUANTITY"));
				} else {
					discount = Float.parseFloat(Config.getProperty("UPDATE_COD_A2_DISCOUNT"));
		    		quantity = Long.parseLong(Config.getProperty("UPDATE_COD_A2_QUANTITY"));
				}
				
				articleTotal = (float) Math.floor( 
						orderArticle.getClientOfferDetailPK().getArticleHistory().getPriceGross()
						* orderArticle.getQuantity()
						* (1 + (orderArticle.getClientOfferDetailPK().getArticleHistory().getPriceVat() / 100))
						* (1 - (orderArticle.getDiscount() / 100))*100)/100;

				assertEquals(orderArticle.getTotalArticle(), articleTotal);
				assertEquals(orderArticle.getDiscount(), discount);
	    		assertEquals(orderArticle.getQuantity(), quantity);
			}
    		
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
    }
    
    /**
     * Eine neue Rechnung wird erstellt und mit dem richtigen Angebot zusammengehängt.
     */
    @Test
    public void test03CreateClientBill(){
    	try {
    		ClientOffer clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
	        
    		ClientBill clientBill = new ClientBill();
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		clientBill.setBilledDate(dateFormat.parse(Config.getProperty("INSERT_CB_BILLEDDATE")));
    		
    		clientBill = clientBillRepository.performNew(clientBill);
    		
    		clientOffer.setClientBill(clientBill);
    		clientOffer = clientOfferRepository.performEdit(clientOffer);
    		
    		//Die Rechnung für das Angebot wird geprüft
    		CLIENT_BILL_ID = clientBill.getIdClientBill();
    		
    		clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
    		
    		assertEquals(clientBill.getBilledDate(), dateFormat.parse(Config.getProperty("INSERT_CB_BILLEDDATE")));
    		
    		clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
	        
	        assertEquals(clientOffer.getClientBill(), clientBill);
	        
    	} catch (Exception ex) {
    		ex.printStackTrace();
			fail(ex.getMessage());
    	}
    }
    
    /**
     * Die Outstandingrechnung wird erstellt
     */
    @Test
    public void test04CreateOutstandingBill(){
    	try {
    		ClientBill clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
	        
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		Outstanding outstanding = new Outstanding();
    		
    		outstanding.setClientBill(clientBill);
    		outstanding.setExpireDate(dateFormat.parse(Config.getProperty("INSERT_OC_EXPIREDATE")));
    		
    		outstanding = outstandingRepository.performNew(outstanding);
    		
    		clientBill.setOutstanding(outstanding);
    		
    		clientBill = clientBillRepository.performEdit(clientBill);
    		
    		//Die OutstandungRechnung für den Kunde wird geprüft
    		CLIENT_OUTSTANDING_ID = outstanding.getIdOutstanding();
    		
    		outstanding = outstandingRepository.findOne(CLIENT_OUTSTANDING_ID);
    		
    		assertEquals(outstanding.getExpireDate(), dateFormat.parse(Config.getProperty("INSERT_OC_EXPIREDATE")));
    		
    		clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
	        
	        assertEquals(clientBill.getOutstanding(), outstanding);
	        
    	} catch (Exception ex) {
    		ex.printStackTrace();
			fail(ex.getMessage());
    	}
    }
    
    /**
     * Die Outstandingrechnung wird geändert
     */
    @Test
    public void test05UpdateOutstandingBill(){
    	try {
    		Outstanding outstanding = outstandingRepository.findOne(CLIENT_OUTSTANDING_ID);
	        
			ClientBill clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
			
			assertEquals(outstanding.getClientBill().getIdClientBill(), clientBill.getIdClientBill());
			assertEquals(clientBill.getOutstanding().getIdOutstanding(), outstanding.getIdOutstanding());
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
			
			outstanding.setExpireDate(dateFormat.parse(Config.getProperty("UPDATE_OC_EXPIREDATE")));
			outstanding.setEmailDate(dateFormat.parse(Config.getProperty("UPDATE_OC_SENDDATE")));
			
			//Der Aufpreis wird ergestellt
			ArticleHistory articleHistory = articleHistoryRepository.findOne(
					new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(0L), articleRepository.findOne(0L))
					);
			
			ClientOfferDetailPK clientOfferDetailPK = new ClientOfferDetailPK(outstanding.getClientBill().getClientOffer(), articleHistory);
			ClientOfferDetail clientOfferDetail = new ClientOfferDetail();
			clientOfferDetail.setClientOfferDetailPK(clientOfferDetailPK);
			clientOfferDetail.setDiscount(0);
			clientOfferDetail.setQuantity(1);
			
			clientOfferDetailRepository.performNew(clientOfferDetail);
			outstanding.getClientBill().getClientOffer().getClientOfferDetail().add(clientOfferDetail);
			outstanding.getClientBill().getClientOffer().calculateTotal();
			
			clientOfferRepository.performEdit(outstanding.getClientBill().getClientOffer());
			
			outstanding = outstandingRepository.performEdit(outstanding);
			
			//Die OutstandungRechnung für den Kunde wird geprüft
			outstanding = outstandingRepository.findOne(CLIENT_OUTSTANDING_ID);
			
			assertEquals(outstanding.getExpireDate(), dateFormat.parse(Config.getProperty("UPDATE_OC_EXPIREDATE")));
	        assertEquals(outstanding.getEmailDate(), dateFormat.parse(Config.getProperty("UPDATE_OC_SENDDATE")));
			
	        assertTrue(outstanding.getClientBill().getClientOffer().getClientOfferDetail().contains(clientOfferDetail));

	        clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
	        
	        assertEquals(clientBill.getOutstanding().getIdOutstanding(), outstanding.getIdOutstanding());
	        
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
    }
    
    /**
     * Die lezte Rechnung wird geändert
     */
    @Test
    public void test06UpdateClientBill(){
    	try {
    		ClientOffer clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
	        
    		ClientBill clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
    		
    		assertEquals(clientOffer.getClientBill().getIdClientBill(), clientBill.getIdClientBill());
    		assertEquals(clientBill.getClientOffer().getIdClientOffer(), clientOffer.getIdClientOffer());
    		
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		clientBill.setBilledDate(dateFormat.parse(Config.getProperty("UPDATE_CB_BILLEDDATE")));
    		clientBill.setPaidDate(dateFormat.parse(Config.getProperty("UPDATE_CB_PAIDDATE")));
    		
    		clientBill = clientBillRepository.performEdit(clientBill);
    		
    		//Die OutstandungRechnung für den Kunde wird geprüft
    		clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
    		
    		assertEquals(clientBill.getBilledDate(), dateFormat.parse(Config.getProperty("UPDATE_CB_BILLEDDATE")));
	        assertEquals(clientBill.getPaidDate(), dateFormat.parse(Config.getProperty("UPDATE_CB_PAIDDATE")));
    		
	        clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
	        
	        assertEquals(clientOffer.getClientBill().getIdClientBill(), clientBill.getIdClientBill());
	        
    	} catch (Exception ex) {
    		ex.printStackTrace();
			fail(ex.getMessage());
    	}
    }
    
    /**
     * Die Outstandingrechnung wird gelöscht
     */
    @Test
    public void test07DeleteOutstandingBill()
    {
    	try {
	        ClientBill clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
	        Outstanding outstanding = outstandingRepository.findOne(CLIENT_OUTSTANDING_ID);
	    	clientBill.setOutstanding(null);
	        
	    	outstandingRepository.delete(outstanding);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!outstandingRepository.exists(CLIENT_OUTSTANDING_ID));
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    }
    
    /**
     * Die Rechnung wird gelöscht
     */
    @Test
    public void test08DeleteClientBill()
    {
    	try {
	        ClientBill clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
	    	ClientOffer clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
	    	clientOffer.setClientBill(null);
	        
	    	clientBillRepository.delete(clientBill);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!clientBillRepository.exists(CLIENT_BILL_ID));
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    }
    
    /**
     * Das Angebot wird gelöscht
     */
    @Test
    public void test09DeleteClientOffer()
    {
    	try {
	    	ClientOffer clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);

	    	clientOfferDetailRepository.delete(clientOffer.getClientOfferDetail());
	        
	    	clientOfferRepository.delete(clientOffer);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!clientOfferRepository.exists(CLIENT_OFFER_ID));
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    	CHECK_ERASE = true;
    }
    
    /**
     * Fehlerprovokation Angebot
     * @throws Exception
     */
    @Test(expected=LengthOverflowException.class)
    public void test10InsertClientOfferError() throws Exception{
    	try {
    		Client client = clientRepository.findOne(CLIENT_ID);
    		
    		//Die Felder werden gefüllt
    		ClientOffer clientOffer = new ClientOffer();
    		clientOffer.setDescription(Config.getProperty("INSERT_CO_DESCRIPTION_F"));
    		clientOffer.setOfferDate(new Date());
    		clientOffer.setEmployee(Session.getEmployee());
    		clientOffer.setClient(client);
    		clientOffer.setTotal(0.0F);
    		
    		clientOffer = clientOfferRepository.performNew(clientOffer);
    		
    		CLIENT_OFFER_ID = clientOffer.getIdClientOffer();
    		
    		//Das Angebot für den Kunde wird geprüft
    		clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
    		
    		assertEquals(clientOffer.getDescription(), Config.getProperty("INSERT_CO_DESCRIPTION"));
	        assertEquals(clientOffer.getEmployee(), Session.getEmployee());
	        assertEquals(clientOffer.getClient(), client);
	        assertEquals(clientOffer.getTotal(), 0.0F);
    		
    		//Hinzufügen den ersten Artikel
    		Article article = articleRepository.findOne(ARTICLE1_ID);
    		ArticleHistory articleHistory = articleHistoryRepository.findOne(new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(ARTICLE1_ID), article));
    		
    		ClientOfferDetail clientOfferDetail = new ClientOfferDetail();
    		clientOfferDetail.setClientOfferDetailPK(new ClientOfferDetailPK(clientOffer, articleHistory));
    		
    		clientOfferDetail.setDiscount(Float.parseFloat(Config.getProperty("INSERT_COD_A1_DISCOUNT_F")));
    		clientOfferDetail.setQuantity(Long.parseLong(Config.getProperty("INSERT_COD_A1_QUANTITY_F")));
    		
    		clientOfferDetail = clientOfferDetailRepository.performNew(clientOfferDetail);
    		
    		clientOffer.setTotal(clientOffer.getTotal() + clientOfferDetail.getTotalArticle());
    		clientOffer.getClientOfferDetail().add(clientOfferDetail);
    		
    		//Hinzufügen den zweiten Artikel
    		article = articleRepository.findOne(ARTICLE2_ID);
    		articleHistory = articleHistoryRepository.findOne(new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(ARTICLE2_ID), article));
    		
    		clientOfferDetail = new ClientOfferDetail();
    		clientOfferDetail.setClientOfferDetailPK(new ClientOfferDetailPK(clientOffer, articleHistory));
    		
    		clientOfferDetail.setDiscount(Float.parseFloat(Config.getProperty("INSERT_COD_A2_DISCOUNT_F")));
    		clientOfferDetail.setQuantity(Long.parseLong(Config.getProperty("INSERT_COD_A2_QUANTITY_F")));
    		
    		clientOfferDetail = clientOfferDetailRepository.performNew(clientOfferDetail);
    		
    		clientOffer.setTotal(clientOffer.getTotal() + clientOfferDetail.getTotalArticle());
    		clientOffer.getClientOfferDetail().add(clientOfferDetail);
    		
    		//Die Artikel wird geprüft
    		clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
    		
    		float articleTotal;
    		float discount;
    		long quantity;
    		
    		for (ClientOfferDetail orderArticle : clientOffer.getClientOfferDetail()) {
				if (orderArticle.getClientOfferDetailPK().getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == ARTICLE1_ID){
		    		discount = Float.parseFloat(Config.getProperty("INSERT_COD_A1_DISCOUNT"));
		    		quantity = Long.parseLong(Config.getProperty("INSERT_COD_A1_QUANTITY"));
				} else {
					discount = Float.parseFloat(Config.getProperty("INSERT_COD_A2_DISCOUNT"));
		    		quantity = Long.parseLong(Config.getProperty("INSERT_COD_A2_QUANTITY"));
				}
				
				articleTotal = orderArticle.getClientOfferDetailPK().getArticleHistory().getPriceSupplier()
						* orderArticle.getQuantity()
						* (1 - (orderArticle.getDiscount() / 100));

				assertEquals(orderArticle.getTotalArticle(), articleTotal);
				assertEquals(orderArticle.getDiscount(), discount);
	    		assertEquals(orderArticle.getQuantity(), quantity);
			}
    		
		} catch (Exception ex) {
			throw ex;
		}
    }
    
    /**
     * Fehlerprovokation Kundenrechnung
     * @throws Exception 
     */
    @Test(expected=ParseException.class)
    public void test11CreateClientBillError() throws Exception{
    	try {
    		ClientOffer clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
	        
    		ClientBill clientBill = new ClientBill();
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		clientBill.setBilledDate(dateFormat.parse(Config.getProperty("INSERT_CB_BILLEDDATE_F")));
    		
    		clientBill = clientBillRepository.performNew(clientBill);
    		
    		clientOffer.setClientBill(clientBill);
    		clientOffer = clientOfferRepository.performEdit(clientOffer);
    		
    		//Das Angebot für den Kunde wird geprüft
    		CLIENT_BILL_ID = clientBill.getIdClientBill();
    		
    		clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
    		
    		assertEquals(clientBill.getBilledDate(), dateFormat.parse(Config.getProperty("INSERT_CB_BILLEDDATE")));
    		
    		clientOffer = clientOfferRepository.findOne(CLIENT_OFFER_ID);
	        
	        assertEquals(clientOffer.getClientBill(), clientBill);
    	} catch (Exception ex) {
    		throw ex;
    	}
    }
    
    /**
     * Fehlerprovokation Outstandingrechnung
     * @throws Exception 
     */
    @Test(expected=ParseException.class)
    public void test12CreateOutstandingBillError() throws Exception{
    	try {
    		ClientBill clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
	        
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		Outstanding outstanding = new Outstanding();
    		
    		outstanding.setClientBill(clientBill);
    		outstanding.setExpireDate(dateFormat.parse(Config.getProperty("INSERT_OC_EXPIREDATE_F")));
    		
    		outstanding = outstandingRepository.performNew(outstanding);
    		
    		clientBill.setOutstanding(outstanding);
    		
    		clientBill = clientBillRepository.performEdit(clientBill);
    		
    		//Das Angebot für den Kunde wird geprüft
    		CLIENT_OUTSTANDING_ID = outstanding.getIdOutstanding();
    		
    		outstanding = outstandingRepository.findOne(CLIENT_OUTSTANDING_ID);
    		
    		assertEquals(outstanding.getExpireDate(), dateFormat.parse(Config.getProperty("INSERT_OC_EXPIREDATE")));
    		
    		clientBill = clientBillRepository.findOne(CLIENT_BILL_ID);
	        
	        assertEquals(clientBill.getOutstanding(), outstanding);
    	} catch (Exception ex) {
    		throw ex;
    	}
    }
}
