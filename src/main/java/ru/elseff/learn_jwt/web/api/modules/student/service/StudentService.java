package ru.elseff.learn_jwt.web.api.modules.student.service;

import ru.elseff.learn_jwt.persistence.Student;

import java.util.List;

public interface StudentService{
    List<Student> getAll();

    Student getSpecific(Long id);

    Student addStudent(Student student);

    void deleteStudent(Long id);
}
