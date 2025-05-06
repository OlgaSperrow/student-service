package ait.cohort55.student.dao;

import ait.cohort55.student.model.Student;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(long id);
    void deleteById(long id);
    Iterable<Student> findAll();

}
