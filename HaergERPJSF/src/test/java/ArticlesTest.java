
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.Selenium;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Fabio Codinha
 */
public class ArticlesTest extends SeleneseTestBase {

    protected Selenium browser;
    public static final String DEFAULT_TIMEOUT = "20000";
    public static final String BROWSER_FIREFOX = "*firefox C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
    public static final String BROWSER_CHROME = "*chrome";
    public static final String BROWSER_IE = "*iexplore";

    public ArticlesTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        browser = new DefaultSelenium("localhost", 4444, BROWSER_IE, "http://localhost:8080/HaegERPJSF/");
        browser.setSpeed("1000");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        browser.stop();
    }

    @Test(priority = 1, enabled = true)
    public void testNewEditCategory() {
        
        browser.start();
        
        browser.open("/HaegERPJSF/");
        browser.type("panel:txtUsername", "admin");
        browser.type("panel:txtPassword", "admin");
        browser.click("panel:btnLogin");
        browser.waitForPageToLoad(DEFAULT_TIMEOUT);
        browser.click("//li[2]/ul/li[2]/a/span");
        browser.waitForPageToLoad(DEFAULT_TIMEOUT);
        browser.click("//td[2]/button");
        browser.waitForPageToLoad(DEFAULT_TIMEOUT);
        browser.type("//td[2]/input", "Esse");
        browser.type("//textarea", "Nur lecker Essen!");
        browser.click("//button");
        browser.click("//button");
        browser.type("//textarea", "Nur leckeres Essen!");
        browser.click("//button");
        browser.click("//td[3]/button");
        browser.waitForPageToLoad(DEFAULT_TIMEOUT);
        browser.type("//td[2]/input", "Ess");
        browser.click("//button");
    }
}