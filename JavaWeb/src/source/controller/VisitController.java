package source.controller;

import source.entities.Article;
import source.entities.User;
import source.service.ArticleService;
import source.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "VisitController")
public class VisitController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String handle = request.getParameter("handle");
        String id = request.getParameter("id");
        int article_id=Integer.parseInt(request.getParameter("article_id"));
        User user;
        switch (handle){
            case "visitOtherBlog":
                user = UserService.getInfo(id);
                request.setAttribute("id", id);
                request.setAttribute("email", user.getEmail());
                request.setAttribute("nickname", user.getNickname());
                request.setAttribute("age", user.getAge());
                request.setAttribute("sex", user.getSex());
                request.setAttribute("tag","visitor");
                //根据文章ID号查找完整的内容
                Article current_article = ArticleService.getArticle(article_id);
                session.setAttribute("article_id", article_id);
                request.setAttribute("title", current_article.getTitle());
                request.setAttribute("article_content", current_article.getArticle_content());
                request.setAttribute("first_date", current_article.getFirst_date());
                request.setAttribute("last_date", current_article.getLast_date());
                request.getRequestDispatcher("prePageArticle.jsp").forward(request, response);//进入文章详情页面
                break;
            default:break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
