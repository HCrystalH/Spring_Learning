package Ex1.fa.training.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @EmbeddedId
    private CourseId courseId;

    @NotBlank(message= "Course Name can't be empty")
    private String courseName;

    @NotBlank(message="Category can't be empty")
    private String category;

    @NotBlank(message="Instructor can't be empty")
    private String instructor;
}
