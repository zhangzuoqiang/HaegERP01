package org.haegerp.entity;

import java.util.Date;

import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.entity.repository.article.ArticleHistoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.exception.LengthOverflowException;
import org.haegerp.session.EmployeeSession;
import org.haegerp.tools.Properties;
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
 * @author Fabio Codinha
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/spring-data.xml")
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class Article_ArticleCategoryTest extends TestCase {
	
    private static long ARTICLE_ID;
    private static long ARTICLE_CATEGORY_ID;
    private static boolean CHECK_SETUP = true;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;
    
    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
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
     * Eine Artikelkategorie wird in die Datenbank erstellt
     */
    @Test
    public void test1InsertArticleCategory()
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
     * Die letzte Artikelkategorie wird geändert
     */
    @Test
    public void test2UpdateArticleCategory()
    {
    	try {
    		ArticleCategory articleCategory = articleCategoryRepository.findOne(ARTICLE_CATEGORY_ID);
    	
	        articleCategory.setName(Properties.getProperty("UPDATE_AC_NAME"));
	        articleCategory.setDescription(Properties.getProperty("UPDATE_AC_DESCRIPTION"));
	        articleCategory.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
	        articleCategory.setLastModifiedDate(new Date());
	        
	        articleCategory = articleCategoryRepository.save(articleCategory);
	        
	        articleCategory = articleCategoryRepository.findOne(ARTICLE_CATEGORY_ID);
	        
	        assertEquals(articleCategory.getName(), Properties.getProperty("UPDATE_AC_NAME"));
	        assertEquals(articleCategory.getDescription(), Properties.getProperty("UPDATE_AC_DESCRIPTION"));
        
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    }
    
    /**
     * Ein Artikel wird erstellt
     */
    @Test
    public void test3InsertArticle(){
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
	        
	        //Die Artikelversion wird kontrolliert
	        //articleRepo.createArticleHistory(article);
	        
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

    /**
     * Der letzter Artikel wird geändert
     */
    @Test
    public void test4UpdateArticle(){
    	
    	try {
	    	//Die Artikelkategorie wird geholt
	        Article article = articleRepository.findOne(ARTICLE_ID);
	    	
	        //Die Felder werden geändert
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
	        article.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
	        article.setLastModifiedDate(new Date());
	        
	        article = articleRepository.save(article);
	        
	        //Der geänderter Artikel wird geprüft
	        article = articleRepository.findOne(ARTICLE_ID);
	        
	        assertEquals(article.getColor(), Properties.getProperty("UPDATE_A_COLOR"));
	        assertEquals(article.getDescription(), Properties.getProperty("UPDATE_A_DESCRIPTION"));
	        assertEquals(article.getEan(), Long.parseLong(Properties.getProperty("UPDATE_A_EAN")));
	        assertEquals(article.getName(), Properties.getProperty("UPDATE_A_NAME"));
	        assertEquals(article.getPriceGross(), Float.parseFloat(Properties.getProperty("UPDATE_A_PRICEGROSS")));
	        assertEquals(article.getPriceSupplier(), Float.parseFloat(Properties.getProperty("UPDATE_A_PRICESUPPLIER")));
	        assertEquals(article.getPriceVat(), Float.parseFloat(Properties.getProperty("UPDATE_A_PRICEVAT")));
	        assertEquals(article.getSizeH(), Float.parseFloat(Properties.getProperty("UPDATE_A_SIZEH")));
	        assertEquals(article.getSizeL(), Float.parseFloat(Properties.getProperty("UPDATE_A_SIZEL")));
	        assertEquals(article.getSizeW(), Float.parseFloat(Properties.getProperty("UPDATE_A_SIZEW")));
	        assertEquals(article.getStock(), Long.parseLong(Properties.getProperty("UPDATE_A_STOCK")));
    	} catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    }
    
    /**
     * Der letzter Artikel wird gelöscht
     */
    @Test
    public void test5DeleteArticle(){
    	try {
	        Article article = articleRepository.findOne(ARTICLE_ID);
	    	
	        articleHistoryRepository.deleteAllVersionsOfArticle(article.getIdArticle());
	        
	        article.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
	        article.setLastModifiedDate(new Date());
	        
	        articleRepository.delete(article);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!articleRepository.exists(ARTICLE_ID));
    	} catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    }
    
    /**
     * Die Artikelkategorie wird gelöscht
     */
    @Test
    public void test6DeleteArticleCategory()
    {
    	try {
	        ArticleCategory articleCategory = articleCategoryRepository.findOne(ARTICLE_CATEGORY_ID);
	    	
	        articleCategory.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
	        articleCategory.setLastModifiedDate(new Date());
	        
	        articleCategoryRepository.delete(articleCategory);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!articleCategoryRepository.exists(ARTICLE_CATEGORY_ID));
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		}
    }
    
    /**
     * Fehlerprovokation ArticleCategory
     * @author Andreas Monschau 15.06.2013
     * @throws Exception 
     */
    @Test(expected=LengthOverflowException.class)
    public void test7InsertArticleCategoryError() throws Exception
    {
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setName(Properties.getProperty("INSERT_AC_NAME_F"));
        articleCategory.setDescription(Properties.getProperty("INSERT_AC_DESCRIPTION_F"));
        
        articleCategory = articleCategoryRepository.save(articleCategory);

        //Die erstellt Artikelkategorie wird geprüft
        ARTICLE_CATEGORY_ID = articleCategory.getIdArticleCategory();
        
        
        articleCategory = articleCategoryRepository.findOne(ARTICLE_CATEGORY_ID);
        
        assertEquals(articleCategory.getName(), Properties.getProperty("INSERT_AC_NAME"));
        assertEquals(articleCategory.getDescription(), Properties.getProperty("INSERT_AC_DESCRIPTION"));
    }
    
    /**
     * Fehlerprovokation Article
     * @author Andreas Monschau 15.06.2013
     * @throws Exception 
     */
    @Test(expected=NumberFormatException.class)
    public void test8InsertArticleError() throws Exception{
    	try {
	    	//Die Artikelkategorie wird geholt
	        ArticleCategory articleCategory = articleCategoryRepository.findOne(ARTICLE_CATEGORY_ID);
	    	
	        Article article = new Article();
	    
	        //Die Felder werden gefüllt
	        article.setArticleCategory(articleCategory);
	        article.setColor(Properties.getProperty("INSERT_A_COLOR_F"));
	        article.setDescription(Properties.getProperty("INSERT_A_DESCRIPTION_F"));
	        article.setEan(Long.parseLong(Properties.getProperty("INSERT_A_EAN_F")));
	        article.setName(Properties.getProperty("INSERT_A_NAME_F"));
	        article.setPriceGross(Float.parseFloat(Properties.getProperty("INSERT_A_PRICEGROSS_F")));
	        article.setPriceVat(Float.parseFloat(Properties.getProperty("INSERT_A_PRICEVAT_F")));
	        article.setSizeH(Float.parseFloat(Properties.getProperty("INSERT_A_SIZEH_F")));
	        article.setSizeL(Float.parseFloat(Properties.getProperty("INSERT_A_SIZEL_F")));
	        article.setSizeW(Float.parseFloat(Properties.getProperty("INSERT_A_SIZEW_F")));
	        article.setStock(Long.parseLong(Properties.getProperty("INSERT_A_STOCK_F")));
	        
	        article = articleRepository.save(article);
	        
	        //Der erstellter Artikel wird geprüft
	        ARTICLE_ID = article.getIdArticle();
	        
	        article = articleRepository.findOne(ARTICLE_ID);
	        
	        assertEquals(article.getColor(), Properties.getProperty("INSERT_A_COLOR"));
	        assertEquals(article.getDescription(), Properties.getProperty("INSERT_A_DESCRIPTION"));
	        assertEquals(article.getEan(), Long.parseLong(Properties.getProperty("INSERT_A_EAN")));
	        assertEquals(article.getName(), Properties.getProperty("INSERT_A_NAME"));
	        assertEquals(article.getPriceGross(), Float.parseFloat(Properties.getProperty("INSERT_A_PRICEGROSS")));
	        assertEquals(article.getPriceVat(), Float.parseFloat(Properties.getProperty("INSERT_A_PRICEVAT")));
	        assertEquals(article.getSizeH(), Float.parseFloat(Properties.getProperty("INSERT_A_SIZEH")));
	        assertEquals(article.getSizeL(), Float.parseFloat(Properties.getProperty("INSERT_A_SIZEL")));
	        assertEquals(article.getSizeW(), Float.parseFloat(Properties.getProperty("INSERT_A_SIZEW")));
	        assertEquals(article.getStock(), Integer.parseInt(Properties.getProperty("INSERT_A_STOCK")));
	        assertEquals(article.getArticleCategory(), articleCategory);
    	} catch (Exception ex) {
    		throw ex;
    	}
    }
}
