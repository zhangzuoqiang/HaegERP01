package org.haegerp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.haegerp.controller.ArticleCategoryController;
import org.haegerp.controller.ArticleController;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.haegerp.enums.ArticleColumns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import javax.swing.SwingConstants;

@Component
public class ArticleView extends JFrame {
	
	private static final long serialVersionUID = 2464190735195227843L;
	
	@Autowired
	private ArticleController articleController;
	
	@Autowired
	private ArticleCategoryController articleCategoryController;
	
	@Autowired
	private ArticleCategoryRepository articleCategoryRepository;
	
	public ArticleView() {
    }
	
	protected void btnNext_ActionPerformed(ActionEvent e) {
		articleController.getNextPage(sldNumberResults.getValue());
		loadTable();
	}
	
	protected void btnPrevious_ActionPerformed(ActionEvent e) {
		articleController.getPreviousPage(sldNumberResults.getValue());
		loadTable();
	}
	
	protected void sldNumberResults_MouseReleased(MouseEvent e) {
		articleController.getFirstPage(sldNumberResults.getValue());
		loadTable();
	}
	
	protected void cbbCategory_PropertyChange(PropertyChangeEvent e) {
		//FIXME: LoadTable with DI or Interfaces
		
		//articleController.getArticlesByCategory(((String)cbbCategory.getSelectedItem()));
		//loadTable();
	}
	
	public void setUp(){
		/** FIXME: Articles
		 * ----> Category
		 * ----> Search
		 * ----> Delete
		 * ----> New
		 * ----> Edit
		 */
		pnlTblArticles = new JScrollPane();
        tblArticles = new JTable();
        btnPrevious = new JButton();
        btnNext = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();
        btnNew = new JButton();
        pnlCenterNewR = new JPanel();
        pnlCenterNewL = new JPanel();
        lblSlider = new JLabel();
        
        setTitle("Articles Management");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlTblArticles.setViewportView(tblArticles);

        btnPrevious.setText("Previous");
        btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrevious_ActionPerformed(e);
			}
        });
        
        btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNext_ActionPerformed(e);
			}
        });
        btnNext.setText("Next");

        btnEdit.setText("Edit");

        btnDelete.setText("Delete");

        btnNew.setText("New");

        javax.swing.GroupLayout gl_pnlCenterNewR = new javax.swing.GroupLayout(pnlCenterNewR);
        pnlCenterNewR.setLayout(gl_pnlCenterNewR);
        gl_pnlCenterNewR.setHorizontalGroup(
            gl_pnlCenterNewR.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gl_pnlCenterNewR.setVerticalGroup(
            gl_pnlCenterNewR.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gl_pnlCenterNewL = new javax.swing.GroupLayout(pnlCenterNewL);
        pnlCenterNewL.setLayout(gl_pnlCenterNewL);
        gl_pnlCenterNewL.setHorizontalGroup(
            gl_pnlCenterNewL.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gl_pnlCenterNewL.setVerticalGroup(
            gl_pnlCenterNewL.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        
        pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(pnlTblArticles, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        				.addComponent(pnlSearch, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(btnPrevious, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pnlCenterNewL, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pnlCenterNewR, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(pnlSearch, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pnlTblArticles, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnPrevious)
        						.addComponent(btnNext))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnEdit)
        						.addComponent(btnDelete)))
        				.addComponent(pnlCenterNewR, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pnlCenterNewL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNew))
        			.addContainerGap())
        );
        pnlSearch.setLayout(null);
        
        lblCategory = new JLabel();
        lblCategory.setText("Category");
        lblCategory.setBounds(10, 28, 58, 14);
        pnlSearch.add(lblCategory);
        
        cbbCategory = new JComboBox<String>();
        cbbCategory.setBounds(86, 24, 177, 22);
        pnlSearch.add(cbbCategory);
        cbbCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"All"}));
        cbbCategory.setSelectedIndex(0);
        loadCbbCategory();
        cbbCategory.addPropertyChangeListener(new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent e) {
				cbbCategory_PropertyChange(e);
			}
		});
        
        txtSearch = new JTextField();
        txtSearch.setBounds(86, 58, 177, 20);
        pnlSearch.add(txtSearch);
        txtSearch.setColumns(10);
        
        lblSearch = new JLabel();
        lblSearch.setText("Search");
        lblSearch.setBounds(10, 61, 45, 14);
        pnlSearch.add(lblSearch);
        
        btnSearch = new JButton();
        btnSearch.setText("Search");
        btnSearch.setBounds(487, 57, 75, 23);
        pnlSearch.add(btnSearch);
        
        cbbField = new JComboBox<String>();
        cbbField.setBounds(275, 57, 200, 22);
        pnlSearch.add(cbbField);
        cbbField.setModel(new DefaultComboBoxModel<String>(new String[] {"All"}));
        cbbField.setSelectedIndex(0);
        loadCbbField();
        
        lblSlider.setBounds(487, 28, 26, 14);
        pnlSearch.add(lblSlider);
        getContentPane().setLayout(layout);
        
        sldNumberResults = new JSlider();
        sldNumberResults.setPaintTicks(true);
        sldNumberResults.setSnapToTicks(true);
        sldNumberResults.setMinorTickSpacing(5);
        sldNumberResults.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent arg0) {
        		lblSlider.setText(String.valueOf(sldNumberResults.getValue()));
        	}
        });
        
        sldNumberResults.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				sldNumberResults_MouseReleased(e);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
        
        sldNumberResults.setValue(10);
        sldNumberResults.setMajorTickSpacing(20);
        sldNumberResults.setMaximum(200);
        sldNumberResults.setMinimum(10);
        sldNumberResults.setBounds(275, 19, 200, 27);
        pnlSearch.add(sldNumberResults);
        
        lblPage = new JLabel("");
        lblPage.setHorizontalAlignment(SwingConstants.LEFT);
        lblPage.setBounds(580, 62, 110, 16);
        pnlSearch.add(lblPage);
        
        loadTable();
        
        pack();
	}

	private void loadCbbField() {
		for (int i = 2; i < 8; i++) {
			cbbField.addItem(ArticleColumns.values()[i].name());
		}
	}

	private void loadCbbCategory(){
		List<ArticleCategory> categories = articleCategoryController.getAllCategories();
		for (ArticleCategory articleCategory : categories) {
			cbbCategory.addItem(articleCategory.getName());
		}
	}
	
	private void loadTable(){
		tblArticles.setModel(
        	new DefaultTableModel(
        			loadTablePage(sldNumberResults.getValue()) ,
        			new String [] {
        				"EAN", "Name", "Category", "Price Vat", "Price Gross", "Price Supplier", "Stock"
        			})
	        {
				private static final long serialVersionUID = 1L;
	
				@Override
		        public boolean isCellEditable(int row, int column) {
		        	return false;
		        }
	        }
    	);
		lblPage.setText("Page " + (articleController.getPage().getNumber() +1) + "/" + articleController.getPage().getTotalPages());
	}
	
	private Object[][] loadTablePage(int size){
		articleController.loadPage(size);
		Page<Article> page = articleController.getPage();
        Object[][] rows = new Object[page.getContent().size()][7];
        for (int i = 0; i < page.getContent().size(); i++) {
        	Article article = page.getContent().get(i);
        	
        	rows[i][0] = article.getEan();
        	rows[i][1] = article.getName();
			rows[i][2] = articleCategoryRepository.findOne(article.getArticleCategory().getIdArticleCategory()).getName();
			rows[i][3] = article.getPriceVat();
			rows[i][4] = article.getPriceGross();
			rows[i][5] = article.getPriceSupplier();
			rows[i][6] = article.getStock();
		}
        
        return rows;
	}
	
	private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnNew;
    private JButton btnNext;
    private JButton btnPrevious;
    private JPanel pnlCenterNewR;
    private JPanel pnlCenterNewL;
    
    private JScrollPane pnlTblArticles;
    private JTable tblArticles;
    
    private JPanel pnlSearch;
    private JTextField txtSearch;
    private JComboBox<String> cbbField;
	private JLabel lblCategory;
	private JComboBox<String> cbbCategory;
	private JLabel lblSearch;
	private JButton btnSearch;
	
	private JLabel lblSlider;
	private JSlider sldNumberResults;
	private JLabel lblPage;
}
