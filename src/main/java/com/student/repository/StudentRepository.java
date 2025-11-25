package com.student.repository;

import com.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    List<Student> findByCourse(String course);

    List<Student> findBySemester(Integer semester);

    List<Student> findByFirstNameContainingIgnoreCase(String firstName);

    Optional<Student> findByRollNumber(String rollNumber);

}
