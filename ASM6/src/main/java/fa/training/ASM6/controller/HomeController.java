package fa.training.ASM6.controller;

import fa.training.ASM6.entity.Course;
import fa.training.ASM6.service.CategoryService;
import fa.training.ASM6.service.CourseService;
import fa.training.ASM6.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
    private final CourseService courseService;
    private final CategoryService categoryService;
    private final ReviewService reviewService;

    /*
        Model: used to transport data to the view layer
        -> bridge between Java code and UI, also a container to pass
        data from controller to view (HTML)
     */
    @GetMapping
    public String home(
            @RequestParam(defaultValue = "0") int page,
            Model model
    ){
        int PAGE_SIZE = 10;
        Page<Course> coursePage = courseService.findPublishedCourses(page, PAGE_SIZE);

        // courses for current page
        model.addAttribute("courses",coursePage.getContent());

        // current page # (highlight current page, next/prev button)
        model.addAttribute("currentPage",page);

        // Pagination links 1 2 3 4 5
        model.addAttribute("totalPages",coursePage.getTotalPages());

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("recentReviews", reviewService.getRecentReviews());

        // render the view name "index" -> Spring use ViewResolver
        return "index";
    }
}
