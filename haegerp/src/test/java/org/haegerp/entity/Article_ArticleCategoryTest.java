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
 * Dieses Test Suite wird Artikel und Artikelkategorie testen
 * 
 * @author Wolf
 *
 */
public class Article_ArticleCategoryTest extends TestCase {
	/**
     * Ein Testfall wird erstellt
     *
     * @param testName name vom Testfall
     */
    public Article_ArticleCategoryTest( String testName )
    {
        super( testName );
    }

    /**
     * @return Die Testfälle für testen
     */
    public static Test suite()
    {	
        return new TestSuite( Article_ArticleCategoryTest.class );
    }
    
    Properties properties = new Properties();
    
    //Abfragen
    //Artikelkategorie
    private static String QUERY_AC_BY_ID = "from ArticleCategory where idArticleCategory = ";
    
    //Artikel
    private static String QUERY_A_BY_ID = "from Article where idArticle = ";
    
    /**
     * Eine Artikelkategorie wird in die Datenbank erstellt
     */
    public void test1InsertArticleCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        ArticleCategory articleCategory = new ArticleCategory();
	        articleCategory.setName(properties.getProperty("INSERT_AC_NAME"));
	        articleCategory.setDescription(properties.getProperty("INSERT_AC_DESCRIPTION"));
	        
	        HibernateUtil.insert(articleCategory, session);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        QUERY_AC_BY_ID = QUERY_AC_BY_ID + articleCategory.getIdArticleCategory();
	        
	        
	        articleCategory = (ArticleCategory) HibernateUtil.selectObject(QUERY_AC_BY_ID, session);
	        
	        assertEquals(articleCategory.getName(), properties.getProperty("INSERT_AC_NAME"));
	        assertEquals(articleCategory.getDescription(), properties.getProperty("INSERT_AC_DESCRIPTION"));
        
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
    }
    
    /**
     * Die letzte Artikelkategorie wird geändert
     */
    public void test2UpdateArticleCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
    		ArticleCategory articleCategory = (ArticleCategory) HibernateUtil.selectObject(QUERY_AC_BY_ID, session);
    	
	        articleCategory.setName(properties.getProperty("UPDATE_AC_NAME"));
	        articleCategory.setDescription(properties.getProperty("UPDATE_AC_DESCRIPTION"));
	        
	        HibernateUtil.update(articleCategory, session);
        
	        articleCategory = (ArticleCategory) HibernateUtil.selectObject(QUERY_AC_BY_ID, session);
	        
	        assertEquals(articleCategory.getName(), properties.getProperty("UPDATE_AC_NAME"));
	        assertEquals(articleCategory.getDescription(), properties.getProperty("UPDATE_AC_DESCRIPTION"));
        
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
    }
    
    /**
     * Ein Artikel wird erstellt
     */
    public void test3InsertArticle(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	    	//Die Artikelkategorie wird geholt
	        ArticleCategory articleCategory = (ArticleCategory)HibernateUtil.selectObject(QUERY_AC_BY_ID, session);
	    	
	        Article article = new Article();
        
	        //Die Felder werden gefüllt
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
	        
	        HibernateUtil.insert(article, session);
	        
	        //Der erstellter Artikel wird geprüft
	        QUERY_A_BY_ID = QUERY_A_BY_ID + article.getIdArticle();
	        
	        article = (Article)HibernateUtil.selectObject(QUERY_A_BY_ID, session);
	        
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
        
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
    }

    /**
     * Der letzter Artikel wird geändert
     */
    public void test4UpdateArticle(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	    	//Die Artikelkategorie wird geholt
	        Article article = (Article) HibernateUtil.selectObject(QUERY_A_BY_ID, session);
	    	
	        //Die Felder werden geändert
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
	        
	        HibernateUtil.insert(article, session);
	        
	        //Der geänderter Artikel wird geprüft
	        article = (Article) HibernateUtil.selectObject(QUERY_A_BY_ID, session);
	        
	        assertEquals(article.getColor(), properties.getProperty("UPDATE_A_COLOR"));
	        assertEquals(article.getDescription(), properties.getProperty("UPDATE_A_DESCRIPTION"));
	        assertEquals(article.getEan(), Long.parseLong(properties.getProperty("UPDATE_A_EAN")));
	        assertEquals(article.getName(), properties.getProperty("UPDATE_A_NAME"));
	        assertEquals(article.getPriceGross(), Float.parseFloat(properties.getProperty("UPDATE_A_PRICEGROSS")));
	        assertEquals(article.getPriceVat(), Float.parseFloat(properties.getProperty("UPDATE_A_PRICEVAT")));
	        assertEquals(article.getSizeH(), Float.parseFloat(properties.getProperty("UPDATE_A_SIZEH")));
	        assertEquals(article.getSizeL(), Float.parseFloat(properties.getProperty("UPDATE_A_SIZEL")));
	        assertEquals(article.getSizeW(), Float.parseFloat(properties.getProperty("UPDATE_A_SIZEW")));
	        assertEquals(article.getStock(), Integer.parseInt(properties.getProperty("UPDATE_A_STOCK")));
    	} catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
    }
    
    /**
     * Der letzter Artikel wird gelöscht
     */
    public void test5DeleteArticle(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	        Article article = (Article) HibernateUtil.selectObject(QUERY_A_BY_ID, session);
	    	
	        HibernateUtil.delete(article, session);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(HibernateUtil.selectList(QUERY_A_BY_ID, session).isEmpty());
    	} catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
    }
    
    /**
     * Die Artikelkategorie wird gelöscht
     */
    public void test6DeleteArticleCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	        ArticleCategory articleCategory = (ArticleCategory) HibernateUtil.selectObject(QUERY_AC_BY_ID, session);
	    	
	        HibernateUtil.delete(articleCategory, session);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(HibernateUtil.selectList(QUERY_AC_BY_ID, session).isEmpty());
	    } catch (Exception e) {
			e.printStackTrace();
	    	fail(e.getMessage());
		} finally {
			if (session.isOpen())
				session.close();
		}
    }
}
