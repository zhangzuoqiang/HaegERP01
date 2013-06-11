package org.haegerp.entity;

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
    
    //Artikel Felder
    //Erstellung
    private static long INSERT_A_EAN = Long.parseLong("1234567890123");
    private static String INSERT_A_NAME = "Kartoffel";
    private static float INSERT_A_PRICEVAT = Float.parseFloat("19.5");
    private static float INSERT_A_PRICEGROSS = Float.parseFloat("1500.00");
    private static int INSERT_A_STOCK = 500;
    private static String INSERT_A_COLOR = "Grün";
    private static float INSERT_A_SIZEH = Float.parseFloat("2.1");
    private static float INSERT_A_SIZEL = Float.parseFloat("1.6");
    private static float INSERT_A_SIZEW = Float.parseFloat("1.9");
    private static String INSERT_A_DESCRIPTION = "Frisch";
    
    //Änderung
    private static long UPDATE_A_EAN = Long.parseLong("3210987654321");
    private static String UPDATE_A_NAME = "Kartoffel";
    private static float UPDATE_A_PRICEVAT = Float.parseFloat("11.5");
    private static float UPDATE_A_PRICEGROSS = Float.parseFloat("1200.00");
    private static int UPDATE_A_STOCK = 100;
    private static String UPDATE_A_COLOR = "Schwarzgrün";
    private static float UPDATE_A_SIZEH = Float.parseFloat("20.1");
    private static float UPDATE_A_SIZEL = Float.parseFloat("15.6");
    private static float UPDATE_A_SIZEW = Float.parseFloat("14.9");
    private static String UPDATE_A_DESCRIPTION = "Nicht so frisch";
    
    //Artikelkategorie Felder
    //Erstellung
    private static String INSERT_AC_NAME = "Essen";
    private static String INSERT_AC_DESCRIPTION = "Alle sind gut";
    
    //Änderung
    private static String UPDATE_AC_NAME = "Getränke";
    private static String UPDATE_AC_DESCRIPTION = "Alle sind nicht so gut";
    
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
	        ArticleCategory articleCategory = new ArticleCategory();
	        articleCategory.setName(INSERT_AC_NAME);
	        articleCategory.setDescription(INSERT_AC_DESCRIPTION);
	        
	        HibernateUtil.insert(articleCategory, session);
	        
	        //Die erstellt Artikelkategorie wird geprüft
	        QUERY_AC_BY_ID = QUERY_AC_BY_ID + articleCategory.getIdArticleCategory();
	        
	        
	        articleCategory = (ArticleCategory) HibernateUtil.selectObject(QUERY_AC_BY_ID, session);
	        
	        assertEquals(articleCategory.getName(), INSERT_AC_NAME);
	        assertEquals(articleCategory.getDescription(), INSERT_AC_DESCRIPTION);
        
	    } catch (Exception e) {
			e.printStackTrace();
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
    		ArticleCategory articleCategory = (ArticleCategory) HibernateUtil.selectObject(QUERY_AC_BY_ID, session);
    	
	        articleCategory.setName(UPDATE_AC_NAME);
	        articleCategory.setDescription(UPDATE_AC_DESCRIPTION);
	        
	        HibernateUtil.update(articleCategory, session);
        
	        articleCategory = (ArticleCategory) HibernateUtil.selectObject(QUERY_AC_BY_ID, session);
	        
	        assertEquals(articleCategory.getName(), UPDATE_AC_NAME);
	        assertEquals(articleCategory.getDescription(), UPDATE_AC_DESCRIPTION);
        
	    } catch (Exception e) {
			e.printStackTrace();
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
	    	//Die Artikelkategorie wird geholt
	        ArticleCategory articleCategory = (ArticleCategory)HibernateUtil.selectObject(QUERY_AC_BY_ID, session);
	    	
	        Article article = new Article();
        
	        //Die Felder werden gefüllt
	        article.setArticleCategory(articleCategory);
	        article.setColor(INSERT_A_COLOR);
	        article.setDescription(INSERT_A_DESCRIPTION);
	        article.setEan(INSERT_A_EAN);
	        article.setName(INSERT_A_NAME);
	        article.setPriceGross(INSERT_A_PRICEGROSS);
	        article.setPriceVat(INSERT_A_PRICEVAT);
	        article.setSizeH(INSERT_A_SIZEH);
	        article.setSizeL(INSERT_A_SIZEL);
	        article.setSizeW(INSERT_A_SIZEW);
	        article.setStock(INSERT_A_STOCK);
	        
	        HibernateUtil.insert(article, session);
	        
	        //Der erstellter Artikel wird geprüft
	        QUERY_A_BY_ID = QUERY_A_BY_ID + article.getIdArticle();
	        
	        article = (Article)HibernateUtil.selectObject(QUERY_A_BY_ID, session);
	        
	        assertEquals(article.getColor(), INSERT_A_COLOR);
	        assertEquals(article.getDescription(), INSERT_A_DESCRIPTION);
	        assertEquals(article.getEan(), INSERT_A_EAN);
	        assertEquals(article.getName(), INSERT_A_NAME);
	        assertEquals(article.getPriceGross(), INSERT_A_PRICEGROSS);
	        assertEquals(article.getPriceVat(), INSERT_A_PRICEVAT);
	        assertEquals(article.getSizeH(), INSERT_A_SIZEH);
	        assertEquals(article.getSizeL(), INSERT_A_SIZEL);
	        assertEquals(article.getSizeW(), INSERT_A_SIZEW);
	        assertEquals(article.getArticleCategory(), articleCategory);
        
	    } catch (Exception e) {
			e.printStackTrace();
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
	    	//Die Artikelkategorie wird geholt
	        Article article = (Article) HibernateUtil.selectObject(QUERY_A_BY_ID, session);
	    	
	        //Die Felder werden geändert
	        article.setColor(UPDATE_A_COLOR);
	        article.setDescription(UPDATE_A_DESCRIPTION);
	        article.setEan(UPDATE_A_EAN);
	        article.setName(UPDATE_A_NAME);
	        article.setPriceGross(UPDATE_A_PRICEGROSS);
	        article.setPriceVat(UPDATE_A_PRICEVAT);
	        article.setSizeH(UPDATE_A_SIZEH);
	        article.setSizeL(UPDATE_A_SIZEL);
	        article.setSizeW(UPDATE_A_SIZEW);
	        article.setStock(UPDATE_A_STOCK);
	        
	        HibernateUtil.insert(article, session);
	        
	        //Der geänderter Artikel wird geprüft
	        article = (Article) HibernateUtil.selectObject(QUERY_A_BY_ID, session);
	        
	        assertEquals(article.getColor(), UPDATE_A_COLOR);
	        assertEquals(article.getDescription(), UPDATE_A_DESCRIPTION);
	        assertEquals(article.getEan(), UPDATE_A_EAN);
	        assertEquals(article.getName(), UPDATE_A_NAME);
	        assertEquals(article.getPriceGross(), UPDATE_A_PRICEGROSS);
	        assertEquals(article.getPriceVat(), UPDATE_A_PRICEVAT);
	        assertEquals(article.getSizeH(), UPDATE_A_SIZEH);
	        assertEquals(article.getSizeL(), UPDATE_A_SIZEL);
	        assertEquals(article.getSizeW(), UPDATE_A_SIZEW);
    	} catch (Exception e) {
			e.printStackTrace();
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
	        session.update(article);
	        assertTrue(HibernateUtil.selectList(QUERY_A_BY_ID, session).isEmpty());
	        System.out.println(((Article)HibernateUtil.selectList(QUERY_A_BY_ID, session).get(0)).getDescription());
    	} catch (Exception e) {
			e.printStackTrace();
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
		} finally {
			if (session.isOpen())
				session.close();
		}
    }
}
