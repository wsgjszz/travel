package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     * 根据分类cid和查询条件rname查询对应的记录总数量
     * @param cid
     * @param rname
     * @return
     */
    public int findTotalCount(int cid,String rname);

    /**
     * 查询分页展示的List数据集合
     * @param cid
     * @param star
     * @param size
     * @param rname
     * @return
     */
    public List<Route> findByPage(int cid,int star,int size,String rname);

    /**
     * 根据线路rid查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
