package com.flush_card.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.flush_card.demo.entity.Categories;

@AutoConfigureMockMvc
@SpringBootTest
public class CategoryRepositoryImplTest {

    @Autowired
    private CategoryRepositoryImpl categoryRepository;

    @Nested
    @DisplayName("Category Repository Tests")
    class categoriesListTests {
        private List<Categories> categoryList;

        @BeforeEach
        void getCategoriesList() {
            this.categoryList = categoryRepository.selectCategories();
        }

        @Test
        void categoriesListIsNotEmpty() throws Exception {
            assertFalse(categoryList.isEmpty());
        }

        @Test
        void categoriesListHasDefaultValue() throws Exception {
            assertEquals("AWS", categoryList.get(0).getCategoryName());
        }
    }

    @Nested
    @DisplayName("Category Repository Extracted Id Test")
    class extractedCategoriesListTest {
        private List<Categories> categoryList;

        @BeforeEach
        void getExtractedCategoriesList() {
            this.categoryList = categoryRepository.selectUserCategories(2);
        }

        @Test
        void categoriesListHasDefaultValue() throws Exception {
            assertEquals("Azure", categoryList.get(0).getCategoryName());
        }
    }

}
