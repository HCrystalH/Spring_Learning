package fa.training.ASM6.controller;

import fa.training.ASM6.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Public controller displaying the category page
 * Shows all categories with their frequency counts for browsing
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "public/categories";
    }
}
