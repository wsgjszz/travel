package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {

    /**
     * 根据线路rid查询对应的Seller记录
     * @param rid
     * @return
     */
    public Seller findByRid(int rid);
}
