package org.haegerp.entity;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
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
 * Dieses Test Suite wird Mitarbeiter, Gehaltkategorie, Division und Benutzergruppe testen
 * 
 * @author Wolf
 *
 */
public class Employee_SalaryCategory_Division_UserGroupTest extends TestCase {
	/**
     * Ein Testfall wird erstellt
     *
     * @param testName name vom Testfall
     */
    public Employee_SalaryCategory_Division_UserGroupTest( String testName )
    {
        super( testName );
    }

    /**
     * @return Die Testfälle für testen
     */
    public static Test suite()
    {	
        return new TestSuite( Employee_SalaryCategory_Division_UserGroupTest.class );
    }
    
    Properties properties = new Properties();
    
    //Abfragen
    //Mitarbeiter
    private static String QUERY_E_BY_ID = "from Employee where idEmployee = ";
    private static String QUERY_EU_BY_ID = "from EmployeeUser where idEmployee = ";
    
    //Gehaltkategorie
    private static String QUERY_SC_BY_ID = "from SalaryCategory where idSalaryCategory = ";
    
    //Division
    private static String QUERY_D_BY_ID = "from Division where idDivision = ";
    
    //User-Group
    private static String QUERY_UG_BY_ID = "from UserGroup where idUserGroup = ";
    
    //Permission
    private static String QUERY_UG_PI_BY_ID = "from Permission where ";
    private static String QUERY_UG_PU_BY_ID = "from Permission where ";
    
    /**
     * Eine Gehaltkategorie wird in die Datenbank erstellt
     */
    public void test01InsertSalaryCategory()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        //Die Felder werden gefüllt
	        SalaryCategory salaryCategory = new SalaryCategory();
	        salaryCategory.setDescription(properties.getProperty("INSERT_SC_DESCRIPTION"));
	        salaryCategory.setSalaryFrom(Float.parseFloat(properties.getProperty("INSERT_SC_SALARYFROM")));
	        salaryCategory.setSalaryTo(Float.parseFloat(properties.getProperty("INSERT_SC_SALARYTO")));
	        
	        HibernateUtil.insert(salaryCategory, session);
	        
	        //Die erstellt Gehaltkategorie wird geprüft
	        QUERY_SC_BY_ID = QUERY_SC_BY_ID + salaryCategory.getIdSalaryCategory();
	        salaryCategory = (SalaryCategory) HibernateUtil.selectObject(QUERY_SC_BY_ID, session);
	        
	        assertEquals(salaryCategory.getSalaryFrom(), Float.parseFloat(properties.getProperty("INSERT_SC_SALARYFROM")));
	        assertEquals(salaryCategory.getSalaryTo(), Float.parseFloat(properties.getProperty("INSERT_SC_SALARYTO")));
	        assertEquals(salaryCategory.getDescription(), properties.getProperty("INSERT_SC_DESCRIPTION"));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Die letzte Gehaltkategorie wird geändert
     */
    public void test02UpdateSalaryCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        SalaryCategory salaryCategory = (SalaryCategory) HibernateUtil.selectObject(QUERY_SC_BY_ID, session);
	
	        //Die Felder werden gefüllt
	        salaryCategory.setDescription(properties.getProperty("UPDATE_SC_DESCRIPTION"));
	        salaryCategory.setSalaryFrom(Float.parseFloat(properties.getProperty("UPDATE_SC_SALARYFROM")));
	        salaryCategory.setSalaryTo(Float.parseFloat(properties.getProperty("UPDATE_SC_SALARYTO")));
	        
	        HibernateUtil.update(salaryCategory, session);
	
	        //Die geändert Gehaltkategorie wird geprüft
	        salaryCategory = (SalaryCategory) HibernateUtil.selectObject(QUERY_SC_BY_ID, session);
	        
	        assertEquals(salaryCategory.getSalaryFrom(), Float.parseFloat(properties.getProperty("UPDATE_SC_SALARYFROM")));
	        assertEquals(salaryCategory.getSalaryTo(), Float.parseFloat(properties.getProperty("UPDATE_SC_SALARYTO")));
	        assertEquals(salaryCategory.getDescription(), properties.getProperty("UPDATE_SC_DESCRIPTION"));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Eine Division wird in die Datenbank erstellt
     */
    public void test03InsertDivision()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        //Die Felder werden gefüllt
	        Division division = new Division();
	        division.setDescription(properties.getProperty("INSERT_D_DESCRIPTION"));
	        division.setName(properties.getProperty("INSERT_D_NAME"));
	        
	        HibernateUtil.insert(division, session);
	        
	        //Die erstellt Division wird geprüft
	        QUERY_D_BY_ID = QUERY_D_BY_ID + division.getIdDivision();
	        
	        division = (Division) HibernateUtil.selectObject(QUERY_D_BY_ID, session);
	        
	        assertEquals(division.getDescription(), properties.getProperty("INSERT_D_DESCRIPTION"));
	        assertEquals(division.getName(), properties.getProperty("INSERT_D_NAME"));
        } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Die letzte Gehaltkategorie wird geändert
     */
    public void test04UpdateSalaryCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        Division division = (Division) HibernateUtil.selectObject(QUERY_D_BY_ID, session);
	
	        //Die Felder werden gefüllt
	        division.setDescription(properties.getProperty("UPDATE_D_DESCRIPTION"));
	        division.setName(properties.getProperty("UPDATE_D_NAME"));
	        
	        HibernateUtil.update(division, session);
	        
	        //Die geändert Division wird geprüft
	        division = (Division) HibernateUtil.selectObject(QUERY_D_BY_ID, session);
	        
	        assertEquals(division.getDescription(), properties.getProperty("UPDATE_D_DESCRIPTION"));
	        assertEquals(division.getName(), properties.getProperty("UPDATE_D_NAME"));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Eine Benutzergruppe wird in die Datenbank erstellt
     */
    public void test05InsertUserGroup()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        //Die Felder werden gefüllt
	        UserGroup userGroup = new UserGroup();
	        userGroup.setDescription(properties.getProperty("INSERT_UG_DESCRIPTION"));
	        userGroup.setName(properties.getProperty("INSERT_UG_NAME"));
	        
	        QUERY_UG_PI_BY_ID = QUERY_UG_PI_BY_ID +
	        		"idPermission = " + properties.getProperty("INSERT_UG_PERMISSION1") +
	        		" OR idPermission = " + properties.getProperty("INSERT_UG_PERMISSION2") +
	        		" OR idPermission = " + properties.getProperty("INSERT_UG_PERMISSION3");
	        
	        List<?> list = HibernateUtil.selectList(QUERY_UG_PI_BY_ID, session);
	        for (int i = 0; i < list.size(); i++) {
				Permission permission = (Permission) list.get(i);
				userGroup.getPermissions().add(permission);
			}
	        
	        HibernateUtil.insert(userGroup, session);
	        
	        //Die erstellt Benutzergruppe wird geprüft
	        QUERY_UG_BY_ID = QUERY_UG_BY_ID + userGroup.getIdUserGroup();
	        
	        userGroup = (UserGroup) HibernateUtil.selectObject(QUERY_UG_BY_ID, session);
	        
	        assertTrue(userGroup.getPermissions().containsAll(HibernateUtil.selectList(QUERY_UG_PI_BY_ID, session)));
	        
	        assertEquals(userGroup.getDescription(), properties.getProperty("INSERT_UG_DESCRIPTION"));
	        assertEquals(userGroup.getName(), properties.getProperty("INSERT_UG_NAME"));
        } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Die letzte Benutzergruppe wird geändert
     */
    public void test06UpdateUserGroup()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        UserGroup userGroup = (UserGroup) HibernateUtil.selectObject(QUERY_UG_BY_ID, session);
	    	
	        //Die Felder werden gefüllt
	        userGroup.setDescription(properties.getProperty("UPDATE_UG_DESCRIPTION"));
	        userGroup.setName(properties.getProperty("UPDATE_UG_NAME"));
	        userGroup.setPermissions(new HashSet<Permission>(0));
	        
	        QUERY_UG_PU_BY_ID = QUERY_UG_PU_BY_ID +
	        		"idPermission = " + properties.getProperty("UPDATE_UG_PERMISSION1") +
	        		" OR idPermission = " + properties.getProperty("UPDATE_UG_PERMISSION2") +
	        		" OR idPermission = " + properties.getProperty("UPDATE_UG_PERMISSION3");

	        List<?> list = HibernateUtil.selectList(QUERY_UG_PU_BY_ID, session);
	        
	        for (int i = 0; i < list.size(); i++) {
				Permission permission = (Permission) list.get(i);
				userGroup.getPermissions().add(permission);
			}
	        
	        HibernateUtil.update(userGroup, session);
	        
	        //Die geändert Benutzergruppe wird geprüft
	        userGroup = (UserGroup) HibernateUtil.selectObject(QUERY_UG_BY_ID, session);
	        
	        assertTrue(userGroup.getPermissions().containsAll(HibernateUtil.selectList(QUERY_UG_PU_BY_ID, session)));
	        
	        assertEquals(userGroup.getDescription(), properties.getProperty("UPDATE_UG_DESCRIPTION"));
	        assertEquals(userGroup.getName(), properties.getProperty("UPDATE_UG_NAME"));
        } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Ein Mitarbeiter wird erstellt
     */
    public void test07InsertEmployee(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	    	//Die Gehaltkategorie wird geholt
	        SalaryCategory salaryCategory = (SalaryCategory) HibernateUtil.selectObject(QUERY_SC_BY_ID, session);
	    	
	        //Die Division wird geholt
	        Division division = (Division) HibernateUtil.selectObject(QUERY_D_BY_ID, session);
	        
	        //Die Benutzergruppe wird geholt
	        UserGroup userGroup = (UserGroup) HibernateUtil.selectObject(QUERY_UG_BY_ID, session);
	        
	        Employee employee = new Employee();
	        
	        //Die Felder werden gefüllt
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
	        
	        HibernateUtil.insert(employee, session);
	        
	        //Benutzername und Kenntwort werden gefüllt
	        EmployeeUser employeeUser = new EmployeeUser();
	        employeeUser.setEmployee(employee);
	        employeeUser.setPassword(properties.getProperty("INSERT_E_PASSWORD"));
	        employeeUser.setUsername(properties.getProperty("INSERT_E_USERNAME"));
	        
	        HibernateUtil.insert(employeeUser, session);
	        
	        //Der erstellter Mitarbeiter wird geprüft
	        QUERY_E_BY_ID = QUERY_E_BY_ID + employee.getIdEmployee();
	        
	        employee = (Employee) HibernateUtil.selectObject(QUERY_E_BY_ID, session);
	        
	        QUERY_EU_BY_ID = QUERY_EU_BY_ID + employeeUser.getIdEmployee();
	        
	        employeeUser = (EmployeeUser) HibernateUtil.selectObject(QUERY_EU_BY_ID, session);
	        
	        assertEquals(employee.getAddress(), properties.getProperty("INSERT_E_ADDRESS"));
	        assertEquals(employee.getCity(), properties.getProperty("INSERT_E_CITY"));
	        assertEquals(employee.getCountry(), properties.getProperty("INSERT_E_COUNTRY"));
	        assertEquals(employee.getDivision(), division);
	        assertEquals(employee.getEmail(), properties.getProperty("INSERT_E_EMAIL"));
	        assertEquals(employee.getIdCard(), Long.parseLong(properties.getProperty("INSERT_E_IDCARD")));
	        assertEquals(employee.getMobileNumber(), properties.getProperty("INSERT_E_MOBILENUMBER"));
	        assertEquals(employee.getName(), properties.getProperty("INSERT_E_NAME"));
	        assertEquals(employee.getPhoneNumber(), properties.getProperty("INSERT_E_PHONENUMBER"));
	        assertEquals(employee.getRegion(), properties.getProperty("INSERT_E_REGION"));
	        assertEquals(employee.getSalaryCategory(), salaryCategory);
	        assertEquals(employee.getUserGroup(), userGroup);
	        assertEquals(employee.getZipCode(), properties.getProperty("INSERT_E_ZIPCODE"));
	        assertEquals(employeeUser.getPassword(), properties.getProperty("INSERT_E_PASSWORD"));
	        assertEquals(employeeUser.getUsername(), properties.getProperty("INSERT_E_USERNAME"));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Der letzter Mitarbeiter wird geändert
     */
    public void test08UpdateEmployee(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        Employee employee = (Employee) HibernateUtil.selectObject(QUERY_E_BY_ID, session);
	        
	        //Die Felder werden gefüllt
	        employee.setAddress(properties.getProperty("UPDATE_E_ADDRESS"));
	        employee.setCity(properties.getProperty("UPDATE_E_CITY"));
	        employee.setCountry(properties.getProperty("UPDATE_E_COUNTRY"));
	        employee.setEmail(properties.getProperty("UPDATE_E_EMAIL"));
	        employee.setIdCard(Long.parseLong(properties.getProperty("UPDATE_E_IDCARD")));
	        employee.setMobileNumber(properties.getProperty("UPDATE_E_MOBILENUMBER"));
	        employee.setName(properties.getProperty("UPDATE_E_NAME"));
	        employee.setPhoneNumber(properties.getProperty("UPDATE_E_PHONENUMBER"));
	        employee.setRegion(properties.getProperty("UPDATE_E_REGION"));
	        employee.setZipCode(properties.getProperty("UPDATE_E_ZIPCODE"));
	        
	        HibernateUtil.update(employee, session);
	        
	        //Benutzername und Kenntwort werden geändert
	        EmployeeUser employeeUser = employee.getEmployeeUser();
	        employeeUser.setPassword(properties.getProperty("UPDATE_E_PASSWORD"));
	        employeeUser.setUsername(properties.getProperty("UPDATE_E_USERNAME"));
	        
	        HibernateUtil.update(employeeUser, session);
	        
	        //Der erstellter Mitarbeiter wird geprüft
	        employee = (Employee) HibernateUtil.selectObject(QUERY_E_BY_ID, session);
	        
	        employeeUser = (EmployeeUser) HibernateUtil.selectObject(QUERY_EU_BY_ID, session);
	        
	        assertEquals(employee.getAddress(), properties.getProperty("UPDATE_E_ADDRESS"));
	        assertEquals(employee.getCity(), properties.getProperty("UPDATE_E_CITY"));
	        assertEquals(employee.getCountry(), properties.getProperty("UPDATE_E_COUNTRY"));
	        assertEquals(employee.getEmail(), properties.getProperty("UPDATE_E_EMAIL"));
	        assertEquals(employee.getIdCard(), Long.parseLong(properties.getProperty("UPDATE_E_IDCARD")));
	        assertEquals(employee.getMobileNumber(), properties.getProperty("UPDATE_E_MOBILENUMBER"));
	        assertEquals(employee.getName(), properties.getProperty("UPDATE_E_NAME"));
	        assertEquals(employee.getPhoneNumber(), properties.getProperty("UPDATE_E_PHONENUMBER"));
	        assertEquals(employee.getRegion(), properties.getProperty("UPDATE_E_REGION"));
	        assertEquals(employee.getZipCode(), properties.getProperty("UPDATE_E_ZIPCODE"));
	        assertEquals(employeeUser.getPassword(), properties.getProperty("UPDATE_E_PASSWORD"));
	        assertEquals(employeeUser.getUsername(), properties.getProperty("UPDATE_E_USERNAME"));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Der letzter Mitarbeiter wird gelöscht
     */
    public void test09DeleteEmployee(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	    	
	        Employee employee = (Employee) HibernateUtil.selectObject(QUERY_E_BY_ID, session);
	    	
	        EmployeeUser employeeUser = (EmployeeUser) HibernateUtil.selectObject(QUERY_EU_BY_ID, session);
	        
	        HibernateUtil.delete(employeeUser, session);
	        HibernateUtil.delete(employee, session);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(HibernateUtil.selectList(QUERY_E_BY_ID, session).isEmpty());
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Die Gehaltkategorie wird gelöscht
     */
    public void test10DeleteSalaryCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	        
	        SalaryCategory salaryCategory = (SalaryCategory) HibernateUtil.selectObject(QUERY_SC_BY_ID, session);
	    	
	        HibernateUtil.delete(salaryCategory, session);

	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(HibernateUtil.selectList(QUERY_SC_BY_ID, session).isEmpty());
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Die Division wird gelöscht
     */
    public void test11DeleteDivision()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	        Division division = (Division) HibernateUtil.selectObject(QUERY_D_BY_ID, session);
	    	
	        HibernateUtil.delete(division, session);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(HibernateUtil.selectList(QUERY_D_BY_ID, session).isEmpty());
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
    
    /**
     * Die Benutzergruppe wird gelöscht
     */
    public void test12DeleteUserGroup()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try {
	        UserGroup userGroup = (UserGroup) HibernateUtil.selectObject(QUERY_UG_BY_ID, session);
	    	
	        HibernateUtil.delete(userGroup, session);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(HibernateUtil.selectList(QUERY_UG_BY_ID, session).isEmpty());
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
}
