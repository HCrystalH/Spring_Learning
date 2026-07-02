package fa.training.ASM6.repository;

import fa.training.ASM6.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Optional<Instructor> findByUsername(String username);
}
