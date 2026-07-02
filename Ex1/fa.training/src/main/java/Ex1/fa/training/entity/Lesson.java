package Ex1.fa.training.entity;

import Ex1.fa.training.enums.ContentType;
import Ex1.fa.training.enums.CourseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    private String lessonName;

    @NotNull(message="Duration must not be null")
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @NotNull(message="Content Type is required")
    private ContentType contentType;

    @Enumerated(EnumType.STRING)
    @NotNull(message="Content Type is required")
    private CourseStatus status;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="course_code",referencedColumnName="course_code"),
            @JoinColumn(name="start_date", referencedColumnName="start_date")
    })
    private Course course;
}
