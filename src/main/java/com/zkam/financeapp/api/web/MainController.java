package com.zkam.financeapp.api.web;

import com.zkam.financeapp.api.CustomerAccount;
import com.zkam.financeapp.api.User;
import com.zkam.financeapp.api.rest.UserController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    UserController userController;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userController.listAllUsers());
        return "index";
    }

    @RequestMapping("/add-user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add-user/save")
    public String addUser(@Valid User user) {
        userController.create(user);
        return "redirect:/";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userController.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/user-detail/{id}")
    public String createAccount(@PathVariable Long id, Model model) {
        User user = userController.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("accounts", user.getAccounts());
        model.addAttribute("infoText", user.getName() + "'s Accounts");
        return "user-detail";
    }

    @RequestMapping("/add-account/{id}")
    public String addAccount(@PathVariable Long id, Model model) {
        User user = userController.getById(id);
        CustomerAccount newAccount = new CustomerAccount();
        newAccount.setUserId(user.getUserId());
        model.addAttribute("newAccount", newAccount);
        return "create-account";
    }

    @PostMapping("/create-account/save")
    public String createAccount(@Valid CustomerAccount customerAccount) {
        userController.createAccount(customerAccount.getUserId(), Optional.ofNullable(customerAccount.getTransactionAmount()));
        return "redirect:/user-detail/" + customerAccount.getUserId();
    }

}
