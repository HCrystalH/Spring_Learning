package Ex2.fa.training.repository;

import Ex2.fa.training.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
