package com.student_management.student_management_system.service;

import com.student_management.student_management_system.model.Student;
import com.student_management.student_management_system.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Method to get All Students

    public List<Student> getAllStudents() {
        log.info("Fetching all students");
        try{
            List<Student> students = studentRepository.findAll();
            log.debug("Found {} students", students.size());
            return students;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Failed to fetch students",e);
        }
    }

    //Method to get Single Student by ID
}
