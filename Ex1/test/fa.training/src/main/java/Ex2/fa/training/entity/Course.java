package Ex2.fa.training.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

    @EmbeddedId
    private CourseId courseId;

    private String courseName;

    private String category;

    private String instructor;
}
