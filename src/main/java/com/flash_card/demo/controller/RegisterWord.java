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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flash_card.demo.entity.Words;
import com.flash_card.demo.repository.CategoryRepositoryImpl;
import com.flash_card.demo.repository.WordsRepository;

@Controller
@RequestMapping("/register")
public class RegisterWord {

    private final WordsRepository wordsRepository;
    private final CategoryRepositoryImpl categoryRepositoryImpl;

    public RegisterWord(WordsRepository wordsRepository, CategoryRepositoryImpl categoryRepositoryImpl) {
        this.wordsRepository = wordsRepository;
        this.categoryRepositoryImpl = categoryRepositoryImpl;
    }

    @GetMapping("/word")
    public String getMethodName(Model model) {
        List<Words> wordsList = wordsRepository.selectWords();
        model.addAttribute("words", wordsList);
        return "/register/word";
    }

    @GetMapping("/word/{userId:[\\d]+}")
    public String getMethodName(@PathVariable("userId") Integer userId,
            @RequestParam(value = "category", required = false, defaultValue = "DefaultCategory") String category,
            Model model) {
        Integer categoryId = categoryRepositoryImpl.CategoryNameToId(category);
        List<Words> wordsList = wordsRepository.selectWordsFromUserCategory(userId, categoryId);

        Words newWord = new Words();

        model.addAttribute("categoryName", category);
        model.addAttribute("wordsLength", wordsList.size());
        model.addAttribute("userId", userId);
        model.addAttribute("wordsList", wordsList);
        model.addAttribute("word", newWord);
        return "register/word";
    }

    @PostMapping("/word/{userId:[\\d]+}")
    public String postMethodName(@PathVariable("userId") Integer userId,
            @RequestParam(value = "category", required = false, defaultValue = "DefaultCategory") String category,
            @ModelAttribute Words words,
            RedirectAttributes redirectAttributes) {
        words.setUserId(userId);
        words.setCategoryId(categoryRepositoryImpl.CategoryNameToId(category));
        try {
            int affectedRows = wordsRepository.insertWord(words);

            if (affectedRows != 1) {
                throw new DataIntegrityViolationException("予期された一行の挿入が達成されませんでした。挿入された行数: " + affectedRows);
            }
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("warning", "登録された単語は既に登録済みです");
        } catch (DataAccessException e) {
            System.out.println("Failed to register new category due to an unexpected error: " + e.getMessage());
        }
        return "redirect:" + userId + "?category=" + category;
    }

}
