package org.haegerp.tools;

import java.security.MessageDigest;

/**
 * Diese Klasse macht die Übersetzung vom klaren Kennwort zu MD5
 * 
 * @author Fabio Codinha
 */
public class MD5Digest {

    /**
     * Übersetzung zu MD5
     * 
     * @param password Klares Kennwort
     * @return MD5 Kennwort
     */
    public static String toMD5(char[] password) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(String.valueOf(password).getBytes());

            byte[] mdbytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }

    /**
     * Übersetzung zu MD5
     * 
     * @param password Klares Kennwort
     * @return MD5 Kennwort
     */
    public static String toMD5(String password) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte[] mdbytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }
}
