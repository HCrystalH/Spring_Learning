package fa.training.ASM6.repository;

import fa.training.ASM6.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByCourseIdAndStatusOrderByCreatedAtDesc(Integer courseId, Integer status);

    List<Review> findByStatusOrderByCreatedAtDesc(Integer status);

    Page<Review> findByStatus(Integer status, Pageable pageable);

    long countByStatus(Integer status);

    List<Review> findTop5ByOrderByCreatedAtDesc();
}
