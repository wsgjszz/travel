package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.Route_ImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    RouteDaoImpl routeDao=new RouteDaoImpl();
    Route_ImgDaoImpl route_imgDao=new Route_ImgDaoImpl();
    SellerDaoImpl sellerDao=new SellerDaoImpl();

    @Override
    public PageBean<Route> queryPage(int cid, int currentPage, int pageSize,String rname) {

        //1.重新封装PageBean对象
        PageBean<Route> pageBean = new PageBean<>();
        //2.设置属性
        //2.1设置当前页码
        pageBean.setCurrentPage(currentPage);
        //2.2设置每页显示行数
        pageBean.setPageSize(pageSize);
        //2.3设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pageBean.setTotalCounts(totalCount);
        //2.4设置当前页面显示的List数据集合
        int start=(currentPage-1)*pageSize; //计算起始的记录数
        List<Route> routes = routeDao.findByPage(cid, start, pageSize,rname);
        pageBean.setList(routes);
        //2.5设置总页码
        int totalPages=totalCount%pageSize==0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        pageBean.setTotalPages(totalPages);
        //3.返回PageBean对象
        return pageBean;
    }


    @Override
    public Route findDetail(int rid) {
        //1.根据rid查询对应的Route对象
        Route route=routeDao.findOne(rid);
        //2.1根据rid查询对应的Img数据集合
        List<RouteImg> imgs = route_imgDao.findImgAll(rid);
        //2.2设置Route对象的routeImgList属性
        route.setRouteImgList(imgs);
        //3.1根据商家sid查询对应的Seller对象
        Seller seller = sellerDao.findByRid(route.getSid());
        //3.2设置Route对象的seller属性
        route.setSeller(seller);

        return route;
    }
}
