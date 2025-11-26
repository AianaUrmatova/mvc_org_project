package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Instructor;
import peaksoft.service.InstructorService;

@Controller
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping()
    public String getAllInstructors(Model model){
        model.addAttribute("allInstructors",instructorService.getAllInstructors());
        return "getAllInstructors";
    }
    @GetMapping("/new")
    public String createInstructor(Model model){
        model.addAttribute("newInstructor",new Instructor());
        return "newInstructor";
    }
    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute("newInstructor") Instructor instructor){
        instructor.setPhoneNumber("+996" + instructor.getPhoneNumber());
        instructorService.saveInstructor(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/{id}")
    public String getInstructor(@PathVariable("id") Long id, Model model){
        model.addAttribute("instructor",instructorService.getInstructorById(id));
        return "updateInstructor";
    }
    @PostMapping("/update/{id}")
    public String updateInstructor(@PathVariable("id") Long id, @ModelAttribute Instructor instructor){
        instructor.setPhoneNumber("+996" + instructor.getPhoneNumber());
        instructorService.updateInstructor(id, instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/{id}/delete")
    public String deleteInstructor(@PathVariable("id") Long id){
        instructorService.deleteInstructor(id);
        return "redirect:/instructors";
    }

    @GetMapping("/{id}/courses")
    public String getInstructorCourses(@PathVariable("id") Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("courses", instructor.getCourses());

        return "instructorCourses";
    }

}