package Ex2.fa.training.entity;

import Ex2.fa.training.enums.ContentType;
import Ex2.fa.training.enums.LessonStatus;
import jakarta.persistence.*;
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

    private Integer duration;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Enumerated(EnumType.STRING)
    private LessonStatus status;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "course_code", referencedColumnName = "course_code"),
            @JoinColumn(name = "start_date", referencedColumnName = "start_date")
    })
    private Course course;
}
