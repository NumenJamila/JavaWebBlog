package source.controller;

import source.entities.Article;
import source.entities.User;
import source.service.ArticleService;
import source.service.MysqlLink;
import source.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 主页面将要显示的内容
 */
//@WebServlet(name = "/ContentController")
public class ArticleController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String currentDate = MysqlLink.addQuotes(new java.sql.Date(System.currentTimeMillis()));
        HttpSession session = request.getSession();

//        int article_id= (int) session.getAttribute("article_id");
        User user;
        String blogTitle = null;
        String blogContent = null;

        int article_id;
        String handle = request.getParameter("handle");
        String id= request.getParameter("id");
        if (handle != null) {
            switch (handle) {
                case "blogShow":
                    user = UserService.getInfo(id);
                    request.setAttribute("id", id);
                    request.setAttribute("email", user.getEmail());
                    request.setAttribute("nickname", user.getNickname());
                    request.setAttribute("age", user.getAge());
                    request.setAttribute("sex", user.getSex());

                    article_id=Integer.parseInt(request.getParameter("article_id"));
                    Article current_article = ArticleService.getArticle(article_id);
                    session.setAttribute("article_id", article_id);
                    request.setAttribute("title", current_article.getTitle());
                    request.setAttribute("article_content", current_article.getArticle_content());
                    request.setAttribute("first_date", current_article.getFirst_date());
                    request.setAttribute("last_date", current_article.getLast_date());
                    request.getRequestDispatcher("prePageArticle.jsp").forward(request, response);//进入主页面
                    break;
                case "returnBlogList":
                    user = UserService.getInfo(id);
                    request.setAttribute("id", id);
                    request.setAttribute("email", user.getEmail());
                    request.setAttribute("nickname", user.getNickname());
                    request.setAttribute("age", user.getAge());
                    request.setAttribute("sex", user.getSex());
                    request.getRequestDispatcher("prePageList.jsp").forward(request, response);
                    break;
                case "editBlogOption":
                    blogTitle = request.getParameter("blogTitle");
                    blogContent = request.getParameter("blogContent");
                    article_id = (int) session.getAttribute("article_id");
                    ArticleService.updateArticle(article_id, blogTitle, blogContent, currentDate);
                    request.getRequestDispatcher("prePageList.jsp").forward(request, response);
                    break;
                case "newBlogOption":
                    blogTitle = request.getParameter("blogTitle");
                    blogContent = request.getParameter("blogContent");
                    request.setAttribute("id",id);
                    ArticleService.insertArticle(id, blogTitle, blogContent, currentDate, currentDate);
                    request.getRequestDispatcher("prePageList.jsp").forward(request, response);
                    break;
                case "deleteBlog":
                    article_id = (int) session.getAttribute("article_id");
                    ArticleService.deleteArticle(article_id);
                    request.setAttribute("id",id);
                    request.getRequestDispatcher("prePageList.jsp").forward(request, response);
                default:
                    break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
