package cn.itcast.travel.service;

import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import org.junit.Test;

public class FavoriteServiceImplTest {

    FavoriteServiceImpl favoriteService=new FavoriteServiceImpl();

    @Test
    public void favoriteService(){
        favoriteService.favorite(4,3);
    }
}
