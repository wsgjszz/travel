package cn.itcast.travel.dao;

import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Route;
import org.junit.Test;

import java.util.List;

public class RouteDaoImplTest {
    RouteDaoImpl routeDao=new RouteDaoImpl();

    @Test
    public void findTotalCounts(){
        int totalCount = routeDao.findTotalCount(5,"");
        System.out.println(totalCount);
        List<Route> page = routeDao.findByPage(5, 0, 5, "");
        for (Route route : page) {
            System.out.println(route+" ");
        }
    }
}
