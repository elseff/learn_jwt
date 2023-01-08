package ru.elseff.learn_jwt.web.api.modules.user.service;

import ru.elseff.learn_jwt.persistence.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(Long id);

    User addUser(User user);

    User getByUsername(String username);

    boolean existsById(Long id);

    boolean existsByUsername(String username);

    void deleteUser(Long id);
}
