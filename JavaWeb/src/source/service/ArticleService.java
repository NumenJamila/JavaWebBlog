package source.service;

import source.entities.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sophia on 05/08/2017.message
 */
public class ArticleService extends MysqlLink {
    private static final String TABLE = "article";

    public static boolean insertArticle(String id, String title, String article_content, String first_date, String last_date) {
        ResultSet resultSet = select(TABLE, ALL, equalsAnd(toArray("id"), toArray(id)));
        if (resultSet != null) {
            try {
                if (resultSet.next())
                    return insert(TABLE, toArray("id", "title", "article_content", "first_date", "last_date"), toArray(id, title, article_content, first_date, last_date));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean deleteArticle(int article_id) {
        return delete(TABLE, equalsAnd(toArray("article_id"), toArray(article_id)));
    }

    public static boolean updateArticle(int article_id, String title, String article_content, String last_date) {
        return isExisted(article_id) && update(TABLE, toArray("title", "article_content", "last_date"), toArray(title, article_content, last_date), equalsAnd(toArray("article_id"), toArray(article_id)));
    }

    private static boolean isExisted(int article_id) {
        return exist(TABLE, "article_id", equalsOr(toArray("article_id"), toArray(article_id)));
    }

    public static ArrayList<Article> getArticleList(String id) {
        ArrayList<Article> articleList = new ArrayList<>();
        ResultSet resultSet;
        if (id==null) {
            resultSet = sortArticle();
        } else {
            resultSet = select(TABLE, ALL, equalsAnd(toArray("id"), toArray(id)));
        }
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    articleList.add(new Article(
                            resultSet.getInt("article_id"),
                            resultSet.getString("title"),
                            resultSet.getString("article_content"),
                            resultSet.getDate("first_date"),
                            resultSet.getDate("last_date"),
                            resultSet.getString("id")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return articleList;
    }

    public static Article getArticle(int article_id) {
        Article article = new Article();
        ResultSet resultSet = select(TABLE, ALL, equalsAnd(toArray("article_id"), toArray(article_id)));
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    article.setArticle_id(resultSet.getInt("article_id"));
                    article.setTitle(resultSet.getString("title"));
                    article.setArticle_content(resultSet.getString("article_content"));
                    article.setFirst_date(resultSet.getDate("first_date"));
                    article.setLast_date(resultSet.getDate("last_date"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return article;
    }

    public static void main(String[] args) {
        Article article= getArticle(26);
        System.out.println(article.getTitle());
    }
}
