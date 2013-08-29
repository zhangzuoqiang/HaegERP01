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
 *
 * @author Wolf
 */
public class MyContextListener implements ServletContextListener{
    
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
        
        /*Context initContext;
        DataSource dataSource;
        try {
            initContext = new InitialContext();
            dataSource = (DataSource)initContext.lookup("jdbc/dataSource");
        } catch (NamingException ex) {
            Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            java.sql.Driver myOracleDriver = DriverManager.getDriver("jdbc:oracle:thin:@127.0.0.1:1521:HAEGERP");
            DriverManager.deregisterDriver(myOracleDriver);
        } catch (SQLException ex) {
            Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataSource = null;*/
    }
    
}
