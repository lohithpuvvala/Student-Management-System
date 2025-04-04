package com.student_management.student_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;



@Data
@Entity
@Table(name = "students",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "email"
        )
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", length = 50)
    @NotNull(message = "Name is required")
    private String name;

    @Column(name = "email", unique = true , updatable = true)
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "date_of_birth", nullable = false, columnDefinition = "DATE")
    @Past(message = "Date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private SortedSet<Course> courses = new TreeSet<>(Comparator.comparing(Course::getCode));
}
