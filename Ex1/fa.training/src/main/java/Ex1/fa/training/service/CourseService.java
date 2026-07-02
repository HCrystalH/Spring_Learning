package Ex1.fa.training.service;

import Ex1.fa.training.entity.Course;
import Ex1.fa.training.entity.CourseId;
import Ex1.fa.training.repository.CourseRepository;
import Ex1.fa.training.service.base.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService extends GenericServiceImpl<Course,CourseId> {
    public CourseService(CourseRepository courseRepository){
        super(courseRepository);
    }
}
