package peaksoft.repo.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.repo.StudentRepo;

import java.util.List;


@Repository
@Transactional
public class StudentRepoImpl implements StudentRepo {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return entityManager.createQuery(
                "SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses",
                Student.class
        ).getResultList();

    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.createQuery(
                "SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id = :id",
                Student.class
        ).setParameter("id", id).getSingleResult();
    }


    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = entityManager.find(Student.class, id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        entityManager.merge(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        Student st = entityManager.find(Student.class, id);
        for (Course c : st.getCourses()) {
            c.getStudents().remove(st);
        }
        st.getCourses().clear();
        entityManager.remove(st);
    }

}

