package Ex1.fa.training.repository;

import Ex1.fa.training.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
