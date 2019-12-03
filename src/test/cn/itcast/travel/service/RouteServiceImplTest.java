package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import org.junit.Test;

public class RouteServiceImplTest {
    RouteServiceImpl service=new RouteServiceImpl();

    @Test
    public void queryPage(){
        //PageBean<Route> routePageBean = service.queryPage(5, 1, 5,"兵马俑");
        PageBean<Route> routePageBean = service.queryPage(5, 1, 5,"兵马俑");
        System.out.println(routePageBean);
    }
}
