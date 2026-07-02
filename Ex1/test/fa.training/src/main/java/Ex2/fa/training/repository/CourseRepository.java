package Ex2.fa.training.repository;

import Ex2.fa.training.entity.Course;
import Ex2.fa.training.entity.CourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, CourseId> {
}
