package fa.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LmsUser implements Serializable {
    private long userId;
    private String name;
    private String email;

    public void printInfo(){
        System.out.println("User [ID=" + userId + ", Name=" + name + ", Email=" + email + "]");
    }

    @Override
    public String toString() {
        return "LmsUser[" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ']';
    }
}
