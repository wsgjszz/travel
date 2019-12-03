package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;


public class FavoriteDaoImpl implements FavoriteDao {

    JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql="select * from tab_favorite where rid=? and uid=?";
            favorite = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        }catch (Exception e){}
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql="select count(*) from tab_favorite where rid=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void favoriteByUidAndRid(int uid, int rid) {
        String sql="INSERT INTO tab_favorite VALUES(?,?,?)";
        jdbcTemplate.update(sql,rid,new Date(),uid);
    }
}
