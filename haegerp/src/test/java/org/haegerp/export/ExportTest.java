package org.haegerp.export;

import java.util.Date;

import junit.framework.TestCase;

import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.article.ArticleHistoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.session.EmployeeSession;
import org.haegerp.tools.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 * Dieses Test Suite wird Exporte testen
 * 
 * @author Fabio Codinha
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/spring-data.xml")
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class ExportTest extends TestCase {
	    
    private static long ARTICLE_CATEGORY_ID;
    private static long ARTICLE_ID;
    
    private static Long[] ARTICLES_ID;
    
	private static boolean CHECK_SETUP = true;
	private static boolean CHECK_DESTROY = false;
    
    //Repositories
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;
    
    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    @Qualifier("exportToCSV")
    private Export exportToCSV;
    
    @Autowired
    @Qualifier("exportToXML")
    private Export exportToXML;
    
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
	    	
	    	insertArticleCategory();
	    	ARTICLES_ID = new Long[4];
        	insertArticle();
        	ARTICLES_ID[0] = ARTICLE_ID;
        	insertArticle();
        	ARTICLES_ID[1] = ARTICLE_ID;
        	insertArticle();
        	ARTICLES_ID[2] = ARTICLE_ID;
        	insertArticle();
        	ARTICLES_ID[3] = ARTICLE_ID;
    	}
    }
    
    @Override
    @After
    public void tearDown() throws Exception {
    	super.tearDown();
    	if (CHECK_DESTROY)
    	{
    		CHECK_DESTROY = false;
    		
    		articleRepository.delete(ARTICLES_ID[0]);
    		articleHistoryRepository.deleteAllVersionsOfArticle(ARTICLES_ID[0]);
    		articleRepository.delete(ARTICLES_ID[1]);
    		articleHistoryRepository.deleteAllVersionsOfArticle(ARTICLES_ID[1]);
    		articleRepository.delete(ARTICLES_ID[2]);
    		articleHistoryRepository.deleteAllVersionsOfArticle(ARTICLES_ID[2]);
    		articleRepository.delete(ARTICLES_ID[3]);
    		articleHistoryRepository.deleteAllVersionsOfArticle(ARTICLES_ID[3]);
    		articleCategoryRepository.delete(ARTICLE_CATEGORY_ID);
    	}
    }
    
    /**
     * Export zur CSV-Datei
     */
    @Test
    public void test1ExportCSV(){
        try {
        	assertTrue(exportToCSV.export(articleRepository.findAll(), Properties.getProperty("EXPORT_PATH")));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Export zur XML-Datei
     */
    @Test
    public void test2ExportXML(){
        try {
        	CHECK_DESTROY = true;
        	assertTrue(exportToXML.export(articleRepository.findAll(), Properties.getProperty("EXPORT_PATH")));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Eine Artikelkategorie wird in die Datenbank erstellt
     */
    public void insertArticleCategory()
    {
		try {
	        ArticleCategory articleCategory = new ArticleCategory();
	        articleCategory.setName(Properties.getProperty("INSERT_AC_NAME"));
	        articleCategory.setDescription(Properties.getProperty("INSERT_AC_DESCRIPTION"));
	        articleCategory.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
	        articleCategory.setLastModifiedDate(new Date());
	        
	        articleCategory = articleCategoryRepository.save(articleCategory);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        ARTICLE_CATEGORY_ID = articleCategory.getIdArticleCategory();
	        
	        articleCategory = articleCategoryRepository.findOne(ARTICLE_CATEGORY_ID);
	        
	        assertEquals(articleCategory.getName(), Properties.getProperty("INSERT_AC_NAME"));
	        assertEquals(articleCategory.getDescription(), Properties.getProperty("INSERT_AC_DESCRIPTION"));
	    
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    }
    
    /**
     * Ein Artikel wird erstellt
     */
    public void insertArticle(){
    	try {
	    	//Die Artikelkategorie wird geholt
	        ArticleCategory articleCategory = articleCategoryRepository.findOne(ARTICLE_CATEGORY_ID);
	    	
	        Article article = new Article();
        
	        //Die Felder werden gefüllt
	        article.setArticleCategory(articleCategory);
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
	        article.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
	        article.setLastModifiedDate(new Date());
	        
	        article = articleRepository.save(article);
	        
	        //Der erstellter Artikel wird geprüft
	        ARTICLE_ID = article.getIdArticle();
	        
	        article = articleRepository.findOne(ARTICLE_ID);
	        
	        assertEquals(article.getColor(), Properties.getProperty("INSERT_A_COLOR"));
	        assertEquals(article.getDescription(), Properties.getProperty("INSERT_A_DESCRIPTION"));
	        assertEquals(article.getEan(), Long.parseLong(Properties.getProperty("INSERT_A_EAN")));
	        assertEquals(article.getName(), Properties.getProperty("INSERT_A_NAME"));
	        assertEquals(article.getPriceGross(), Float.parseFloat(Properties.getProperty("INSERT_A_PRICEGROSS")));
	        assertEquals(article.getPriceSupplier(), Float.parseFloat(Properties.getProperty("INSERT_A_PRICESUPPLIER")));
	        assertEquals(article.getPriceVat(), Float.parseFloat(Properties.getProperty("INSERT_A_PRICEVAT")));
	        assertEquals(article.getSizeH(), Float.parseFloat(Properties.getProperty("INSERT_A_SIZEH")));
	        assertEquals(article.getSizeL(), Float.parseFloat(Properties.getProperty("INSERT_A_SIZEL")));
	        assertEquals(article.getSizeW(), Float.parseFloat(Properties.getProperty("INSERT_A_SIZEW")));
	        assertEquals(article.getStock(), Long.parseLong(Properties.getProperty("INSERT_A_STOCK")));
	        assertEquals(article.getArticleCategory(), articleCategory);
        
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    }
}