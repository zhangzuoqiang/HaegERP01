/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haegerp.jsf.context;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.haegerp.tools.C3P0Utility;

/**
 * Diese Klasse wird als Listener angemeldet, um die Verbindungen des C3P0s zu schlieﬂen
 * 
 * @author Wolf
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        C3P0Utility utility = new C3P0Utility();

        try {
            utility.closeConnections();
            java.sql.Driver myOracleDriver = DriverManager.getDriver("jdbc:oracle:thin:@127.0.0.1:1521:HAEGERP");
            DriverManager.deregisterDriver(myOracleDriver);
        } catch (SQLException ex) {
            Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
