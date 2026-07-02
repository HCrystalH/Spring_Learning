package fa.training.ASM6.service;

import fa.training.ASM6.entity.Review;
import fa.training.ASM6.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Manages student review submission (PENDING by default)
 * instructor approval workflow, and public review feeds
 */
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public static final int STATUS_PENDING = 1;
    public static final int STATUS_APPROVED = 2;

    public List<Review> findByCourseAndStatus(Integer courseId, Integer status){
        return reviewRepository.findByCourseIdAndStatusOrderByCreatedAtDesc(courseId, status);
    }

    public List<Review> findByStatus(Integer status){
        return reviewRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    public Page<Review> findPendingReviews(int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());
        return reviewRepository.findByStatus(STATUS_PENDING, pageable);
    }

    public Page<Review> findApprovedReviews(int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());
        return reviewRepository.findByStatus(STATUS_APPROVED, pageable);
    }

    public Review save(Review review){
        return reviewRepository.save(review);
    }

    public Review findById(Integer reviewId){
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public void delete(Integer reviewId){
        reviewRepository.deleteById(reviewId);
    }

    public void approve(Integer reviewId){
        Review review = findById(reviewId);
        if(review != null){
            review.setStatus(STATUS_APPROVED);
            reviewRepository.save(review);
        }
    }

    public long countPending(){
        return reviewRepository.countByStatus(STATUS_PENDING);
    }

    public List<Review> getRecentReviews(){
        return reviewRepository.findTop5ByOrderByCreatedAtDesc();
    }
}
