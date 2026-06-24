package JSFW_S_A101.fa.training.controller;

import JSFW_S_A101.fa.training.model.User;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    // Show form
    @GetMapping("/register")
    public String showForm (Model model){
        model.addAttribute("user",new User());
        return "register";  // templates/register.html
    }

    /**
     * @ModelAttribute("user"): bind form data into user Object
     * @Valid: turn on Validation from validation of User
     * BindingResult: contain all validation errors
     */
    @PostMapping("/register")
    public String submitForm(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult
    ){
        // Validation error, without this -> app crash
        if(bindingResult.hasErrors())   return "register";

        return "registerSuccess";
    }
}
