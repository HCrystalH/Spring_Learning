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
    // Show form
    @GetMapping("/courses/new")
    public String newCourse(Model model){
        // Model interface: hoat dong nhu 1 Map (Key-Value)
        model.addAttribute("course", new Course());
        return "create_course";
    }

    /**
     * @ModelAttribute: nguoc dong tu View ve Controler
     * thay vi doc tung input fields -> dat @ModelAttribute trc Object
     * -> Spring auto map field data into this Object
     * Ko can request.getParameter
     */
    @PostMapping("/courses/create")
    public String submitCourse(
            @Valid @ModelAttribute("course") Course course,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors())   return "create_course";

        return "create_success";
    }
}
