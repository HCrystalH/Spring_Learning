package Ex1.fa.training.entity;

import Ex1.fa.training.enums.ContentType;
import Ex1.fa.training.enums.CourseStatus;
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

    private ContentType contentType;

    private CourseStatus status;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="course_code",referencedColumnName="courseCode"),
            @JoinColumn(name="start_date", referencedColumnName="startDate")
    })
    private Course course;
}
