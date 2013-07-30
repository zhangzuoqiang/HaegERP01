package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

import org.haegerp.controller.ClientCategoryController;
import org.haegerp.controller.ClientController;
import org.haegerp.entity.Client;
import org.haegerp.entity.ClientCategory;
import org.haegerp.gui.clientdetails.ClientDetailsInterface;
import org.haegerp.gui.clientdetails.ClientEditView;
import org.haegerp.gui.clientdetails.ClientNewView;
import org.haegerp.gui.clientdetails.ClientShowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * 
 * @author Wolf
 */
public class ClientDetails extends javax.swing.JFrame {

	private static final long serialVersionUID = 2949647041784163844L;
	
	private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String REGEX_ZIPCODE = "^[A-Za-z]-[0-9]*5|[0-9]*5$";
	private static final String REGEX_PHONE = "^\\+[0-9]*|[0-9]*$";
	
	@Autowired
	private ClientController clientController;
	
	public ClientController getClientController() {
		return clientController;
	}

	@Autowired
	private ClientCategoryController clientCategoryController;
	
	public ClientCategoryController getClientCategoryController() {
		return clientCategoryController;
	}

	@Autowired
	private ClientCategoryManagement clientCategoryManagement;
	
	public ClientCategoryManagement getClientCategoryManagement() {
		return clientCategoryManagement;
	}
	
	@Autowired
	private ClientManagement clientManagement;

	public ClientManagement getClientManagement() {
		return clientManagement;
	}

	private ClientDetailsInterface clientDetailsView;
	
	private List<ClientCategory> categories;
	
	public List<ClientCategory> getCategories() {
		return categories;
	}

	private Client client;
	
    public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientDetails() {
    }
    
    public void setNewMode(){
    	clientDetailsView = new ClientNewView();
    	clientDetailsView.applyView(this);
    }
    
    public void setEditMode(){
    	clientDetailsView = new ClientEditView();
    	clientDetailsView.applyView(this);
    }
    
    public void setShowMode(){
    	clientDetailsView = new ClientShowView();
    	clientDetailsView.applyView(this);
    }
    
    private String checkFields() {
		String errors = "";
		
		//Required Fields
		if (txtTaxID.getText().equals(""))
			errors += "ID\n";
		if (txtName.getText().equals(""))
			errors += "Name\n";
		if (cbbCategory.getSelectedIndex() < 1)
			errors += "Client Category\n";
		if (txtAddress.getText().equals(""))
			errors += "Address\n";
		if (txtCity.getText().equals(""))
			errors += "City\n";
		if (txtZipCode.getText().equals(""))
			errors += "ZIP Code\n";
		if (txtCountry.getText().equals(""))
			errors += "Country\n";
		if (txtEmail.getText().equals(""))
			errors += "E-Mail\n";
		
		if (!errors.equals(""))
			errors = "The following fields have not been filled and are required:\n" + errors + "\nThose fields are required.";
		else {
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
			matcher = pattern.matcher(txtMobileNumber.getText());
			if (!txtMobileNumber.getText().equals("") && !matcher.matches())
				errors += "Mobile Number: +49888123456 or 0888123456\n";
			pattern = Pattern.compile(REGEX_PHONE);
			matcher = pattern.matcher(txtFaxNumber.getText());
			if (!txtFaxNumber.getText().equals("") && !matcher.matches())
				errors += "Fax Number: +49888123456 or 0888123456\n";
			
			if (!errors.equals(""))
				errors = "The following fields contain errors:\n" + errors;
		}
		
		
		return errors;
	}
    
    //Listeners
    protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
    	String errors = "";
    	if (!(clientDetailsView instanceof ClientShowView)) {
    		errors = checkFields();
    	}
    	if (errors.equals(""))
    		clientDetailsView.btnSaveEdit(this);
    	else
    		JOptionPane.showMessageDialog(this, errors, "", JOptionPane.ERROR_MESSAGE);
	}
    
	protected void btnCancel_ActionPerformed(ActionEvent e) {
		clientDetailsView.btnCancel(this);
	}
	
	protected void ClientDetails_DocusGained(FocusEvent e) {
		loadCbbCategory();
	}
    
    @PostConstruct
    private void setUp(){
    	lblTaxID = new javax.swing.JLabel();
    	lblName = new javax.swing.JLabel();
    	lblCategory = new javax.swing.JLabel();
    	lblAddress = new javax.swing.JLabel();
    	lblCity = new javax.swing.JLabel();
    	lblRegion = new javax.swing.JLabel();
    	lblZipCode = new javax.swing.JLabel();
    	lblCountry = new javax.swing.JLabel();
    	lblEmail = new javax.swing.JLabel();
    	lblPhoneNumber = new javax.swing.JLabel();
    	lblMobileNumber = new javax.swing.JLabel();
    	lblFaxNumber = new javax.swing.JLabel();
    	lblDescription = new javax.swing.JLabel();
        
        txtTaxID = new javax.swing.JFormattedTextField();
        txtTaxID.setDocument(new JTextFieldLimit(15));
        txtName = new javax.swing.JTextField();
        txtName.setDocument(new JTextFieldLimit(100));
        cbbCategory = new javax.swing.JComboBox<String>();
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
        txtMobileNumber = new javax.swing.JTextField();
        txtMobileNumber.setDocument(new JTextFieldLimit(20));
        txtFaxNumber = new javax.swing.JTextField();
        txtFaxNumber.setDocument(new JTextFieldLimit(20));
        
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtDescription.setDocument(new JTextFieldLimit(256));
        
        btnSaveEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        pnlLeft = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client Details");
        setMinimumSize(new java.awt.Dimension(405, 565));

        addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) { }
			
			public void focusGained(FocusEvent e) {
				ClientDetails_DocusGained(e);
			}
		});
        
        lblTaxID.setText("ID");
        lblName.setText("Name");
        lblCategory.setText("Category");
        lblAddress.setText("Address");
        lblCity.setText("City");
        lblRegion.setText("Region");
        lblZipCode.setText("ZIP-Code");
        lblCountry.setText("Country");
        lblEmail.setText("E-Mail");
        lblPhoneNumber.setText("Phone Number");
        lblMobileNumber.setText("Mobile Number");
        lblFaxNumber.setText("Fax Number");
        lblDescription.setText("Description");
        
        loadCbbCategory();

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);
        
        btnSaveEdit.setText("Save");
        btnSaveEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveEdit_ActionPerformed(e);
			}
		});

        btnCancel.setText("Cancel");
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
                    .addComponent(jScrollPane2)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDescription)
                            .addComponent(lblZipCode)
                            .addComponent(lblCity)
                            .addComponent(lblRegion)
                            .addComponent(lblName)
                            .addComponent(lblTaxID)
                            .addComponent(lblAddress)
                            .addComponent(lblCategory)
                            .addComponent(lblCountry)
                            .addComponent(lblEmail)
                            .addComponent(lblPhoneNumber)
                            .addComponent(lblMobileNumber)
                            .addComponent(lblFaxNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtCity, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbbCategory, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTaxID, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtZipCode, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCountry, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMobileNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFaxNumber, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addComponent(btnSaveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTaxID)
                    .addComponent(txtTaxID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategory)
                    .addComponent(cbbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCity)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegion)
                    .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblZipCode)
                    .addComponent(txtZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(3, 3, 3)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMobileNumber)
                    .addComponent(txtMobileNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFaxNumber)
                    .addComponent(txtFaxNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel)
                    .addComponent(btnSaveEdit))
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
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width-getWidth())/2, (dim.height-getHeight())/2);
    }
    
	public void loadCbbCategory() {
		Object idx = null;
		if (cbbCategory.getItemCount() > 0)
			idx = cbbCategory.getSelectedItem();
		
		cbbCategory.removeAllItems();
		categories = clientCategoryController.getAllCategories();
		
		cbbCategory.addItem("Choose One");
		for (ClientCategory clientCategory : categories) {
			cbbCategory.addItem(clientCategory.getName());
		}
		
		cbbCategory.setSelectedItem(idx);
	}

	public javax.swing.JButton btnCancel;
    public javax.swing.JButton btnSaveEdit;
    
    private javax.swing.JLabel lblTaxID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblRegion;
    private javax.swing.JLabel lblZipCode;
    private javax.swing.JLabel lblCountry;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblMobileNumber;
    private javax.swing.JLabel lblFaxNumber;
    private javax.swing.JLabel lblDescription;
    
    public javax.swing.JFormattedTextField txtTaxID;
    public javax.swing.JTextField txtName;
    public javax.swing.JComboBox<String> cbbCategory;
    public javax.swing.JTextField txtAddress;
    public javax.swing.JTextField txtCity;
    public javax.swing.JTextField txtRegion;
    public javax.swing.JTextField txtZipCode;
    public javax.swing.JTextField txtCountry;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtPhoneNumber;
    public javax.swing.JTextField txtMobileNumber;
    public javax.swing.JTextField txtFaxNumber;
    
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea txtDescription;
    
    public javax.swing.JPanel pnlLeft;
    public javax.swing.JPanel pnlCenter;
    public javax.swing.JPanel pnlRight;
}
