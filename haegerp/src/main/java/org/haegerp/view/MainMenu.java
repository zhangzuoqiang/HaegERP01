package org.haegerp.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 3267457954593344809L;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("HaegERP");
		pnlForm = new JPanel();
        pnlMenus = new JPanel();
        lblHumanResourcesMenu = new JLabel();
        lblArticlesMenu = new JLabel();
        lblPartnersMenu = new JLabel();
        lblCompanyMenu = new JLabel();
        lblSupplierOrdersMenu = new JLabel();
        lblClientOrdersMenu = new JLabel();
        lblOutstandingMenu = new JLabel();
        pnlContent = new JPanel();
        lblTitle = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlMenus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMenus.setFont(new java.awt.Font("Trebuchet MS", 0, 11));

        lblHumanResourcesMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        lblHumanResourcesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/haegerp/resources/images/open.png")));
        lblHumanResourcesMenu.setText(" Human Resources");
        lblHumanResourcesMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHumanResourcesMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblArticlesMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        lblArticlesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/haegerp/resources/images/open.png")));
        lblArticlesMenu.setText(" Articles");
        lblArticlesMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPartnersMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        lblPartnersMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/haegerp/resources/images/open.png")));
        lblPartnersMenu.setText(" Business Partners");
        lblPartnersMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblPartnersMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblCompanyMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        lblCompanyMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/haegerp/resources/images/open.png")));
        lblCompanyMenu.setText(" Company");
        lblCompanyMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblCompanyMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblSupplierOrdersMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        lblSupplierOrdersMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/haegerp/resources/images/open.png")));
        lblSupplierOrdersMenu.setText(" Supplier's Orders");
        lblSupplierOrdersMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblSupplierOrdersMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblClientOrdersMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        lblClientOrdersMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/haegerp/resources/images/open.png")));
        lblClientOrdersMenu.setText(" Client's Orders");
        lblClientOrdersMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblClientOrdersMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblOutstandingMenu.setFont(new java.awt.Font("Trebuchet MS", 0, 11));
        lblOutstandingMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/haegerp/resources/images/open.png")));
        lblOutstandingMenu.setText(" Outstanding");
        lblOutstandingMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblOutstandingMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout pnlMenusLayout = new javax.swing.GroupLayout(pnlMenus);
        pnlMenus.setLayout(pnlMenusLayout);
        pnlMenusLayout.setHorizontalGroup(
            pnlMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHumanResourcesMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(lblPartnersMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(lblCompanyMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(lblSupplierOrdersMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(lblClientOrdersMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(lblOutstandingMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(lblArticlesMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMenusLayout.setVerticalGroup(
            pnlMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblArticlesMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPartnersMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHumanResourcesMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCompanyMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSupplierOrdersMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClientOrdersMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOutstandingMenu)
                .addContainerGap(286, Short.MAX_VALUE))
        );

        pnlContent.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlContent.setFont(new java.awt.Font("Trebuchet MS", 0, 11));

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

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
	}
	
	private JLabel lblArticlesMenu;
    private JLabel lblClientOrdersMenu;
    private JLabel lblCompanyMenu;
    private JLabel lblHumanResourcesMenu;
    private JLabel lblOutstandingMenu;
    private JLabel lblPartnersMenu;
    private JLabel lblSupplierOrdersMenu;
    private JLabel lblTitle;
    private JPanel pnlContent;
    private JPanel pnlForm;
    private JPanel pnlMenus;
}
