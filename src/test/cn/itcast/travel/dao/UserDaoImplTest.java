package cn.itcast.travel.dao;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import org.junit.Test;

public class UserDaoImplTest {

    UserDaoImpl userDao=new UserDaoImpl();

    @Test
    public void findByUsernameAndPassword(){
        User testuser4 = userDao.findByUsernameAndPassword("testuser4", "20288524");
        System.out.println(testuser4);
    }
}
