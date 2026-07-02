package Ex1.fa.training.entity;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseId implements Serializable {

    @Column(name="course_code")
    private String courseCode;

    @Column(name="start_date")
    private LocalDate startDate;

}
