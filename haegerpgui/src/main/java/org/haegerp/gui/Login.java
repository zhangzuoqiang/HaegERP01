package org.haegerp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.haegerp.controller.EmployeeController;
import org.haegerp.entity.Employee;
import org.haegerp.entity.Permission;
import org.haegerp.session.EmployeeSession;
import org.haegerp.tools.MD5Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Login extends JFrame {

    @Autowired
    private MainMenu mainMenu;
    @Autowired
    private EmployeeController employeeController;
    private static final long serialVersionUID = -2223474247110028871L;

    public Login() {
    }

    //Listeners
    protected void btnLogin_ActionPerformed(ActionEvent e) {

        String passwordMD5 = MD5Digest.toMD5(txtPassword.getPassword());
        Employee employee = employeeController.isLoginCorrect(txtUser.getText(), passwordMD5);

        if (employee != null) {

            EmployeeSession.setEmployee(employee);

            mainMenu.btnArticlesMenu.setVisible(false);
            mainMenu.btnPartnersMenu.setVisible(false);
            mainMenu.btnHumanResourcesMenu.setVisible(false);
            mainMenu.btnCompanyMenu.setVisible(false);
            mainMenu.btnSupplierOrdersMenu.setVisible(false);
            mainMenu.btnClientOrdersMenu.setVisible(false);

            for (Permission permission : employee.getUserGroup().getPermissions()) {
                if (permission.getIdPermission() == 1L) {
                    mainMenu.btnArticlesMenu.setVisible(true);
                }
                if (permission.getIdPermission() == 2L) {
                    mainMenu.btnPartnersMenu.setVisible(true);
                }
                if (permission.getIdPermission() == 3L) {
                    mainMenu.btnHumanResourcesMenu.setVisible(true);
                }
                if (permission.getIdPermission() == 4L) {
                    mainMenu.btnCompanyMenu.setVisible(true);
                }
                if (permission.getIdPermission() == 5L) {
                    mainMenu.btnSupplierOrdersMenu.setVisible(true);
                }
                if (permission.getIdPermission() == 6L) {
                    mainMenu.btnClientOrdersMenu.setVisible(true);
                }
            }

            this.setVisible(false);
            mainMenu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "The credentials inserted are wrong.\nPlease try again.", "Wrong Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    @PostConstruct
    public void setUp() {
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblLogo = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HaegERP Login");
        setMaximumSize(new java.awt.Dimension(253, 223));
        setMinimumSize(new java.awt.Dimension(253, 223));
        setResizable(false);

        lblUsername.setText("Username");
        txtUser.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    btnLogin_ActionPerformed(null);
                }
            }
        });

        lblPassword.setText("Password");
        txtPassword.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    btnLogin_ActionPerformed(null);
                }
            }
        });

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Header.png")));

        btnLogin.setText("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnLogin_ActionPerformed(e);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                .addGroup(layout.createSequentialGroup()
                .addComponent(lblPassword)
                .addGap(12, 12, 12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtUser)
                .addComponent(txtPassword)))
                .addGroup(layout.createSequentialGroup()
                .addComponent(lblLogo)
                .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblUsername)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPassword)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }
    private JButton btnLogin;
    private JLabel lblLogo;
    private JLabel lblPassword;
    private JLabel lblUsername;
    private JPasswordField txtPassword;
    private JTextField txtUser;
}
