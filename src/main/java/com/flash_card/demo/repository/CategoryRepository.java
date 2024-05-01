package com.flash_card.demo.repository;

import java.util.List;

import com.flash_card.demo.entity.Categories;

public interface CategoryRepository {
    int insertCategory(Categories categories);

    int updateCategory(Categories categories);

    String CategoryIdToName(Integer id);

    Integer CategoryNameToId(String name);

    List<Categories> selectCategories();

    List<Categories> selectUserCategories(Integer id);
}