package org.haegerp.jsf.controller.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.haegerp.controller.ArticleController;
import org.haegerp.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
@Scope(value = "session")
public class ArticleBean {
    
    @Autowired
    private ArticleController articleController;

    private List<Article> articles;
    
    private int pageSize;
    
    public ArticleBean() {
    }
    
    @PostConstruct
    public void setUp(){
        articles = articleController.loadPage(10).getContent();
        for (Article article : articles) {
            System.out.println(article.getName());
        }
    }

    /**
     * @return the articleController
     */
    public ArticleController getArticleController() {
        return articleController;
    }

    /**
     * @return the articles
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
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
    
    
}
