package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.haegerp.controller.ArticleCategoryController;

import org.haegerp.controller.ArticleController;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.jsf.controller.form.FormArticle;
import org.haegerp.jsf.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
@Scope(value = "session")
public class ArticleBean implements Serializable{
    
    @Autowired
    private ArticleController articleController;
    @Autowired
    private ArticleCategoryController articleCategoryController;

    private Article article;
    private long articleId;
    
    private FormArticle formArticle;
    
    private List<ArticleCategory> categories;
    private Object[][] articleObjects;
    
    private int cbSearchValue;
    
    private int pageSize;
    private String search;

    private Validator validator = new Validator();
    
    public ArticleBean() {
        pageSize = 10;
    }
    
    @PostConstruct
    public void setUp(){
        articleObjects = articleController.loadTableRows(pageSize);
        article = new Article();
        categories = articleCategoryController.getAllCategories();
        formArticle = new FormArticle(false);
    }

    public void setUpSearch(){
        articleController.setSearch(search, pageSize);
        articleController.setCategory((long)cbSearchValue, pageSize);
        articleObjects = articleController.loadTableRows(pageSize);
    }
    
    public String getArticleCategoryName(long id){
        return articleCategoryController.getArticleCategoryById(id).getName();
    }
    
    public String prepareView(long id, boolean disabled) {
        article = articleController.getArticleById(id);
        categories = articleCategoryController.getAllCategories();
        if (article != null) {
            formArticle = new FormArticle(article, disabled);
            return "articleDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Article was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }
    
    public String prepareNew(){
        article = new Article();
        formArticle = new FormArticle(false);
        return "articleDetails?faces-redirect=true";
    }
    
    public String btnCancel_ActionPerformed(){
        if (formArticle.isDisabled() || article.getIdArticle() == 0) {
            return "articleManagement?faces-redirect=true";
        } else {
            formArticle = new FormArticle(article, true);
            return "articleDetails?faces-redirect=true";
        }
    }
    
    public void btnEditSave_ActionPerformed(){
        if (formArticle.isDisabled()) {
            formArticle = new FormArticle(article, false);
        } else {
            long id = 0;
            try {
                id = doSave();
                setUpSearch();
            } catch(Exception ex) {
                ex.printStackTrace(System.err);
            }
            
            prepareView(id, true);
        }
    }
    
    public long doSave() throws Exception{
        ArticleCategory articleCategory = articleCategoryController.getArticleCategoryById(formArticle.getCbValue());
        
        article.setEan(Long.valueOf(formArticle.getTxtEan()));
        article.setName(formArticle.getTxtName());
        article.setArticleCategory(articleCategory);
        article.setColor(formArticle.getTxtColor());
        article.setPriceVat(Float.valueOf(formArticle.getTxtPriceVat())/100F);
        article.setPriceGross(Float.valueOf(formArticle.getTxtPriceGross()));
        article.setPriceSupplier(Float.valueOf(formArticle.getTxtPriceSupplier()));
        article.setStock(Long.valueOf(formArticle.getTxtStock()));
        article.setColor(formArticle.getTxtColor());
        article.setSizeH(Float.valueOf(formArticle.getTxtSizeH()));
        article.setSizeL(Float.valueOf(formArticle.getTxtSizeL()));
        article.setSizeW(Float.valueOf(formArticle.getTxtSizeW()));
        article.setDescription(formArticle.getTxtDescription());
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        Long idEmployee = (Long)session.getAttribute("idemployee");
        
        article.setIdEmployeeModify(idEmployee);
        article.setLastModifiedDate(new Date());
        
        return articleController.save(article).getIdArticle();
    }
    
    public void delete(){
        Article deleteArticle = articleController.getArticleById(articleId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteArticle != null){
            try {
                articleController.delete(deleteArticle);
                severity = FacesMessage.SEVERITY_INFO;
                msg = "Article " + deleteArticle.getName() + " was deleted.";
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Article was not found in the Database.";
        }
        
        fMessage = new FacesMessage(severity, msg, null);

        FacesContext.getCurrentInstance().addMessage(null, fMessage);
        setUp();
        setUpSearch();
    }
    
    public void previousPage(){
        if (articleController.getPreviousPage(pageSize))
            articleObjects = articleController.loadTableRows(pageSize);
    }
    
    public void nextPage(){
        if (articleController.getNextPage(pageSize))
            articleObjects = articleController.loadTableRows(pageSize);
    }
    
    public String getPageNumber(){
        return (articleController.getPage().getNumber()+1) + " / " + articleController.getPage().getTotalPages();
    }
    
    /**
     * @return the articleController
     */
    public ArticleController getArticleController() {
        return articleController;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * @return the formArticle
     */
    public FormArticle getFormArticle() {
        return formArticle;
    }

    /**
     * @param formArticle the formArticle to set
     */
    public void setFormArticle(FormArticle formArticle) {
        this.formArticle = formArticle;
    }

    /**
     * @return the categories
     */
    public List<ArticleCategory> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<ArticleCategory> categories) {
        this.categories = categories;
    }

    /**
     * @return the articleId
     */
    public long getArticleId() {
        return articleId;
    }

    /**
     * @param articleId the articleId to set
     */
    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    /**
     * @return the cbSearchValue
     */
    public int getCbSearchValue() {
        return cbSearchValue;
    }

    /**
     * @param cbSearchValue the cbSearchValue to set
     */
    public void setCbSearchValue(int cbSearchValue) {
        this.cbSearchValue = cbSearchValue;
    }

    /**
     * @return the articleObjects
     */
    public Object[][] getArticleObjects() {
        return articleObjects;
    }

    /**
     * @param articleObjects the articleObjects to set
     */
    public void setArticleObjects(Object[][] articleObjects) {
        this.articleObjects = articleObjects;
    }

    /**
     * @return the validator
     */
    public Validator getValidator() {
        return validator;
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}