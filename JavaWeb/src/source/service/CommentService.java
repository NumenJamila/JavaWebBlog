package source.service;

import source.entities.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentService extends MysqlLink {
    private static final String TABLE = "comment";

    public static boolean insertComment(String id, int article_id, String comment_content, String comment_date) {
        return insert(TABLE, toArray("id", "article_id", "comment_content", "comment_date"), toArray(id, article_id, comment_content, comment_date));
    }

    public static boolean deleteComment(int comment_id) {
        return delete(TABLE, equalsAnd(toArray("comment_id"), toArray(comment_id)));
    }

    public static ArrayList<Comment> getCommentsList(int article_id) {
        ArrayList<Comment> commentsList = new ArrayList<>();
        ResultSet resultSet = select(TABLE, ALL, equalsAnd(toArray("article_id"), toArray(article_id)));
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    commentsList.add(new Comment(
                            resultSet.getInt("comment_id"),
                            resultSet.getString("comment_content"),
                            resultSet.getDate("comment_date"),
                            resultSet.getString("id")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return commentsList;
    }

    public static void main(String[] args) {
        ArrayList<Comment> commentsList = getCommentsList(33);
        for (Comment comment : commentsList) {
            System.out.println(comment.getId()+comment.getComment_id() + comment.getComment_content() + comment.getComment_date());
        }
    }
}
