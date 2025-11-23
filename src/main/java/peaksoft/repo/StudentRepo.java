package peaksoft.repo;

import peaksoft.entity.Student;

import java.util.List;

public interface StudentRepo {
    void  addStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    void updateStudent(Long id,Student student);
    void deleteStudent(Long id);
}
