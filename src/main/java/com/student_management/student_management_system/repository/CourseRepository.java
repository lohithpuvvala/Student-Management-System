package com.student_management.student_management_system.repository;

import com.student_management.student_management_system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Course entity.
 * Extends JpaRepository for basic CRUD operations and JpaSpecificationExecutor for querydsl support
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

    /**
     * Custom query method to find course by code
     * @param code unique code of the course
     * @return Optional<Course> containing the course if found
     */

    Optional<Course> findByCode(String code);
}
