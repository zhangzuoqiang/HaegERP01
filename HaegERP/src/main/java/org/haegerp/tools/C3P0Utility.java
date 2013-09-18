/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haegerp.tools;

import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.PooledDataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Diese Klasse versucht, die Pools von C3P0 zu schließen
 * 
 * @author Fabio Codinha
 */
public class C3P0Utility {
    
    /**
     * Die Pools werden geschlossen
     */
    public void closeConnections(){
        for(Object o : C3P0Registry.getPooledDataSources()) {
            try {
                ((PooledDataSource)o).hardReset();
                ((PooledDataSource)o).close();
            } catch(Exception ex) {
                Logger.getGlobal().log(Level.SEVERE, "Failed to close PooledDataSource", ex);
            }
        }
    }
}
