package ASM2.fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course implements Serializable {
    private String title;
    private String instructorName;
    private String description;
    private int durationHours;

    /*
        Ex: The course "Spring Framework"
        [Course: Spring Framework] by John Doe (40 hours): Learn Spring Core, MVC, and Boot
     */
    public void displayInfo(){
        System.out.println("[Course: " + title + "] by " + instructorName
                + " (" + durationHours + " hours): " + description);
    }

    @Override
    public String toString() {
        return "Course [" +
                "title='" + title + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", description='" + description + '\'' +
                ", durationHours=" + durationHours +
                ']';
    }
}
