package org.haegerp.launcher;

import java.awt.EventQueue;

import org.haegerp.gui.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Launcher of the Swing Application
 *
 * @author Fabio Codinha
 */
@Component
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-data-gui.xml");
        Main app = (Main) context.getBean("launcher");
        app.start(args);
    }
    @Autowired
    private Login login;

    private void start(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
