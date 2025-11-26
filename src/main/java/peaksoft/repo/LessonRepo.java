package peaksoft.repo;

import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonRepo {
    void  addLesson(Lesson lesson);
    void addLessonToCourse(Long courseId, Lesson lesson);
    List<Lesson> getAllLessons();
    Lesson getLessonById(Long id);
    void updateLession(Long id, Lesson lesson);
    void deleteLession(Long id);
}
