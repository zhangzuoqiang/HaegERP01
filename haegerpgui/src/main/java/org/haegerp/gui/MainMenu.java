package org.haegerp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.haegerp.gui.main.MainMenuArticle;
import org.haegerp.gui.main.MainMenuClientOrders;
import org.haegerp.gui.main.MainMenuHumanResources;
import org.haegerp.gui.main.MainMenuInterface;
import org.haegerp.gui.main.MainMenuNormal;
import org.haegerp.gui.main.MainMenuPartners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Das ist das Hauptmenü von dem System
 * 
 * @author Wolf
 *
 */
@Component
public class MainMenu extends JFrame {

	private static final long serialVersionUID = 3267457954593344809L;
	
	private MainMenuInterface mainMenuView;
	
	@Autowired
	private ArticleManagement articleManagement;
	
	@Autowired
	private ArticleCategoryManagement articleCategoryManagement;
	
	@Autowired
	private ClientManagement clientManagement;
	
	@Autowired
	private ClientCategoryManagement clientCategoryManagement;
	
	@Autowired
	private SupplierManagement supplierManagement;
	
	@Autowired
	private DivisionManagement divisionManagement;
	
	@Autowired
	private SalaryCategoryManagement salaryCategoryManagement;
	
	//Article
	/**
	 * Menü der Artikel werden gezeigt oder ausgeblendet.
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnArticlesMenu_ActionPerformed(ActionEvent e) {
		if (mainMenuView instanceof MainMenuArticle)
			mainMenuView = new MainMenuNormal();
		else
			mainMenuView = new MainMenuArticle();
		
		mainMenuView.applyView(this);
	}
	
	/**
	 * Diese Methode wird die Artikelverwaltung öffnen
	 * 
	 * @param e 
	 * 		Default Parameter
	 */
	@Transactional
	private void btnArticlesSubMenu_ActionPerformed(ActionEvent e) {
		articleManagement.setVisible(true);
	}
	
	/**
	 * Diese Methode wird die Artikelkategorieverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnArticlesCategorySubMenu_ActionPerformed(ActionEvent e) {
		articleCategoryManagement.setVisible(true);
	}
	
	//Business Partners
	/**
	 * Menü der Geschäftspartners werden gezeigt oder ausgeblendet.
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnPartnersMenu_ActionPerformed(ActionEvent e) {
		if (mainMenuView instanceof MainMenuPartners)
			mainMenuView = new MainMenuNormal();
		else
			mainMenuView = new MainMenuPartners();
		
		mainMenuView.applyView(this);
	}
	
	/**
	 * Diese Methode wird die Kundenverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnPartnersClientSubMenu_ActionPerformed(ActionEvent e) {
		clientManagement.setVisible(true);
	}
	
	/**
	 * Diese Methode wird die Kundenkategorieverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnPartnersClientCategorySubMenu_ActionPerformed(ActionEvent e) {
		clientCategoryManagement.setVisible(true);
	}
	
	/**
	 * Diese Methode wird die Lieferantenverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnPartnersSupplierSubMenu_ActionPerformed(ActionEvent e) {
		supplierManagement.setVisible(true);
	}
	
	//Human Resources
	/**
	 * Menü der Personalabteilung werden gezeigt oder ausgeblendet.
	 *  
	 * @param e
	 * 		Default Parameter
	 */
	public void btnHumanResourcesMenu_ActionPerformed(ActionEvent e) {
		if (mainMenuView instanceof MainMenuHumanResources)
			mainMenuView = new MainMenuNormal();
		else
			mainMenuView = new MainMenuHumanResources();
		
		mainMenuView.applyView(this);
	}
	
	/**
	 * Diese Methode wird die Mitarbeiterverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnHumanResourcesEmployeeSubMenu_ActionPerformed(ActionEvent e) {
		// TODO 03.) Open Employee Management
	}
	
	/**
	 * Diese Methode wird die Divisionverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnHumanResourcesDivisionSubMenu_ActionPerformed(ActionEvent e) {
		divisionManagement.setVisible(true);
	}
	
	/**
	 * Diese Methode wird die Gehaltkategorieverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnHumanResourcesSalaryCategorySubMenu_ActionPerformed(ActionEvent e) {
		salaryCategoryManagement.setVisible(true);
	}
	
	/**
	 * Diese Methode wird die Gehaltkategorieverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnHumanResourcesUserGroupSubMenu_ActionPerformed(ActionEvent e) {
		// TODO 06.) Open UserGroup Management
	}
	
	//Company
	/**
	 * Diese Methode wird die Firmaverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnCompanyMenu_ActionPerformed(ActionEvent e) {
		// TODO 07.) Open Company Management
	}
	
	//Supplier Orders
	/**
	 * Diese Methode wird die Lieferantenbestellungenverwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnSupplierOrdersMenu_ActionPerformed(ActionEvent e) {
		// TODO 08.) Open SupplierOrder Management
	}
	
	//Client Orders
	/**
	 * Menü der Kundenbestellungen werden gezeigt oder ausgeblendet.
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnClientOrdersMenu_ActionPerformed(ActionEvent e) {
		if (mainMenuView instanceof MainMenuClientOrders)
			mainMenuView = new MainMenuNormal();
		else
			mainMenuView = new MainMenuClientOrders();
		
		mainMenuView.applyView(this);
	}
	
	/**
	 * Diese Methode wird die Kundenbestelungen Verwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnClientOrdersSubMenu_ActionPerformed(ActionEvent e) {
		// TODO 09.) Open Client Orders Management
	}
	
	/**
	 * Diese Methode wird die ausstehende Verwaltung öffnen
	 * 
	 * @param e
	 * 		Default Parameter
	 */
	public void btnClientOrdersOutstandingSubMenu_ActionPerformed(ActionEvent e) {
		// TODO 10.) Open Outstanding Management
	}
	
	public MainMenu() { }
	
	@PostConstruct
	public void setUp(){
		mainMenuView = new MainMenuNormal();
		
		setTitle("HaegERP");
		pnlForm = new JPanel();
        pnlMenus = new JPanel();
        
        //Articles
        btnArticlesMenu = new JButton();
        btnArticlesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnArticlesMenu_ActionPerformed(e);
			}
        });
        btnArticlesMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnArticlesSubMenu = new JButton();
        btnArticlesSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnArticlesSubMenu_ActionPerformed(e);
			}
		});
        btnArticlesSubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnArticlesCategorySubMenu = new JButton();
        btnArticlesCategorySubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnArticlesCategorySubMenu_ActionPerformed(e);
			}
		});
        btnArticlesCategorySubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        //BusinessPartners
        btnPartnersMenu = new JButton();
        btnPartnersMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPartnersMenu_ActionPerformed(e);
			}
		});
        btnPartnersMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnPartnersClientSubMenu = new JButton();
        btnPartnersClientSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPartnersClientSubMenu_ActionPerformed(e);
			}
		});
        btnPartnersClientSubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnPartnersClientCategorySubMenu = new JButton();
        btnPartnersClientCategorySubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPartnersClientCategorySubMenu_ActionPerformed(e);
			}
		});
        btnPartnersClientCategorySubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnPartnersSupplierSubMenu = new JButton();
        btnPartnersSupplierSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPartnersSupplierSubMenu_ActionPerformed(e);
			}
		});
        btnPartnersSupplierSubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        //HumanResources
        btnHumanResourcesMenu = new JButton();
        btnHumanResourcesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHumanResourcesMenu_ActionPerformed(e);
			}
		});
        btnHumanResourcesMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnHumanResourcesEmployeeSubMenu = new JButton();
        btnHumanResourcesEmployeeSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHumanResourcesEmployeeSubMenu_ActionPerformed(e);
			}
		});
        btnHumanResourcesEmployeeSubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnHumanResourcesDivisionSubMenu = new JButton();
        btnHumanResourcesDivisionSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHumanResourcesDivisionSubMenu_ActionPerformed(e);
			}
		});
        btnHumanResourcesDivisionSubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnHumanResourcesSalaryCategorySubMenu = new JButton();
        btnHumanResourcesSalaryCategorySubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHumanResourcesSalaryCategorySubMenu_ActionPerformed(e);
			}
		});
        btnHumanResourcesSalaryCategorySubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnHumanResourcesUserGroupSubMenu = new JButton();
        btnHumanResourcesUserGroupSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHumanResourcesUserGroupSubMenu_ActionPerformed(e);
			}
		});
        btnHumanResourcesUserGroupSubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        //Company
        btnCompanyMenu = new JButton();
        btnCompanyMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCompanyMenu_ActionPerformed(e);
			}
		});
        btnCompanyMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        //SupplierOrders
        btnSupplierOrdersMenu = new JButton();
        btnSupplierOrdersMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSupplierOrdersMenu_ActionPerformed(e);
			}
		});
        btnSupplierOrdersMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        //ClientOrders
        btnClientOrdersMenu = new JButton();
        btnClientOrdersMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClientOrdersMenu_ActionPerformed(e);
			}
		});
        btnClientOrdersMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnClientOrdersOutstandingSubMenu = new JButton();
        btnClientOrdersOutstandingSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClientOrdersOutstandingSubMenu_ActionPerformed(e);
			}
		});
        btnClientOrdersOutstandingSubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        btnClientOrdersSubMenu = new JButton();
        btnClientOrdersSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClientOrdersSubMenu_ActionPerformed(e);
			}
		});
        btnClientOrdersSubMenu.setHorizontalAlignment(SwingConstants.LEFT);
        
        pnlContent = new JPanel();
        lblTitle = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlMenus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMenus.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        
        //Articles
        btnArticlesMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnArticlesMenu.setIcon(null);
        btnArticlesMenu.setText(" Article Menu");
        btnArticlesMenu.setBorder(UIManager.getBorder("Button.border"));

        btnArticlesSubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnArticlesSubMenu.setIcon(null);
        btnArticlesSubMenu.setText(" Articles");
        btnArticlesSubMenu.setBorder(UIManager.getBorder("Button.border"));
        
        btnArticlesCategorySubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnArticlesCategorySubMenu.setIcon(null);
        btnArticlesCategorySubMenu.setText(" Articles Category");
        btnArticlesCategorySubMenu.setBorder(UIManager.getBorder("Button.border"));
        
        //BusinessPartners
        btnPartnersMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnPartnersMenu.setIcon(null);
        btnPartnersMenu.setText(" Business Partners Menu");
        btnPartnersMenu.setBorder(UIManager.getBorder("Button.border"));
        btnPartnersMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        btnPartnersClientSubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnPartnersClientSubMenu.setIcon(null);
        btnPartnersClientSubMenu.setText(" Clients");
        btnPartnersClientSubMenu.setBorder(UIManager.getBorder("Button.border"));
        btnPartnersClientSubMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        btnPartnersClientCategorySubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnPartnersClientCategorySubMenu.setIcon(null);
        btnPartnersClientCategorySubMenu.setText(" Client Category");
        btnPartnersClientCategorySubMenu.setBorder(UIManager.getBorder("Button.border"));
        btnPartnersClientCategorySubMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        btnPartnersSupplierSubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnPartnersSupplierSubMenu.setIcon(null);
        btnPartnersSupplierSubMenu.setText(" Suppliers");
        btnPartnersSupplierSubMenu.setBorder(UIManager.getBorder("Button.border"));
        btnPartnersSupplierSubMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        //HumanResources
        btnHumanResourcesMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnHumanResourcesMenu.setIcon(null);
        btnHumanResourcesMenu.setText(" Human Resources Menu");
        btnHumanResourcesMenu.setBorder(UIManager.getBorder("Button.border"));
        btnHumanResourcesMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        btnHumanResourcesEmployeeSubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnHumanResourcesEmployeeSubMenu.setIcon(null);
        btnHumanResourcesEmployeeSubMenu.setText(" Employee");
        btnHumanResourcesEmployeeSubMenu.setBorder(UIManager.getBorder("Button.border"));
        btnHumanResourcesEmployeeSubMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        btnHumanResourcesDivisionSubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnHumanResourcesDivisionSubMenu.setIcon(null);
        btnHumanResourcesDivisionSubMenu.setText(" Division");
        btnHumanResourcesDivisionSubMenu.setBorder(UIManager.getBorder("Button.border"));
        btnHumanResourcesDivisionSubMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        btnHumanResourcesSalaryCategorySubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnHumanResourcesSalaryCategorySubMenu.setIcon(null);
        btnHumanResourcesSalaryCategorySubMenu.setText(" Salary Groups");
        btnHumanResourcesSalaryCategorySubMenu.setBorder(UIManager.getBorder("Button.border"));
        btnHumanResourcesSalaryCategorySubMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        btnHumanResourcesUserGroupSubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnHumanResourcesUserGroupSubMenu.setIcon(null);
        btnHumanResourcesUserGroupSubMenu.setText(" User Groups");
        btnHumanResourcesUserGroupSubMenu.setBorder(UIManager.getBorder("Button.border"));
        btnHumanResourcesUserGroupSubMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        //Company
        btnCompanyMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnCompanyMenu.setIcon(null);
        btnCompanyMenu.setText(" Company Menu");
        btnCompanyMenu.setBorder(UIManager.getBorder("Button.border"));
        btnCompanyMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        //SupplierOrders
        btnSupplierOrdersMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnSupplierOrdersMenu.setIcon(null);
        btnSupplierOrdersMenu.setText(" Supplier's Orders Menu");
        btnSupplierOrdersMenu.setBorder(UIManager.getBorder("Button.border"));
        btnSupplierOrdersMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        //ClientOrders
        btnClientOrdersMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnClientOrdersMenu.setIcon(null);
        btnClientOrdersMenu.setText(" Client's Orders Menu");
        btnClientOrdersMenu.setBorder(UIManager.getBorder("Button.border"));
        btnClientOrdersMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnClientOrdersSubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnClientOrdersSubMenu.setIcon(null);
        btnClientOrdersSubMenu.setText(" Client Orders");
        btnClientOrdersSubMenu.setBorder(UIManager.getBorder("Button.border"));
        btnClientOrdersSubMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        btnClientOrdersOutstandingSubMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        btnClientOrdersOutstandingSubMenu.setIcon(null);
        btnClientOrdersOutstandingSubMenu.setText(" Outstanding");
        btnClientOrdersOutstandingSubMenu.setBorder(UIManager.getBorder("Button.border"));
        btnClientOrdersOutstandingSubMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        mainMenuView.applyView(this);
        
        pnlContent.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlContent.setFont(new java.awt.Font("Trebuchet MS", 0, 11));

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMenus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        GroupLayout gl_pnlContent = new GroupLayout(pnlContent);
        gl_pnlContent.setHorizontalGroup(
        	gl_pnlContent.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 508, Short.MAX_VALUE)
        );
        gl_pnlContent.setVerticalGroup(
        	gl_pnlContent.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 501, Short.MAX_VALUE)
        );
        pnlContent.setLayout(gl_pnlContent);

        lblTitle.setFont(new java.awt.Font("Calibri", 1, 36));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("HaegERP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
	
	public JPanel pnlForm;
	public JLabel lblTitle;
	
	public JPanel pnlMenus;
	
	public JButton btnArticlesMenu;
	public JButton btnArticlesSubMenu;
	public JButton btnArticlesCategorySubMenu;
	
	public JButton btnPartnersMenu;
	public JButton btnPartnersClientSubMenu;
	public JButton btnPartnersClientCategorySubMenu;
	public JButton btnPartnersSupplierSubMenu;
	
	public JButton btnHumanResourcesMenu;
	public JButton btnHumanResourcesEmployeeSubMenu;
	public JButton btnHumanResourcesDivisionSubMenu;
	public JButton btnHumanResourcesSalaryCategorySubMenu;
	public JButton btnHumanResourcesUserGroupSubMenu;
	
	public JButton btnCompanyMenu;
    
	public JButton btnSupplierOrdersMenu;
    
	public JButton btnClientOrdersMenu;
	public JButton btnClientOrdersSubMenu;
	public JButton btnClientOrdersOutstandingSubMenu;
    
	public JPanel pnlContent;
}
