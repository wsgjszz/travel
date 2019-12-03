package cn.itcast.travel.web.fy_User;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取激活码
        String code = request.getParameter("code");
        if (code==null){
            //无效的激活码
            response.getWriter().write("<h2>激活失败，请联系管理员！</h2>");
        }
        //2.调用service方法
        UserServiceImpl userService = new UserServiceImpl();
        User active = userService.active(code);
        if (active!=null){
            //激活成功
            response.getWriter().write("<h2>激活成功，请<a href='login.html'>登录</a></h2>");
        }else {
            //激活失败
            response.getWriter().write("<h2>激活失败，请联系管理员！</h2>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
