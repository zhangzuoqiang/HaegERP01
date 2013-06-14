package org.haegerp.entity;

import java.io.FileInputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.haegerp.entity.repository.ArticleCategoryRepository;
import org.haegerp.entity.repository.ArticleRepository;
import org.haegerp.entity.repository.DivisionRepository;
import org.haegerp.entity.repository.EmployeeRepository;
import org.haegerp.entity.repository.PermissionRepository;
import org.haegerp.entity.repository.SalaryCategoryRepository;
import org.haegerp.entity.repository.SupplierOrderDetailRepository;
import org.haegerp.entity.repository.SupplierOrderRepository;
import org.haegerp.entity.repository.SupplierRepository;
import org.haegerp.entity.repository.UserGroupRepository;
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
	
    private Properties properties = new Properties();
    
    private static long ARTICLE1_ID;
    private static long ARTICLE2_ID;
    private static long ARTICLE_CATEGORY_ID;
    
    private static long SUPPLIER_ID;
    
    private static long EMPLOYEE_ID;
    private static long SALARY_CATEGORY_ID;
    private static long DIVISION_ID;
    private static long USER_GROUP_ID;
    
    private static long SUPPLIERORDER_ID;
    
    @Autowired
    private ArticleRepository articleRepo;
    @Autowired
    private ArticleCategoryRepository articleCategoryRepo;
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
    
    @Override
    @Before
    public void setUp() throws Exception {
    	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
    	
    	//Die Artikel werden erstellt
    	//Kategorie
    	ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setName(properties.getProperty("INSERT_AC_NAME"));
        articleCategory.setDescription(properties.getProperty("INSERT_AC_DESCRIPTION"));
        
        articleCategory = articleCategoryRepo.save(articleCategory);
        ARTICLE_CATEGORY_ID = articleCategory.getIdArticleCategory();
        
        //Artikel eins
        Article article = new Article();
        article.setArticleCategory(articleCategory);
        article.setColor(properties.getProperty("INSERT_A_COLOR"));
        article.setDescription(properties.getProperty("INSERT_A_DESCRIPTION"));
        article.setEan(Long.parseLong(properties.getProperty("INSERT_A_EAN")));
        article.setName(properties.getProperty("INSERT_A_NAME"));
        article.setPriceGross(Float.parseFloat(properties.getProperty("INSERT_A_PRICEGROSS")));
        article.setPriceVat(Float.parseFloat(properties.getProperty("INSERT_A_PRICEVAT")));
        article.setSizeH(Float.parseFloat(properties.getProperty("INSERT_A_SIZEH")));
        article.setSizeL(Float.parseFloat(properties.getProperty("INSERT_A_SIZEL")));
        article.setSizeW(Float.parseFloat(properties.getProperty("INSERT_A_SIZEW")));
        article.setStock(Integer.parseInt(properties.getProperty("INSERT_A_STOCK")));
        
        article = articleRepo.save(article);
        ARTICLE1_ID = article.getIdArticle();
        
        //Artikel zwei
        article = new Article();
        article.setArticleCategory(articleCategory);
        article.setColor(properties.getProperty("UPDATE_A_COLOR"));
        article.setDescription(properties.getProperty("UPDATE_A_DESCRIPTION"));
        article.setEan(Long.parseLong(properties.getProperty("UPDATE_A_EAN")));
        article.setName(properties.getProperty("UPDATE_A_NAME"));
        article.setPriceGross(Float.parseFloat(properties.getProperty("UPDATE_A_PRICEGROSS")));
        article.setPriceVat(Float.parseFloat(properties.getProperty("UPDATE_A_PRICEVAT")));
        article.setSizeH(Float.parseFloat(properties.getProperty("UPDATE_A_SIZEH")));
        article.setSizeL(Float.parseFloat(properties.getProperty("UPDATE_A_SIZEL")));
        article.setSizeW(Float.parseFloat(properties.getProperty("UPDATE_A_SIZEW")));
        article.setStock(Integer.parseInt(properties.getProperty("UPDATE_A_STOCK")));
        
        article = articleRepo.save(article);
        ARTICLE2_ID = article.getIdArticle();
        
        //Der Lieferant wird erstellt
        Supplier supplier = new Supplier();
    	supplier.setName(properties.getProperty("INSERT_S_NAME"));
    	supplier.setTaxId(Long.parseLong(properties.getProperty("INSERT_S_TAXID")));
    	supplier.setAddress(properties.getProperty("INSERT_S_ADDRESS"));
    	supplier.setZipCode(properties.getProperty("INSERT_S_ZIPCODE"));
    	supplier.setCity(properties.getProperty("INSERT_S_CITY"));
    	supplier.setRegion(properties.getProperty("INSERT_S_REGION"));
    	supplier.setCountry(properties.getProperty("INSERT_S_COUNTRY"));
    	supplier.setEmail(properties.getProperty("INSERT_S_EMAIL"));
    	supplier.setPhoneNumber(properties.getProperty("INSERT_S_PHONENUMBER"));
    	supplier.setMobileNumber(properties.getProperty("INSERT_S_MOBILENUMBER"));
    	supplier.setFaxNumber(properties.getProperty("INSERT_S_FAXNUMBER"));
    	supplier.setDescription(properties.getProperty("INSERT_S_DESCRIPTION"));
    	
    	supplier = supplierRepo.save(supplier);
    	SUPPLIER_ID = supplier.getIdBusinessPartner();
    	
    	//Mitarbeiter wird erstellt
    	//Gehaltkategorie
    	SalaryCategory salaryCategory = new SalaryCategory();
        salaryCategory.setDescription(properties.getProperty("INSERT_SC_DESCRIPTION"));
        salaryCategory.setSalaryFrom(Float.parseFloat(properties.getProperty("INSERT_SC_SALARYFROM")));
        salaryCategory.setSalaryTo(Float.parseFloat(properties.getProperty("INSERT_SC_SALARYTO")));
        
        salaryCategory = salaryCategoryRepository.save(salaryCategory);
        SALARY_CATEGORY_ID = salaryCategory.getIdSalaryCategory();
        
        //Abteilung
        Division division = new Division();
        division.setDescription(properties.getProperty("INSERT_D_DESCRIPTION"));
        division.setName(properties.getProperty("INSERT_D_NAME"));
        
        division = divisionRepository.save(division);
        DIVISION_ID = division.getIdDivision();
        
        //Benutzergruppe
        UserGroup userGroup = new UserGroup();
        userGroup.setDescription(properties.getProperty("INSERT_UG_DESCRIPTION"));
        userGroup.setName(properties.getProperty("INSERT_UG_NAME"));
        
        //Hinzufügen erlaubnise
        List<Permission> permissionList = new LinkedList<Permission>();
        
        permissionList.add(permissionRepository.findOne(Long.parseLong(properties.getProperty("INSERT_UG_PERMISSION1"))));
        permissionList.add(permissionRepository.findOne(Long.parseLong(properties.getProperty("INSERT_UG_PERMISSION2"))));
        permissionList.add(permissionRepository.findOne(Long.parseLong(properties.getProperty("INSERT_UG_PERMISSION3"))));
        
        for (int i = 0; i < permissionList.size(); i++) {
			userGroup.getPermissions().add(permissionList.get(i));
		}
        
        userGroup = userGroupRepository.save(userGroup);
        USER_GROUP_ID = userGroup.getIdUserGroup();
        
        //Mitarbeiter
        Employee employee = new Employee();
        employee.setAddress(properties.getProperty("INSERT_E_ADDRESS"));
        employee.setCity(properties.getProperty("INSERT_E_CITY"));
        employee.setCountry(properties.getProperty("INSERT_E_COUNTRY"));
        employee.setDivision(division);
        employee.setEmail(properties.getProperty("INSERT_E_EMAIL"));
        employee.setIdCard(Long.parseLong(properties.getProperty("INSERT_E_IDCARD")));
        employee.setMobileNumber(properties.getProperty("INSERT_E_MOBILENUMBER"));
        employee.setName(properties.getProperty("INSERT_E_NAME"));
        employee.setPhoneNumber(properties.getProperty("INSERT_E_PHONENUMBER"));
        employee.setRegion(properties.getProperty("INSERT_E_REGION"));
        employee.setSalaryCategory(salaryCategory);
        employee.setUserGroup(userGroup);
        employee.setZipCode(properties.getProperty("INSERT_E_ZIPCODE"));
        employee.setPassword(properties.getProperty("INSERT_E_PASSWORD"));
        employee.setUsername(properties.getProperty("INSERT_E_USERNAME"));
        
        employee = employeeRepository.save(employee);
        EMPLOYEE_ID = employee.getIdEmployee();
        
    }
    
    @Override
    @After
    public void tearDown() throws Exception {
//    	//Die Artikel werden gelöscht
//    	Article article = articleRepo.findOne(ARTICLE1_ID);
//        articleRepo.delete(article);
//        article = articleRepo.findOne(ARTICLE2_ID);
//        articleRepo.delete(article);
//        
//        //Die Artikelkategorie wird gelöscht
//        ArticleCategory articleCategory = articleCategoryRepo.findOne(ARTICLE_CATEGORY_ID);
//        articleCategoryRepo.delete(articleCategory);
//        
//        //Der Lieferant wird gelöscht
//        Supplier supplier = supplierRepo.findOne(SUPPLIER_ID);
//        supplierRepo.delete(supplier.getIdBusinessPartner());
//        
//        //Der Mitarbeiter wird gelöscht
//        Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
//        employeeRepository.delete(employee);
//        //Gehaltkategorie
//        SalaryCategory salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
//        salaryCategoryRepository.delete(salaryCategory);
//        //Abteilung
//        Division division = divisionRepository.findOne(DIVISION_ID);
//        divisionRepository.delete(division);
//        //Benutzergrupper
//        UserGroup userGroup = userGroupRepository.findOne(USER_GROUP_ID);
//        userGroupRepository.delete(userGroup);
    }
    
    /**
     * Eine Bestellung wird erstellt
     */
    @Test
    public void test1InsertSupplierOrder(){
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
    		
    		Supplier supplier = supplierRepo.findOne(SUPPLIER_ID);
    		Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
    		
    		//Die Felder werden gefüllt
    		SupplierOrder supplierOrder = new SupplierOrder();
    		supplierOrder.setDescription(properties.getProperty("INSERT_SO_DESCRIPTION"));
    		supplierOrder.setOrderDate(new Date());
    		supplierOrder.setEmployee(employee);
    		supplierOrder.setSupplier(supplier);
    		supplierOrder.setTotal(0);
    		
    		supplierOrder = supplierOrderRepository.save(supplierOrder);
    		
    		SUPPLIERORDER_ID = supplierOrder.getIdSupplierOrder();
    		
    		//Die Bestellung des Lieferant wird geprüft
    		supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
    		
    		assertEquals(supplierOrder.getDescription(), properties.getProperty("INSERT_SO_DESCRIPTION"));
	        assertEquals(supplierOrder.getEmployee(), employee);
	        assertEquals(supplierOrder.getSupplier(), supplier);
	        assertEquals(supplierOrder.getTotal(), Float.parseFloat("0.0"));
    		
    		//Hinzufügen den ersten Artikel
    		Article article = articleRepo.findOne(ARTICLE1_ID);
    		SupplierOrderDetail supplierOrderDetail = new SupplierOrderDetail();
    		supplierOrderDetail.setIdSupplierOrderDetail(new IdSupplierOrderDetail());
    		supplierOrderDetail.getIdSupplierOrderDetail().setArticle(article);
    		supplierOrderDetail.getIdSupplierOrderDetail().setSupplierOrder(supplierOrder);
    		supplierOrderDetail.setDiscount(Float.parseFloat(properties.getProperty("INSERT_SOD_A1_DISCOUNT")));
    		supplierOrderDetail.setQuantity(Long.parseLong(properties.getProperty("INSERT_SOD_A1_QUANTITY")));
    		supplierOrderDetail.setPrice(Float.parseFloat(properties.getProperty("INSERT_SOD_A1_PRICE")));
    		supplierOrderDetail.setPriceVat(Float.parseFloat(properties.getProperty("INSERT_SOD_A1_PRICEVAT")));
    		
    		supplierOrderDetail = supplierOrderDetailRepository.save(supplierOrderDetail);
    		
    		supplierOrder.setTotal(supplierOrder.getTotal() + supplierOrderDetail.getArticleTotal());
    		supplierOrder.getSupplierOrderDetail().add(supplierOrderDetail);
    		
    		//Hinzufügen den zweiten Artikel
    		article = articleRepo.findOne(ARTICLE2_ID);
    		supplierOrderDetail = new SupplierOrderDetail();
    		supplierOrderDetail.setIdSupplierOrderDetail(new IdSupplierOrderDetail());
    		supplierOrderDetail.getIdSupplierOrderDetail().setArticle(article);
    		supplierOrderDetail.getIdSupplierOrderDetail().setSupplierOrder(supplierOrder);
    		supplierOrderDetail.setDiscount(Float.parseFloat(properties.getProperty("INSERT_SOD_A2_DISCOUNT")));
    		supplierOrderDetail.setQuantity(Long.parseLong(properties.getProperty("INSERT_SOD_A2_QUANTITY")));
    		supplierOrderDetail.setPrice(Float.parseFloat(properties.getProperty("INSERT_SOD_A2_PRICE")));
    		supplierOrderDetail.setPriceVat(Float.parseFloat(properties.getProperty("INSERT_SOD_A2_PRICEVAT")));
    		
    		supplierOrderDetail = supplierOrderDetailRepository.save(supplierOrderDetail);
    		
    		supplierOrder.setTotal(supplierOrder.getTotal() + supplierOrderDetail.getArticleTotal());
    		supplierOrder.getSupplierOrderDetail().add(supplierOrderDetail);
    		
    		supplierOrder = supplierOrderRepository.save(supplierOrder);
    		
    		//Die Artikel wird geprüft
    		supplierOrder = supplierOrderRepository.findOne(SUPPLIERORDER_ID);
    		
    		float articleTotal;
    		float discount;
    		long quantity;
    		float price;
    		float priceVat;
    		
    		for (SupplierOrderDetail orderArticle : supplierOrder.getSupplierOrderDetail()) {
				if (orderArticle.getIdSupplierOrderDetail().getArticle().getIdArticle() == ARTICLE1_ID){
		    		discount = Float.parseFloat(properties.getProperty("INSERT_SOD_A1_DISCOUNT"));
		    		quantity = Long.parseLong(properties.getProperty("INSERT_SOD_A1_QUANTITY"));
		    		price = Float.parseFloat(properties.getProperty("INSERT_SOD_A1_PRICE"));
		    		priceVat = Float.parseFloat(properties.getProperty("INSERT_SOD_A1_PRICEVAT"));
				} else {
					discount = Float.parseFloat(properties.getProperty("INSERT_SOD_A2_DISCOUNT"));
		    		quantity = Long.parseLong(properties.getProperty("INSERT_SOD_A2_QUANTITY"));
		    		price = Float.parseFloat(properties.getProperty("INSERT_SOD_A2_PRICE"));
		    		priceVat = Float.parseFloat(properties.getProperty("INSERT_SOD_A2_PRICEVAT"));
				}
				
				articleTotal = orderArticle.getPrice() 
	    				* ((orderArticle.getPriceVat() / 100) + 1) 
	    				* orderArticle.getQuantity()
	    				* (orderArticle.getDiscount() / 100);
				
				assertEquals(orderArticle.getArticleTotal(), articleTotal);
				assertEquals(orderArticle.getDiscount(), discount);
	    		assertEquals(orderArticle.getQuantity(), quantity);
	    		assertEquals(orderArticle.getPrice(), price);
	    		assertEquals(orderArticle.getPriceVat(), priceVat);
			}
    		
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
    }
}
