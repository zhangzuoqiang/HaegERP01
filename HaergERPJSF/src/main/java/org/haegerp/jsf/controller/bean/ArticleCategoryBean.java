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

import org.haegerp.entity.ArticleCategory;
import org.haegerp.jsf.controller.form.FormArticleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
@Scope(value = "session")
public class ArticleCategoryBean implements Serializable{
    
    @Autowired
    private ArticleCategoryController articleCategoryController;

    private ArticleCategory articleCategory;
    private long articleCategoryId;

    private List<ArticleCategory> articleCategories;
    private Object[][] articleCategoryObjects;
    
    private FormArticleCategory formArticleCategory;
    
    private int pageSize;
    private String search;
    
    public ArticleCategoryBean() {
        pageSize = 10;
    }
    
    @PostConstruct
    public void setUp(){
        setArticleCategoryObjects(articleCategoryController.loadTableRows(pageSize));
        articleCategory = new ArticleCategory();
        setFormArticleCategory(new FormArticleCategory(false));
    }

    public void setUpSearch(){
        articleCategoryController.setSearch(search, pageSize);
        setArticleCategoryObjects(articleCategoryController.loadTableRows(pageSize));
    }
    
    public String prepareView(long id, boolean disabled) {
        articleCategory = articleCategoryController.getArticleCategoryById(id);
        if (articleCategory != null) {
            setFormArticleCategory(new FormArticleCategory(articleCategory, disabled));
            return "articleCategoryDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Article's Category was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }
    
    public String prepareNew(){
        articleCategory = new ArticleCategory();
        setFormArticleCategory(new FormArticleCategory(false));
        return "articleCategoryDetails?faces-redirect=true";
    }
    
    public String btnCancel_ActionPerformed(){
        if (getFormArticleCategory().isDisabled() || articleCategory.getIdArticleCategory()== 0) {
            return "articleCategoryManagement?faces-redirect=true";
        } else {
            setFormArticleCategory(new FormArticleCategory(articleCategory, true));
            return "articleCategoryDetails?faces-redirect=true";
        }
    }
    
    public void btnEditSave_ActionPerformed(){
        if (getFormArticleCategory().isDisabled()) {
            setFormArticleCategory(new FormArticleCategory(articleCategory, false));
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
        articleCategory.setName(getFormArticleCategory().getTxtName());
        articleCategory.setDescription(getFormArticleCategory().getTxtDescription());
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        Long idEmployee = (Long)session.getAttribute("idemployee");
        
        articleCategory.setIdEmployeeModify(idEmployee);
        articleCategory.setLastModifiedDate(new Date());
        
        return articleCategoryController.save(articleCategory).getIdArticleCategory();
    }
    
    public void delete(){
        ArticleCategory deleteArticleCategory = articleCategoryController.getArticleCategoryById(getArticleCategoryId());
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteArticleCategory != null){
            try {
                articleCategoryController.delete(deleteArticleCategory);
                severity = FacesMessage.SEVERITY_INFO;
                msg = "Article's Category " + deleteArticleCategory.getName() + " was deleted.";
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Article's Category was not found in the Database.";
        }
        
        fMessage = new FacesMessage(severity, msg, null);

        FacesContext.getCurrentInstance().addMessage(null, fMessage);
        setUp();
        setUpSearch();
    }
    
    public void previousPage(){
        if (articleCategoryController.getPreviousPage(getPageSize()))
            setArticleCategoryObjects(articleCategoryController.loadTableRows(pageSize));
    }
    
    public void nextPage(){
        if (articleCategoryController.getNextPage(getPageSize()))
            setArticleCategoryObjects(articleCategoryController.loadTableRows(pageSize));
    }
    
    public String getPageNumber(){
        return (articleCategoryController.getPage().getNumber()+1) + " / " + articleCategoryController.getPage().getTotalPages();
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
     * @return the articleCategories
     */
    public List<ArticleCategory> getArticleCategories() {
        return articleCategories;
    }

    /**
     * @param articleCategories the articleCategories to set
     */
    public void setArticleCategories(List<ArticleCategory> articleCategories) {
        this.articleCategories = articleCategories;
    }

    /**
     * @return the articleCategoryObjects
     */
    public Object[][] getArticleCategoryObjects() {
        return articleCategoryObjects;
    }

    /**
     * @param articleCategoryObjects the articleCategoryObjects to set
     */
    public void setArticleCategoryObjects(Object[][] articleCategoryObjects) {
        this.articleCategoryObjects = articleCategoryObjects;
    }

    /**
     * @return the articleCategoryId
     */
    public long getArticleCategoryId() {
        return articleCategoryId;
    }

    /**
     * @param articleCategoryId the articleCategoryId to set
     */
    public void setArticleCategoryId(long articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    /**
     * @return the formArticleCategory
     */
    public FormArticleCategory getFormArticleCategory() {
        return formArticleCategory;
    }

    /**
     * @param formArticleCategory the formArticleCategory to set
     */
    public void setFormArticleCategory(FormArticleCategory formArticleCategory) {
        this.formArticleCategory = formArticleCategory;
    }
}