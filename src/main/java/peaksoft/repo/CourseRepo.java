package peaksoft.repo;


import org.springframework.web.multipart.MultipartFile;
import peaksoft.entity.Course;

import java.io.IOException;
import java.util.List;

public interface CourseRepo {
    void saveCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    void updateCourse(Long id, Course course , MultipartFile imageFile) throws IOException;
    void deleteCourse(Long id);
    void merge(Course course);
}
