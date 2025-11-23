package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repo.InstructorRepo;
import peaksoft.service.InstructorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepo instructorRepo;

    @Override
    public void saveInstructor(Instructor instructor) {
        instructorRepo.saveInstructor(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepo.getAllInstructors();    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepo.getInstructorById(id);
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        instructorRepo.updateInstructor(id, instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        Instructor instructor = instructorRepo.getInstructorById(id);
        for (Course course : instructor.getCourses()) {
            course.getInstructors().remove(instructor);
    }
        instructorRepo.deleteInstructor(id);
}

    @Override
    public List<Course> getCourses(Long instructorId) {
        Instructor instructor = instructorRepo.getInstructorById(instructorId);
        return instructor.getCourses();
    }
}
