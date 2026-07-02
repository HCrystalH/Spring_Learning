package fa.training.ASM6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing a course in the sharing system.
 * Status uses integer codes: 1=DRAFT, 2=PUBLISHED, 3=ARCHIVED.
 * Category is stored as a comma-separated string for simplicity
 * Timestamps are auto-managed via @PrePersist and @PreUpdate. used for sorting at DB
 */
@Entity
@Table(name = "tbl_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message="Title is required")
    private String title;

    @NotBlank(message = "Description can't not be empty")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Content can't not be empty")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /*
        @NotNull validate the object before persistence ( during request validation)
        nullable: prevent NULL in DB
     */
    @NotNull(message = "Course status is required")
    @Column(nullable = false)
    private Integer status;

    // Optional, a list of comma-separated words
    private String category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
