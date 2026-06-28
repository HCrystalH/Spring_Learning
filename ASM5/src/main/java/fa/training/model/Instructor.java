package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends LmsUser{
    private String department;
    private String bio;

    public Instructor(long userId, String name, String email, String department, String bio) {
        super(userId, name, email);
        this.department = department;
        this.bio = bio;
    }

    @Override
    public void printInfo(){
        System.out.println("Instructor [ID=" + getUserId() + ", Name=" + getName() + ", Email=" + getEmail()
                + ", Dept=" + department + ", Bio=" + bio + "]");
    }
}
