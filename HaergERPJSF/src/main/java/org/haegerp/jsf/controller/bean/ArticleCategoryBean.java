package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import java.util.Date;
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

/**
 * Bean für die Seiten "ArticleCategoryManagement" und "Details"
 * 
 * @author Fabio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class ArticleCategoryBean implements Serializable {

    @Autowired
    private ArticleCategoryController articleCategoryController;
    
    //Injected Manualy in @PostConstruct ArticleBean.setUp()
    private ArticleBean articleBean;
    //Artikelkategorie, die in der Seite der Details zeigen wird
    private ArticleCategory articleCategory;
    //Hilfsvariable für die Methode, die eine Artikelkategorie löschen
    private long articleCategoryId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormArticleCategory formArticleCategory;
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] articleCategoryObjects;
    
    //Wie viel Artikel wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingefügt hat
    private String search;

    /**
     * Defaultwert
     */
    public ArticleCategoryBean() {
        pageSize = 10;
    }

    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp() {
        setArticleCategoryObjects(articleCategoryController.loadTableRows(pageSize));
        articleCategory = new ArticleCategory();
        setFormArticleCategory(new FormArticleCategory(false));
    }

    /**
     * Wenn der Benutzer die Suche benutzen möchte, wird diese Methode gerufen
     */
    public void setUpSearch() {
        articleCategoryController.setSearch(search, pageSize);
        setArticleCategoryObjects(articleCategoryController.loadTableRows(pageSize));
    }

    /**
     * Die Seite der Details wir vorbereitet
     * 
     * @param id ID der Artikelkategorie
     * @param disabled True - Die Artikelkategorie wird nur gezeigt; False - Die Artikelkategorie kann geändert werden.
     * @return Wenn die ID gültig ist dann die Seite der Derails wird geladen
     */
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
    
    /**
     * Die Seite der Details wird eine neue Artikelkategorie zu erstellen vorbereitet
     * @return Seite Details
     */
    public String prepareNew() {
        articleCategory = new ArticleCategory();
        setFormArticleCategory(new FormArticleCategory(false));
        return "articleCategoryDetails?faces-redirect=true";
    }

    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt hat
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed() {
        if (getFormArticleCategory().isDisabled() || articleCategory.getIdArticleCategory() == 0) {
            return "articleCategoryManagement?faces-redirect=true";
        } else {
            setFormArticleCategory(new FormArticleCategory(articleCategory, true));
            return "articleCategoryDetails?faces-redirect=true";
        }
    }
    
    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert. 
     * Sonst versucht das System die Artikelkategorie in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed() {
        if (getFormArticleCategory().isDisabled()) {
            setFormArticleCategory(new FormArticleCategory(articleCategory, false));
        } else {
            long id = 0;
            try {
                id = doSave();
                setUpSearch();
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }

            prepareView(id, true);
        }
    }
    
    /**
     * Eine Artikelkategorie wird in der Datanbank gespeichern
     * 
     * @return ID der Artikelkategorie
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme geworfen.
     */
    public long doSave() throws Exception {
        articleCategory.setName(getFormArticleCategory().getTxtName());
        articleCategory.setDescription(getFormArticleCategory().getTxtDescription());

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        articleCategory.setIdEmployeeModify(idEmployee);
        articleCategory.setLastModifiedDate(new Date());

        ArticleCategory newArticleCategory = articleCategoryController.save(articleCategory);

        updateDependencies();

        return newArticleCategory.getIdArticleCategory();
    }
    
    /**
     * Das System versuche eine Artikelkategorie zu löschen.
     */
    public void delete() {
        ArticleCategory deleteArticleCategory = articleCategoryController.getArticleCategoryById(articleCategoryId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteArticleCategory != null) {
            try {
                if (articleCategoryController.isCategoryArticlesEmpty(articleCategoryId)) {
                    articleCategoryController.delete(deleteArticleCategory);
                    severity = FacesMessage.SEVERITY_INFO;
                    msg = "Article's Category " + deleteArticleCategory.getName() + " was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "Article's Category " + deleteArticleCategory.getName() + " still has Articles";
                }
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
        updateDependencies();
    }

    /**
     * Die vorige Seite wird geladen
     */
    public void previousPage() {
        if (articleCategoryController.getPreviousPage(getPageSize())) {
            setArticleCategoryObjects(articleCategoryController.loadTableRows(pageSize));
        }
    }

    /**
     * Die nächste Seite wird geladen
     */
    public void nextPage() {
        if (articleCategoryController.getNextPage(getPageSize())) {
            setArticleCategoryObjects(articleCategoryController.loadTableRows(pageSize));
        }
    }

    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     * @return Der Wert zu zeigen
     */
    public String getPageNumber() {
        return (articleCategoryController.getPage().getNumber() + 1) + " / " + articleCategoryController.getPage().getTotalPages();
    }

    /**
     * Wenn die Kategorien geändert wird und das Formular des Artikels schon geladen wurde, dann muss das System das ArtikelBean aktualisieren
     */
    public void updateDependencies() {
        if (articleBean != null) {
            articleBean.setUp();
        }
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

    /**
     * @return the articleBean
     */
    public ArticleBean getArticleBean() {
        return articleBean;
    }

    /**
     * @param articleBean the articleBean to set
     */
    public void setArticleBean(ArticleBean articleBean) {
        this.articleBean = articleBean;
    }
}