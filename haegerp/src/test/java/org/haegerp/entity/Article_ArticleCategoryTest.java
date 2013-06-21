package org.haegerp.entity;

import java.io.FileInputStream;
import java.util.Properties;

import org.haegerp.entity.repository.ArticleCategoryRepository;
import org.haegerp.entity.repository.ArticleHistoryRepository;
import org.haegerp.entity.repository.ArticleRepository;
import org.haegerp.entity.repository.EmployeeRepository;
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
public class Article_ArticleCategoryTest extends TestCase {
	
    private static Properties properties = new Properties();
    
    private static long ARTICLE_ID;
    private static long ARTICLE_CATEGORY_ID;
    private static boolean CHECK_SETUP = true;
    
    @Autowired
    private ArticleRepository articleRepo;
    
    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;
    
    @Autowired
    private ArticleCategoryRepository articleCategoryRepo;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    @Before
    public void setUp() throws Exception {
    	super.setUp();
    	if (CHECK_SETUP)
    	{
    		CHECK_SETUP = false;
	    	properties.load(new FileInputStream("./config.properties"));
	    	Session.setEmployee(employeeRepository.findOne(1L));
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
	        articleCategory.setName(properties.getProperty("INSERT_AC_NAME"));
	        articleCategory.setDescription(properties.getProperty("INSERT_AC_DESCRIPTION"));
	        
	        articleCategory = articleCategoryRepo.performNew(articleCategory);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        ARTICLE_CATEGORY_ID = articleCategory.getIdArticleCategory();
	        
	        
	        articleCategory = articleCategoryRepo.findOne(ARTICLE_CATEGORY_ID);
	        
	        assertEquals(articleCategory.getName(), properties.getProperty("INSERT_AC_NAME"));
	        assertEquals(articleCategory.getDescription(), properties.getProperty("INSERT_AC_DESCRIPTION"));
        
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
    		ArticleCategory articleCategory = articleCategoryRepo.findOne(ARTICLE_CATEGORY_ID);
    	
	        articleCategory.setName(properties.getProperty("UPDATE_AC_NAME"));
	        articleCategory.setDescription(properties.getProperty("UPDATE_AC_DESCRIPTION"));
	        
	        articleCategory = articleCategoryRepo.performEdit(articleCategory);
	        
	        articleCategory = articleCategoryRepo.findOne(ARTICLE_CATEGORY_ID);
	        
	        assertEquals(articleCategory.getName(), properties.getProperty("UPDATE_AC_NAME"));
	        assertEquals(articleCategory.getDescription(), properties.getProperty("UPDATE_AC_DESCRIPTION"));
        
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
	        ArticleCategory articleCategory = articleCategoryRepo.findOne(ARTICLE_CATEGORY_ID);
	    	
	        Article article = new Article();
        
	        //Die Felder werden gefüllt
	        article.setArticleCategory(articleCategory);
	        article.setColor(properties.getProperty("INSERT_A_COLOR"));
	        article.setDescription(properties.getProperty("INSERT_A_DESCRIPTION"));
	        article.setEan(Long.parseLong(properties.getProperty("INSERT_A_EAN")));
	        article.setName(properties.getProperty("INSERT_A_NAME"));
	        article.setPriceGross(Float.parseFloat(properties.getProperty("INSERT_A_PRICEGROSS")));
	        article.setPriceSupplier(Float.parseFloat(properties.getProperty("INSERT_A_PRICESUPPLIER")));
	        article.setPriceVat(Float.parseFloat(properties.getProperty("INSERT_A_PRICEVAT")));
	        article.setSizeH(Float.parseFloat(properties.getProperty("INSERT_A_SIZEH")));
	        article.setSizeL(Float.parseFloat(properties.getProperty("INSERT_A_SIZEL")));
	        article.setSizeW(Float.parseFloat(properties.getProperty("INSERT_A_SIZEW")));
	        article.setStock(Long.parseLong(properties.getProperty("INSERT_A_STOCK")));
	        
	        article = articleRepo.performNew(article);
	        
	        //Die Artikelversion wird kontrolliert
	        articleRepo.createArticleHistory(article);
	        
	        //Der erstellter Artikel wird geprüft
	        ARTICLE_ID = article.getIdArticle();
	        
	        article = articleRepo.findOne(ARTICLE_ID);
	        
	        assertEquals(article.getColor(), properties.getProperty("INSERT_A_COLOR"));
	        assertEquals(article.getDescription(), properties.getProperty("INSERT_A_DESCRIPTION"));
	        assertEquals(article.getEan(), Long.parseLong(properties.getProperty("INSERT_A_EAN")));
	        assertEquals(article.getName(), properties.getProperty("INSERT_A_NAME"));
	        assertEquals(article.getPriceGross(), Float.parseFloat(properties.getProperty("INSERT_A_PRICEGROSS")));
	        assertEquals(article.getPriceSupplier(), Float.parseFloat(properties.getProperty("INSERT_A_PRICESUPPLIER")));
	        assertEquals(article.getPriceVat(), Float.parseFloat(properties.getProperty("INSERT_A_PRICEVAT")));
	        assertEquals(article.getSizeH(), Float.parseFloat(properties.getProperty("INSERT_A_SIZEH")));
	        assertEquals(article.getSizeL(), Float.parseFloat(properties.getProperty("INSERT_A_SIZEL")));
	        assertEquals(article.getSizeW(), Float.parseFloat(properties.getProperty("INSERT_A_SIZEW")));
	        assertEquals(article.getStock(), Long.parseLong(properties.getProperty("INSERT_A_STOCK")));
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
	        Article article = articleRepo.findOne(ARTICLE_ID);
	    	
	        //Die Felder werden geändert
	        article.setColor(properties.getProperty("UPDATE_A_COLOR"));
	        article.setDescription(properties.getProperty("UPDATE_A_DESCRIPTION"));
	        article.setEan(Long.parseLong(properties.getProperty("UPDATE_A_EAN")));
	        article.setName(properties.getProperty("UPDATE_A_NAME"));
	        article.setPriceGross(Float.parseFloat(properties.getProperty("UPDATE_A_PRICEGROSS")));
	        article.setPriceSupplier(Float.parseFloat(properties.getProperty("UPDATE_A_PRICESUPPLIER")));
	        article.setPriceVat(Float.parseFloat(properties.getProperty("UPDATE_A_PRICEVAT")));
	        article.setSizeH(Float.parseFloat(properties.getProperty("UPDATE_A_SIZEH")));
	        article.setSizeL(Float.parseFloat(properties.getProperty("UPDATE_A_SIZEL")));
	        article.setSizeW(Float.parseFloat(properties.getProperty("UPDATE_A_SIZEW")));
	        article.setStock(Long.parseLong(properties.getProperty("UPDATE_A_STOCK")));
	        
	        article = articleRepo.performEdit(article);
	        
	        //Die Artikelversion wird kontrolliert
	        articleRepo.createArticleHistory(article);
	        
	        //Der geänderter Artikel wird geprüft
	        article = articleRepo.findOne(ARTICLE_ID);
	        
	        assertEquals(article.getColor(), properties.getProperty("UPDATE_A_COLOR"));
	        assertEquals(article.getDescription(), properties.getProperty("UPDATE_A_DESCRIPTION"));
	        assertEquals(article.getEan(), Long.parseLong(properties.getProperty("UPDATE_A_EAN")));
	        assertEquals(article.getName(), properties.getProperty("UPDATE_A_NAME"));
	        assertEquals(article.getPriceGross(), Float.parseFloat(properties.getProperty("UPDATE_A_PRICEGROSS")));
	        assertEquals(article.getPriceSupplier(), Float.parseFloat(properties.getProperty("UPDATE_A_PRICESUPPLIER")));
	        assertEquals(article.getPriceVat(), Float.parseFloat(properties.getProperty("UPDATE_A_PRICEVAT")));
	        assertEquals(article.getSizeH(), Float.parseFloat(properties.getProperty("UPDATE_A_SIZEH")));
	        assertEquals(article.getSizeL(), Float.parseFloat(properties.getProperty("UPDATE_A_SIZEL")));
	        assertEquals(article.getSizeW(), Float.parseFloat(properties.getProperty("UPDATE_A_SIZEW")));
	        assertEquals(article.getStock(), Long.parseLong(properties.getProperty("UPDATE_A_STOCK")));
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
	        Article article = articleRepo.findOne(ARTICLE_ID);
	    	
	        articleHistoryRepository.deleteAllVersionsOfArticle(article.getIdArticle());
	        
	        articleRepo.delete(article);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!articleRepo.exists(ARTICLE_ID));
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
	        ArticleCategory articleCategory = articleCategoryRepo.findOne(ARTICLE_CATEGORY_ID);
	    	
	        articleCategoryRepo.delete(articleCategory);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!articleCategoryRepo.exists(ARTICLE_CATEGORY_ID));
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
        articleCategory.setName(properties.getProperty("INSERT_AC_NAME_F"));
        articleCategory.setDescription(properties.getProperty("INSERT_AC_DESCRIPTION_F"));
        
        articleCategory = articleCategoryRepo.performNew(articleCategory);

        //Die erstellt Artikelkategorie wird geprüft
        ARTICLE_CATEGORY_ID = articleCategory.getIdArticleCategory();
        
        
        articleCategory = articleCategoryRepo.findOne(ARTICLE_CATEGORY_ID);
        
        assertEquals(articleCategory.getName(), properties.getProperty("INSERT_AC_NAME"));
        assertEquals(articleCategory.getDescription(), properties.getProperty("INSERT_AC_DESCRIPTION"));
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
	        ArticleCategory articleCategory = articleCategoryRepo.findOne(ARTICLE_CATEGORY_ID);
	    	
	        Article article = new Article();
	    
	        //Die Felder werden gefüllt
	        article.setArticleCategory(articleCategory);
	        article.setColor(properties.getProperty("INSERT_A_COLOR_F"));
	        article.setDescription(properties.getProperty("INSERT_A_DESCRIPTION_F"));
	        article.setEan(Long.parseLong(properties.getProperty("INSERT_A_EAN_F")));
	        article.setName(properties.getProperty("INSERT_A_NAME_F"));
	        article.setPriceGross(Float.parseFloat(properties.getProperty("INSERT_A_PRICEGROSS_F")));
	        article.setPriceVat(Float.parseFloat(properties.getProperty("INSERT_A_PRICEVAT_F")));
	        article.setSizeH(Float.parseFloat(properties.getProperty("INSERT_A_SIZEH_F")));
	        article.setSizeL(Float.parseFloat(properties.getProperty("INSERT_A_SIZEL_F")));
	        article.setSizeW(Float.parseFloat(properties.getProperty("INSERT_A_SIZEW_F")));
	        article.setStock(Integer.parseInt(properties.getProperty("INSERT_A_STOCK_F")));
	        
	        article = articleRepo.performNew(article);
	        
	        //Der erstellter Artikel wird geprüft
	        ARTICLE_ID = article.getIdArticle();
	        
	        article = articleRepo.findOne(ARTICLE_ID);
	        
	        assertEquals(article.getColor(), properties.getProperty("INSERT_A_COLOR"));
	        assertEquals(article.getDescription(), properties.getProperty("INSERT_A_DESCRIPTION"));
	        assertEquals(article.getEan(), Long.parseLong(properties.getProperty("INSERT_A_EAN")));
	        assertEquals(article.getName(), properties.getProperty("INSERT_A_NAME"));
	        assertEquals(article.getPriceGross(), Float.parseFloat(properties.getProperty("INSERT_A_PRICEGROSS")));
	        assertEquals(article.getPriceVat(), Float.parseFloat(properties.getProperty("INSERT_A_PRICEVAT")));
	        assertEquals(article.getSizeH(), Float.parseFloat(properties.getProperty("INSERT_A_SIZEH")));
	        assertEquals(article.getSizeL(), Float.parseFloat(properties.getProperty("INSERT_A_SIZEL")));
	        assertEquals(article.getSizeW(), Float.parseFloat(properties.getProperty("INSERT_A_SIZEW")));
	        assertEquals(article.getStock(), Integer.parseInt(properties.getProperty("INSERT_A_STOCK")));
	        assertEquals(article.getArticleCategory(), articleCategory);
    	} catch (Exception ex) {
    		throw ex;
    	}
    }
}
