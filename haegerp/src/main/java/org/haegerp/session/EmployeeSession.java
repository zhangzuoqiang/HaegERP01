package org.haegerp.session;

import org.haegerp.entity.Employee;

/**
 * Diese Klasse speichert, was das System braucht.<br/>
 * 
 * @author Wolf
 *
 */
public class EmployeeSession {
	
	//Welcher Mitarbeiter wird angemeldet
	private static Employee employee;
	
	/**
	 * 
	 * @return employee - Welcher Mitarbeiter wird angemeldet
	 */
	public static Employee getEmployee() {
		return employee;
	}

	/**
	 * 
	 * @param employee Welcher Mitarbeiter wird angemeldet
	 */
	public static void setEmployee(Employee employee) {
		EmployeeSession.employee = employee;
	}
	
	
}
