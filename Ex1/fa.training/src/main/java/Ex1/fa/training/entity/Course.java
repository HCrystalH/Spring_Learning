package Ex1.fa.training.entity;

import jakarta.persistence.Embedded;
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
@EqualsAndHashCode
public class Course {
    @Embedded
    private CourseId courseId;

    @NotBlank(message= "Course Name can't be empty")
    private String courseName;

    @NotBlank(message="Category can't be empty")
    private String category;

    @NotBlank(message="Instructor can't be empty")
    private String instructor;
}
