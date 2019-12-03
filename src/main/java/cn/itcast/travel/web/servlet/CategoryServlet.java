package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    CategoryServiceImpl categoryService=new CategoryServiceImpl();

    /**
     * 查询Category表中所有记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.查询redis中的category数据
        Jedis jedis = JedisUtil.getJedis();
        Set<String> category = jedis.zrange("category", 0, -1);
        //调用zrangeWithScores方法，可以获取到对应的cid
        //Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        //2.判断
        if (category==null || category.size()==0){
            System.out.println("从数据库中查询");
            //数据未缓存,从数据库中查询
            cs = categoryService.findAll();
            //将查询到的数据存入redis
            for (int i=0;i<cs.size();i++){
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
            //排序
            List<Category> cas=new ArrayList<Category>();
            for (int i = 1; i <= cs.size(); i++) {
                Category c_a = new Category();
                for (Category c : cs) {
                    if (c.getCid()==i){
                        c_a=c;
                        break;
                    }
                }
                cas.add(c_a);
            }
            cs=cas;
        }else {
            System.out.println("从redis中查询");
            //从缓存中取出数据，转化数据类型
            cs=new ArrayList<Category>();
            //排序
            int cid=1;
            for (String name : category) {
                Category ca = new Category();
                ca.setCname(name);
                ca.setCid(cid);
                cs.add(ca);
                cid++;
            }
            /*for (Tuple tuple : category) {
                Category ca = new Category();
                ca.setCid((int) tuple.getScore());
                ca.setCname(tuple.getElement());
                cs.add(ca);
            }*/
        }

        writeValue(cs,response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
