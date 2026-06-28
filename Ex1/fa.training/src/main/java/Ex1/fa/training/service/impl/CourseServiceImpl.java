package Ex1.fa.training.service.impl;

import Ex1.fa.training.entity.Course;
import Ex1.fa.training.entity.CourseId;
import Ex1.fa.training.repository.CourseRepository;
import Ex1.fa.training.service.CourseService;
import Ex1.fa.training.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(CourseId courseId) {

        return courseRepository.findById(courseId);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void delete(CourseId id) {

        courseRepository.deleteById(id);
    }
}
