package ru.elseff.learn_jwt.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elseff.learn_jwt.persistence.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String aLong);
    boolean existsByUsername(String username);
}
