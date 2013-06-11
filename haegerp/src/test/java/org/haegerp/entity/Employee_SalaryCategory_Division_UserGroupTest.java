package org.haegerp.entity;

import java.util.HashSet;
import java.util.List;

import org.haegerp.util.HibernateUtil;
import org.hibernate.Query;
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
    
    //Mitarbeiter Felder
    //Erstellung
    private static long INSERT_E_IDCARD = Long.parseLong("1234567890123");
    private static String INSERT_E_NAME = "Fabio C";
    private static String INSERT_E_ADDRESS = "Konigswinter Str. 321";
    private static String INSERT_E_ZIPCODE = "53333";
    private static String INSERT_E_CITY = "Berlin";
    private static String INSERT_E_REGION = "Berlin";
    private static String INSERT_E_COUNTRY = "France";
    private static String INSERT_E_EMAIL = "fab.c@haeger-consulting.de";
    private static String INSERT_E_PHONENUMBER = "+49228123456";
    private static String INSERT_E_MOBILENUMBER = "+491761234567";
    
    private static String INSERT_E_USERNAME = "cfab";
    private static String INSERT_E_PASSWORD = "21232f297a57a5a743894a0e4a801fc3"; //admin
    
    //Änderung
    private static long UPDATE_E_IDCARD = Long.parseLong("1214161810121");
    private static String UPDATE_E_NAME = "Fabio Codinha";
    private static String UPDATE_E_ADDRESS = "Siegburger Str. 123";
    private static String UPDATE_E_ZIPCODE = "52222";
    private static String UPDATE_E_CITY = "Bonn";
    private static String UPDATE_E_REGION = "Nordrhein-Westfalen";
    private static String UPDATE_E_COUNTRY = "Germany";
    private static String UPDATE_E_EMAIL = "fabio.codinha@haeger-consulting.de";
    private static String UPDATE_E_PHONENUMBER = "+49228654321";
    private static String UPDATE_E_MOBILENUMBER = "+491767654321";
    
    private static String UPDATE_E_USERNAME = "fcodinha";
    private static String UPDATE_E_PASSWORD = "e24246efc397bc0cf2dce9fbc3631736"; //notadmin
    
    //Gehaltkategorie Felder
    //Erstellung
    private static float INSERT_SC_SALARYFROM = Float.parseFloat("300000");
    private static float INSERT_SC_SALARYTO = Float.parseFloat("400000");
    private static String INSERT_SC_DESCRIPTION = "IT-Berater Monatlich Anfanger";
    
    //Änderung
    private static float UPDATE_SC_SALARYFROM = Float.parseFloat("30000");
    private static float UPDATE_SC_SALARYTO = Float.parseFloat("42500");
    private static String UPDATE_SC_DESCRIPTION = "IT-Berater Jährlich Anfanger";
    
    //Division
    //Erstellung
    private static String INSERT_D_NAME = "IT-Berater";
    private static String INSERT_D_DESCRIPTION = "IT-Berater";
    
    //Änderung
    private static String UPDATE_D_NAME = "Finanz-Berater";
    private static String UPDATE_D_DESCRIPTION = "Finanz-Berater";
    
    //UserGroup
    //Erstellung
    private static String INSERT_UG_NAME = "Finanz-Berater";
    private static String INSERT_UG_DESCRIPTION = "Finanz-Berater Erlaubnise";
    private static int INSERT_UG_PERMISSION1 = 1;
    private static int INSERT_UG_PERMISSION2 = 2;
    private static int INSERT_UG_PERMISSION3 = 3;
    
    //Änderung
    private static String UPDATE_UG_NAME = "Finanz-Beraterin";
    private static String UPDATE_UG_DESCRIPTION = "Finanzin-Berater Erlaubnise";
    private static int UPDATE_UG_PERMISSION1 = 4;
    private static int UPDATE_UG_PERMISSION2 = 5;
    private static int UPDATE_UG_PERMISSION3 = 3;
    
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
    private static String QUERY_UG_PI_BY_ID = "from Permission where idPermission = " + INSERT_UG_PERMISSION1 +
    		" OR idPermission = " + INSERT_UG_PERMISSION2 +
    		" OR idPermission = " + INSERT_UG_PERMISSION3;
    private static String QUERY_UG_PU_BY_ID = "from Permission where idPermission = " + UPDATE_UG_PERMISSION1 +
    		" OR idPermission = " + UPDATE_UG_PERMISSION2 +
    		" OR idPermission = " + UPDATE_UG_PERMISSION3;
    
    /**
     * Eine Gehaltkategorie wird in die Datenbank erstellt
     */
    public void test01InsertSalaryCategory()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
	        //Die Felder werden gefüllt
	        SalaryCategory salaryCategory = new SalaryCategory();
	        salaryCategory.setDescription(INSERT_SC_DESCRIPTION);
	        salaryCategory.setSalaryFrom(INSERT_SC_SALARYFROM);
	        salaryCategory.setSalaryTo(INSERT_SC_SALARYTO);
	        
	        HibernateUtil.insert(salaryCategory, session);
	        
	        //Die erstellt Gehaltkategorie wird geprüft
	        QUERY_SC_BY_ID = QUERY_SC_BY_ID + salaryCategory.getIdSalaryCategory();
	        salaryCategory = (SalaryCategory) HibernateUtil.selectObject(QUERY_SC_BY_ID, session);
	        
	        assertEquals(salaryCategory.getSalaryFrom(), INSERT_SC_SALARYFROM);
	        assertEquals(salaryCategory.getSalaryTo(), INSERT_SC_SALARYTO);
	        assertEquals(salaryCategory.getDescription(), INSERT_SC_DESCRIPTION);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
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
    		
	        SalaryCategory salaryCategory = (SalaryCategory) HibernateUtil.selectObject(QUERY_SC_BY_ID, session);
	
	        //Die Felder werden gefüllt
	        salaryCategory.setDescription(UPDATE_SC_DESCRIPTION);
	        salaryCategory.setSalaryFrom(UPDATE_SC_SALARYFROM);
	        salaryCategory.setSalaryTo(UPDATE_SC_SALARYTO);
	        
	        HibernateUtil.update(salaryCategory, session);
	
	        //Die geändert Gehaltkategorie wird geprüft
	        salaryCategory = (SalaryCategory) HibernateUtil.selectObject(QUERY_SC_BY_ID, session);
	        
	        assertEquals(salaryCategory.getSalaryFrom(), UPDATE_SC_SALARYFROM);
	        assertEquals(salaryCategory.getSalaryTo(), UPDATE_SC_SALARYTO);
	        assertEquals(salaryCategory.getDescription(), UPDATE_SC_DESCRIPTION);
    	} catch (Exception ex) {
	    	ex.printStackTrace();
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
	        //Die Felder werden gefüllt
	        Division division = new Division();
	        division.setDescription(INSERT_D_DESCRIPTION);
	        division.setName(INSERT_D_NAME);
	        
	        HibernateUtil.insert(division, session);
	        
	        //Die erstellt Division wird geprüft
	        QUERY_D_BY_ID = QUERY_D_BY_ID + division.getIdDivision();
	        
	        division = (Division) HibernateUtil.selectObject(QUERY_D_BY_ID, session);
	        
	        assertEquals(division.getDescription(), INSERT_D_DESCRIPTION);
	        assertEquals(division.getName(), INSERT_D_NAME);
        } catch (Exception ex) {
	    	ex.printStackTrace();
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
    		
	        Division division = (Division) HibernateUtil.selectObject(QUERY_D_BY_ID, session);
	
	        //Die Felder werden gefüllt
	        division.setDescription(UPDATE_D_DESCRIPTION);
	        division.setName(UPDATE_D_NAME);
	        
	        HibernateUtil.update(division, session);
	        
	        //Die geändert Division wird geprüft
	        division = (Division) HibernateUtil.selectObject(QUERY_D_BY_ID, session);
	        
	        assertEquals(division.getDescription(), UPDATE_D_DESCRIPTION);
	        assertEquals(division.getName(), UPDATE_D_NAME);
    	} catch (Exception ex) {
	    	ex.printStackTrace();
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
	        //Die Felder werden gefüllt
	        UserGroup userGroup = new UserGroup();
	        userGroup.setDescription(INSERT_UG_DESCRIPTION);
	        userGroup.setName(INSERT_UG_NAME);
	        
	        session.beginTransaction();
	        Query query = session.createQuery(QUERY_UG_PI_BY_ID);
	        session.getTransaction().commit();
	        List<?> list = HibernateUtil.selectList(QUERY_UG_PI_BY_ID, session);
	        for (int i = 0; i < query.list().size(); i++) {
				Permission permission = (Permission) list.get(i);
				userGroup.getPermissions().add(permission);
			}
	        
	        HibernateUtil.insert(userGroup, session);
	        
	        //Die erstellt Benutzergruppe wird geprüft
	        QUERY_UG_BY_ID = QUERY_UG_BY_ID + userGroup.getIdUserGroup();
	        
	        userGroup = (UserGroup) HibernateUtil.selectObject(QUERY_UG_BY_ID, session);
	        
	        assertTrue(userGroup.getPermissions().containsAll(HibernateUtil.selectList(QUERY_UG_PI_BY_ID, session)));
	        
	        assertEquals(userGroup.getDescription(), INSERT_UG_DESCRIPTION);
	        assertEquals(userGroup.getName(), INSERT_UG_NAME);
        } catch (Exception ex) {
	    	ex.printStackTrace();
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
	    	
	        UserGroup userGroup = (UserGroup) HibernateUtil.selectObject(QUERY_UG_BY_ID, session);
	    	
	        //Die Felder werden gefüllt
	        userGroup.setDescription(UPDATE_UG_DESCRIPTION);
	        userGroup.setName(UPDATE_UG_NAME);
	        userGroup.setPermissions(new HashSet<Permission>(0));
	        
	        List<?> list = HibernateUtil.selectList(QUERY_UG_PU_BY_ID, session);
	        
	        for (int i = 0; i < list.size(); i++) {
				Permission permission = (Permission) list.get(i);
				userGroup.getPermissions().add(permission);
			}
	        
	        HibernateUtil.update(userGroup, session);
	        
	        //Die geändert Benutzergruppe wird geprüft
	        userGroup = (UserGroup) HibernateUtil.selectObject(QUERY_UG_BY_ID, session);
	        
	        assertTrue(userGroup.getPermissions().containsAll(HibernateUtil.selectList(QUERY_UG_PU_BY_ID, session)));
	        
	        assertEquals(userGroup.getDescription(), UPDATE_UG_DESCRIPTION);
	        assertEquals(userGroup.getName(), UPDATE_UG_NAME);
        } catch (Exception ex) {
	    	ex.printStackTrace();
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
	    	//Die Gehaltkategorie wird geholt
	        SalaryCategory salaryCategory = (SalaryCategory) HibernateUtil.selectObject(QUERY_SC_BY_ID, session);
	    	
	        //Die Division wird geholt
	        Division division = (Division) HibernateUtil.selectObject(QUERY_D_BY_ID, session);
	        
	        //Die Benutzergruppe wird geholt
	        UserGroup userGroup = (UserGroup) HibernateUtil.selectObject(QUERY_UG_BY_ID, session);
	        
	        Employee employee = new Employee();
	        
	        //Die Felder werden gefüllt
	        employee.setAddress(INSERT_E_ADDRESS);
	        employee.setCity(INSERT_E_CITY);
	        employee.setCountry(INSERT_E_COUNTRY);
	        employee.setDivision(division);
	        employee.setEmail(INSERT_E_EMAIL);
	        employee.setIdCard(INSERT_E_IDCARD);
	        employee.setMobileNumber(INSERT_E_MOBILENUMBER);
	        employee.setName(INSERT_E_NAME);
	        employee.setPhoneNumber(INSERT_E_PHONENUMBER);
	        employee.setRegion(INSERT_E_REGION);
	        employee.setSalaryCategory(salaryCategory);
	        employee.setUserGroup(userGroup);
	        employee.setZipCode(INSERT_E_ZIPCODE);
	        
	        HibernateUtil.insert(employee, session);
	        
	        //Benutzername und Kenntwort werden gefüllt
	        EmployeeUser employeeUser = new EmployeeUser();
	        employeeUser.setEmployee(employee);
	        employeeUser.setPassword(INSERT_E_PASSWORD);
	        employeeUser.setUsername(INSERT_E_USERNAME);
	        
	        HibernateUtil.insert(employeeUser, session);
	        
	        //Der erstellter Mitarbeiter wird geprüft
	        QUERY_E_BY_ID = QUERY_E_BY_ID + employee.getIdEmployee();
	        
	        employee = (Employee) HibernateUtil.selectObject(QUERY_E_BY_ID, session);
	        
	        QUERY_EU_BY_ID = QUERY_EU_BY_ID + employeeUser.getIdEmployee();
	        
	        employeeUser = (EmployeeUser) HibernateUtil.selectObject(QUERY_EU_BY_ID, session);
	        
	        assertEquals(employee.getAddress(), INSERT_E_ADDRESS);
	        assertEquals(employee.getCity(), INSERT_E_CITY);
	        assertEquals(employee.getCountry(), INSERT_E_COUNTRY);
	        assertEquals(employee.getDivision(), division);
	        assertEquals(employee.getEmail(), INSERT_E_EMAIL);
	        assertEquals(employee.getIdCard(), INSERT_E_IDCARD);
	        assertEquals(employee.getMobileNumber(), INSERT_E_MOBILENUMBER);
	        assertEquals(employee.getName(), INSERT_E_NAME);
	        assertEquals(employee.getPhoneNumber(), INSERT_E_PHONENUMBER);
	        assertEquals(employee.getRegion(), INSERT_E_REGION);
	        assertEquals(employee.getSalaryCategory(), salaryCategory);
	        assertEquals(employee.getUserGroup(), userGroup);
	        assertEquals(employee.getZipCode(), INSERT_E_ZIPCODE);
	        assertEquals(employeeUser.getPassword(), INSERT_E_PASSWORD);
	        assertEquals(employeeUser.getUsername(), INSERT_E_USERNAME);
    	} catch (Exception ex) {
	    	ex.printStackTrace();
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
    		
	        Employee employee = (Employee) HibernateUtil.selectObject(QUERY_E_BY_ID, session);
	        
	        //Die Felder werden gefüllt
	        employee.setAddress(UPDATE_E_ADDRESS);
	        employee.setCity(UPDATE_E_CITY);
	        employee.setCountry(UPDATE_E_COUNTRY);
	        employee.setEmail(UPDATE_E_EMAIL);
	        employee.setIdCard(UPDATE_E_IDCARD);
	        employee.setMobileNumber(UPDATE_E_MOBILENUMBER);
	        employee.setName(UPDATE_E_NAME);
	        employee.setPhoneNumber(UPDATE_E_PHONENUMBER);
	        employee.setRegion(UPDATE_E_REGION);
	        employee.setZipCode(UPDATE_E_ZIPCODE);
	        
	        HibernateUtil.update(employee, session);
	        
	        //Benutzername und Kenntwort werden geändert
	        EmployeeUser employeeUser = employee.getEmployeeUser();
	        employeeUser.setPassword(UPDATE_E_PASSWORD);
	        employeeUser.setUsername(UPDATE_E_USERNAME);
	        
	        HibernateUtil.update(employeeUser, session);
	        
	        //Der erstellter Mitarbeiter wird geprüft
	        employee = (Employee) HibernateUtil.selectObject(QUERY_E_BY_ID, session);
	        
	        employeeUser = (EmployeeUser) HibernateUtil.selectObject(QUERY_EU_BY_ID, session);
	        
	        assertEquals(employee.getAddress(), UPDATE_E_ADDRESS);
	        assertEquals(employee.getCity(), UPDATE_E_CITY);
	        assertEquals(employee.getCountry(), UPDATE_E_COUNTRY);
	        assertEquals(employee.getEmail(), UPDATE_E_EMAIL);
	        assertEquals(employee.getIdCard(), UPDATE_E_IDCARD);
	        assertEquals(employee.getMobileNumber(), UPDATE_E_MOBILENUMBER);
	        assertEquals(employee.getName(), UPDATE_E_NAME);
	        assertEquals(employee.getPhoneNumber(), UPDATE_E_PHONENUMBER);
	        assertEquals(employee.getRegion(), UPDATE_E_REGION);
	        assertEquals(employee.getZipCode(), UPDATE_E_ZIPCODE);
	        assertEquals(employeeUser.getPassword(), UPDATE_E_PASSWORD);
	        assertEquals(employeeUser.getUsername(), UPDATE_E_USERNAME);
    	} catch (Exception ex) {
	    	ex.printStackTrace();
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
	    } finally {
	    	if (session.isOpen())
	    		session.close();
	    }
    }
}
