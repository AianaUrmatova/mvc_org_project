package peaksoft.repo;

import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorRepo {
    void saveInstructor(Instructor instructor);
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(Long id);
    void updateInstructor(Long id, Instructor instructor);
    void deleteInstructor(Long id);
}
