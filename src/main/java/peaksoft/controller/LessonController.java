package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Lesson;
import peaksoft.service.CourseService;
import peaksoft.service.LessonService;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final CourseService courseService;

    @GetMapping()
    public String getAllLessons(Model model) {
        model.addAttribute("lessons",lessonService.getAllLessons());
        return "getAllLessons";
    }

    @GetMapping("/new")
    public String createLesson(Model model) {
        model.addAttribute("newLesson", new Lesson());
        model.addAttribute("allCourses", courseService.getAllCourses());
        return "newLesson";
    }
    @PostMapping("/save")
    public String saveLesson(@RequestParam("courseId") Long courseId,
                             @ModelAttribute("newLesson") Lesson lesson) {
        lessonService.addLessonToCourse(courseId, lesson);
        return "redirect:/lessons";
    }



    @GetMapping("/{id}")
    public String getLesson(@PathVariable("id") Long id, Model model) {
        model.addAttribute("lesson",lessonService.getLessonById(id));
        return "updateLesson";
    }
    @PostMapping("/update/{id}")
    public String updateLesson(@PathVariable("id") Long id, @ModelAttribute("newLesson") Lesson lesson) {
        lessonService.updateLesson(id, lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/{id}/delete")
    public String deleteLesson(@PathVariable("id") Long id, Model model) {
        lessonService.deleteLesson(id);
        return "redirect:/lessons";
    }

}