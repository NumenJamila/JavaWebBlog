package source.controller;

import source.entities.User;
import source.service.UserService;
import source.service.MysqlLink;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by sophia on 02/08/2017.
 */
@WebServlet(name = "/UserController")
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String opt = request.getParameter("opt");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        User user;

        if (opt != null) {
            switch (opt) {
                case "login":
                    if (UserService.judgeLogin(id, password)) {
                        //每次进入主页面时取出数据
                        user = UserService.getInfo(id);
                        session.setAttribute("owner",id);
                        request.setAttribute("id", id);
                        request.setAttribute("password", password);
                        request.setAttribute("email", user.getEmail());
                        request.setAttribute("nickname", user.getNickname());
                        request.setAttribute("age", user.getAge());
                        request.setAttribute("sex", user.getSex());
                        request.getRequestDispatcher("prePageList.jsp").forward(request,response);
                        //response.sendRedirect("prePageList.jsp");//进入主页面
                    } else {
                        request.getRequestDispatcher("login.jsp?opt=login&val=failed").forward(request, response);
                        //response.sendRedirect("../login.jsp?opt=login&val=failed");//返回登录页面
                    }
                    break;
                case "register":
                    String email = request.getParameter("email");
                    if (UserService.register(id, password, email)) {
                        //每次进入主页面时取出数据
                        user = UserService.getInfo(id);
                        session.setAttribute("owner",id);
                        request.setAttribute("id", id);//保存账号、密码
                        request.setAttribute("password", password);
                        request.setAttribute("email", email);
                        request.setAttribute("nickname", user.getNickname());
                        request.setAttribute("age", user.getAge());
                        request.setAttribute("sex", user.getSex());
                        //response.sendRedirect("../home.jsp?tag=0");
                        request.getRequestDispatcher("prePageList.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("login.jsp?opt=register&val=failed").forward(request, response);
                        //response.sendRedirect("../login.jsp?opt=register&val=failed");//返回注册页面
                    }
                    break;
                case "logout":
                    if (session.getAttribute("owner") != null) {
                      //session.invalidate();
                        session.removeAttribute("owner");
                        session.removeAttribute("id");
                        session.removeAttribute("password");
                        session.removeAttribute("email");
                        session.removeAttribute("nickname");
                        session.removeAttribute("age");
                        session.removeAttribute("sex");
                    }
                    response.sendRedirect("login.jsp?opt=logout");//返回注册页面
                    //request.getRequestDispatcher("login.jsp?opt=logout").forward(request,response);
                    break;
                default:
                    response.sendRedirect("login.jsp?opt=failed");//返回注册页面
                    break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}