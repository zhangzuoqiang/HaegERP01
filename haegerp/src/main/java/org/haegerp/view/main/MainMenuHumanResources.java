package org.haegerp.view.main;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.haegerp.view.MainMenu;

/**
 * Diese Klasse zeigt die Personalabteilungsoptionen
 * 
 * @author Wolf
 *
 */
public class MainMenuHumanResources implements MainMenuInterface {

	public void applyView(MainMenu mainMenu) {
		mainMenu.pnlMenus.removeAll();
		javax.swing.GroupLayout pnlMenusLayout = new javax.swing.GroupLayout(mainMenu.pnlMenus);
		pnlMenusLayout.setHorizontalGroup(
	        	pnlMenusLayout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(pnlMenusLayout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(pnlMenusLayout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(mainMenu.btnArticlesMenu, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
	        				
	        				.addComponent(mainMenu.btnPartnersMenu, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
	        				
	        				.addComponent(mainMenu.btnHumanResourcesMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
	        				.addComponent(mainMenu.btnHumanResourcesEmployeeSubMenu, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(mainMenu.btnHumanResourcesDivisionSubMenu, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(mainMenu.btnHumanResourcesSalaryCategorySubMenu, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(mainMenu.btnHumanResourcesUserGroupSubMenu, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
	        				
	        				.addComponent(mainMenu.btnCompanyMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
	        				
	        				.addComponent(mainMenu.btnSupplierOrdersMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
	        				
	        				.addComponent(mainMenu.btnClientOrdersMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
	        				)
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
	        			.addComponent(mainMenu.btnHumanResourcesEmployeeSubMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        			.addComponent(mainMenu.btnHumanResourcesDivisionSubMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        			.addComponent(mainMenu.btnHumanResourcesSalaryCategorySubMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        			.addComponent(mainMenu.btnHumanResourcesUserGroupSubMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			
	        			.addComponent(mainMenu.btnCompanyMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			
	        			.addComponent(mainMenu.btnSupplierOrdersMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			
	        			.addComponent(mainMenu.btnClientOrdersMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap(217, Short.MAX_VALUE))
	        );
	        mainMenu.pnlMenus.setLayout(pnlMenusLayout);
	}

}
