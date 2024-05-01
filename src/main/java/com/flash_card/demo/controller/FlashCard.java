package com.flash_card.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flash_card.demo.entity.Words;
import com.flash_card.demo.repository.CategoryRepositoryImpl;
import com.flash_card.demo.repository.WordsRepositoryImpl;

@Controller
@RequestMapping("/flashcard")
public class FlashCard {

    private final WordsRepositoryImpl wordsRepository;
    private final CategoryRepositoryImpl categoryRepository;

    FlashCard(CategoryRepositoryImpl categoryRepository, WordsRepositoryImpl wordsRepository) {
        this.categoryRepository = categoryRepository;
        this.wordsRepository = wordsRepository;
    }

    @GetMapping("/{userId:[\\d]+}")
    public String getMethodName(@PathVariable("userId") Integer userId,
            @RequestParam(value = "category", required = false, defaultValue = "DefaultCategory") String category,
            Model model) {
        Integer categoryId = categoryRepository.CategoryNameToId(category);
        List<Words> wordsList = wordsRepository.selectWordsFromUserCategory(userId, categoryId);
        model.addAttribute("words", wordsList);
        model.addAttribute("userId", userId);
        return "/flashcard/flash";
    }

    @GetMapping("/start/{userId:[\\d]+}")
    public String startFlashCard(@PathVariable("userId") Integer userId,
            @RequestParam(value = "category", required = false, defaultValue = "DefaultCategory") String category,
            Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("categoryName", category);
        return "/flashcard/start";
    }
}
