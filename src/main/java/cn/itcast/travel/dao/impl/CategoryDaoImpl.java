package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有记录
     * @return 包含所有记录的List集合
     */
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }


}
