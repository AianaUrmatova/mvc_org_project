package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.repo.LessonRepo;

import java.util.List;
@Repository
@Transactional
public class LessonRepoImpl implements LessonRepo {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addLesson(Lesson lesson) {
        entityManager.persist(lesson);
    }

    @Override
    public void addLessonToCourse(Long courseId, Lesson lesson) {
        Course course = entityManager.find(Course.class, courseId);
        lesson.setCourse(course);
        entityManager.persist(lesson);
        course.getLessons().add(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return entityManager.createQuery("select  l from Lesson l", Lesson.class).getResultList();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return entityManager.find(Lesson.class, id);
    }

    @Override
    public void updateLession(Long id, Lesson lesson) {
        Lesson lesson1 = entityManager.find(Lesson.class, id);
        lesson1.setTitle(lesson.getTitle());
        lesson1.setDescription(lesson.getDescription());
        lesson1.setPublisherDate(lesson.getPublisherDate());
        entityManager.merge(lesson);
    }

    @Override
    public void deleteLession(Long id) {
        entityManager.remove(entityManager.find(Lesson.class, id));
    }
}

