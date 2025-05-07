package ait.cohort55.student.controller;

import ait.cohort55.student.dto.ScoreDto;
import ait.cohort55.student.dto.StudentAddDto;
import ait.cohort55.student.dto.StudentDto;
import ait.cohort55.student.dto.StudentUpdateDto;
import ait.cohort55.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class StudentController {
   // @Autowired

    private final StudentService studentService;

    @PostMapping("/student")
    public Boolean addStudent(@RequestBody StudentAddDto studentAddDto) {
        return studentService.addStudent(studentAddDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }

    @PatchMapping("/student/{id}")
    public StudentAddDto updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(id, studentUpdateDto);
    }

    @PatchMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable Long id, @RequestBody ScoreDto scoreDto) {
        return studentService.addScore(id, scoreDto);
    }

    @GetMapping("/students/name/{name}")
    public List<StudentDto> findAllStudentsByName(@PathVariable String name) {
        return studentService.findAllStudentsByName(name);
    }
@GetMapping("/quantity/students")
    public Long getStudentsQuantityByName(@RequestParam Set<String> names) {
        return studentService.getStudentsQuantityByName(new HashSet<>(names));
    }

    @GetMapping("/students/exam/{exam}/minscore/{minScore}")
    public List<StudentDto> findStudentsByExamNameMinScore(@PathVariable String exam, @PathVariable Integer minScore) {
        return studentService.findStudentsByExamNameMinScore(exam, minScore);
    }
}
