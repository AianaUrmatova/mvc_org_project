package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import peaksoft.entity.Course;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor

public class CourseController {
    private final CourseService courseService;
    private final InstructorService instructorService;

    @GetMapping()
    public String getAllCourses(Model model) {
        model.addAttribute("allCourses", courseService.getAllCourses());
        model.addAttribute("allInstructors", instructorService.getAllInstructors());
        return "getAllCourses";
    }


    @GetMapping("/new")
    public String createCourse(Model model) {
        model.addAttribute("newCourse", new Course());
        return "newCourse";
    }

    @PostMapping("/save")
    public String saveCourse(
            @ModelAttribute("newCourse") Course course,
            @RequestParam(value = "image", required = false) MultipartFile imageFile)
            throws IOException {

        if (imageFile != null && !imageFile.isEmpty()) {

            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            course.setImageUrl("/uploads/" + fileName);
        }
        courseService.saveCourse(course);
        return "redirect:/courses";

    }


    @GetMapping("/{id}")
    public String getCourse(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "updateCourse";
    }


    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") Long id,
                               @ModelAttribute Course course,
                               @RequestParam("image") MultipartFile imageFile) throws IOException {

        courseService.updateCourse(id, course, imageFile);
        return "redirect:/courses";
    }


    @GetMapping("/{id}/delete")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @GetMapping("/{id}/assign")
    public String assignPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("allInstructors", instructorService.getAllInstructors());
        return "assignInstructor";
    }

    @PostMapping("/{courseId}/assign")
    public String assign(
            @PathVariable("courseId") Long courseId,
            @RequestParam("instructorId") Long instructorId) {
        courseService.assignInstructor(courseId, instructorId);
        return "redirect:/courses";
    }

    @GetMapping("/{id}/details")
    public String courseDetails(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("lessons", course.getLessons());
        model.addAttribute("students", course.getStudents());
        model.addAttribute("instructors", course.getInstructors());
        return "courseDetails";
    }



}
