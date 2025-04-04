package com.student_management.student_management_system.repository;

import com.student_management.student_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Student entity.
 * Extends JpaRepository for basic CRUD operations and JpaSpecificationExecutor for querydsl support
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    /**
     *Custom query method to find student by email
     *@param email unique email of the student
     *@return Optional<Student> containing the student if found
     */

    Optional<Student> findByEmail(String email);
}
