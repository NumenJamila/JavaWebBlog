package source.entities;


import java.sql.Date;

/**
 * Created by sophia on 28/07/2017.
 */
public class Article {
    private int article_id;
    private String title;
    private String article_content;
    private Date first_date;
    private Date last_date;
    private String id;

    public Article(){}

    public Article(String title) {
        this.title = title;
    }

    public Article(int article_id, String title, String article_content, Date first_date, Date last_date,String id) {
        this.article_id = article_id;
        this.title = title;
        this.article_content = article_content;
        this.first_date = first_date;
        this.last_date = last_date;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public Date getFirst_date() {
        return first_date;
    }

    public void setFirst_date(Date first_date) {
        this.first_date = first_date;
    }

    public Date getLast_date() {
        return last_date;
    }

    public void setLast_date(Date last_date) {
        this.last_date = last_date;
    }

}
