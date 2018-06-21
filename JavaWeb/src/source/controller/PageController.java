package source.controller;

import source.entities.User;
import source.entities.Article;
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
 * Created by sophia on 11/08/2017.
 */
@WebServlet(name = "PageController")
public class PageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String currentDate = MysqlLink.addQuotes(new java.sql.Date(System.currentTimeMillis()));
        HttpSession session = request.getSession();
        String handle = request.getParameter("handle");
        String id = request.getParameter("id");
        User user;
        String keyword=request.getParameter("keyword");
		switch (handle) {
            case "jumpMyBlog":
            user = UserService.getInfo(id);
            request.setAttribute("id", id);
            request.setAttribute("email", user.getEmail());
            request.setAttribute("nickname", user.getNickname());
            request.setAttribute("age", user.getAge());
            request.setAttribute("sex", user.getSex());
            request.getRequestDispatcher("prePageList.jsp").forward(request, response);
            break;
            case "jumpHome":
                user = UserService.getInfo(id);
                request.setAttribute("id", id);
                request.getRequestDispatcher("homePage.jsp").forward(request, response);
                break;
            case "search":
                user = UserService.getInfo(id);
                request.setAttribute("id", id);
                request.setAttribute("keyword", keyword);
                request.getRequestDispatcher("searchlist.jsp").forward(request, response);
                break;
            default:break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
