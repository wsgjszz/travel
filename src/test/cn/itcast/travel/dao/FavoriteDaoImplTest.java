package cn.itcast.travel.dao;

import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import org.junit.Test;

public class FavoriteDaoImplTest {

    FavoriteDaoImpl favoriteDao=new FavoriteDaoImpl();

    @Test
    public void favoriteByUidAndRid(){
        favoriteDao.favoriteByUidAndRid(1,2);
    }
}
