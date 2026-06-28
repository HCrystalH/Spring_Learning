package JSFW_S_A101.fa.training.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @NotBlank(message="The title must not be empty")
    @Size(min=5, message =" The title must have at least 5 characters")
    String title;

    @NotBlank(message="The instructor name must not be empty")
    @Size(min=2, message ="The instructor name must have at least 2 characters")
    String instructorName;

    @NotBlank(message="The email must not be empty")
    @Email(message="Invalid email format")
    String instructorEmail;

    @NotBlank(message="The description must not be empty")
    @Size(min = 10, max = 200, message ="The description must be between 10 and 200 characters length")
    String description;

    @NotNull(message = "The duration hour must not be NULL")
    @Min(value = 1, message = "The duration Hour must be at least 1")
    Integer durationHours;

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                ", description='" + description + '\'' +
                ", durationHours=" + durationHours +
                '}';
    }
}
