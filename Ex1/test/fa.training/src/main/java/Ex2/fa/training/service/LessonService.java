package Ex2.fa.training.service;

import Ex2.fa.training.entity.Course;
import Ex2.fa.training.entity.Lesson;
import Ex2.fa.training.repository.LessonRepository;
import Ex2.fa.training.service.base.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService extends GenericServiceImpl<Lesson, Long> {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        super(lessonRepository);
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> findByCourse(Course course) {
        return lessonRepository.findByCourse(course);
    }
}
