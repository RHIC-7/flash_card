package com.flash_card.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flash_card.demo.entity.Categories;
import com.flash_card.demo.repository.CategoryRepository;
import com.flash_card.demo.repository.WordsRepository;

@Controller
@RequestMapping("/get")
public class GetCategory {

    private final CategoryRepository categoryRepository;
    private final WordsRepository wordsRepository;

    public GetCategory(CategoryRepository categoryRepository, WordsRepository wordsRepository) {
        this.categoryRepository = categoryRepository;
        this.wordsRepository = wordsRepository;
    }

    @GetMapping("/category")
    public String getCategories(Model model) {
        List<Categories> categoriesList = categoryRepository.selectCategories();
        model.addAttribute("categories", categoriesList);
        return "/get/category";
    }

    @GetMapping("/category/{id}")
    public String getCategories(Model model, @PathVariable("id") String id) {
        List<Categories> categoriesList = categoryRepository.selectUserCategories(Integer.parseInt(id));
        List<Integer> wordInCategory = wordsRepository.numberOfWordsInEachCategory(Integer.parseInt(id));
        model.addAttribute("wordListLength", wordInCategory);
        model.addAttribute("categories", categoriesList);
        model.addAttribute("userId", id);
        return "/get/category";
    }

}
