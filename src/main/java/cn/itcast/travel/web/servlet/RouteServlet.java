package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    RouteServiceImpl service=new RouteServiceImpl();

    public void queryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        //2.处理参数
        int currentPage=0; //当前页码，如果不设置，默认为1
        System.out.println("currentPageStr:"+currentPageStr);
        if (currentPageStr!=null && !"".equals(currentPageStr) && !"null".equals(currentPageStr)){
            currentPage=Integer.parseInt(currentPageStr);
        }else {
            currentPage=1;
        }
        int pageSize=0; //每页显示记录数，如果不设置，默认为5
        if (pageSizeStr!=null && !"".equals(pageSizeStr)){
            pageSize=Integer.parseInt(pageSizeStr);
        }else {
            pageSize=5;
        }
        int cid=0; //分类id
        if (cidStr!=null && !"".equals(cidStr) && !"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        if ("null".equals(rname) && !"undefined".equals(rname)){
            rname="";
        }
        //3.调用service查询
        PageBean<Route> queryPage = service.queryPage(cid, currentPage, pageSize,rname);
        //4.将数据封装为JSON，写回客户端
        writeValue(queryPage,response);
    }

    public void findDetail(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException {
        //1.接收rid参数
        String ridStr = request.getParameter("rid");
        int rid = 0;
        if (ridStr != null && !"".equals(ridStr)) {
            rid = Integer.parseInt(ridStr);
        }
        //2.调用service
        Route route=service.findDetail(rid);
        //3.将数据封装为JSON，写回客户端
        writeValue(route,response);
    }

}
