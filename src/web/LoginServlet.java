package web;

import dao.UserDao;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import util.DbUtil;
import util.StringUtil;

public class LoginServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();

    public LoginServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        request.setAttribute("userName", userName);
        request.setAttribute("password", password);
        if (!StringUtil.isEmpty(userName) && !StringUtil.isEmpty(password)) {
            User user = new User(userName, password);
            Connection con = null;

            try {
                con = this.dbUtil.getCon();
                User currentUser = this.userDao.login(con, user);
                if (currentUser == null) {
                    request.setAttribute("error", "用户名或密码错误！");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    HttpSession session=request.getSession();
                    session.setAttribute("currentUser", currentUser);

                    response.sendRedirect("main.jsp");
                }
            } catch (Exception var16) {
                var16.printStackTrace();
            } finally {
                try {
                    this.dbUtil.closeCon(con);
                } catch (Exception var15) {
                    var15.printStackTrace();
                }

            }

        } else {
            request.setAttribute("error", "用户名或密码为空！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
