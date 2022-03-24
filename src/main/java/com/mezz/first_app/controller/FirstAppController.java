package com.mezz.first_app.controller;

import java.util.List;

import com.mezz.first_app.entity.User;
import com.mezz.first_app.service.FirstAppService;
import com.mezz.first_app.service.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FirstAppController {
    @Autowired 
    private FirstAppService service;

    @GetMapping (path = "/Users")
    public String showUserList (Model model){
        List<User> listUsers = service.listUsers();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("activePage", "users");
        return "users";
    }

    @GetMapping (path = "/Users/New")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle", "Add New User"); 
        model.addAttribute("activePage", "newUser");
        return "user_form";
    }

    @PostMapping("/findUser")
    public String findUser (@RequestParam String lastName, Model model){
        List<User> users = service.findByLastName(lastName);
        model.addAttribute("listUsers", users);
        return "users";
    }

    @PostMapping (path = "/Users/Save")
    public String saveUser(User user, RedirectAttributes ra){
        service.addUser(user);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/Users";
    }

    @GetMapping (path = "/Users/Edit/{id}")
    public String showEditForm(@PathVariable ("id") Integer id, Model model, RedirectAttributes ra){
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: "+id+")"); 
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/Users"; 
        }
    }

    @GetMapping (path = "/Users/Delete/{id}")
    public String deleteUser(@PathVariable ("id") Integer id, RedirectAttributes ra){
        try {
            service.deleteUser(id);
            ra.addFlashAttribute("message","The user Id "+id+" has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/Users"; 
    }

}
