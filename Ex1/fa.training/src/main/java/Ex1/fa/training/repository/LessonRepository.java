package Ex1.fa.training.repository;

import Ex1.fa.training.entity.Course;
import Ex1.fa.training.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findByCourse(Course course);
}
