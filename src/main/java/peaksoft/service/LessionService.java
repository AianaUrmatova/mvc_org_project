package peaksoft.service;

import peaksoft.entity.Lession;

import java.util.List;

public interface LessionService {
    void  addLesson(Lession lession, Long courseId);
    void addLessonToCourse(Long courseId, Lession lession);
    List<Lession> getAllLessons();
    Lession getLessonById(Long id);
    void updateLesson(Long id,Lession lession);
    void deleteLesson(Long id);
}
