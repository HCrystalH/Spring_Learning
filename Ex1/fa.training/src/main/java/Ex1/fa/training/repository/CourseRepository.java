package Ex1.fa.training.repository;

import Ex1.fa.training.entity.Course;
import Ex1.fa.training.entity.CourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, CourseId> {
}
