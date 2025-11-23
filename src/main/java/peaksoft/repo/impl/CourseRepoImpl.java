package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import peaksoft.entity.Course;
import peaksoft.repo.CourseRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
@Repository
@Transactional
public class CourseRepoImpl  implements CourseRepo {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return entityManager.createQuery("select c from Course c", Course.class).getResultList();
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.createQuery(
                        "select c from Course c " +
                                "left join fetch c.lessons " +
                                "left join fetch c.students " +
                                "left join fetch c.instructors " +
                                "where c.id = :id", Course.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void updateCourse(Long id, Course course, MultipartFile imageFile) throws IOException {
        Course course1 = entityManager.find(Course.class, id);

        if (course1 != null) {
            course1.setTitle(course.getTitle());
            course1.setDescription(course.getDescription());
            course1.setDateOfStart(course.getDateOfStart());
            if (!imageFile.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("uploads");

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                course1.setImageUrl("/uploads/" + fileName);

            }
        }
    }



    @Override
    public void deleteCourse(Long id) {
        entityManager.remove(entityManager.find(Course.class, id));
    }


    public void merge(Course course) {
        entityManager.merge(course);
    }
}

