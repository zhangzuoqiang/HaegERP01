package org.haegerp.entity;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.haegerp.entity.repository.employee.DivisionRepository;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.entity.repository.employee.PermissionRepository;
import org.haegerp.entity.repository.employee.SalaryCategoryRepository;
import org.haegerp.entity.repository.employee.UserGroupRepository;
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
 * Dieses Test Suite wird Mitarbeiter, Gehaltkategorie, Division und Benutzergruppe testen
 * 
 * @author Wolf
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/spring-data.xml")
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class Employee_SalaryCategory_Division_UserGroupTest extends TestCase {
	
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
    
    private static long EMPLOYEE_ID;
    
    private static long SALARY_CATEGORY_ID;
    
    private static long DIVISION_ID;
    
    private static long USER_GROUP_ID;
    
    private static boolean CHECK_SETUP = true;
    
    @Override
    @Before
    public void setUp() throws Exception {
    	super.setUp();
    	if (CHECK_SETUP)
    	{
    		CHECK_SETUP = false;
	    	Session.setEmployee(employeeRepository.findOne(1L));
    	}
    }
    
    /**
     * Eine Gehaltkategorie wird in die Datenbank erstellt
     */
    @Test
    public void test01InsertSalaryCategory()
    {
        try {
	        //Die Felder werden gefüllt
	        SalaryCategory salaryCategory = new SalaryCategory();
	        salaryCategory.setDescription(Config.getProperty("INSERT_SC_DESCRIPTION"));
	        salaryCategory.setSalaryFrom(Float.parseFloat(Config.getProperty("INSERT_SC_SALARYFROM")));
	        salaryCategory.setSalaryTo(Float.parseFloat(Config.getProperty("INSERT_SC_SALARYTO")));
	        
	        salaryCategory = salaryCategoryRepository.performNew(salaryCategory);
	        
	        //Die erstellt Gehaltkategorie wird geprüft
	        SALARY_CATEGORY_ID = salaryCategory.getIdSalaryCategory();
	        salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	        
	        assertEquals(salaryCategory.getSalaryFrom(), Float.parseFloat(Config.getProperty("INSERT_SC_SALARYFROM")));
	        assertEquals(salaryCategory.getSalaryTo(), Float.parseFloat(Config.getProperty("INSERT_SC_SALARYTO")));
	        assertEquals(salaryCategory.getDescription(), Config.getProperty("INSERT_SC_DESCRIPTION"));
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Die letzte Gehaltkategorie wird geändert
     */
    @Test
    public void test02UpdateSalaryCategory()
    {
    	try {
	        SalaryCategory salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	
	        //Die Felder werden gefüllt
	        salaryCategory.setDescription(Config.getProperty("UPDATE_SC_DESCRIPTION"));
	        salaryCategory.setSalaryFrom(Float.parseFloat(Config.getProperty("UPDATE_SC_SALARYFROM")));
	        salaryCategory.setSalaryTo(Float.parseFloat(Config.getProperty("UPDATE_SC_SALARYTO")));
	        
	        salaryCategory = salaryCategoryRepository.performEdit(salaryCategory);
	
	        //Die geändert Gehaltkategorie wird geprüft
	        salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	        
	        assertEquals(salaryCategory.getSalaryFrom(), Float.parseFloat(Config.getProperty("UPDATE_SC_SALARYFROM")));
	        assertEquals(salaryCategory.getSalaryTo(), Float.parseFloat(Config.getProperty("UPDATE_SC_SALARYTO")));
	        assertEquals(salaryCategory.getDescription(), Config.getProperty("UPDATE_SC_DESCRIPTION"));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Eine Division wird in die Datenbank erstellt
     */
    @Test
    public void test03InsertDivision()
    {
        try {
	        //Die Felder werden gefüllt
	        Division division = new Division();
	        division.setDescription(Config.getProperty("INSERT_D_DESCRIPTION"));
	        division.setName(Config.getProperty("INSERT_D_NAME"));
	        
	        division = divisionRepository.performNew(division);
	        
	        //Die erstellt Division wird geprüft
	        DIVISION_ID = division.getIdDivision();
	        
	        division = divisionRepository.findOne(DIVISION_ID);
	        
	        assertEquals(division.getDescription(), Config.getProperty("INSERT_D_DESCRIPTION"));
	        assertEquals(division.getName(), Config.getProperty("INSERT_D_NAME"));
        } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Die letzte Gehaltkategorie wird geändert
     */
    @Test
    public void test04UpdateSalaryCategory()
    {
    	try {
	        Division division = divisionRepository.findOne(DIVISION_ID);
	
	        //Die Felder werden gefüllt
	        division.setDescription(Config.getProperty("UPDATE_D_DESCRIPTION"));
	        division.setName(Config.getProperty("UPDATE_D_NAME"));
	        
	        division = divisionRepository.performEdit(division);
	        
	        //Die geändert Division wird geprüft
	        division = divisionRepository.findOne(DIVISION_ID);
	        
	        assertEquals(division.getDescription(), Config.getProperty("UPDATE_D_DESCRIPTION"));
	        assertEquals(division.getName(), Config.getProperty("UPDATE_D_NAME"));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Eine Benutzergruppe wird in die Datenbank erstellt
     */
    @Test
    public void test05InsertUserGroup()
    {
        try {
	        //Die Felder werden gefüllt
	        UserGroup userGroup = new UserGroup();
	        userGroup.setDescription(Config.getProperty("INSERT_UG_DESCRIPTION"));
	        userGroup.setName(Config.getProperty("INSERT_UG_NAME"));
	        

	        //Hinzufügen erlaubnise
	        List<Permission> permissionList = new LinkedList<Permission>();
	        
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Config.getProperty("INSERT_UG_PERMISSION1"))));
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Config.getProperty("INSERT_UG_PERMISSION2"))));
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Config.getProperty("INSERT_UG_PERMISSION3"))));
	        
	        for (int i = 0; i < permissionList.size(); i++) {
				userGroup.getPermissions().add(permissionList.get(i));
			}
	        
	        userGroup = userGroupRepository.performNew(userGroup);
	        
	        //Die erstellt Benutzergruppe wird geprüft
	        USER_GROUP_ID = userGroup.getIdUserGroup();
	        
	        userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	        
	        assertTrue(userGroup.getPermissions().containsAll(permissionList));
	        
	        assertEquals(userGroup.getDescription(), Config.getProperty("INSERT_UG_DESCRIPTION"));
	        assertEquals(userGroup.getName(), Config.getProperty("INSERT_UG_NAME"));
        } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Die letzte Benutzergruppe wird geändert
     */
    @Test
    public void test06UpdateUserGroup()
    {
        try {
	        UserGroup userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	    	
	        //Die Felder werden gefüllt
	        userGroup.setDescription(Config.getProperty("UPDATE_UG_DESCRIPTION"));
	        userGroup.setName(Config.getProperty("UPDATE_UG_NAME"));
	        userGroup.setPermissions(new HashSet<Permission>(0));
	        
	        //Hinzufügen erlaubnise
	        List<Permission> permissionList = new LinkedList<Permission>();
	        
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Config.getProperty("UPDATE_UG_PERMISSION1"))));
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Config.getProperty("UPDATE_UG_PERMISSION2"))));
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Config.getProperty("UPDATE_UG_PERMISSION3"))));
	        
	        for (int i = 0; i < permissionList.size(); i++) {
				userGroup.getPermissions().add(permissionList.get(i));
			}
	        
	        userGroup = userGroupRepository.performEdit(userGroup);
	        
	        //Die geändert Benutzergruppe wird geprüft
	        userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	        
	        assertTrue(userGroup.getPermissions().containsAll(permissionList));
	        
	        assertEquals(userGroup.getDescription(), Config.getProperty("UPDATE_UG_DESCRIPTION"));
	        assertEquals(userGroup.getName(), Config.getProperty("UPDATE_UG_NAME"));
        } catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Ein Mitarbeiter wird erstellt
     */
    @Test
    public void test07InsertEmployee(){
    	try {
	    	//Die Gehaltkategorie wird geholt
	        SalaryCategory salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	    	
	        //Die Division wird geholt
	        Division division = divisionRepository.findOne(DIVISION_ID);
	        
	        //Die Benutzergruppe wird geholt
	        UserGroup userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	        
	        Employee employee = new Employee();
	        
	        //Die Felder werden gefüllt
	        employee.setAddress(Config.getProperty("INSERT_E_ADDRESS"));
	        employee.setCity(Config.getProperty("INSERT_E_CITY"));
	        employee.setCountry(Config.getProperty("INSERT_E_COUNTRY"));
	        employee.setDivision(division);
	        employee.setEmail(Config.getProperty("INSERT_E_EMAIL"));
	        employee.setIdCard(Long.parseLong(Config.getProperty("INSERT_E_IDCARD")));
	        employee.setMobileNumber(Config.getProperty("INSERT_E_MOBILENUMBER"));
	        employee.setName(Config.getProperty("INSERT_E_NAME"));
	        employee.setPhoneNumber(Config.getProperty("INSERT_E_PHONENUMBER"));
	        employee.setRegion(Config.getProperty("INSERT_E_REGION"));
	        employee.setSalaryCategory(salaryCategory);
	        employee.setUserGroup(userGroup);
	        employee.setZipCode(Config.getProperty("INSERT_E_ZIPCODE"));
	        
	      //Benutzername und Kenntwort werden erstellt
	        employee.setPassword(Config.getProperty("INSERT_E_PASSWORD"));
	        employee.setUsername(Config.getProperty("INSERT_E_USERNAME"));
	        
	        employee = employeeRepository.performNew(employee);
	        
	        //Der erstellter Mitarbeiter wird geprüft
	        EMPLOYEE_ID = employee.getIdEmployee();
	        
	        employee = employeeRepository.findOne(EMPLOYEE_ID);
	        
	        assertEquals(employee.getAddress(), Config.getProperty("INSERT_E_ADDRESS"));
	        assertEquals(employee.getCity(), Config.getProperty("INSERT_E_CITY"));
	        assertEquals(employee.getCountry(), Config.getProperty("INSERT_E_COUNTRY"));
	        assertEquals(employee.getDivision(), division);
	        assertEquals(employee.getEmail(), Config.getProperty("INSERT_E_EMAIL"));
	        assertEquals(employee.getIdCard(), Long.parseLong(Config.getProperty("INSERT_E_IDCARD")));
	        assertEquals(employee.getMobileNumber(), Config.getProperty("INSERT_E_MOBILENUMBER"));
	        assertEquals(employee.getName(), Config.getProperty("INSERT_E_NAME"));
	        assertEquals(employee.getPhoneNumber(), Config.getProperty("INSERT_E_PHONENUMBER"));
	        assertEquals(employee.getRegion(), Config.getProperty("INSERT_E_REGION"));
	        assertEquals(employee.getSalaryCategory(), salaryCategory);
	        assertEquals(employee.getUserGroup(), userGroup);
	        assertEquals(employee.getZipCode(), Config.getProperty("INSERT_E_ZIPCODE"));
	        assertEquals(employee.getPassword(), Config.getProperty("INSERT_E_PASSWORD"));
	        assertEquals(employee.getUsername(), Config.getProperty("INSERT_E_USERNAME"));
	        
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Der letzter Mitarbeiter wird geändert
     */
    @Test
    public void test08UpdateEmployee(){
    	try {
	        Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
	        
	        //Die Felder werden gefüllt
	        employee.setAddress(Config.getProperty("UPDATE_E_ADDRESS"));
	        employee.setCity(Config.getProperty("UPDATE_E_CITY"));
	        employee.setCountry(Config.getProperty("UPDATE_E_COUNTRY"));
	        employee.setEmail(Config.getProperty("UPDATE_E_EMAIL"));
	        employee.setIdCard(Long.parseLong(Config.getProperty("UPDATE_E_IDCARD")));
	        employee.setMobileNumber(Config.getProperty("UPDATE_E_MOBILENUMBER"));
	        employee.setName(Config.getProperty("UPDATE_E_NAME"));
	        employee.setPhoneNumber(Config.getProperty("UPDATE_E_PHONENUMBER"));
	        employee.setRegion(Config.getProperty("UPDATE_E_REGION"));
	        employee.setZipCode(Config.getProperty("UPDATE_E_ZIPCODE"));
	        
	        //Benutzername und Kenntwort werden geändert
	        employee.setPassword(Config.getProperty("UPDATE_E_PASSWORD"));
	        employee.setUsername(Config.getProperty("UPDATE_E_USERNAME"));
	        
	        employee = employeeRepository.performEdit(employee);
	        
	        //Der erstellter Mitarbeiter wird geprüft
	        employee = employeeRepository.findOne(EMPLOYEE_ID);
	        
	        assertEquals(employee.getAddress(), Config.getProperty("UPDATE_E_ADDRESS"));
	        assertEquals(employee.getCity(), Config.getProperty("UPDATE_E_CITY"));
	        assertEquals(employee.getCountry(), Config.getProperty("UPDATE_E_COUNTRY"));
	        assertEquals(employee.getEmail(), Config.getProperty("UPDATE_E_EMAIL"));
	        assertEquals(employee.getIdCard(), Long.parseLong(Config.getProperty("UPDATE_E_IDCARD")));
	        assertEquals(employee.getMobileNumber(), Config.getProperty("UPDATE_E_MOBILENUMBER"));
	        assertEquals(employee.getName(), Config.getProperty("UPDATE_E_NAME"));
	        assertEquals(employee.getPhoneNumber(), Config.getProperty("UPDATE_E_PHONENUMBER"));
	        assertEquals(employee.getRegion(), Config.getProperty("UPDATE_E_REGION"));
	        assertEquals(employee.getZipCode(), Config.getProperty("UPDATE_E_ZIPCODE"));
	        assertEquals(employee.getPassword(), Config.getProperty("UPDATE_E_PASSWORD"));
	        assertEquals(employee.getUsername(), Config.getProperty("UPDATE_E_USERNAME"));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Der letzter Mitarbeiter wird gelöscht
     */
    @Test
    public void test09DeleteEmployee(){
    	try {
	    	
	        Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
	    	
	        employeeRepository.delete(employee);
	        
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!employeeRepository.exists(EMPLOYEE_ID));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Die Gehaltkategorie wird gelöscht
     */
    @Test
    public void test10DeleteSalaryCategory()
    {
    	try {
	        
	        SalaryCategory salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	    	
	        salaryCategoryRepository.delete(salaryCategory);

	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!salaryCategoryRepository.exists(SALARY_CATEGORY_ID));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Die Division wird gelöscht
     */
    @Test
    public void test11DeleteDivision()
    {
    	try {
	        Division division = divisionRepository.findOne(DIVISION_ID);
	    	
	        divisionRepository.delete(division);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!divisionRepository.exists(DIVISION_ID));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Die Benutzergruppe wird gelöscht
     */
    @Test
    public void test12DeleteUserGroup()
    {
    	try {
	        UserGroup userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	    	
	        userGroupRepository.delete(userGroup);
	        
	        //Suchen noch einmal und keine Aufzeichnung gefunden
	        assertTrue(!userGroupRepository.exists(USER_GROUP_ID));
    	} catch (Exception ex) {
	    	ex.printStackTrace();
	    	fail(ex.getMessage());
	    }
    }
    
    /**
     * Fehlerprovokation SalaryCategory
     * @throws Exception 
     */
    @Test(expected=LengthOverflowException.class)
    public void test13InsertSalaryCategoryError() throws Exception
    {
        try {
	        //Die Felder werden gefüllt
	        SalaryCategory salaryCategory = new SalaryCategory();
	        salaryCategory.setDescription(Config.getProperty("INSERT_SC_DESCRIPTION_F"));
	        salaryCategory.setSalaryFrom(Float.parseFloat(Config.getProperty("INSERT_SC_SALARYFROM_F")));
	        salaryCategory.setSalaryTo(Float.parseFloat(Config.getProperty("INSERT_SC_SALARYTO_F")));
	        
	        salaryCategory = salaryCategoryRepository.performNew(salaryCategory);
	        
	        //Die erstellt Gehaltkategorie wird geprüft
	        SALARY_CATEGORY_ID = salaryCategory.getIdSalaryCategory();
	        salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	        
	        assertEquals(salaryCategory.getSalaryFrom(), Float.parseFloat(Config.getProperty("INSERT_SC_SALARYFROM")));
	        assertEquals(salaryCategory.getSalaryTo(), Float.parseFloat(Config.getProperty("INSERT_SC_SALARYTO")));
	        assertEquals(salaryCategory.getDescription(), Config.getProperty("INSERT_SC_DESCRIPTION"));
	    } catch (Exception ex) {
	    	throw ex;
	    }
    }
    
    /**
     * Fehlerprovokation Division
     * @throws Exception 
     */
    @Test(expected=LengthOverflowException.class)
    public void test14InsertDivisionError() throws Exception
    {
        try {
	        //Die Felder werden gefüllt
	        Division division = new Division();
	        division.setDescription(Config.getProperty("INSERT_D_DESCRIPTION_F"));
	        division.setName(Config.getProperty("INSERT_D_NAME_F"));
	        
	        division = divisionRepository.performNew(division);
	        
	        //Die erstellt Division wird geprüft
	        DIVISION_ID = division.getIdDivision();
	        
	        division = divisionRepository.findOne(DIVISION_ID);
	        
	        assertEquals(division.getDescription(), Config.getProperty("INSERT_D_DESCRIPTION"));
	        assertEquals(division.getName(), Config.getProperty("INSERT_D_NAME"));
        } catch (Exception ex) {
	    	throw ex;
	    }
    }
    
    /**
     * Fehlerprovokation UserGroup
     * @throws Exception 
     */
    @Test(expected=LengthOverflowException.class)
    public void test15InsertUserGroupError() throws Exception
    {
        try {
	        //Die Felder werden gefüllt
	        UserGroup userGroup = new UserGroup();
	        userGroup.setDescription(Config.getProperty("INSERT_UG_DESCRIPTION_F"));
	        userGroup.setName(Config.getProperty("INSERT_UG_NAME_F"));
	        

	        //Hinzufügen erlaubnise
	        List<Permission> permissionList = new LinkedList<Permission>();
	        
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Config.getProperty("INSERT_UG_PERMISSION1_F"))));
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Config.getProperty("INSERT_UG_PERMISSION2_F"))));
	        permissionList.add(permissionRepository.findOne(Long.parseLong(Config.getProperty("INSERT_UG_PERMISSION3_F"))));
	        
	        for (int i = 0; i < permissionList.size(); i++) {
				userGroup.getPermissions().add(permissionList.get(i));
			}
	        
	        userGroup = userGroupRepository.performNew(userGroup);
	        
	        //Die erstellt Benutzergruppe wird geprüft
	        USER_GROUP_ID = userGroup.getIdUserGroup();
	        
	        userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	        
	        assertTrue(userGroup.getPermissions().containsAll(permissionList));
	        
	        assertEquals(userGroup.getDescription(), Config.getProperty("INSERT_UG_DESCRIPTION"));
	        assertEquals(userGroup.getName(), Config.getProperty("INSERT_UG_NAME"));
        } catch (Exception ex) {
	    	throw ex;
	    }
    }
    
    /**
     * Fehlerprovokation Employee
     * @throws Exception 
     */
    @Test(expected=LengthOverflowException.class)
    public void test16InsertEmployeeError() throws Exception{
    	try {
	    	//Die Gehaltkategorie wird geholt
	        SalaryCategory salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	    	
	        //Die Division wird geholt
	        Division division = divisionRepository.findOne(DIVISION_ID);
	        
	        //Die Benutzergruppe wird geholt
	        UserGroup userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	        
	        Employee employee = new Employee();
	        
	        //Die Felder werden gefüllt
	        employee.setAddress(Config.getProperty("INSERT_E_ADDRESS_F"));
	        employee.setCity(Config.getProperty("INSERT_E_CITY_F"));
	        employee.setCountry(Config.getProperty("INSERT_E_COUNTRY_F"));
	        employee.setDivision(division);
	        employee.setEmail(Config.getProperty("INSERT_E_EMAIL_F"));
	        employee.setIdCard(Long.parseLong(Config.getProperty("INSERT_E_IDCARD_F")));
	        employee.setMobileNumber(Config.getProperty("INSERT_E_MOBILENUMBER_F"));
	        employee.setName(Config.getProperty("INSERT_E_NAME_F"));
	        employee.setPhoneNumber(Config.getProperty("INSERT_E_PHONENUMBER_F"));
	        employee.setRegion(Config.getProperty("INSERT_E_REGION_F"));
	        employee.setSalaryCategory(salaryCategory);
	        employee.setUserGroup(userGroup);
	        employee.setZipCode(Config.getProperty("INSERT_E_ZIPCODE_F"));
	        
	      //Benutzername und Kenntwort werden erstellt
	        employee.setPassword(Config.getProperty("INSERT_E_PASSWORD_F"));
	        employee.setUsername(Config.getProperty("INSERT_E_USERNAME_F"));
	        
	        employee = employeeRepository.performNew(employee);
	        
	        //Der erstellter Mitarbeiter wird geprüft
	        EMPLOYEE_ID = employee.getIdEmployee();
	        
	        employee = employeeRepository.findOne(EMPLOYEE_ID);
	        
	        assertEquals(employee.getAddress(), Config.getProperty("INSERT_E_ADDRESS"));
	        assertEquals(employee.getCity(), Config.getProperty("INSERT_E_CITY"));
	        assertEquals(employee.getCountry(), Config.getProperty("INSERT_E_COUNTRY"));
	        assertEquals(employee.getDivision(), division);
	        assertEquals(employee.getEmail(), Config.getProperty("INSERT_E_EMAIL"));
	        assertEquals(employee.getIdCard(), Long.parseLong(Config.getProperty("INSERT_E_IDCARD")));
	        assertEquals(employee.getMobileNumber(), Config.getProperty("INSERT_E_MOBILENUMBER"));
	        assertEquals(employee.getName(), Config.getProperty("INSERT_E_NAME_F"));
	        assertEquals(employee.getPhoneNumber(), Config.getProperty("INSERT_E_PHONENUMBER"));
	        assertEquals(employee.getRegion(), Config.getProperty("INSERT_E_REGION"));
	        assertEquals(employee.getSalaryCategory(), salaryCategory);
	        assertEquals(employee.getUserGroup(), userGroup);
	        assertEquals(employee.getZipCode(), Config.getProperty("INSERT_E_ZIPCODE"));
	        assertEquals(employee.getPassword(), Config.getProperty("INSERT_E_PASSWORD"));
	        assertEquals(employee.getUsername(), Config.getProperty("INSERT_E_USERNAME"));
	        
    	} catch (Exception ex) {
	    	throw ex;
	    }
    }
}
