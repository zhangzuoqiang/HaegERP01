package org.haegerp.entity;

import java.util.HashSet;

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

        //Die Felder werden gefüllt
        SalaryCategory salaryCategory = new SalaryCategory();
        salaryCategory.setDescription(INSERT_SC_DESCRIPTION);
        salaryCategory.setSalaryFrom(INSERT_SC_SALARYFROM);
        salaryCategory.setSalaryTo(INSERT_SC_SALARYTO);
        
        session.beginTransaction();
        session.save(salaryCategory);
        session.getTransaction().commit();
        
        //Die erstellt Gehaltkategorie wird geprüft
        session.beginTransaction();
        QUERY_SC_BY_ID = QUERY_SC_BY_ID + salaryCategory.getIdSalaryCategory();
        Query query = session.createQuery(QUERY_SC_BY_ID);
        session.getTransaction().commit();
        
        salaryCategory = (SalaryCategory) query.uniqueResult();
        
        assertEquals(salaryCategory.getSalaryFrom(), INSERT_SC_SALARYFROM);
        assertEquals(salaryCategory.getSalaryTo(), INSERT_SC_SALARYTO);
        assertEquals(salaryCategory.getDescription(), INSERT_SC_DESCRIPTION);
    }
    
    /**
     * Die letzte Gehaltkategorie wird geändert
     */
    public void test02UpdateSalaryCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        Query query = session.createQuery(QUERY_SC_BY_ID);
        session.getTransaction().commit();

        SalaryCategory salaryCategory = (SalaryCategory) query.uniqueResult();

        //Die Felder werden gefüllt
        salaryCategory.setDescription(UPDATE_SC_DESCRIPTION);
        salaryCategory.setSalaryFrom(UPDATE_SC_SALARYFROM);
        salaryCategory.setSalaryTo(UPDATE_SC_SALARYTO);
        
        session.beginTransaction();
        session.merge(salaryCategory);
        session.getTransaction().commit();

        //Die geändert Gehaltkategorie wird geprüft
        session.beginTransaction();
        query = session.createQuery(QUERY_SC_BY_ID);
        session.getTransaction().commit();
        
        salaryCategory = (SalaryCategory) query.uniqueResult();
        
        assertEquals(salaryCategory.getSalaryFrom(), UPDATE_SC_SALARYFROM);
        assertEquals(salaryCategory.getSalaryTo(), UPDATE_SC_SALARYTO);
        assertEquals(salaryCategory.getDescription(), UPDATE_SC_DESCRIPTION);
    }
    
    /**
     * Eine Division wird in die Datenbank erstellt
     */
    public void test03InsertDivision()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        //Die Felder werden gefüllt
        Division division = new Division();
        division.setDescription(INSERT_D_DESCRIPTION);
        division.setName(INSERT_D_NAME);
        
        session.beginTransaction();
        session.save(division);
        session.getTransaction().commit();
        
        //Die erstellt Division wird geprüft
        session.beginTransaction();
        QUERY_D_BY_ID = QUERY_D_BY_ID + division.getIdDivision();
        Query query = session.createQuery(QUERY_D_BY_ID);
        session.getTransaction().commit();
        
        division = (Division) query.uniqueResult();
        
        assertEquals(division.getDescription(), INSERT_D_DESCRIPTION);
        assertEquals(division.getName(), INSERT_D_NAME);
    }
    
    /**
     * Die letzte Gehaltkategorie wird geändert
     */
    public void test04UpdateSalaryCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        Query query = session.createQuery(QUERY_D_BY_ID);
        session.getTransaction().commit();
        
        Division division = (Division) query.uniqueResult();

        //Die Felder werden gefüllt
        division.setDescription(UPDATE_D_DESCRIPTION);
        division.setName(UPDATE_D_NAME);
        
        session.beginTransaction();
        session.merge(division);
        session.getTransaction().commit();
        
        //Die geändert Division wird geprüft
        session.beginTransaction();
        query = session.createQuery(QUERY_D_BY_ID);
        session.getTransaction().commit();
        
        division = (Division) query.uniqueResult();
        
        assertEquals(division.getDescription(), UPDATE_D_DESCRIPTION);
        assertEquals(division.getName(), UPDATE_D_NAME);
    }
    
    /**
     * Eine Benutzergruppe wird in die Datenbank erstellt
     */
    public void test05InsertUserGroup()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        //Die Felder werden gefüllt
        UserGroup userGroup = new UserGroup();
        userGroup.setDescription(INSERT_UG_DESCRIPTION);
        userGroup.setName(INSERT_UG_NAME);
        
        session.beginTransaction();
        Query query = session.createQuery(QUERY_UG_PI_BY_ID);
        session.getTransaction().commit();
        for (int i = 0; i < query.list().size(); i++) {
			Permission permission = (Permission) query.list().get(i);
			userGroup.getPermissions().add(permission);
		}
        
        session.beginTransaction();
        session.save(userGroup);
        session.getTransaction().commit();
        
        //Die erstellt Benutzergruppe wird geprüft
        session.beginTransaction();
        QUERY_UG_BY_ID = QUERY_UG_BY_ID + userGroup.getIdUserGroup();
        query = session.createQuery(QUERY_UG_BY_ID);
        session.getTransaction().commit();
        
        userGroup = (UserGroup) query.uniqueResult();
        
        session.beginTransaction();
        query = session.createQuery(QUERY_UG_PI_BY_ID);
        session.getTransaction().commit();
        
        assertTrue(userGroup.getPermissions().containsAll(query.list()));
        
        assertEquals(userGroup.getDescription(), INSERT_UG_DESCRIPTION);
        assertEquals(userGroup.getName(), INSERT_UG_NAME);
    }
    
    /**
     * Die letzte Benutzergruppe wird geändert
     */
    public void test06UpdateUserGroup()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        
    	session.beginTransaction();
    	Query query = session.createQuery(QUERY_UG_BY_ID);
        session.getTransaction().commit();
        
        UserGroup userGroup = (UserGroup) query.uniqueResult();
    	
        session.beginTransaction();
        query = session.createQuery(QUERY_UG_PU_BY_ID);
        session.getTransaction().commit();
        
        //Die Felder werden gefüllt
        userGroup.setDescription(UPDATE_UG_DESCRIPTION);
        userGroup.setName(UPDATE_UG_NAME);
        userGroup.setPermissions(new HashSet<Permission>(0));
        
        for (int i = 0; i < query.list().size(); i++) {
			Permission permission = (Permission) query.list().get(i);
			userGroup.getPermissions().add(permission);
		}
        
        session.beginTransaction();
        session.merge(userGroup);
        session.getTransaction().commit();
        
        //Die geändert Benutzergruppe wird geprüft
        session.beginTransaction();
        query = session.createQuery(QUERY_UG_BY_ID);
        session.getTransaction().commit();
        
        userGroup = (UserGroup) query.uniqueResult();
        
        session.beginTransaction();
        query = session.createQuery(QUERY_UG_PU_BY_ID);
        session.getTransaction().commit();
        
        assertTrue(userGroup.getPermissions().containsAll(query.list()));
        
        assertEquals(userGroup.getDescription(), UPDATE_UG_DESCRIPTION);
        assertEquals(userGroup.getName(), UPDATE_UG_NAME);
    }
    
    /**
     * Ein Mitarbeiter wird erstellt
     */
    public void test07InsertEmployee(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	
    	//Die Gehaltkategorie wird geholt
    	session.beginTransaction();
        Query query = session.createQuery(QUERY_SC_BY_ID);
        session.getTransaction().commit();
        
        SalaryCategory salaryCategory = (SalaryCategory) query.uniqueResult();
    	
        //Die Division wird geholt
    	session.beginTransaction();
        query = session.createQuery(QUERY_D_BY_ID);
        session.getTransaction().commit();
        
        Division division = (Division) query.uniqueResult();
        
        //Die Benutzergruppe wird geholt
    	session.beginTransaction();
        query = session.createQuery(QUERY_UG_BY_ID);
        session.getTransaction().commit();
        
        UserGroup userGroup = (UserGroup) query.uniqueResult();
        
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
        
        session.beginTransaction();
        session.save(employee);
        
        
        EmployeeUser employeeUser = new EmployeeUser();
        employeeUser.setEmployee(employee);
        employeeUser.setPassword(INSERT_E_PASSWORD);
        employeeUser.setUsername(INSERT_E_USERNAME);
        
        session.save(employeeUser);
        session.getTransaction().commit();
        
        //Der erstellter Mitarbeiter wird geprüft
        session.beginTransaction();
        QUERY_E_BY_ID = QUERY_E_BY_ID + employee.getIdEmployee();
        query = session.createQuery(QUERY_E_BY_ID);
        session.getTransaction().commit();
        
        employee = (Employee) query.uniqueResult();
        
        session.beginTransaction();
        QUERY_EU_BY_ID = QUERY_EU_BY_ID + employeeUser.getIdEmployee();
        query = session.createQuery(QUERY_EU_BY_ID);
        session.getTransaction().commit();
        
        employeeUser = (EmployeeUser) query.uniqueResult();
        
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
        
    }
    
    /**
     * Der letzter Mitarbeiter wird geändert
     */
    public void test08UpdateEmployee(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	
    	session.beginTransaction();
        Query query = session.createQuery(QUERY_E_BY_ID);
        session.getTransaction().commit();
        
        Employee employee = (Employee) query.uniqueResult();
        
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
        
        session.beginTransaction();
        session.merge(employee);
        
        
        EmployeeUser employeeUser = employee.getEmployeeUser();
        employeeUser.setPassword(UPDATE_E_PASSWORD);
        employeeUser.setUsername(UPDATE_E_USERNAME);
        
        session.merge(employeeUser);
        session.getTransaction().commit();
        
        //Der erstellter Mitarbeiter wird geprüft
        session.beginTransaction();
        query = session.createQuery(QUERY_E_BY_ID);
        session.getTransaction().commit();
        
        employee = (Employee) query.uniqueResult();
        
        session.beginTransaction();
        query = session.createQuery(QUERY_EU_BY_ID);
        session.getTransaction().commit();
        
        employeeUser = (EmployeeUser) query.uniqueResult();
        
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
    }
    
    /**
     * Der letzter Mitarbeiter wird gelöscht
     */
    public void test09DeleteEmployee(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        Query query = session.createQuery(QUERY_E_BY_ID);
        session.getTransaction().commit();
        
        Employee employee = (Employee) query.uniqueResult();
    	
        session.beginTransaction();
        query = session.createQuery(QUERY_EU_BY_ID);
        session.getTransaction().commit();
        
        EmployeeUser employeeUser = (EmployeeUser) query.uniqueResult();
        
        session.beginTransaction();
        session.delete(employeeUser);
        session.delete(employee);
        session.getTransaction().commit();
        
        //Suchen noch einmal
        session.beginTransaction();
        query = session.createQuery(QUERY_E_BY_ID);
        session.getTransaction().commit();
        
        //keine Aufzeichnung gefunden
        assertTrue(query.list().isEmpty());
    }
    
    /**
     * Die Gehaltkategorie wird gelöscht
     */
    public void test10DeleteSalaryCategory()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        Query query = session.createQuery(QUERY_SC_BY_ID);
        session.getTransaction().commit();
        
        SalaryCategory salaryCategory = (SalaryCategory) query.uniqueResult();
    	
        session.beginTransaction();
        session.delete(salaryCategory);
        session.getTransaction().commit();
        
        //Suchen noch einmal
        session.beginTransaction();
        query = session.createQuery(QUERY_SC_BY_ID);
        session.getTransaction().commit();
        
        //keine Aufzeichnung gefunden
        assertTrue(query.list().isEmpty());
    }
    
    /**
     * Die Division wird gelöscht
     */
    public void test11DeleteDivision()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        Query query = session.createQuery(QUERY_D_BY_ID);
        session.getTransaction().commit();
        
        Division division = (Division) query.uniqueResult();
    	
        session.beginTransaction();
        session.delete(division);
        session.getTransaction().commit();
        
        //Suchen noch einmal
        session.beginTransaction();
        query = session.createQuery(QUERY_D_BY_ID);
        session.getTransaction().commit();
        
        //keine Aufzeichnung gefunden
        assertTrue(query.list().isEmpty());
    }
    
    /**
     * Die Benutzergruppe wird gelöscht
     */
    public void test12DeleteUserGroup()
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        Query query = session.createQuery(QUERY_UG_BY_ID);
        session.getTransaction().commit();
        
        UserGroup userGroup = (UserGroup) query.uniqueResult();
    	
        session.beginTransaction();
        session.delete(userGroup);
        session.getTransaction().commit();
        
        //Suchen noch einmal
        session.beginTransaction();
        query = session.createQuery(QUERY_UG_BY_ID);
        session.getTransaction().commit();
        
        //keine Aufzeichnung gefunden
        assertTrue(query.list().isEmpty());
    }
}
