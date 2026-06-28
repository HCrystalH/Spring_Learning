package Ex1.fa.training.service.impl;

import Ex1.fa.training.entity.Course;
import Ex1.fa.training.entity.Lesson;
import Ex1.fa.training.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Override
    public Lesson save(Lesson lesson) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Lesson findById(Long id) {
        return null;
    }

    @Override
    public List<Lesson> getLessons(Course course) {
        return List.of();
    }
}
