package org.haegerp.tools;

import java.security.MessageDigest;

public class MD5Digest {
	
	public static String toMD5(char[] password) {
		
		MessageDigest md;
		try {
		md = MessageDigest.getInstance("MD5");
        md.update(String.valueOf(password).getBytes());
        
        byte[] mdbytes = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
		
        return sb.toString();
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static String toMD5(String password) {
		
		MessageDigest md;
		try {
		md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        
        byte[] mdbytes = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
		
        return sb.toString();
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
