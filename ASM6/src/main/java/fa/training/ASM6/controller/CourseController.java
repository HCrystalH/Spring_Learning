package fa.training.ASM6.controller;

import fa.training.ASM6.entity.Course;
import fa.training.ASM6.entity.Review;
import fa.training.ASM6.service.CategoryService;
import fa.training.ASM6.service.CourseService;
import fa.training.ASM6.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final ReviewService reviewService;
    private final CategoryService categoryService;

    @GetMapping("/{id:[0-9]+}")
    public String detail(
            @PathVariable("id") Integer courseId,
            Model model
    ) {
        Course course = courseService.findById(courseId);

        if (course == null || course.getStatus() != CourseService.STATUS_PUBLISHED) {
            return "redirect:/";
        }

        model.addAttribute("course", course);
        model.addAttribute(
                "reviews",
                reviewService.findByCourseAndStatus(
                        courseId,
                        ReviewService.STATUS_APPROVED
                ));
        model.addAttribute("review", new Review());

        return "public/course-detail";
    }

    /**
     * This follows the Post/Redirect/Get (PRG) pattern
     * Flow: Browser -> POST /courses/1/reviews -> validate -> save review -> redirect -> GET /courses/1
     * Without the redirect, refreshing the page would resubmit the form
     */
    @PostMapping("/{id}/reviews")
    public String addReview(
            @PathVariable("id") Integer id,
            @Valid @ModelAttribute("review") Review review,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) return "redirect:/courses/" + id;

        Course course = courseService.findById(id);
        if (course == null || course.getStatus() != CourseService.STATUS_PUBLISHED) {
            return "redirect:/";
        }

        review.setCourse(course);
        review.setStatus(ReviewService.STATUS_PENDING);
        review.setCreatedAt(LocalDateTime.now());
        reviewService.save(review);

        /*
            POST -> 302 Redirect -> GET
            2 separate HTTP methods -> The model from the POST request is lost
            Ex: return "redirect:/courses/1" -> addAttribute -> Spring create : /courses/1?message=Success
            RedirectAttributes: the redirected GET request can access the param

            addFlashAttribute is often even better -> Why? -> it store the attribute temporarily in Session
            Ex: After redirect : GET /courses/1 -> model automatically contains message
            WITHOUT putting it into the URL
         */
        redirectAttributes.addFlashAttribute("message","Your review has been submitted and is pending approval");

        return "redirect:/courses/" + id;
    }

    /*
        Listing published courses for a specific category with pagination
        Ex: GET /courses/category?name=Java -> default = 0
            GET /courses/category?name=Spring&page=2
     */
    @GetMapping("/categories")
    public String byCategory(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ){
        /*
            Every page contains at most 10 courses
            Example: 17 courses
                Page 0 -> courses 1-10
                Page 1 -> courses 11-17
         */
        int PAGE_SIZE = 10;

        /*
            Why not List<Course> ?
            Still get the courses but doesn't know
                - How many total courses exist
                - How many pages there are
                - Whether there's a next or prev page
            => use Page<Course>
         */
        Page<Course> coursePage = courseService.findPublishedByCategory(name,page,PAGE_SIZE);

        // get only the courses of the current page
        model.addAttribute("courses",coursePage.getContent());

        // HTML can highlight current page 1 2 [3] 4 Spring pages are 0-based
        model.addAttribute("currentPage",page);

        /*
            Example 37 courses , page size = 10 -> total page = 4
            View can gen -> Previous 1 2 3 4 Next
         */
        model.addAttribute("totalPages",coursePage.getTotalPages());

        model.addAttribute("selectedCategory",name);
        model.addAttribute(
                "categories",
                categoryService.findAll()
        );

        return "public/categories";
    }
}
