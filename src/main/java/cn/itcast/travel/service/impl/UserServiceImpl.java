package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    UserDao userDao=new UserDaoImpl();

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public User regist(User user) {
        //1.检查用户是否存在
        boolean flag = userDao.findByUsername(user.getUsername());
        if (flag){
            //用户已存在
            System.out.println("用户已存在");
            return null;
        }
        //2.保存用户记录
        user.setStatus("N"); //设置状态未激活
        String uuid = UuidUtil.getUuid(); //调用UuidUtil获取唯一字符串
        user.setCode(uuid); //设置激活码
        userDao.save(user);
        //3.发送激活邮件
        new MailUtils().sendMail(user.getEmail(),"请点击激活<a href='http://localhost/travel/user/active?code="+uuid+"'>【黑马旅游网】</a>","激活邮件");
        return user;
    }

    /**
     * 用户激活
     * @param code
     * @return
     */
    @Override
    public User active(String code) {
        //1.根据激活码查询用户
        User user=userDao.findByCode(code);
        if (user==null) return null;
        //2.修改对应用户的激活状态
        userDao.updateStatus(user.getUid());
        return user;
    }

    @Override
    public User login(User user) {
        //1.根据用户名和密码查询对象
        User login = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        //2.返回结果
        return login;
    }
}
