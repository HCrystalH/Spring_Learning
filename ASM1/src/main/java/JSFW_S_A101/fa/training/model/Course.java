package JSFW_S_A101.fa.training.model;

import jakarta.validation.constraints.*;

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

    public Course() {
    }

    public Course(String title, String instructorName, String instructorEmail, String description, Integer durationHours) {
        this.title = title;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.description = description;
        this.durationHours = durationHours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

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
