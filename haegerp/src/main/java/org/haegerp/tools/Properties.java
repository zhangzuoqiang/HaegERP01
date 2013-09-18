package org.haegerp.tools;

import java.io.FileInputStream;

/**
 * Diese Klasse lädt die 'Properties' Datei und holt das 'Property' mit dem
 * passenden Wert.
 *
 * @author Fabio Codinha
 */
public class Properties {

    private static java.util.Properties properties = new java.util.Properties();

    /**
     *
     * @return True - Wenn die Datei richtig geladen wird; False - Sonst
     */
    public static boolean loadProperties() {
        try {
            properties.load(new FileInputStream("./config.properties"));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return false;
        }
    }

    /**
     * Holt den Wert von der 'Properties' Datei mit dem folgenden Schlüssel
     * @param property Schlüssel
     * @return Wert
     */
    public static String getProperty(String property) {
        return properties.getProperty(property);
    }
}
