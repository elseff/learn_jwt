package ru.elseff.learn_jwt.web.api.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.elseff.learn_jwt.persistence.User;
import ru.elseff.learn_jwt.web.api.modules.user.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getSpecific(@PathVariable Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
