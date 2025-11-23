package peaksoft.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import peaksoft.entity.Course;

import peaksoft.entity.Instructor;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.InstructorRepo;
import peaksoft.service.CourseService;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl  implements CourseService {
    private final CourseRepo courseRepo;
    private final InstructorRepo instructorRepo;
    @Override
    public void saveCourse(Course course) {
        courseRepo.saveCourse(course);
    }

    @Override
    @Transactional
    public void assignInstructor(Long courseId, Long instructorId) {
        Course course = courseRepo.getCourseById(courseId);
        Instructor instructor = instructorRepo.getInstructorById(instructorId);
        course.getInstructors().add(instructor);
        instructor.getCourses().add(course);
        courseRepo.merge(course);
    }


    @Override
    public List<Course> getAllCourses() {
        return courseRepo.getAllCourses();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.getCourseById(id);
    }

    @Override
    public void updateCourse(Long id, Course course, MultipartFile imageFile) throws IOException {
        courseRepo.updateCourse(id, course, imageFile );
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepo.deleteCourse(id);
    }
}

