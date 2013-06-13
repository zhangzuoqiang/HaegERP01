package org.haegerp.entity;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.haegerp.entity.repository.DivisionRepository;
import org.haegerp.entity.repository.EmployeeRepository;
import org.haegerp.entity.repository.EmployeeUserRepository;
import org.haegerp.entity.repository.PermissionRepository;
import org.haegerp.entity.repository.SalaryCategoryRepository;
import org.haegerp.entity.repository.UserGroupRepository;
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
	
    Properties properties = new Properties();
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeUserRepository employeeUserRepository;
    @Autowired
    private SalaryCategoryRepository salaryCategoryRepository;
    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    
    private static int EMPLOYEE_ID;
    
    private static int SALARY_CATEGORY_ID;
    
    private static int DIVISION_ID;
    
    private static int USER_GROUP_ID;
    
    /**
     * Eine Gehaltkategorie wird in die Datenbank erstellt
     */
    @Test
    public void test01InsertSalaryCategory()
    {
        try {
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        //Die Felder werden gefüllt
	        SalaryCategory salaryCategory = new SalaryCategory();
	        salaryCategory.setDescription(properties.getProperty("INSERT_SC_DESCRIPTION"));
	        salaryCategory.setSalaryFrom(Float.parseFloat(properties.getProperty("INSERT_SC_SALARYFROM")));
	        salaryCategory.setSalaryTo(Float.parseFloat(properties.getProperty("INSERT_SC_SALARYTO")));
	        
	        salaryCategory = salaryCategoryRepository.save(salaryCategory);
	        
	        //Die erstellt Gehaltkategorie wird geprüft
	        SALARY_CATEGORY_ID = salaryCategory.getIdSalaryCategory();
	        salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	        
	        assertEquals(salaryCategory.getSalaryFrom(), Float.parseFloat(properties.getProperty("INSERT_SC_SALARYFROM")));
	        assertEquals(salaryCategory.getSalaryTo(), Float.parseFloat(properties.getProperty("INSERT_SC_SALARYTO")));
	        assertEquals(salaryCategory.getDescription(), properties.getProperty("INSERT_SC_DESCRIPTION"));
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
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        SalaryCategory salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	
	        //Die Felder werden gefüllt
	        salaryCategory.setDescription(properties.getProperty("UPDATE_SC_DESCRIPTION"));
	        salaryCategory.setSalaryFrom(Float.parseFloat(properties.getProperty("UPDATE_SC_SALARYFROM")));
	        salaryCategory.setSalaryTo(Float.parseFloat(properties.getProperty("UPDATE_SC_SALARYTO")));
	        
	        salaryCategory = salaryCategoryRepository.save(salaryCategory);
	
	        //Die geändert Gehaltkategorie wird geprüft
	        salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	        
	        assertEquals(salaryCategory.getSalaryFrom(), Float.parseFloat(properties.getProperty("UPDATE_SC_SALARYFROM")));
	        assertEquals(salaryCategory.getSalaryTo(), Float.parseFloat(properties.getProperty("UPDATE_SC_SALARYTO")));
	        assertEquals(salaryCategory.getDescription(), properties.getProperty("UPDATE_SC_DESCRIPTION"));
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
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        //Die Felder werden gefüllt
	        Division division = new Division();
	        division.setDescription(properties.getProperty("INSERT_D_DESCRIPTION"));
	        division.setName(properties.getProperty("INSERT_D_NAME"));
	        
	        division = divisionRepository.save(division);
	        
	        //Die erstellt Division wird geprüft
	        DIVISION_ID = division.getIdDivision();
	        
	        division = divisionRepository.findOne(DIVISION_ID);
	        
	        assertEquals(division.getDescription(), properties.getProperty("INSERT_D_DESCRIPTION"));
	        assertEquals(division.getName(), properties.getProperty("INSERT_D_NAME"));
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
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        Division division = divisionRepository.findOne(DIVISION_ID);
	
	        //Die Felder werden gefüllt
	        division.setDescription(properties.getProperty("UPDATE_D_DESCRIPTION"));
	        division.setName(properties.getProperty("UPDATE_D_NAME"));
	        
	        division = divisionRepository.save(division);
	        
	        //Die geändert Division wird geprüft
	        division = divisionRepository.findOne(DIVISION_ID);
	        
	        assertEquals(division.getDescription(), properties.getProperty("UPDATE_D_DESCRIPTION"));
	        assertEquals(division.getName(), properties.getProperty("UPDATE_D_NAME"));
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
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        //Die Felder werden gefüllt
	        UserGroup userGroup = new UserGroup();
	        userGroup.setDescription(properties.getProperty("INSERT_UG_DESCRIPTION"));
	        userGroup.setName(properties.getProperty("INSERT_UG_NAME"));
	        

	        //Hinzufügen erlaubnise
	        List<Permission> permissionList = new LinkedList<Permission>();
	        
	        permissionList.add(permissionRepository.findOne(Integer.parseInt(properties.getProperty("INSERT_UG_PERMISSION1"))));
	        permissionList.add(permissionRepository.findOne(Integer.parseInt(properties.getProperty("INSERT_UG_PERMISSION2"))));
	        permissionList.add(permissionRepository.findOne(Integer.parseInt(properties.getProperty("INSERT_UG_PERMISSION3"))));
	        
	        for (int i = 0; i < permissionList.size(); i++) {
				userGroup.getPermissions().add(permissionList.get(i));
			}
	        
	        userGroup = userGroupRepository.save(userGroup);
	        
	        //Die erstellt Benutzergruppe wird geprüft
	        USER_GROUP_ID = userGroup.getIdUserGroup();
	        
	        userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	        
	        assertTrue(userGroup.getPermissions().containsAll(permissionList));
	        
	        assertEquals(userGroup.getDescription(), properties.getProperty("INSERT_UG_DESCRIPTION"));
	        assertEquals(userGroup.getName(), properties.getProperty("INSERT_UG_NAME"));
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
        	properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        UserGroup userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	    	
	        //Die Felder werden gefüllt
	        userGroup.setDescription(properties.getProperty("UPDATE_UG_DESCRIPTION"));
	        userGroup.setName(properties.getProperty("UPDATE_UG_NAME"));
	        userGroup.setPermissions(new HashSet<Permission>(0));
	        
	        //Hinzufügen erlaubnise
	        List<Permission> permissionList = new LinkedList<Permission>();
	        
	        permissionList.add(permissionRepository.findOne(Integer.parseInt(properties.getProperty("UPDATE_UG_PERMISSION1"))));
	        permissionList.add(permissionRepository.findOne(Integer.parseInt(properties.getProperty("UPDATE_UG_PERMISSION2"))));
	        permissionList.add(permissionRepository.findOne(Integer.parseInt(properties.getProperty("UPDATE_UG_PERMISSION3"))));
	        
	        for (int i = 0; i < permissionList.size(); i++) {
				userGroup.getPermissions().add(permissionList.get(i));
			}
	        
	        userGroup = userGroupRepository.save(userGroup);
	        
	        //Die geändert Benutzergruppe wird geprüft
	        userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	        
	        assertTrue(userGroup.getPermissions().containsAll(permissionList));
	        
	        assertEquals(userGroup.getDescription(), properties.getProperty("UPDATE_UG_DESCRIPTION"));
	        assertEquals(userGroup.getName(), properties.getProperty("UPDATE_UG_NAME"));
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
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	    	//Die Gehaltkategorie wird geholt
	        SalaryCategory salaryCategory = salaryCategoryRepository.findOne(SALARY_CATEGORY_ID);
	    	
	        //Die Division wird geholt
	        Division division = divisionRepository.findOne(DIVISION_ID);
	        
	        //Die Benutzergruppe wird geholt
	        UserGroup userGroup = userGroupRepository.findOne(USER_GROUP_ID);
	        
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
	        
	        employee = employeeRepository.save(employee);
	        
	        //Benutzername und Kenntwort werden gefüllt
	        EmployeeUser employeeUser = new EmployeeUser();
	        employeeUser.setPassword(properties.getProperty("INSERT_E_PASSWORD"));
	        employeeUser.setUsername(properties.getProperty("INSERT_E_USERNAME"));
	        
	        employeeUser = employeeUserRepository.save(employeeUser);
	        
	        //Der erstellter Mitarbeiter wird geprüft
	        EMPLOYEE_ID = employee.getIdEmployee();
	        
	        employee = employeeRepository.findOne(EMPLOYEE_ID);
	        
	        employeeUser = employeeUserRepository.findOne(EMPLOYEE_ID);
	        
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
	    }
    }
    
    /**
     * Der letzter Mitarbeiter wird geändert
     */
    @Test
    public void test08UpdateEmployee(){
    	try {
    		properties.load(new FileInputStream("./src/test/java/org/haegerp/entity/config.properties"));
	        Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
	        
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
	        
	        employee = employeeRepository.save(employee);
	        
	        //Benutzername und Kenntwort werden geändert
	        EmployeeUser employeeUser = employee.getEmployeeUser();
	        employeeUser.setPassword(properties.getProperty("UPDATE_E_PASSWORD"));
	        employeeUser.setUsername(properties.getProperty("UPDATE_E_USERNAME"));
	        
	        employeeUser = employeeUserRepository.save(employeeUser);
	        
	        //Der erstellter Mitarbeiter wird geprüft
	        employee = employeeRepository.findOne(EMPLOYEE_ID);
	        
	        employeeUser = employeeUserRepository.findOne(EMPLOYEE_ID);
	        
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
	    }
    }
    
    /**
     * Der letzter Mitarbeiter wird gelöscht
     */
    @Test
    public void test09DeleteEmployee(){
    	try {
	    	
	        Employee employee = employeeRepository.findOne(EMPLOYEE_ID);
	    	
	        EmployeeUser employeeUser = employeeUserRepository.findOne(EMPLOYEE_ID);;
	        
	        employeeUserRepository.delete(employeeUser);
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
}
