package Ex1.fa.training.controller;

import Ex1.fa.training.repository.CourseRepository;
import Ex1.fa.training.service.CourseService;
import lombok.Getter;
import org.springframework.stereotype.Controller;

@Controller
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {}

    public CourseController(CourseService courseService, CourseRepository courseRepository) {}


}
