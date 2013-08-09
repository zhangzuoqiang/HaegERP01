package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

import org.haegerp.controller.CompanyController;
import org.haegerp.controller.EmployeeController;
import org.haegerp.entity.Company;
import org.haegerp.gui.companydetails.CompanyDetailsInterface;
import org.haegerp.gui.companydetails.CompanyEditView;
import org.haegerp.gui.companydetails.CompanyShowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * 
 * @author Wolf
 */
public class CompanyDetails extends javax.swing.JFrame {

	private static final long serialVersionUID = 2949647041784163844L;
	
	private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String REGEX_ZIPCODE = "^[A-Za-z]-[0-9]{5}|[0-9]{5}$";
	private static final String REGEX_PHONE = "^\\+[0-9]*|[0-9]*$";
	
	@Autowired
	private CompanyController companyController;
	
	public CompanyController getCompanyController() {
		return companyController;
	}
	
	@Autowired
	private EmployeeController employeeController;
	
	public EmployeeController getEmployeeController() {
		return employeeController;
	}

	private CompanyDetailsInterface companyDetailsView;
	
	private Company company;
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyDetails() {
	}
    
    public void setEditMode(){
    	companyDetailsView = new CompanyEditView();
    	companyDetailsView.applyView(this);
    }
    
    public void setShowMode(){
    	companyDetailsView = new CompanyShowView();
    	companyDetailsView.applyView(this);
    }
    
    //Listeners
    protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
    	String errors = "";
    	if (!(companyDetailsView instanceof CompanyShowView)) {
    		errors = checkFields();
    	}
    	if (errors.equals(""))
    		companyDetailsView.btnSaveEdit(this);
    	else
    		JOptionPane.showMessageDialog(this, errors, "", JOptionPane.ERROR_MESSAGE);
	}
    
    private String checkFields() {
		String errors = "";
		
		Pattern pattern = Pattern.compile(REGEX_EMAIL);
		Matcher matcher = pattern.matcher(txtEmail.getText());
		if (!matcher.matches())
			errors += "E-Mail: client@domain.com\n";
		pattern = Pattern.compile(REGEX_ZIPCODE);
		matcher = pattern.matcher(txtZipCode.getText());
		if (!matcher.matches())
			errors += "Zip-Code: X-##### or #####\n";
		pattern = Pattern.compile(REGEX_PHONE);
		matcher = pattern.matcher(txtPhoneNumber.getText());
		if (!txtPhoneNumber.getText().equals("") && !matcher.matches())
			errors += "Phone Number: +49888123456 or 0888123456\n";
		pattern = Pattern.compile(REGEX_PHONE);
		matcher = pattern.matcher(txtFaxNumber.getText());
		if (!txtFaxNumber.getText().equals("") && !matcher.matches())
			errors += "Fax Number: +49888123456 or 0888123456\n";
		
		if (!errors.equals(""))
			errors = "The following fields contain errors:\n" + errors;
		
		return errors;
	}
    
	protected void btnCancel_ActionPerformed(ActionEvent e) {
		companyDetailsView.btnCancel(this);
	}
    
    @PostConstruct
    private void setUp(){
    	lblTaxID = new javax.swing.JLabel();
    	lblName = new javax.swing.JLabel();
    	lblOwner = new javax.swing.JLabel();
    	lblSector = new javax.swing.JLabel();
    	lblAddress = new javax.swing.JLabel();
    	lblCity = new javax.swing.JLabel();
    	lblRegion = new javax.swing.JLabel();
    	lblZipCode = new javax.swing.JLabel();
    	lblCountry = new javax.swing.JLabel();
    	lblEmail = new javax.swing.JLabel();
    	lblPhoneNumber = new javax.swing.JLabel();
    	lblFaxNumber = new javax.swing.JLabel();
    	lblLastModifiedUser = new javax.swing.JLabel();
    	lblLastModified = new javax.swing.JLabel();
    	lblSector = new javax.swing.JLabel();
    	
        txtTaxID = new javax.swing.JFormattedTextField();
        txtTaxID.setDocument(new JTextFieldLimit(15));
        txtName = new javax.swing.JTextField();
        txtName.setDocument(new JTextFieldLimit(50));
        txtOwner = new javax.swing.JTextField();
        txtOwner.setDocument(new JTextFieldLimit(100));
        txtSector = new javax.swing.JTextField();
        txtSector.setDocument(new JTextFieldLimit(100));
        txtAddress = new javax.swing.JTextField();
        txtAddress.setDocument(new JTextFieldLimit(100));
        txtCity = new javax.swing.JTextField();
        txtCity.setDocument(new JTextFieldLimit(50));
        txtRegion = new javax.swing.JTextField();
        txtRegion.setDocument(new JTextFieldLimit(50));
        txtZipCode = new javax.swing.JTextField();
        txtZipCode.setDocument(new JTextFieldLimit(12));
        txtCountry = new javax.swing.JTextField();
        txtCountry.setDocument(new JTextFieldLimit(50));
        txtEmail = new javax.swing.JTextField();
        txtEmail.setDocument(new JTextFieldLimit(50));
        txtPhoneNumber = new javax.swing.JTextField();
        txtPhoneNumber.setDocument(new JTextFieldLimit(20));
        txtFaxNumber = new javax.swing.JTextField();
        txtFaxNumber.setDocument(new JTextFieldLimit(20));
        txtLastModified = new javax.swing.JTextField();
        txtLastModifiedUser = new javax.swing.JTextField();
        
        btnSaveEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        pnlLeft = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Company Details");
        setMinimumSize(new java.awt.Dimension(405, 565));

        lblTaxID.setText("ID-Card");

        lblName.setText("Name");

        lblOwner.setText("Owner");

        lblAddress.setText("Address");

        lblCity.setText("City");

        lblRegion.setText("Region");

        lblZipCode.setText("Zip-Code");

        lblCountry.setText("Country");

        lblEmail.setText("E-Mail");

        lblPhoneNumber.setText("Phone Number");

        lblFaxNumber.setText("Fax Number");
        
        lblSector.setText("Sector");

        lblLastModified.setText("Last Modified");

        lblLastModifiedUser.setText("User");
        
        txtLastModified.setEditable(false);
        txtLastModifiedUser.setEditable(false);

        btnSaveEdit.setText("Save");
        btnSaveEdit.setMaximumSize(new java.awt.Dimension(75, 23));
        btnSaveEdit.setMinimumSize(new java.awt.Dimension(75, 23));
        btnSaveEdit.setPreferredSize(new java.awt.Dimension(75, 23));
        btnSaveEdit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btnSaveEdit_ActionPerformed(e);
			}
		});

        btnCancel.setText("Cancel");
        btnCancel.setMaximumSize(new java.awt.Dimension(75, 23));
        btnCancel.setMinimumSize(new java.awt.Dimension(75, 23));
        btnCancel.setPreferredSize(new java.awt.Dimension(75, 23));
        btnCancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btnCancel_ActionPerformed(e);
			}
		});
        
        txtTaxID.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        
        pnlCenter.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
                pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCenterLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblPhoneNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFaxNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSaveEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFaxNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(txtPhoneNumber))
                                .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlCenterLayout.createSequentialGroup()
                                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblTaxID)
                                                .addComponent(lblName))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnlCenterLayout.createSequentialGroup()
                                            .addComponent(lblOwner)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(pnlCenterLayout.createSequentialGroup()
                                        .addComponent(lblSector)
                                        .addGap(58, 58, 58)))
                                .addGroup(pnlCenterLayout.createSequentialGroup()
                                    .addComponent(lblAddress)
                                    .addGap(51, 51, 51)))
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtAddress)
                                .addComponent(txtSector)
                                .addComponent(txtOwner)
                                .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addComponent(txtTaxID, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCity)
                                .addComponent(lblRegion)
                                .addComponent(lblZipCode)
                                .addComponent(lblCountry)
                                .addComponent(lblEmail))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addComponent(txtZipCode)
                                .addComponent(txtCity)
                                .addComponent(txtRegion)
                                .addComponent(txtCountry, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblLastModified, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLastModifiedUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtLastModifiedUser)
                                .addComponent(txtLastModified, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap())
            );
            pnlCenterLayout.setVerticalGroup(
                pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCenterLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTaxID)
                        .addComponent(txtTaxID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblName))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOwner))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSector))
                    .addGap(18, 18, 18)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAddress)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblZipCode)
                        .addComponent(txtZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCity)
                        .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRegion)
                        .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCountry)
                        .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEmail)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPhoneNumber)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFaxNumber)
                        .addComponent(txtFaxNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLastModified)
                        .addComponent(txtLastModified, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLastModifiedUser)
                        .addComponent(txtLastModifiedUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSaveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
            .addComponent(pnlRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        
        company = companyController.getCompany();
        
        setShowMode();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width-getWidth())/2, (dim.height-getHeight())/2);
    }
    
    public javax.swing.JButton btnCancel;
    public javax.swing.JButton btnSaveEdit;
    
    public javax.swing.JLabel lblAddress;
    public javax.swing.JLabel lblCity;
    public javax.swing.JLabel lblCountry;
    public javax.swing.JLabel lblEmail;
    public javax.swing.JLabel lblFaxNumber;
    public javax.swing.JLabel lblLastModifiedUser;
    public javax.swing.JLabel lblTaxID;
    public javax.swing.JLabel lblLastModified;
    public javax.swing.JLabel lblName;
    public javax.swing.JLabel lblOwner;
    public javax.swing.JLabel lblPhoneNumber;
    public javax.swing.JLabel lblRegion;
    public javax.swing.JLabel lblSector;
    public javax.swing.JLabel lblZipCode;
    
    public javax.swing.JTextField txtAddress;
    public javax.swing.JTextField txtCity;
    public javax.swing.JTextField txtCountry;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtFaxNumber;
    public javax.swing.JFormattedTextField txtTaxID;
    public javax.swing.JTextField txtLastModified;
    public javax.swing.JTextField txtLastModifiedUser;
    public javax.swing.JTextField txtName;
    public javax.swing.JTextField txtOwner;
    public javax.swing.JTextField txtPhoneNumber;
    public javax.swing.JTextField txtRegion;
    public javax.swing.JTextField txtSector;
    public javax.swing.JTextField txtZipCode;
    
    public javax.swing.JPanel pnlLeft;
    public javax.swing.JPanel pnlCenter;
    public javax.swing.JPanel pnlRight;

}
