package ru.elseff.learn_jwt.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elseff.learn_jwt.persistence.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
