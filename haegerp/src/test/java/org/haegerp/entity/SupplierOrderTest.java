package org.haegerp.entity;

import org.haegerp.Properties;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.article.ArticleHistoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.entity.repository.employee.DivisionRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.entity.repository.employee.PermissionRepository;
import org.haegerp.entity.repository.employee.SalaryCategoryRepository;
import org.haegerp.entity.repository.employee.UserGroupRepository;
import org.haegerp.entity.repository.supplier.SupplierBillRepository;
import org.haegerp.entity.repository.supplier.SupplierOrderDetailRepository;
import org.haegerp.entity.repository.supplier.SupplierOrderRepository;
import org.haegerp.entity.repository.supplier.SupplierRepository;
import org.haegerp.session.EmployeeSession;
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
public class SupplierOrderTest extends TestCase {
	
    private static long ARTICLE1_ID;
    private static long ARTICLE2_ID;
    
    private static long ARTICLE_CATEGORY_ID;
    
    private static long SUPPLIER_ID;
    
    private static long EMPLOYEE_ID;
    private static long SALARY_CATEGORY_ID;
    private static long DIVISION_ID;
    private static long USER_GROUP_ID;
    
    private static long SUPPLIERORDER_ID;
    private static long SUPPLIERBILL_ID;
    
    private static boolean CHECK_SETUP = true;
    private static boolean CHECK_ERASE = false;
    
    @Autowired
    private ArticleRepository articleRepo;
    @Autowired
    private ArticleCategoryRepository articleCategoryRepo;
    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;
    @Autowired
    private SupplierRepository supplierRepo;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SalaryCategoryRepository salaryCategoryRepository;
    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private SupplierOrderRepository supplierOrderRepository;
    @Autowired
    private SupplierOrderDetailRepository supplierOrderDetailRepository;
    @Autowired
    private SupplierBillRepository supplierBillRepository;
    
    @Override
    @Before
    public void setUp() throws Exception {
    	if (CHECK_SETUP)
    	{
    		CHECK_SETUP = false;
    		
	    	EmployeeSession.setEmployee(employeeRepository.findOne(1L));
	    	if (!Properties.loadProperties()){
	    		fail("Failed to load Properties File.");
	    	}
	    	
	    	//Die Artikel werden erstellt
	    	//Kategorie
	    	ArticleCategory articleCategory = new ArticleCategory();
	        articleCategory.setName(Properties.getProperty("INSERT_AC_NAME"));
	        articleCategory.setDescription(Properties.getProperty("INSERT_AC_DESCRIPTION"));
	        
	        articleCategory = articleCategoryRepo.save(articleCategory);
	        
	        ARTICLE_CATEGORY_ID = articleCategory.getIdArticleCategory();
	        
	        //Artikel eins
	        //Erstellung
	        Article article = new Article();
	        article.setArticleCategory(articleCategory);
	        article.setArticleCategory(articleCategory);
	        article.setColor(Properties.getProperty("UPDATE_A_COLOR"));
	        article.setDescription(Properties.getProperty("UPDATE_A_DESCRIPTION"));
	        article.setEan(Long.parseLong(Properties.getProperty("UPDATE_A_EAN")));
	        article.setName(Properties.getProperty("UPDATE_A_NAME"));
	        article.setPriceGross(Float.parseFloat(Properties.getProperty("UPDATE_A_PRICEGROSS")));
	        article.setPriceSupplier(Float.parseFloat(Properties.getProperty("UPDATE_A_PRICESUPPLIER")));
	        article.setPriceVat(Float.parseFloat(Properties.getProperty("UPDATE_A_PRICEVAT")));
	        article.setSizeH(Float.parseFloat(Properties.getProperty("UPDATE_A_SIZEH")));
	        article.setSizeL(Float.parseFloat(Properties.getProperty("UPDATE_A_SIZEL")));
	        article.setSizeW(Float.parseFloat(Properties.getProperty("UPDATE_A_SIZEW")));
	        article.setStock(Long.parseLong(Properties.getProperty("UPDATE_A_STOCK")));
	        
	        article = articleRepo.save(article);
	        ARTICLE1_ID = article.getIdArticle();
	        
	        //Artikel Eins wird geändert
	        article = articleRepo.findOne(ARTICLE1_ID);
	        article.setColor(Properties.getProperty("INSERT_A_COLOR"));
	        article.setDescription(Properties.getProperty("INSERT_A_DESCRIPTION"));
	        article.setEan(Long.parseLong(Properties.getProperty("INSERT_A_EAN")));
	        article.setName(Properties.getProperty("INSERT_A_NAME"));
	        article.setPriceGross(Float.parseFloat(Properties.getProperty("INSERT_A_PRICEGROSS")));
	        article.setPriceSupplier(Float.parseFloat(Properties.getProperty("INSERT_A_PRICESUPPLIER")));
	        article.setPriceVat(Float.parseFloat(Properties.getProperty("INSERT_A_PRICEVAT")));
	        article.setSizeH(Float.parseFloat(Properties.getProperty("INSERT_A_SIZEH")));
	        article.setSizeL(Float.parseFloat(Properties.getProperty("INSERT_A_SIZEL")));
	        article.setSizeW(Float.parseFloat(Properties.getProperty("INSERT_A_SIZEW")));
	        article.setStock(Long.parseLong(Properties.getProperty("INSERT_A_STOCK")));
	        
	        article = articleRepo.save(article);
	        
	        //Artikel zwei
	        article = new Article();
	        article.setArticleCategory(articleCategory);
	        article.setColor(Properties.getProperty("UPDATE_A_COLOR"));
	        article.setDescription(Properties.getProperty("UPDATE_A_DESCRIPTION"));
	        article.setEan(Long.parseLong(Properties.getProperty("UPDATE_A_EAN")));
	        article.setName(Properties.getProperty("UPDATE_A_NAME"));
	        article.setPriceGross(Float.parseFloat(Properties.getProperty("UPDATE_A_PRICEGROSS")));
	        article.setPriceSupplier(Float.parseFloat(Properties.getProperty("UPDATE_A_PRICESUPPLIER")));
	        article.setPriceVat(Float.parseFloat(Properties.getProperty("UPDATE_A_PRICEVAT")));
	        article.setSizeH(Float.parseFloat(Properties.getProperty("UPDATE_A_SIZEH")));
	        article.setSizeL(Float.parseFloat(Properties.getProperty("UPDATE_A_SIZEL")));
	        article.setSizeW(Float.parseFloat(Properties.getProperty("UPDATE_A_SIZEW")));
	        article.setStock(Long.parseLong(Properties.getProperty("UPDATE_A_STOCK")));
	        
	        article = articleRepo.save(article);
	        ARTICLE2_ID = article.getIdArticle();
	        
	        //Der Lieferant wird erstellt
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
	    	SUPPLIER_ID = supplier.getIdBusinessPartner();
	    	
	    	//Mitarbeiter wird erstellt
	    	//Gehaltkategorie
	    	SalaryCategory salaryCategory = new SalaryCategory();
	        salaryCategory.setDescription(Properties.getProperty("INSERT_SC_DESCRIPTION"));
	        salaryCategory.setSalaryFrom(Float.parseFloat(Properties.getProperty("INSERT_SC_SALARYFROM")));
	        salaryCategory.setSalaryTo(Float.parseFloat(Properties.getProperty("INSERT_SC_SALARYTO")));
	        
	        salaryCategory = salaryCategoryRepository.save(salaryCategory);
	        SALARY_CATEGORY_ID = salaryCategory.getIdSalaryCategory();
	        
	        //Abteilung
	        Division division = new Division();
	        division.setDescription(Properties.getProperty("INSERT_D_DESCRIPTION"));
	        division.setName(Properties.getProperty("INSERT_D_NAME"));
	        
	        division = divisionRepository.save(division);
	        DIVISION_ID = division.getIdDivision();
	        
	        //Benutzergruppe
	        UserGroup userGroup = new UserGroup();
	        userGroup.setDescription(Properties.getProperty("INSERT_UG_DESCRIPTION"));
	        userGroup.setName(Properties.getProperty("INSERT_UG_NAME"));
	        
	        //Hinzufügen erlaubnise
	        List<Permission> permissionList = new LinkedList<Permission>();
	        
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Properties.getProperty("INSERT_UG_PERMISSION1"))));
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Properties.getProperty("INSERT_UG_PERMISSION2"))));
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Properties.getProperty("INSERT_UG_PERMISSION3"))));
	        
	        for (int i = 0; i < permissionList.size(); i++) {
				userGroup.getPermissions().add(permissionList.get(i));
			}
	        
	        userGroup = userGroupRepository.save(userGroup);
	        USER_GROUP_ID = userGroup.getIdUserGroup();
	        
	        //Mitarbeiter
	        Employee employee = new Employee();
	        employee.setAddress(Properties.getProperty("INSERT_E_ADDRESS"));
	        employee.setCity(Properties.getProperty("INSERT_E_CITY"));
	        employee.setCountry(Properties.getProperty("INSERT_E_COUNTRY"));
	        employee.setDivision(division);
	        employee.setEmail(Properties.getProperty("INSERT_E_EMAIL"));
	        employee.setIdCard(Long.parseLong(Properties.getProperty("INSERT_E_IDCARD")));
	        employee.setMobileNumber(Properties.getProperty("INSERT_E_MOBILENUMBER"));
	        employee.setName(Properties.getProperty("INSERT_E_NAME"));
	        employee.setPhoneNumber(Properties.getProperty("INSERT_E_PHONENUMBER"));
	        employee.setRegion(Properties.getProperty("INSERT_E_REGION"));
	        employee.setSalaryCategory(salaryCategory);
	        employee.setUserGroup(userGroup);
	        employee.setZipCode(Properties.getProperty("INSERT_E_ZIPCODE"));
	        employee.setPassword(Properties.getProperty("INSERT_E_PASSWORD"));
	        employee.setUsername(Properties.getProperty("INSERT_E_USERNAME"));
	        
	        employee = employeeRepository.save(employee);
	        EMPLOYEE_ID = employee.getIdEmployee();
    	}
        
    }
    
    @Override
    @After
    public void tearDown() throws Exception {
    	if (CHECK_ERASE)
    	{
	    	//Die Artikel werden gelöscht
	    	Article article = articleRepo.findOne(ARTICLE1_ID);
	        articleRepo.delete(article);
	        article = articleRepo.findOne(ARTICLE2_ID);
	        articleRepo.delete(article);
	        
	        articleHistoryRepository.deleteAllVersionsOfArticle(ARTICLE1_ID);
	        articleHistoryRepository.deleteAllVersionsOfArticle(ARTICLE2_ID);
	        
	        //Die Artikelkategorie wird gelöscht
	        ArticleCategory articleCategory = articleCategoryRepo.findOne(ARTICLE_CATEGORY_ID);
	        articleCategoryRepo.delete(articleCategory);
	        
	        //Der Lieferant wird gelöscht
	        Supplier supplier = supplierRepo.findOne(SUPPLIER_ID);
	        supplierRepo.delete(supplier.getIdBusinessPartner());
	        
	        //Der Mitarbeiter wird gelöscht
	        Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
	        employeeRepository.delete(employee);
	        //Gehaltkategorie
	        SalaryCategory salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	        salaryCategoryRepository.delete(salaryCategory);
	        //Abteilung
	        Division division = divisionRepository.findOne(DIVISION_ID);
	        divisionRepository.delete(division);
	        //Benutzergrupper
	        UserGroup userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	        userGroupRepository.delete(userGroup);
	        CHECK_ERASE = false;
    	}
    }
    
    /**
     * Eine Bestellung wird erstellt
     */
    @Test
    public void test1InsertSupplierOrder(){
    	try {
    		Supplier supplier = supplierRepo.findOne(SUPPLIER_ID);
    		Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		//Die Felder werden gefüllt
    		SupplierOrder supplierOrder = new SupplierOrder();
    		supplierOrder.setSendDate(dateFormat.parse(Properties.getProperty("INSERT_SO_SENDDATE")));
    		supplierOrder.setDescription(Properties.getProperty("INSERT_SO_DESCRIPTION"));
    		supplierOrder.setOrderDate(new Date());
    		supplierOrder.setEmployee(employee);
    		supplierOrder.setSupplier(supplier);
    		supplierOrder.setTotal(0);
    		
    		supplierOrder = supplierOrderRepository.save(supplierOrder);
    		
    		SUPPLIERORDER_ID = supplierOrder.getIdSupplierOrder();
    		
    		//Die Bestellung des Lieferant wird geprüft
    		supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
    		
    		assertEquals(supplierOrder.getSendDate(), dateFormat.parse(Properties.getProperty("INSERT_SO_SENDDATE")));
    		assertEquals(supplierOrder.getDescription(), Properties.getProperty("INSERT_SO_DESCRIPTION"));
	        assertEquals(supplierOrder.getEmployee(), employee);
	        assertEquals(supplierOrder.getSupplier(), supplier);
	        assertEquals(supplierOrder.getTotal(), 0.0F);
    		
    		//Hinzufügen den ersten Artikel
    		Article article = articleRepo.findOne(ARTICLE1_ID);
    		ArticleHistory articleHistory = articleHistoryRepository.findOne(new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(ARTICLE1_ID), article));
    		
    		SupplierOrderDetail supplierOrderDetail = new SupplierOrderDetail();
    		supplierOrderDetail.setSupplierOrderDetailPK(new SupplierOrderDetailPK(supplierOrder, articleHistory));
    		
    		supplierOrderDetail.setDiscount(Float.parseFloat(Properties.getProperty("INSERT_SOD_A1_DISCOUNT")));
    		supplierOrderDetail.setQuantity(Long.parseLong(Properties.getProperty("INSERT_SOD_A1_QUANTITY")));
    		
    		supplierOrderDetail = supplierOrderDetailRepository.save(supplierOrderDetail);
    		
    		supplierOrder.getSupplierOrderDetail().add(supplierOrderDetail);
    		supplierOrder.calculateTotal();
    		
    		//Hinzufügen den zweiten Artikel
    		article = articleRepo.findOne(ARTICLE2_ID);
    		articleHistory = articleHistoryRepository.findOne(new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(ARTICLE2_ID), article));
    		
    		supplierOrderDetail = new SupplierOrderDetail();
    		supplierOrderDetail.setSupplierOrderDetailPK(new SupplierOrderDetailPK(supplierOrder, articleHistory));
    		
    		supplierOrderDetail.setDiscount(Float.parseFloat(Properties.getProperty("INSERT_SOD_A2_DISCOUNT")));
    		supplierOrderDetail.setQuantity(Long.parseLong(Properties.getProperty("INSERT_SOD_A2_QUANTITY")));
    		
    		supplierOrderDetail = supplierOrderDetailRepository.save(supplierOrderDetail);
    		
    		supplierOrder.getSupplierOrderDetail().add(supplierOrderDetail);
    		supplierOrder.calculateTotal();
    		
    		//Die Artikel wird geprüft
    		supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
    		
    		float articleTotal;
    		float discount;
    		long quantity;
    		
    		for (SupplierOrderDetail orderArticle : supplierOrder.getSupplierOrderDetail()) {
				if (orderArticle.getSupplierOrderDetailPK().getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == ARTICLE1_ID){
		    		discount = Float.parseFloat(Properties.getProperty("INSERT_SOD_A1_DISCOUNT"));
		    		quantity = Long.parseLong(Properties.getProperty("INSERT_SOD_A1_QUANTITY"));
				} else {
					discount = Float.parseFloat(Properties.getProperty("INSERT_SOD_A2_DISCOUNT"));
		    		quantity = Long.parseLong(Properties.getProperty("INSERT_SOD_A2_QUANTITY"));
				}
				
				articleTotal = orderArticle.getSupplierOrderDetailPK().getArticleHistory().getPriceSupplier()
						* orderArticle.getQuantity()
						* (1 - (orderArticle.getDiscount() / 100));

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
     * Eine Bestellung wird geändert
     */
    @Test
    public void test2UpdateSupplierOrder(){
    	try {
    		Supplier supplier = supplierRepo.findOne(SUPPLIER_ID);
    		Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		//Die Felder werden gefüllt
    		SupplierOrder supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
    		supplierOrder.setSendDate(dateFormat.parse(Properties.getProperty("UPDATE_SO_SENDDATE")));
    		supplierOrder.setDescription(Properties.getProperty("UPDATE_SO_DESCRIPTION"));
    		supplierOrder.setEmployee(employee);
    		supplierOrder.setSupplier(supplier);
    		
    		supplierOrder = supplierOrderRepository.save(supplierOrder);
    		
    		//Die Bestellung des Lieferant wird geprüft
    		supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
    		
    		assertEquals(supplierOrder.getSendDate(), dateFormat.parse(Properties.getProperty("UPDATE_SO_SENDDATE")));
    		assertEquals(supplierOrder.getDescription(), Properties.getProperty("UPDATE_SO_DESCRIPTION"));
	        assertEquals(supplierOrder.getEmployee(), employee);
	        assertEquals(supplierOrder.getSupplier(), supplier);
    		
    		//Änderung die Artikel
	        supplierOrder.setTotal(0.0F);
    		for (SupplierOrderDetail orderArticle : supplierOrder.getSupplierOrderDetail()) {
				if (orderArticle.getSupplierOrderDetailPK().getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == ARTICLE1_ID){
		    		orderArticle.setDiscount(Float.parseFloat(Properties.getProperty("UPDATE_SOD_A1_DISCOUNT")));
		    		orderArticle.setQuantity(Long.parseLong(Properties.getProperty("UPDATE_SOD_A1_QUANTITY")));
				} else {
					orderArticle.setDiscount(Float.parseFloat(Properties.getProperty("UPDATE_SOD_A2_DISCOUNT")));
		    		orderArticle.setQuantity(Long.parseLong(Properties.getProperty("UPDATE_SOD_A2_QUANTITY")));
				}
				supplierOrderDetailRepository.save(orderArticle);
    		}
    		supplierOrder.calculateTotal();
    		
    		//Die Artikel wird geprüft
    		supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
    		
    		float articleTotal;
    		float discount;
    		long quantity;
    		
    		for (SupplierOrderDetail orderArticle : supplierOrder.getSupplierOrderDetail()) {
				if (orderArticle.getSupplierOrderDetailPK().getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == ARTICLE1_ID){
		    		discount = Float.parseFloat(Properties.getProperty("UPDATE_SOD_A1_DISCOUNT"));
		    		quantity = Long.parseLong(Properties.getProperty("UPDATE_SOD_A1_QUANTITY"));
				} else {
					discount = Float.parseFloat(Properties.getProperty("UPDATE_SOD_A2_DISCOUNT"));
		    		quantity = Long.parseLong(Properties.getProperty("UPDATE_SOD_A2_QUANTITY"));
				}
				
				articleTotal = orderArticle.getSupplierOrderDetailPK().getArticleHistory().getPriceSupplier()
						* orderArticle.getQuantity()
						* (1 - (orderArticle.getDiscount() / 100));

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
     * Eine neue Rechnung wird erstellt und mit der richtigen Bestellung zusammengehängt.
     */
    @Test
    public void test3CreateSupplierBill(){
    	try {
    		SupplierOrder supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
	        
    		SupplierBill supplierBill = new SupplierBill();
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		supplierBill.setReceivedDate(dateFormat.parse(Properties.getProperty("INSERT_SB_RECEIVEDDATE")));
    		supplierBill.setPaidDate(dateFormat.parse(Properties.getProperty("INSERT_SB_PAIDDATE")));
    		
    		supplierBill = supplierBillRepository.save(supplierBill);
    		
    		supplierOrder.setSupplierBill(supplierBill);
    		supplierOrder = supplierOrderRepository.save(supplierOrder);
    		
    		//Die Bestellung des Lieferant wird geprüft
    		SUPPLIERBILL_ID = supplierBill.getIdSupplierBill();
    		
    		supplierBill = supplierBillRepository.findOne(SUPPLIERBILL_ID);
    		
    		assertEquals(supplierBill.getReceivedDate(), dateFormat.parse(Properties.getProperty("INSERT_SB_RECEIVEDDATE")));
	        assertEquals(supplierBill.getPaidDate(), dateFormat.parse(Properties.getProperty("INSERT_SB_PAIDDATE")));
    		
	        supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
	        
	        assertEquals(supplierOrder.getSupplierBill(), supplierBill);
	        
    	} catch (Exception ex) {
    		ex.printStackTrace();
			fail(ex.getMessage());
    	}
    }
    
    /**
     * Die lezte Rechnung wird geändert
     */
    @Test
    public void test4UpdateSupplierBill(){
    	try {
    		SupplierOrder supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
	        
    		SupplierBill supplierBill = supplierBillRepository.findOne(SUPPLIERBILL_ID);
    		
    		assertEquals(supplierOrder.getSupplierBill().getIdSupplierBill(), supplierBill.getIdSupplierBill());
    		assertEquals(supplierBill.getSupplierOrder().getIdSupplierOrder(), supplierOrder.getIdSupplierOrder());
    		
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		supplierBill.setReceivedDate(dateFormat.parse(Properties.getProperty("UPDATE_SB_RECEIVEDDATE")));
    		supplierBill.setPaidDate(dateFormat.parse(Properties.getProperty("UPDATE_SB_PAIDDATE")));
    		
    		supplierBill = supplierBillRepository.save(supplierBill);
    		
    		supplierBill = supplierBillRepository.findOne(SUPPLIERBILL_ID);
    		
    		assertEquals(supplierBill.getReceivedDate(), dateFormat.parse(Properties.getProperty("UPDATE_SB_RECEIVEDDATE")));
	        assertEquals(supplierBill.getPaidDate(), dateFormat.parse(Properties.getProperty("UPDATE_SB_PAIDDATE")));
    		
	        supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
	        
	        assertEquals(supplierOrder.getSupplierBill().getIdSupplierBill(), supplierBill.getIdSupplierBill());
	        
    	} catch (Exception ex) {
    		ex.printStackTrace();
			fail(ex.getMessage());
    	}
    }
    
    /**
     * Die Liferantbestellung wird gelöscht
     */
    @Test
    public void test5DeleteSupplierBill()
    {
    	try {
	        SupplierBill supplierBill = supplierBillRepository.findOne(SUPPLIERBILL_ID);
	    	SupplierOrder supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
	    	supplierOrder.setSupplierBill(null);
	        
	    	supplierBillRepository.delete(supplierBill);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!supplierBillRepository.exists(SUPPLIERBILL_ID));
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    }
    
    /**
     * Die Bestellung wird gelöscht
     */
    @Test
    public void test6DeleteSupplierOrder()
    {
    	try {
	    	SupplierOrder supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);

	    	supplierOrderDetailRepository.delete(supplierOrder.getSupplierOrderDetail());
	        
	    	supplierOrderRepository.delete(supplierOrder);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!supplierOrderRepository.exists(SUPPLIERORDER_ID));
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    	CHECK_ERASE = true;
    }
    
    /**
     * Fehlerprovokation Bestellung
     * @throws Exception 
     */
    @Test(expected=ParseException.class)
    public void test7InsertSupplierOrderError() throws Exception{
    	try {
    		Supplier supplier = supplierRepo.findOne(SUPPLIER_ID);
    		Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		//Die Felder werden gefüllt
    		SupplierOrder supplierOrder = new SupplierOrder();
    		supplierOrder.setSendDate(dateFormat.parse(Properties.getProperty("INSERT_SO_SENDDATE_F")));
    		supplierOrder.setDescription(Properties.getProperty("INSERT_SO_DESCRIPTION_F"));
    		supplierOrder.setOrderDate(new Date());
    		supplierOrder.setEmployee(employee);
    		supplierOrder.setSupplier(supplier);
    		supplierOrder.setTotal(0);
    		
    		supplierOrder = supplierOrderRepository.save(supplierOrder);
    		
    		SUPPLIERORDER_ID = supplierOrder.getIdSupplierOrder();
    		
    		//Die Bestellung des Lieferant wird geprüft
    		supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
    		
    		assertEquals(supplierOrder.getSendDate(), dateFormat.parse(Properties.getProperty("INSERT_SO_SENDDATE")));
    		assertEquals(supplierOrder.getDescription(), Properties.getProperty("INSERT_SO_DESCRIPTION"));
	        assertEquals(supplierOrder.getEmployee(), employee);
	        assertEquals(supplierOrder.getSupplier(), supplier);
	        assertEquals(supplierOrder.getTotal(), 0.0F);
    		
    		//Hinzufügen den ersten Artikel
    		Article article = articleRepo.findOne(ARTICLE1_ID);
    		ArticleHistory articleHistory = articleHistoryRepository.findOne(new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(ARTICLE1_ID), article));
    		
    		SupplierOrderDetail supplierOrderDetail = new SupplierOrderDetail();
    		supplierOrderDetail.setSupplierOrderDetailPK(new SupplierOrderDetailPK(supplierOrder, articleHistory));
    		
    		supplierOrderDetail.setDiscount(Float.parseFloat(Properties.getProperty("INSERT_SOD_A1_DISCOUNT_F")));
    		supplierOrderDetail.setQuantity(Long.parseLong(Properties.getProperty("INSERT_SOD_A1_QUANTITY_F")));
    		
    		supplierOrderDetail = supplierOrderDetailRepository.save(supplierOrderDetail);
    		
    		supplierOrder.setTotal(supplierOrder.getTotal() + supplierOrderDetail.getTotalArticle());
    		supplierOrder.getSupplierOrderDetail().add(supplierOrderDetail);
    		
    		//Hinzufügen den zweiten Artikel
    		article = articleRepo.findOne(ARTICLE2_ID);
    		articleHistory = articleHistoryRepository.findOne(new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(ARTICLE2_ID), article));
    		
    		supplierOrderDetail = new SupplierOrderDetail();
    		supplierOrderDetail.setSupplierOrderDetailPK(new SupplierOrderDetailPK(supplierOrder, articleHistory));
    		
    		supplierOrderDetail.setDiscount(Float.parseFloat(Properties.getProperty("INSERT_SOD_A2_DISCOUNT_F")));
    		supplierOrderDetail.setQuantity(Long.parseLong(Properties.getProperty("INSERT_SOD_A2_QUANTITY_F")));
    		
    		supplierOrderDetail = supplierOrderDetailRepository.save(supplierOrderDetail);
    		
    		supplierOrder.setTotal(supplierOrder.getTotal() + supplierOrderDetail.getTotalArticle());
    		supplierOrder.getSupplierOrderDetail().add(supplierOrderDetail);
    		
    		//Die Artikel wird geprüft
    		supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
    		
    		float articleTotal;
    		float discount;
    		long quantity;
    		
    		for (SupplierOrderDetail orderArticle : supplierOrder.getSupplierOrderDetail()) {
				if (orderArticle.getSupplierOrderDetailPK().getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == ARTICLE1_ID){
		    		discount = Float.parseFloat(Properties.getProperty("INSERT_SOD_A1_DISCOUNT"));
		    		quantity = Long.parseLong(Properties.getProperty("INSERT_SOD_A1_QUANTITY"));
				} else {
					discount = Float.parseFloat(Properties.getProperty("INSERT_SOD_A2_DISCOUNT"));
		    		quantity = Long.parseLong(Properties.getProperty("INSERT_SOD_A2_QUANTITY"));
				}
				
				articleTotal = orderArticle.getSupplierOrderDetailPK().getArticleHistory().getPriceSupplier()
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
     * Fehlerprovokation Lieferantrechnung
     * @throws Exception 
     */
    @Test(expected=ParseException.class)
    public void test8CreateSupplierBillError() throws Exception{
    	try {
    		SupplierOrder supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
	        
    		SupplierBill supplierBill = new SupplierBill();
    		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
    		
    		supplierBill.setReceivedDate(dateFormat.parse(Properties.getProperty("INSERT_SB_RECEIVEDDATE_F")));
    		supplierBill.setPaidDate(dateFormat.parse(Properties.getProperty("INSERT_SB_PAIDDATE_F")));
    		
    		supplierBill = supplierBillRepository.save(supplierBill);
    		
    		supplierOrder.setSupplierBill(supplierBill);
    		supplierOrder = supplierOrderRepository.save(supplierOrder);
    		
    		//Die Bestellung des Lieferant wird geprüft
    		SUPPLIERBILL_ID = supplierBill.getIdSupplierBill();
    		
    		supplierBill = supplierBillRepository.findOne(SUPPLIERBILL_ID);
    		
    		assertEquals(supplierBill.getReceivedDate(), dateFormat.parse(Properties.getProperty("INSERT_SB_RECEIVEDDATE")));
	        assertEquals(supplierBill.getPaidDate(), dateFormat.parse(Properties.getProperty("INSERT_SB_PAIDDATE")));
    		
	        supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
	        
	        assertEquals(supplierOrder.getSupplierBill(), supplierBill);
	        
    	} catch (Exception ex) {
    		throw ex;
    	}
    }
}
