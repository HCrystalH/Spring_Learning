package fa.training.ASM6.controller;

import fa.training.ASM6.entity.Course;
import fa.training.ASM6.entity.Instructor;
import fa.training.ASM6.entity.Review;
import fa.training.ASM6.service.CategoryService;
import fa.training.ASM6.service.CourseService;
import fa.training.ASM6.service.InstructorService;
import fa.training.ASM6.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Instructor-only controller handling authentication and all
 * administrative functions: dashboard, course CRUD, status management
 * (publish/unpublish/archive), and review moderation (approve/delete)
 * Session-based auth: routes check for "instructor" attribute in session
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {
    private final CourseService courseService;
    private final ReviewService reviewService;
    private final CategoryService categoryService;
    private final InstructorService instructorService;
    private static final int PAGE_SIZE = 10;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("instructor", new Instructor());
        return "instructor/login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute Instructor instructor,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        Instructor instructor1 = instructorService.login(
                instructor.getUsername(),
                instructor.getPassword()
        );
        if(instructor1 == null){
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Invalid username or password"
            );
            return "redirect:/instructor/login";
        }
        session.setAttribute("instructor", instructor1);
        return  "redirect:/instructor/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String dashboard (HttpSession session, Model model){
        if(session.getAttribute("instructor") == null){
            return "redirect:/instructor/login";
        }

        model.addAttribute("totalCourses", courseService.getTotalCourses());
        model.addAttribute("publishedCount",courseService.countByStatus(CourseService.STATUS_PUBLISHED));
        model.addAttribute("pendingReviews", reviewService.countPending());
        model.addAttribute("categoryCount",categoryService.findAll().size());

        return "instructor/dashboard";
    }

    @GetMapping("/courses")
    public String listCourses(
            @RequestParam(defaultValue = "0") int page,
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("instructor") == null){
            return "redirect:/instructor/login";
        }

        Page<Course> coursePage = courseService.findAll(page,PAGE_SIZE);

        model.addAttribute("courses",coursePage.getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages", coursePage.getTotalPages());

        return "instructor/manage-courses";
    }

    @GetMapping("/courses/new")
    public String createForm(HttpSession session, Model model){
        if(session.getAttribute("instructor") == null){
            return "redirect:/instructor/login";
        }
        model.addAttribute("course", new Course());
        return "instructor/course-form";
    }

    /**
        Save form
     */
    @PostMapping("/courses")
    public String createCourse(
            @Valid @ModelAttribute Course course,
            BindingResult bindingResult,
            @RequestParam(required = false) String action,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        if(session.getAttribute("instructor") == null){
            return "redirect:/instructor/login";
        }

        if(bindingResult.hasErrors()){
            return "instructor/course-form";
        }

        if("publish".equals(action)){
            course.setStatus(CourseService.STATUS_PUBLISHED);
        } else {
            course.setStatus(CourseService.STATUS_DRAFT);
        }

        courseService.save(course);
        categoryService.updateFrequency(course.getCategory());
        redirectAttributes.addFlashAttribute(
                "message",
                "Course saved successfully"
        );

        return "redirect:/instructor/courses";
    }

    @GetMapping("/courses/{id}/edit")
    public String editForm(
            @PathVariable("id") Integer id,
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("instructor") == null) return "redirect:/instructor/login";

        Course course = courseService.findById(id);
        if(course == null) return "redirect:/instructor/courses";

        model.addAttribute("course", course);
        return "instructor/course-form";
    }

    @PostMapping("/courses/{id}/edit")
    public String updateCourse(
            @PathVariable("id") Integer id,
            @Valid @ModelAttribute Course course,
            BindingResult bindingResult,
            @RequestParam(required = false) String action,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        if(session.getAttribute("instructor") == null) return "redirect:/instructor/login";
        if(bindingResult.hasErrors()) return "instructor/course-form";

        Course existingCourse = courseService.findById(id);
        if(existingCourse == null) return "redirect:/instructor/courses";

        String oldCategory = existingCourse.getCategory();

        // Update fields
        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setContent(course.getContent());
        existingCourse.setCategory(course.getCategory());
        if("publish".equals(action)){
            existingCourse.setStatus(CourseService.STATUS_PUBLISHED);
        } else if(existingCourse.getStatus() == null){
            existingCourse.setStatus(CourseService.STATUS_DRAFT);
        }

        courseService.save(existingCourse);

        // Update frequency
        if (oldCategory != null && !oldCategory.equals(course.getCategory())) {
            categoryService.decreaseFrequency(oldCategory);
            categoryService.updateFrequency(course.getCategory());
        }

        redirectAttributes.addFlashAttribute("message","Course updated successfully");
        return "redirect:/instructor/courses";
    }

    @PostMapping("/courses/{id}/delete")
    public String deleteCourse(
            @PathVariable("id") Integer id,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        if(session.getAttribute("instructor") == null) return "redirect:/instructor/login";

        Course course = courseService.findById(id);
        if(course != null){
            categoryService.decreaseFrequency(course.getCategory());
            courseService.delete(id);
        }
        redirectAttributes.addFlashAttribute("message","Course deleted successfully");
        return "redirect:/instructor/courses";
    }

    /**
        Handle course status
        3 status: PUBLISHED, ARCHIVED, DRAFT (UNPUBLISHED)
     */
    @PostMapping("/courses/{id}/publish")
    public String publish(
            @PathVariable("id") Integer id,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        return updateCourseStatus(
                id,
                CourseService.STATUS_PUBLISHED,
                "Course published successfully",
                session,
                redirectAttributes
        );
    }

    @PostMapping("/courses/{id}/unpublish")
    public String unpublish(
            @PathVariable("id") Integer id,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        return updateCourseStatus(
                id,
                CourseService.STATUS_DRAFT,
                "Course unpublished successfully",
                session,
                redirectAttributes
        );
    }

    @PostMapping("/courses/{id}/archive")
    public String archive(
            @PathVariable("id") Integer id,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        return updateCourseStatus(
                id,
                CourseService.STATUS_ARCHIVED,
                "Course archived successfully",
                session,
                redirectAttributes
        );
    }

    /**
       Handle Review logic
     */
    @GetMapping("/reviews")
    public String manageReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int approvedPage,
            HttpSession session,
            Model model
    ){
        if (session.getAttribute("instructor") == null) return "redirect:/instructor/login";

        Page<Review> reviewPage = reviewService.findPendingReviews(page,PAGE_SIZE);
        model.addAttribute("reviews", reviewPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reviewPage.getTotalPages());

        Page<Review> approvedReviewPage = reviewService.findApprovedReviews(approvedPage,PAGE_SIZE);
        model.addAttribute("approvedReviews", approvedReviewPage.getContent());
        model.addAttribute("approvedCurrentPage", approvedPage);
        model.addAttribute("approvedTotalPages", approvedReviewPage.getTotalPages());

        return "instructor/manage-reviews";
    }

    @PostMapping("/reviews/{id}/approve")
    public String approveReview(
            @PathVariable("id") Integer id,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        if (session.getAttribute("instructor") == null) return "redirect:/instructor/login";
        reviewService.approve(id);
        redirectAttributes.addFlashAttribute("message","Review approved");
        return "redirect:/instructor/reviews";
    }

    @PostMapping("/reviews/{id}/delete")
    public String deleteReview(
            @PathVariable("id") Integer id,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        if (session.getAttribute("instructor") == null) return "redirect:/instructor/login";
        reviewService.delete(id);
        redirectAttributes.addFlashAttribute("message","Review deleted");

        return "redirect:/instructor/reviews";
    }

    // Helper method reduce boilerplate code at handling course status
    private String updateCourseStatus(
            Integer id,
            Integer status,
            String message,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        if (session.getAttribute("instructor") == null) return "redirect:/instructor/login";
        courseService.updateStatus(id,status);
        redirectAttributes.addFlashAttribute("message",message);

        return "redirect:/instructor/courses";
    }
}
