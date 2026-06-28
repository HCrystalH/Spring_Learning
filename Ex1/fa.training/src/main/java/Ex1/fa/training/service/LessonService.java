package Ex1.fa.training.service;

import Ex1.fa.training.entity.Course;
import Ex1.fa.training.entity.Lesson;

import java.util.List;

public interface LessonService {
    Lesson save(Lesson lesson);

    void delete(Long id);

    Lesson findById(Long id);

    List<Lesson> getLessons(Course course);
}
