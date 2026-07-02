package Ex2.fa.training.service;

import Ex2.fa.training.entity.Course;
import Ex2.fa.training.entity.CourseId;
import Ex2.fa.training.repository.CourseRepository;
import Ex2.fa.training.service.base.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends GenericServiceImpl<Course, CourseId> {

    public CourseService(CourseRepository courseRepository) {
        super(courseRepository);
    }
}
