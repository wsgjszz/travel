package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public boolean findByUsername(String username) {
        User user = null;
        try {
            //1.定义sql
            String sql="select * from tab_user where username=?";
            //2.执行sql
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){}
        if (user!=null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 保存用户记录
     * @param user
     */
    @Override
    public void save(User user) {
        //1.定义sql
        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql
        int update = jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),
                user.getName(),user.getBirthday(),user.getSex(), user.getTelephone(),
                user.getEmail(),user.getStatus(),user.getCode());
        if (update!=0){
            System.out.println("用户记录保存成功");
        }else {
            System.out.println("用户记录保存失败");
        }
    }

    /**
     * 根据激活码查询用户
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            //1.定义sql
            String sql="select * from tab_user where code=?";
            //2.执行sql
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (Exception e){}
        return user;
    }

    /**
     * 更新用户的激活状态
     */
    @Override
    public void updateStatus(int uid) {
        //1.定义sql
        String sql="update tab_user set status='Y' where uid=?";
        //2.执行sql
        int update = jdbcTemplate.update(sql, uid);
        if (update!=0){
            System.out.println("激活成功");
        }else {
            System.out.println("激活失败");
        }
    }

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            //1.定义sql
            String sql="select * from tab_user where username=? and password=?";
            //2.执行
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        }catch (Exception e){}
        return user;
    }
}
