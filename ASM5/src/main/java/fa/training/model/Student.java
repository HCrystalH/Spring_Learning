package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private long studentId;
    private String name;
    private double gpa;

    public void printInfo() {
        System.out.println("Student [ID=" + studentId + ", Name=" + name + ", GPA=" + gpa + "]");
    }

    @Override
    public String toString() {
        return "Student[" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                ']';
    }
}
