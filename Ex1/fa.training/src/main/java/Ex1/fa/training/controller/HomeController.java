package Ex1.fa.training.controller;

import Ex1.fa.training.dto.CourseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/courses/new")
    public String createForm(Model model) {

        model.addAttribute("course", new CourseDTO());

        return "course-form";
    }

}