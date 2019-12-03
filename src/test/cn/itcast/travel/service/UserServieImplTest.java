package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServieImplTest {

    UserService userService=new UserServiceImpl();

    @Test
    public void regist(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("20288524");
        user.setName("张三");
        user.setTelephone("13592822028");
        user.setBirthday("2000-01-18");
        user.setEmail("wsgjszz@163.com");
        user.setSex("男");
        User regist = userService.regist(user);
    }

    @Test
    public void active(){
        User user = new User();
        user.setUsername("testuser4");
        user.setPassword("20288524");
        user.setName("张三");
        user.setTelephone("13592822028");
        user.setBirthday("2000-01-18");
        user.setEmail("wsgjszz@163.com");
        user.setSex("男");
        User regist = userService.regist(user);
        userService.active(regist.getCode());
    }

    @Test
    public void login(){
        User user = new User();
        user.setUsername("testuser4");
        user.setPassword("20288524");
        User login = userService.login(user);
        System.out.println(login);
    }
}
