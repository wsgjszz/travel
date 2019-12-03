package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {

    /**
     * 根据线路rid和用户uid判断当前用户是否收藏该线路
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    public int findCountByRid(int rid);

    public void favoriteByUidAndRid(int uid, int rid);
}
