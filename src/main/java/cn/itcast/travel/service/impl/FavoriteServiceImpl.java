package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    FavoriteDao favoriteDao=new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(int rid, int uid) {
        //调用dao查询
        Favorite favorite = favoriteDao.findByRidAndUid(rid, uid);
        //判断，返回
        if (favorite!=null){
            return true;
        }
        return false;
    }

    @Override
    public int findTimes(int rid) {
        //调用dao查询
        int count=favoriteDao.findCountByRid(rid);
        return count;
    }

    public void favorite(int uid, int rid) {
        //调用dao
        favoriteDao.favoriteByUidAndRid(uid,rid);
    }
}
