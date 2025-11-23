package peaksoft.service;

import org.springframework.web.multipart.MultipartFile;
import peaksoft.entity.Course;

import java.io.IOException;
import java.util.List;

public  interface CourseService {
    void saveCourse(Course course);
    void assignInstructor(Long courseId, Long instructorId);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    void updateCourse(Long id, Course course, MultipartFile imageFile) throws IOException;
    void deleteCourse(Long id);
}
