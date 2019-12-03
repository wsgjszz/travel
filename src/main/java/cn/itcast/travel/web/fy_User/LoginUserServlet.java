package cn.itcast.travel.web.fy_User;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

//@WebServlet("/loginUserServlet")
public class LoginUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.校验验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER"); //确保每个验证码只能使用一次
        if (check==null || checkcode_server==null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            ObjectMapper mapper = new ObjectMapper();
            //设置ContentType类型为JSON
            response.setContentType("application/json;charset=utf-8");
            //将数据写回客户端
            mapper.writeValue(response.getOutputStream(),resultInfo);
            return;
        }
        //2.获取请求参数，封装为User对象
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service方法
        UserServiceImpl userService = new UserServiceImpl();
        User login = userService.login(user);
        //4.判断
        ResultInfo resultInfo = new ResultInfo();
        ObjectMapper mapper = new ObjectMapper();
        if (login!=null){
            //登录成功,重定向到index.html
            resultInfo.setFlag(true);
            session.setAttribute("user",login);
        }else {
            //登录失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
        }
        //5.设置ContentType类型为JSON
        response.setContentType("application/json;charset=utf-8");
        //6.将数据写回客户端
        mapper.writeValue(response.getOutputStream(),resultInfo);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
