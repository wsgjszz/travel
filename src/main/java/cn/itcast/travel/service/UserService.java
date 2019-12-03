package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {

    public User regist(User user);

    public User active(String code);

    public User login(User user);
}
