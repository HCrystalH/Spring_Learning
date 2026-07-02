package fa.training.ASM6.repository;

import fa.training.ASM6.entity.Lookup;
import fa.training.ASM6.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Retrieves reference data by type (e.g., "COURSE_STATUS", "REVIEW_STATUS")
 * for displaying human-readable labels in the UI
 */
public interface LookupRepository extends JpaRepository<Lookup, Integer> {
    List<Lookup> findByType(String type);
}
