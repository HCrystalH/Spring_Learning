package JSFW_S_A101.fa.training.controller;

import JSFW_S_A101.fa.training.model.Course;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {
//    // Show form
//    @GetMapping("/register")
//    public String showForm (Model model){
//        model.addAttribute("user",new User());
//        return "register";  // templates/create_course.html
//    }
//
//    /**
//     * @ModelAttribute("user"): bind form data into user Object
//     * @Valid: turn on Validation from validation of User
//     * BindingResult: contain all validation errors
//     */
//    @PostMapping("/register")
//    public String submitForm(
//            @Valid @ModelAttribute("user") User user,
//            BindingResult bindingResult
//    ){
//        // Validation error, without this -> app crash
//        if(bindingResult.hasErrors())   return "register";
//
//        return "registerSuccess";
//    }
    // Show form
    @GetMapping("/courses/new")
    public String newCourse(Model model){
        model.addAttribute("course", new Course());
        return "create_course";
    }

    @PostMapping("/courses/create")
    public String submitCourse(
            @Valid @ModelAttribute("course") Course course,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors())   return "create_course";

        return "create_success";
    }
}
