package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entity.Lession;

import peaksoft.repo.LessionRepo;
import peaksoft.service.LessionService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class LessionServiceImpl implements LessionService {

    private final LessionRepo lessionRepo;



    @Override
    public void addLesson(Lession lession, Long courseId) {
        lessionRepo.addLession(lession);
    }

    @Override
    public void addLessonToCourse(Long courseId, Lession lession) {
        lessionRepo.addLessionToCourse(courseId, lession);
    }

    @Override
    public List<Lession> getAllLessons() {
        return lessionRepo.getAllLessons();
    }

    @Override
    public Lession getLessonById(Long id) {
        return lessionRepo.getLessonById(id);
    }

    @Override
    public void updateLesson(Long id, Lession lession) {
        lessionRepo.updateLession(id, lession);
    }

    @Override
    public void deleteLesson(Long id) {
        lessionRepo.deleteLession(id);
    }
}
