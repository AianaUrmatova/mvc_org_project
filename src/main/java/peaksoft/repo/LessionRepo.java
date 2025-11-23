package peaksoft.repo;

import peaksoft.entity.Lession;

import java.util.List;

public interface LessionRepo {
    void  addLession(Lession lession);
    void addLessionToCourse(Long courseId, Lession lession);
    List<Lession> getAllLessons();
    Lession getLessonById(Long id);
    void updateLession(Long id,Lession lession);
    void deleteLession(Long id);
}
