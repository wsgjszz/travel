package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet {

    FavoriteServiceImpl favoriteService=new FavoriteServiceImpl();

    /**
     * 判断当前用户是否收藏该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取线路rid
        String ridStr = request.getParameter("rid");
        int rid=0;
        if (ridStr!=null && !"".equals(ridStr) && !"null".equals(ridStr)){
            rid=Integer.parseInt(ridStr);
        }
        //2.获取当前用户对象
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //3.获取用户uid
        int uid=0;
        if (user==null){
            //用户未登录
        }else {
            //用户已登录
            uid=user.getUid();
        }
        //4.调用service方法
        boolean flag = favoriteService.isFavorite(rid, uid);
        //5.将数据写回客户端
        writeValue(flag,response);
    }

    public void findTimes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.获取参数rid
        String ridStr = request.getParameter("rid");
        int rid=0;
        if (ridStr!=null && !"".equals(ridStr) && !"null".equals(ridStr)){
            rid=Integer.parseInt(ridStr);
        }
        //2.调用service
        int times=favoriteService.findTimes(rid);
        //3.将数据写回客户端
        writeValue(times,response);
    }

    public void onclickFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("onclickFavorite被访问了");

        //1.获取参数rid
        String ridStr = request.getParameter("rid");
        int rid=0;
        if (ridStr!=null && !"".equals(ridStr) && !"null".equals(ridStr)){
            rid=Integer.parseInt(ridStr);
        }
        //2.获取当前登录的用户对象
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //3.获取用户uid
        int uid=0;
        if (user==null){
            //用户未登录
        }else {
            //用户已登录
            uid=user.getUid();
        }
        //4.调用service
        favoriteService.favorite(uid,rid);
    }

}
