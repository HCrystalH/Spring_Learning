package Ex2.fa.training.controller;

import Ex2.fa.training.entity.Course;
import Ex2.fa.training.entity.CourseId;
import Ex2.fa.training.entity.Lesson;
import Ex2.fa.training.service.CourseService;
import Ex2.fa.training.service.LessonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;
    private final CourseService courseService;

    public LessonController(LessonService lessonService, CourseService courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}")
    public String listLessons(@PathVariable CourseId courseId, Model model) {
        Course course = courseService.findById(courseId).orElse(null);
        if (course == null) {
            return "redirect:/courses";
        }
        model.addAttribute("course", course);
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("lessons", lessonService.findByCourse(course));
        return "lessons/list";
    }

    @PostMapping("/save")
    public String saveLesson(@ModelAttribute Lesson lesson, @RequestParam String courseCode,
                             @RequestParam String startDate, Model model) {
        CourseId courseId = new CourseId(courseCode, java.time.LocalDate.parse(startDate));
        Course course = courseService.findById(courseId).orElse(null);
        if (course == null) {
            return "redirect:/courses";
        }
        lesson.setCourse(course);
        lessonService.save(lesson);
        return "redirect:/lessons/" + courseCode + "_" + startDate;
    }

    @GetMapping("/edit/{lessonId}")
    public String editLesson(@PathVariable Long lessonId, Model model) {
        Lesson lesson = lessonService.findById(lessonId).orElse(null);
        if (lesson == null) {
            return "redirect:/courses";
        }
        Course course = lesson.getCourse();
        model.addAttribute("course", course);
        model.addAttribute("lesson", lesson);
        model.addAttribute("lessons", lessonService.findByCourse(course));
        return "lessons/list";
    }

    @GetMapping("/delete/{lessonId}")
    public String deleteLesson(@PathVariable Long lessonId) {
        Lesson lesson = lessonService.findById(lessonId).orElse(null);
        if (lesson == null) {
            return "redirect:/courses";
        }
        Course course = lesson.getCourse();
        CourseId courseId = course.getCourseId();
        lessonService.deleteById(lessonId);
        return "redirect:/lessons/" + courseId.getCourseCode() + "_" + courseId.getStartDate();
    }
}
