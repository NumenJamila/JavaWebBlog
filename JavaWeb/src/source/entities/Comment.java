package source.entities;

import java.sql.Date;

/**
 * Created by sophia on 28/07/2017.
 */

public class Comment {
    private int comment_id;
    private String comment_content;
    private Date comment_date;
    private int article_id;
    private String id;

    public Comment(int comment_id, String comment_content, Date comment_date,String id) {
        this.comment_id = comment_id;
        this.comment_content = comment_content;
        this.comment_date = comment_date;
        this.id = id;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}
