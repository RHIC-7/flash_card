package com.flush_card.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.flush_card.demo.entity.Categories;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    };

    @Override
    public void insertCategory(Categories categories, String userName, Integer userId) {

    };

    @Override
    public int updateCategory(Categories categories) {
        return 0;
    };

    @Override
    public List<Categories> selectCategories() {
        String sql = "SELECT * FROM categories";
        List<Categories> list = this.executeSqlCommand(sql);

        return list;
    }

    @Override
    public List<Categories> selectUserCategories(Integer id) {
        String sql = "SELECT * FROM categories WHERE user_id=" + id;
        List<Categories> list = this.executeSqlCommand(sql);

        return list;
    }

    @Override
    public List<Categories> executeSqlCommand(String sql) {
        List<Map<String, Object>> categoryListObject = jdbcTemplate.queryForList(sql);
        List<Categories> list = categoryListObject.stream().map(result -> new Categories(
                (Integer) result.get("category_id"),
                (String) result.get("category_name"),
                (Integer) result.get("user_id"),
                (LocalDateTime) result.get("created_at"))).collect(Collectors.toList());
        return list;
    }
}
