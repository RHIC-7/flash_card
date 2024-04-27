package com.flash_card.demo.controller;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flash_card.demo.entity.Categories;
import com.flash_card.demo.repository.CategoryRepository;

@Controller
@RequestMapping("/register")
public class RegisterCategory {

    private final CategoryRepository categoryRepository;

    public RegisterCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category")
    public String getMethodName(Model model) {
        List<Categories> categoriesList = categoryRepository.selectCategories();
        model.addAttribute("categories", categoriesList);
        return "/register/category";
    }

    @GetMapping("/category/{id:[\\d]+}")
    public String getMethodName(Model model, @PathVariable("id") Integer id) {
        List<Categories> categoriesList = categoryRepository.selectUserCategories(id);

        Categories newCategory = new Categories();

        model.addAttribute("categoriesLength", categoriesList.size());
        model.addAttribute("userId", id);
        model.addAttribute("categories", categoriesList);
        model.addAttribute("category", newCategory);
        return "/register/category";
    }

    @PostMapping("/category/{id:[\\d]+}")
    public String postMethodName(@PathVariable("id") Integer id, @ModelAttribute Categories categories) {
        categories.setUserId(id);
        try {
            int affectedRows = categoryRepository.insertCategory(categories);

            if (affectedRows != 1) {
                throw new DataIntegrityViolationException("予期された一行の挿入が達成されませんでした。挿入された行数: " + affectedRows);
            }
        } catch (DataAccessException e) {
            System.out.println("Failded to register new category");
        }
        return "redirect:/register/category/" + id;
    }

}
