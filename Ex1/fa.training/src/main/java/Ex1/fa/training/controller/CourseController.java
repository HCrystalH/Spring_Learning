package Ex1.fa.training.controller;

import Ex1.fa.training.controller.base.GenericController;
import Ex1.fa.training.entity.Course;
import Ex1.fa.training.entity.CourseId;
import Ex1.fa.training.service.CourseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/courses")
public class CourseController extends GenericController<Course, CourseId> {
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        super(courseService, "courses","course");
        this.courseService = courseService;
    }

    @Override
    protected Class<Course> getEntityClass(){
        return Course.class;
    }

    @PostMapping("/saveForm")
    public String save(@ModelAttribute Course entity, Model model){
        courseService.save(entity);
        model.addAttribute("course",entity);
        model.addAttribute("success", true);
        return "courses/form";
    }

    @GetMapping("/detail/{courseId}")
    public String viewDetail(@PathVariable CourseId courseId, Model model){
        courseService.findById(courseId).ifPresent(course -> {
            model.addAttribute("course",course);
        });
        return "courses/form";
    }

    @GetMapping("/delete/{courseCode}/{startDate}")
    public String delete(
            @PathVariable String courseCode,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {

        CourseId id = new CourseId(courseCode, startDate);
        courseService.deleteById(id);

        return "redirect:/courses";
    }
}
