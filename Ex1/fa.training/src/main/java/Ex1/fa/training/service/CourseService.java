package Ex1.fa.training.service;

import Ex1.fa.training.entity.Course;
import Ex1.fa.training.entity.CourseId;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course save(Course course);

    Optional<Course> findById(CourseId courseId);

    List<Course> findAll();

    void delete(CourseId courseId);
}
