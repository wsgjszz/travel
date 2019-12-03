package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteService {

    public PageBean<Route> queryPage(int cid, int currentPage, int pageSize,String rname);

    /**
     * 根据rid查询线路详情
     * @param rid
     * @return
     */
    public Route findDetail(int rid);
}
