package org.haegerp.gui.main;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.haegerp.gui.MainMenu;

/**
 * Diese Klasse zeigt nur die Hauptoptionen
 * 
 * @author Wolf
 *
 */
public class MainMenuNormal implements MainMenuInterface {

	public void applyView(MainMenu mainMenu) {
		//Menus Panel (Normal)
		mainMenu.pnlMenus.removeAll();
        javax.swing.GroupLayout pnlMenusLayout = new javax.swing.GroupLayout(mainMenu.pnlMenus);
        pnlMenusLayout.setHorizontalGroup(
        	pnlMenusLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlMenusLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(pnlMenusLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(mainMenu.btnArticlesMenu, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        				.addComponent(mainMenu.btnHumanResourcesMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        				.addComponent(mainMenu.btnPartnersMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        				.addComponent(mainMenu.btnCompanyMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        				.addComponent(mainMenu.btnSupplierOrdersMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        				.addComponent(mainMenu.btnClientOrdersMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
        			.addContainerGap())
        );
        pnlMenusLayout.setVerticalGroup(
        	pnlMenusLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlMenusLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(mainMenu.btnArticlesMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(mainMenu.btnPartnersMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(mainMenu.btnHumanResourcesMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(mainMenu.btnCompanyMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(mainMenu.btnSupplierOrdersMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(mainMenu.btnClientOrdersMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(317, Short.MAX_VALUE))
        );
        mainMenu.pnlMenus.setLayout(pnlMenusLayout);
	}

}
