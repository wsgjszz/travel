package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //完成方法的分发
        //1.获取请求路径  /虚拟目录/user/*
        String uri = req.getRequestURI();
        System.out.println("请求路径:"+uri);
        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        //System.out.println("方法名称:"+methodName);
        //3.获取方法对象
        //System.out.println(this);
        try {
           Method method = this.getClass().getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
            //4.执行方法
           method.invoke(this,req,resp); //会发生NoSuchMethodException,因为方法访问权限受限

            /*
            解决方法1：使用getDeclaredMethod()方法，忽略访问权限
            缺陷：导致不想被访问的资源也可以被访问
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true); //暴力反射
            method.invoke(this,req,resp);
            解决方法2：直接修改方法体的访问权限为public
            优点：能指定访问特定资源
            */

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        //1.创建json的核心对象ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //2.设置ContentType类型为JSON
        response.setContentType("application/json;charset=utf-8");
        //3.将数据写回客户端
        mapper.writeValue(response.getOutputStream(),obj);
    }

}
