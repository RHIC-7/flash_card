package com.flash_card.demo.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.flash_card.demo.entity.Users;
import com.flash_card.demo.repository.UserRepositoryImpl;

@Controller
public class Login {

    private final UserRepositoryImpl userRepository;

    Login(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String getMethodName(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return "/login";
    }

    @PostMapping("/login")
    public String startFlashCard(Model model, @ModelAttribute Users user) {
        Users registeredUser = userRepository.selectUser(user.getEmail());
        if (registeredUser == null) {
            return "redirect:/sign-up";
        } else {
            Integer userId = registeredUser.getId();
            model.addAttribute("userId", userId);
            return "redirect:/get/category/" + userId;
        }
    }

    @GetMapping("/sign-up")
    public String signupController(Model model) {
        Users newUser = new Users();
        model.addAttribute("newUser", newUser);
        return "/sign-up";
    }

    @PostMapping("/sign-up")
    public String registerUserController(Model model, @ModelAttribute Users newUser) {
        try {
            Integer affectedUser = userRepository.insertUser(newUser);
            if (affectedUser != 1) {
                throw new DataIntegrityViolationException("予期された一行の挿入が達成されませんでした。挿入された行数: " + affectedUser);
            }
        } catch (DataAccessException e) {
            System.out.println("予期されないデータが送信されました。内容を確認してください。");
            return "redirect:/sign-up";
        }
        model.addAttribute("newUser", newUser);
        return "redirect:/login";
    }

}
