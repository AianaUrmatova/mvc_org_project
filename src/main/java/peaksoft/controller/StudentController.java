package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Student;
import peaksoft.service.CourseService;
import peaksoft.service.StudentService;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    @GetMapping()
    public String getAllStudents(Model model) {
        model.addAttribute("allStudents",studentService.getAllStudents());
        model.addAttribute("allCourses", courseService.getAllCourses());
        return "getAllStudents";
    }

    @GetMapping("/new")
    public String createStudent(Model model) {
        model.addAttribute("newStudent",new Student());
        return "newStudent";
    }
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("newStudent") Student student) {
        student.setPhoneNumber("+996" + student.getPhoneNumber());
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable ("id") Long id, Model model) {
        model.addAttribute("student",studentService.getStudentById(id));
        return "updateStudent";
    }
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable ("id") Long id, @ModelAttribute("student") Student student) {
        student.setPhoneNumber("+996" + student.getPhoneNumber());
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/{id}/courses")
    public String getStudentCourses(@PathVariable ("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        model.addAttribute("courses", student.getCourses());
        return "studentCourses";
    }
    @GetMapping("/{id}/add-course")
    public String addCoursePage(@PathVariable("id") Long id, Model model){
        model.addAttribute("student",studentService.getStudentById(id));
        model.addAttribute("allCourses", courseService.getAllCourses());
        return "addStudentToCourse";
    }
    @PostMapping("/{studentId}/add-course")
    public String assignCourse(@PathVariable("studentId") Long studentId,
                               @RequestParam("courseId") Long courseId) {
        studentService.addStudentsToCourse(courseId, studentId);
        return "redirect:/students";
    }

}
