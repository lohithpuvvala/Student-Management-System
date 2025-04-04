package com.student_management.student_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.sql.Update;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull(message = "Course name is required")
    private String name;

    @Column(name = "code",nullable = false)
    @Length(min = 6, max = 10, message = "Course code must be between 6 and 10 characters")
    @NotNull(message = "Course code is required")
    private String code;

    @Column(name = "credits", nullable = false)
    @Min(1)
    @Max(5)
    private Integer credits;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(
            mappedBy = "courses",
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;
}
