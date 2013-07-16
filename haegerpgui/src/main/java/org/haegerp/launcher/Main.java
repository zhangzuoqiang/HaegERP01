package org.haegerp.launcher;

import java.awt.EventQueue;

import org.haegerp.gui.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-data.xml");
		Main app = (Main) context.getBean("launcher");
		app.start(args);
	}
	
	@Autowired
	private MainMenu mainMenu;

	private void start(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu.setUp();
					mainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
