package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface Route_ImgDao {

    /**
     * 根据线路rid查询对应的Img数据集合
     * @param rid
     * @return
     */
    public List<RouteImg> findImgAll(int rid);
}
