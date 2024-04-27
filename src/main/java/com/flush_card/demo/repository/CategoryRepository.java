package com.flush_card.demo.repository;

import java.util.List;

import com.flush_card.demo.entity.Categories;

public interface CategoryRepository {
    void insertCategory(Categories categories, String categoryName, Integer userId);

    int updateCategory(Categories categories);

    List<Categories> selectCategories();

    List<Categories> selectUserCategories(Integer id);

    List<Categories> executeSqlCommand(String sql);
}