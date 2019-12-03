package cn.itcast.travel.web.servlet;

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

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    UserServiceImpl userService = new UserServiceImpl();

    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
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
            /*ObjectMapper mapper = new ObjectMapper();
            //设置ContentType类型为JSON
            response.setContentType("application/json;charset=utf-8");
            //将数据写回客户端
            mapper.writeValue(response.getOutputStream(),resultInfo);*/
            writeValue(resultInfo,response); //使用BaseServle自定义的抽取方法
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
        //UserServiceImpl userService = new UserServiceImpl();
        User regist = userService.regist(user);
        //4.判断注册是否成功
        ResultInfo resultInfo = new ResultInfo();
        if (regist!=null){
            //注册成功
            resultInfo.setFlag(true);
        }else {
            //注册失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("该用户已存在");
        }
        /*ObjectMapper mapper = new ObjectMapper();
        //5.设置ContentType类型为JSON
        response.setContentType("application/json;charset=utf-8");
        //6.将数据写回客户端
        mapper.writeValue(response.getOutputStream(),resultInfo);*/
        writeValue(resultInfo,response); //使用BaseServle自定义的抽取方法
        return;
    }

    /**
     * 邮件激活功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        //1.获取激活码
        String code = request.getParameter("code");
        if (code==null){
            //无效的激活码
            response.getWriter().write("<h2>激活失败，请联系管理员！</h2>");
        }
        //2.调用service方法
        //UserServiceImpl userService = new UserServiceImpl();
        User active = userService.active(code);
        if (active!=null){
            //激活成功
            response.getWriter().write("<h2>激活成功，请<a href='login.html'>登录</a></h2>");
        }else {
            //激活失败
            response.getWriter().write("<h2>激活失败，请联系管理员！</h2>");
        }
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        //1.校验验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER"); //确保每个验证码只能使用一次
        System.out.println("UserServlet的login方法被访问了");
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
        //UserServiceImpl userService = new UserServiceImpl();
        User login = userService.login(user);
        //4.判断
        ResultInfo resultInfo = new ResultInfo();
        if (login!=null){
            //登录成功,重定向到index.html
            resultInfo.setFlag(true);
            session.setAttribute("user",login);
        }else {
            //登录失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
        }
        /*ObjectMapper mapper = new ObjectMapper();
        //5.设置ContentType类型为JSON
        response.setContentType("application/json;charset=utf-8");
        //6.将数据写回客户端
        mapper.writeValue(response.getOutputStream(),resultInfo);*/
        writeValue(resultInfo,response); //使用BaseServle自定义的抽取方法
    }

    /**
     * 查询单个用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        /*ResultInfo resultInfo = new ResultInfo();
        if (user!=null){
            //已登录状态
            resultInfo.setFlag(true);
            resultInfo.setData(user);
        }else {
            //未登录状态
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("您尚未登录！");
        }*/
        /*ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(),resultInfo);*/
        writeValue(user,response); //使用BaseServle自定义的抽取方法
    }

    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        //1.删除seesion中的user
        request.getSession().removeAttribute("user");
        //2.重定向到index.html
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

}
