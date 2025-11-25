package com.student.service;

import com.student.entity.Student;
import com.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Optional<Student> getStudentByRollNumber(String rollNumber) {
        return studentRepository.findByRollNumber(rollNumber);
    }

    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    public List<Student> getStudentsBySemester(Integer semester) {
        return studentRepository.findBySemester(semester);
    }

    public List<Student> searchStudentsByName(String firstName) {
        return studentRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student existingStudent = student.get();
            existingStudent.setFirstName(studentDetails.getFirstName());
            existingStudent.setLastName(studentDetails.getLastName());
            existingStudent.setEmail(studentDetails.getEmail());
            existingStudent.setRollNumber(studentDetails.getRollNumber());
            existingStudent.setCourse(studentDetails.getCourse());
            existingStudent.setSemester(studentDetails.getSemester());
            existingStudent.setPhoneNumber(studentDetails.getPhoneNumber());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
