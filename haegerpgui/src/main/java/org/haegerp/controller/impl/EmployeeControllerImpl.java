package org.haegerp.controller.impl;

import java.security.MessageDigest;

import org.haegerp.controller.EmployeeController;
import org.haegerp.entity.Employee;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.session.EmployeeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeControllerImpl implements EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public boolean isLoginCorrect(String username, String password) {
		
		MessageDigest md;
		try {
		md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        
        byte[] mdbytes = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
		
        String passwordMD5 = sb.toString();
		
        Employee employee = employeeRepository.login(username, passwordMD5);
        
        if (employee != null) {
        	EmployeeSession.setEmployee(employee);
        	return true;
        } else {
        	return false;
        }
        
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
