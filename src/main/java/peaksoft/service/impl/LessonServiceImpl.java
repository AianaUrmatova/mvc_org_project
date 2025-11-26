package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entity.Lesson;

import peaksoft.repo.LessonRepo;
import peaksoft.service.LessonService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepo lessonRepo;



    @Override
    public void addLesson(Lesson lesson, Long courseId) {
        lessonRepo.addLesson(lesson);
    }

    @Override
    public void addLessonToCourse(Long courseId, Lesson lesson) {
        lessonRepo.addLessonToCourse(courseId, lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepo.getAllLessons();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepo.getLessonById(id);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        lessonRepo.updateLession(id, lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepo.deleteLession(id);
    }
}
