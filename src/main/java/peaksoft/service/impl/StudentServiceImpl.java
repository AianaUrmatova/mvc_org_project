package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.StudentRepo;
import peaksoft.service.StudentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    @Override
    public void addStudent(Student student) {
        studentRepo.addStudent(student);
    }

    @Override
    public void addStudentsToCourse(Long courseId, Long studentId) {
        Course course = courseRepo.getCourseById(courseId);
        Student student = studentRepo.getStudentById(studentId);
        course.getStudents().add(student);
        student.getCourses().add(course);
        courseRepo.merge(course);
        studentRepo.updateStudent(student.getId(), student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.getAllStudents();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.getStudentById(id);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        studentRepo.updateStudent(id, student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.deleteStudent(id);
    }
}
