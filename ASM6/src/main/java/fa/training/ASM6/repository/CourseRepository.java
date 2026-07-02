package fa.training.ASM6.repository;

import fa.training.ASM6.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Page<Course> findByStatus(Integer status, Pageable pageable);

    @Query("""
        SELECT c
        FROM Course c
        WHERE c.status = :status
        AND c.category LIKE %:category%
        """
    )
    Page<Course> findByStatusAndCategoryContaining(
            @Param("status") Integer status,
            @Param("category") String category,
            Pageable pageable);

    @Query("""
        SELECT c
        FROM Course c
        WHERE c.status = :status
        AND c.category LIKE %:category%
        """
    )
    Page<Course> findPublishedByCategory(
            @Param("status") Integer status,
            @Param("category") String category,
            Pageable pageable);

    long countByStatus(Integer status);

    @Query("SELECT COUNT(c) FROM Course c")
    long totalRecords();

    List<Course> findByStatusOrderByCreatedAtDesc(Integer status);
}
