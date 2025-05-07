package ait.cohort55.student.dao;

import ait.cohort55.student.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Stream<Student> findStudentByNameIgnoreCase(String name);

}
