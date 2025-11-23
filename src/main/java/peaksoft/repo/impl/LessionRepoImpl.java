package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Lession;
import peaksoft.repo.LessionRepo;

import java.util.List;
@Repository
@Transactional
public class LessionRepoImpl implements LessionRepo {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addLession(Lession lession) {
        entityManager.persist(lession);
    }

    @Override
    public void addLessionToCourse(Long courseId, Lession lession) {
        Course course = entityManager.find(Course.class, courseId);
        lession.setCourse(course);
        entityManager.persist(lession);
        course.getLessons().add(lession);
    }

    @Override
    public List<Lession> getAllLessons() {
        return entityManager.createQuery("select  l from Lession l", Lession.class).getResultList();
    }

    @Override
    public Lession getLessonById(Long id) {
        return entityManager.find(Lession.class, id);
    }

    @Override
    public void updateLession(Long id, Lession lession) {
        Lession lession1 = entityManager.find(Lession.class, id);
        lession1.setTitle(lession.getTitle());
        lession1.setDescription(lession.getDescription());
        lession1.setPublisherDate(lession.getPublisherDate());
        entityManager.merge(lession);
    }

    @Override
    public void deleteLession(Long id) {
        entityManager.remove(entityManager.find(Lession.class, id));
    }
}

