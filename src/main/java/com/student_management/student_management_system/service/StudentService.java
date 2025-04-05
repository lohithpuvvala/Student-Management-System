package com.student_management.student_management_system.service;

import com.student_management.student_management_system.model.Student;
import com.student_management.student_management_system.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Student getStudentById(Long id) {
        log.info("Fetching student by id: {}", id);
        try{
            Student student = studentRepository
                                .findById(id)
                                .orElseThrow(() ->  new EntityNotFoundException("Student not found with id: "+ id));
            return student;
        }catch (Exception e){
            log.error("Error fetching student with ID: {}"+id+e);
            throw new RuntimeException("Failed to fetch student",e);
        }
    }

    //Method to save a new student
    public Student saveStudent(Student student) {
        log.info("Saving new student: {}", student.getName());
        try{
            return studentRepository.save(student);
        }catch (Exception e){
            log.error("Error saving student: {}", student.getName(),e);
            throw new RuntimeException("Failed to save student",e);
        }
    }

    //Method to update an existing student.
    public Student updateStudent(Student student) {
        log.info("Updating student: {}", student.getName());
        try{
            Student existingStudent = studentRepository
                    .findById(student.getId())
                    .orElseThrow(() ->  new EntityNotFoundException("Student not found with id: "+ student.getId()));
            return studentRepository.save(existingStudent);
        }catch (Exception e){
            log.error("Error updating student: {}", student.getName(),e);
            throw new RuntimeException("Failed to update student",e);
        }
    }

    public void deleteStudent(Long id) {
        log.info("Deleting student: {}", id);
        try{
            Student student = studentRepository
                    .findById(id)
                    .orElseThrow(() ->  new EntityNotFoundException("Student not found with id: "+ id));
            studentRepository.delete(student);
        }catch (Exception e){
            log.error("Error deleting student with ID: {}", id,e);
            throw new RuntimeException("Failed to delete student",e);
        }


        //Custom exception class for resource not found scenarios
        @SuppressWarnings("serial")
         class ResourceNotFoundException extends EntityNotFoundException{
            public ResourceNotFoundException(String message){
                super(message);
            }
        }

    }
}
