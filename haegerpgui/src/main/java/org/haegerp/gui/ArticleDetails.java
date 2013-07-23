package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.text.NumberFormatter;

import org.haegerp.controller.ArticleCategoryController;
import org.haegerp.controller.ArticleController;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.gui.articledetails.ArticleDetailsInterface;
import org.haegerp.gui.articledetails.ArticleEditView;
import org.haegerp.gui.articledetails.ArticleNewView;
import org.haegerp.gui.articledetails.ArticleShowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * 
 * @author Wolf
 */
public class ArticleDetails extends javax.swing.JFrame {

	private static final long serialVersionUID = 2949647041784163844L;
	
	@Autowired
	private ArticleController articleController;
	
	public ArticleController getArticleController() {
		return articleController;
	}

	@Autowired
	private ArticleCategoryController articleCategoryController;
	
	public ArticleCategoryController getArticleCategoryController() {
		return articleCategoryController;
	}

	private ArticleDetailsInterface articleDetailsView;
	
	private List<ArticleCategory> categories;
	
	public List<ArticleCategory> getCategories() {
		return categories;
	}

	private Article article;
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
    public ArticleDetails() {
    }
    
    public void setNewMode(){
    	articleDetailsView = new ArticleNewView();
    	articleDetailsView.applyView(this);
    }
    
    public void setEditMode(){
    	articleDetailsView = new ArticleEditView();
    	articleDetailsView.applyView(this);
    }
    
    public void setShowMode(){
    	articleDetailsView = new ArticleShowView();
    	articleDetailsView.applyView(this);
    }
    
    //Listeners
    protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
		articleDetailsView.btnSaveEdit(this);
	}
    
	protected void btnCancel_ActionPerformed(ActionEvent e) {
		articleDetailsView.btnCancel(this);
	}
    
    @PostConstruct
    private void setUp(){
        lblEan = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        lblPriceVat = new javax.swing.JLabel();
        lblPriceGross = new javax.swing.JLabel();
        lblPriceSupplier = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblSizeH = new javax.swing.JLabel();
        lblLength = new javax.swing.JLabel();
        lblWidth = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        
        txtEan = new javax.swing.JFormattedTextField();
        txtEan.setDocument(new JTextFieldLimit(13));
        txtName = new javax.swing.JTextField();
        txtName.setDocument(new JTextFieldLimit(80));
        cbbCategory = new javax.swing.JComboBox<String>();
        txtColor = new javax.swing.JTextField();
        txtColor.setDocument(new JTextFieldLimit(30));
        txtPriceVat = new javax.swing.JFormattedTextField();
        txtPriceGross = new javax.swing.JFormattedTextField();
        txtPriceSupplier = new javax.swing.JFormattedTextField();
        txtStock = new javax.swing.JFormattedTextField();
        txtSizeH = new javax.swing.JFormattedTextField();
        txtSizeL = new javax.swing.JFormattedTextField();
        txtSizeW = new javax.swing.JFormattedTextField();
        
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtDescription.setDocument(new JTextFieldLimit(256));
        
        btnSaveEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        pnlLeft = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Article Details");
        setMinimumSize(new java.awt.Dimension(405, 565));

        lblEan.setText("EAN");

        lblName.setText("Name");

        lblCategory.setText("Category");

        lblPriceVat.setText("Price VAT");

        lblPriceGross.setText("Price Gross");

        lblPriceSupplier.setText("Price Supplier");

        lblStock.setText("Stock");

        lblColor.setText("Color");

        lblSizeH.setText("Height");

        lblLength.setText("Length");

        lblWidth.setText("Width");

        lblDescription.setText("Description");
        
        categories = articleCategoryController.getAllCategories();
		
		for (ArticleCategory articleCategory : categories) {
			cbbCategory.addItem(articleCategory.getName());
		}

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);
        
        NumberFormat percentEditFormat;
        percentEditFormat = NumberFormat.getNumberInstance();
        percentEditFormat.setMinimumFractionDigits(2);
        percentEditFormat.setMaximumIntegerDigits(2);
        
        NumberFormat percentDisplayFormat = NumberFormat.getPercentInstance();
        percentDisplayFormat.setMaximumIntegerDigits(2);
        percentDisplayFormat.setMinimumFractionDigits(2);
        
        NumberFormatter percentEditFormatter = new NumberFormatter(percentEditFormat) {
        	
        	static final long serialVersionUID = 1L;
			
        	public String valueToString(Object o)
                  throws ParseException {
                Number number = (Number)o;
                if (number != null) {
                    float d = number.floatValue() * 100.0F;
                    number = new Float(d);
                }
                return super.valueToString(number);
            }
        	
            public Object stringToValue(String s)
                   throws ParseException {
                Number number = (Number)super.stringToValue(s);
                if (number != null) {
                    float d = number.floatValue() / 100.0F;
                    number = new Float(d);
                }
                return number;
            }
        };
        
        txtPriceVat.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
        									new javax.swing.text.NumberFormatter(percentDisplayFormat),
        									new javax.swing.text.NumberFormatter(percentDisplayFormat),
        									percentEditFormatter));

        NumberFormat amountDisplayFormat;
        amountDisplayFormat = NumberFormat.getCurrencyInstance();
        amountDisplayFormat.setMinimumFractionDigits(2);
        
        NumberFormat amountEditFormat;
        amountEditFormat = NumberFormat.getNumberInstance();
        
        
        txtPriceGross.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
							        		new NumberFormatter(amountDisplayFormat),
							                new NumberFormatter(amountDisplayFormat),
							                new NumberFormatter(amountEditFormat)));

        txtPriceSupplier.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
								        		new NumberFormatter(amountDisplayFormat),
								                new NumberFormatter(amountDisplayFormat),
								                new NumberFormatter(amountEditFormat)));
        
        txtStock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        txtSizeH.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txtSizeL.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txtSizeW.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

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

        txtEan.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

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
                            .addComponent(lblPriceSupplier)
                            .addComponent(lblPriceGross)
                            .addComponent(lblPriceVat)
                            .addComponent(lblName)
                            .addComponent(lblEan)
                            .addComponent(lblCategory)
                            .addComponent(lblStock)
                            .addComponent(lblColor)
                            .addComponent(lblSizeH)
                            .addComponent(lblLength)
                            .addComponent(lblWidth))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(txtPriceSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtPriceGross, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPriceVat, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbbCategory, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEan, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtStock, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtColor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSizeH, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSizeL, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSizeW, javax.swing.GroupLayout.Alignment.TRAILING)))
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
                    .addComponent(lblEan)
                    .addComponent(txtEan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(lblPriceVat)
                    .addComponent(txtPriceVat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPriceGross)
                    .addComponent(txtPriceGross, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPriceSupplier)
                    .addComponent(txtPriceSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStock)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColor)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSizeH)
                    .addComponent(txtSizeH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLength)
                    .addComponent(txtSizeL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWidth)
                    .addComponent(txtSizeW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    
	public javax.swing.JButton btnCancel;
    public javax.swing.JButton btnSaveEdit;
    
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblEan;
    private javax.swing.JLabel lblLength;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPriceGross;
    private javax.swing.JLabel lblPriceSupplier;
    private javax.swing.JLabel lblPriceVat;
    private javax.swing.JLabel lblSizeH;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblWidth;
    
    public javax.swing.JComboBox<String> cbbCategory;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea txtDescription;
    public javax.swing.JTextField txtColor;
    public javax.swing.JFormattedTextField txtEan;
    public javax.swing.JTextField txtName;
    public javax.swing.JFormattedTextField txtPriceGross;
    public javax.swing.JFormattedTextField txtPriceSupplier;
    public javax.swing.JFormattedTextField txtPriceVat;
    public javax.swing.JFormattedTextField txtSizeH;
    public javax.swing.JFormattedTextField txtSizeL;
    public javax.swing.JFormattedTextField txtSizeW;
    public javax.swing.JFormattedTextField txtStock;
    
    public javax.swing.JPanel pnlLeft;
    public javax.swing.JPanel pnlCenter;
    public javax.swing.JPanel pnlRight;
}
