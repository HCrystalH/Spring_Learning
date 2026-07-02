package Ex2.fa.training.controller;

import Ex2.fa.training.controller.base.GenericController;
import Ex2.fa.training.entity.Course;
import Ex2.fa.training.entity.CourseId;
import Ex2.fa.training.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses")
public class CourseController extends GenericController<Course, CourseId> {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        super(courseService, "courses", "course");
        this.courseService = courseService;
    }

    @Override
    protected Class<Course> getEntityClass() {
        return Course.class;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Course entity, Model model) {
        courseService.save(entity);
        model.addAttribute("course", entity);
        model.addAttribute("success", true);
        return "courses/form";
    }

    @GetMapping("/detail/{courseId}")
    public String viewDetail(@PathVariable CourseId courseId, Model model) {
        courseService.findById(courseId).ifPresent(course -> model.addAttribute("course", course));
        return "courses/form";
    }
}
