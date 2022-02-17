package cz.tul.controllers;

import cz.tul.data.User;
import cz.tul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class UsersController {

    private UserService usersService;

    @Autowired
    public void setUsersService(UserService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/users")
    public String showUsers(Model model) {

        List<User> users = usersService.getAllUsers();

        model.addAttribute("users", users);

        return "users";
    }

}
