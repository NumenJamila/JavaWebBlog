package source.controller;

import source.entities.Article;
import source.entities.Comment;
import source.entities.User;
import source.service.ArticleService;
import source.service.CommentService;
import source.service.MysqlLink;
import source.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by sophia on 08/08/2017.
 */
@WebServlet(name = "/CommentsController")
public class CommentController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String currentDate = MysqlLink.addQuotes(new java.sql.Date(System.currentTimeMillis()));
        HttpSession session = request.getSession();

        int article_id;
        Article current_article;
        User user;
        String id;
        String handle = request.getParameter("handle");

        if (handle != null)
            switch (handle) {
            case "loadComments":
                id = request.getParameter("id");
                article_id = (int) session.getAttribute("article_id");
                PrintWriter writer = response.getWriter();
                ArrayList<Comment> commentsList = CommentService.getCommentsList(article_id);
                int count = 1;
                for (Comment comment : commentsList) {
                    writer.println(
                            count + "level" + "&nbsp;&nbsp;"
                                    + "come from user:" + comment.getId() + "&nbsp;&nbsp;"
                                    + comment.getComment_date() + "&nbsp;&nbsp;"
                                    + "<a href='/CommentController?handle=deleteComment&comment_id=" + comment.getComment_id() + "&id=" + id + "'>删除</a>" + "<br>"
                                    + comment.getComment_content()
                                    + "<hr>");
                    writer.flush();
                    count++;
                }
                break;
            case "newComment":
                article_id = Integer.parseInt(request.getParameter("article_id"));
                String owner = request.getParameter("owner");
                String commentContent = request.getParameter("commentContent");
                CommentService.insertComment(owner, article_id, commentContent, currentDate);
                //得刷新个人信息部分
                id = request.getParameter("id");
                user = UserService.getInfo(id);
                request.setAttribute("id", id);
                request.setAttribute("email", user.getEmail());
                request.setAttribute("nickname", user.getNickname());
                request.setAttribute("age", user.getAge());
                request.setAttribute("sex", user.getSex());
                //得刷新博文的内容
                current_article = ArticleService.getArticle(article_id);
                request.setAttribute("article_id", article_id);
                request.setAttribute("title", current_article.getTitle());
                request.setAttribute("article_content", current_article.getArticle_content());
                request.setAttribute("first_date", current_article.getFirst_date());
                request.setAttribute("last_date", current_article.getLast_date());
                request.getRequestDispatcher("prePageArticle.jsp").forward(request, response);
                break;
            case "deleteComment":
                int comment_id = Integer.parseInt(request.getParameter("comment_id"));
                CommentService.deleteComment(comment_id);
                //得刷新个人信息部分
                id = request.getParameter("id");
                user = UserService.getInfo(id);
                request.setAttribute("id", id);
                request.setAttribute("email", user.getEmail());
                request.setAttribute("nickname", user.getNickname());
                request.setAttribute("age", user.getAge());
                request.setAttribute("sex", user.getSex());
                //得刷新博文的内容
                article_id = (int) session.getAttribute("article_id");
                current_article = ArticleService.getArticle(article_id);
                request.setAttribute("article_id", article_id);
                request.setAttribute("title", current_article.getTitle());
                request.setAttribute("article_content", current_article.getArticle_content());
                request.setAttribute("first_date", current_article.getFirst_date());
                request.setAttribute("last_date", current_article.getLast_date());
                request.getRequestDispatcher("prePageArticle.jsp").forward(request, response);
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
