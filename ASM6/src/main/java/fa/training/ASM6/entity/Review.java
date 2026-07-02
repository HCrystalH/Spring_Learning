package fa.training.ASM6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing a course review submitted by a student.
 * Status uses integer codes: 1=PENDING, 2=APPROVED.
 * Each review belongs to a single Course via a ManyToOne relationship.
 * Rating is an integer between 1 and 5 stars.
 */
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="tbl_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id", nullable=false)
    private Course course;

    @NotBlank(message="Author name is required")
    @Column(name="author_name",nullable = false)
    private String authorName;  // student's name

    @NotBlank(message = "Email is required")
    @Column(nullable = false)
    @Email(message= "Invalid email format")
    private String email;

    @NotNull(message="Rating is required")
    @Min(value = 1, message = "Rating must be at least 1 ")
    @Max(value = 5, message = "Rating must be at most 5 ")
    private Integer rating;

    // the review content in plain text
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotNull(message = "Review status is required")
    @Column(nullable = false)
    private Integer status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = 1;
    }
}
