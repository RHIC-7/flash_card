package com.flash_card.demo.repository;

import java.util.List;

import com.flash_card.demo.entity.Categories;

public interface CategoryRepository {
    int insertCategory(Categories categories);

    int updateCategory(Categories categories);

    List<Categories> selectCategories();

    List<Categories> selectUserCategories(Integer id);

    List<Categories> executeSqlCommand(String sql);
}