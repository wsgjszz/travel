package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询cid对应的分类的总记录数
     * @param cid
     * @return
     */
    @Override
    public int findTotalCount(int cid,String rname) {
        //1.定义sql模板
        String sql="select count(*) from tab_route where 1=1 ";
        StringBuffer sb = new StringBuffer(sql);
        //2.判断参数是否有值
        ArrayList parms = new ArrayList();
        if (cid!=0){
            String str=" and cid=? ";
            sb.append(str);
            parms.add(cid);
        }
        if (rname!=null && !"".equals(rname)){
            String str=" and rname like ? ";
            sb.append(str);
            parms.add("%"+rname+"%");
        }
        sql = sb.toString();
        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql,Integer.class,parms.toArray());
    }

    /**
     * 分页查询
     * @param cid
     * @param star
     * @param size
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int star, int size,String rname) {
        /*String sql="select * from tab_route where cid=? limit ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Route>(Route.class),cid,star,size);*/
        //1.定义sql模板
        String sql="select * from tab_route where 1=1 ";
        StringBuffer sb = new StringBuffer(sql);
        //2.判断参数是否有值
        ArrayList parms = new ArrayList();
        if (cid!=0){
            String str=" and cid=? ";
            sb.append(str);
            parms.add(cid);
        }
        if (rname!=null && !"".equals(rname)){
            String str=" and rname like ? ";
            sb.append(str);
            parms.add("%"+rname+"%");
        }
        if (star!=0 || size!=0){
            String str="limit ?,?";
            sb.append(str);
            parms.add(star);
            parms.add(size);
        }
        sql = sb.toString();
        System.out.println(sql);
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Route>(Route.class),parms.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql="select * from tab_route where rid=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
