package ait.cohort55.student.service;

import ait.cohort55.student.dao.StudentRepository;
import ait.cohort55.student.dto.ScoreDto;
import ait.cohort55.student.dto.StudentAddDto;
import ait.cohort55.student.dto.StudentDto;
import ait.cohort55.student.dto.StudentUpdateDto;
import ait.cohort55.student.dto.exeptions.StudentNotFoundException;
import ait.cohort55.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Component
public class StudentServiceImpl implements StudentService{
    @Autowired

    private StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if(studentRepository.findById(studentAddDto.getId()).isPresent()){
            return false;
        }
        Student student = new Student(studentAddDto.getId(),studentAddDto.getName(),studentAddDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return student == null ? null : new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentDto removeStudent(Long id) {
        StudentDto student = findStudent(id);
        studentRepository.deleteById(id);
        return new StudentDto(student.getId(), student.getName(), student.getScores());
    }

    @Override
    public StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.setName(studentUpdateDto.getName());
        student.setPassword(studentUpdateDto.getPassword());
        studentRepository.save(student);
        return new StudentAddDto(student.getId(), student.getName(), student.getPassword());
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        Map<String, Integer> scores = student.getScores();
        if(scores.containsKey(scoreDto.getExamName())){
            return false;
        } else{
            scores.put(scoreDto.getExamName(), scoreDto.getScore());
            studentRepository.save(student);
            return true;
        }

    }

    @Override
    public List<StudentDto> findAllStudentsByName(String name) {
        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student student : studentRepository.findAll()){
            if(student.getName().equals(name)){
                studentDtos.add(new StudentDto(student.getId(), student.getName(), student.getScores()));
            }
        }
        return studentDtos;
    }

    @Override
    public Long getStudentsQuantityByName(Set<String> names) {
        long count = 0;
        for(Student student : studentRepository.findAll()){
            if(names.contains(student.getName())){
                count++;
            }
        }
        return count;
    }

    @Override
    public List<StudentDto> findStudentsByExamNameMinScore(String examName, Integer minScore) {
        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student student : studentRepository.findAll()){
            int score = student.getScores().get(examName);
            if(score >= minScore){
                studentDtos.add(new StudentDto(student.getId(), student.getName(), student.getScores()));
            }
        }
        return studentDtos;
    }
}
