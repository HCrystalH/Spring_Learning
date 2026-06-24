package JSFW_S_A101.fa.training.model;

import jakarta.validation.constraints.*;
import jakarta.annotation.Nonnull;

public class User {
    @NotBlank(message = "First name must not be empty")
    @Size(min = 2, message = "First name must have at least 2 characters")
    String firstName;

    @NotBlank(message ="Last name must not be empty")
    @Size(min =2, message ="Last name must have at least 2 characters")
    String lastName;

    @NotBlank(message="Email must not be empty")
    @Email(message="Invalid email address")
    String email;

    @NotBlank(message="Password must not be empty")
    @Size(min = 8, max = 20, message ="Password must between 8 and 20 characters length")
    String password;

    @Min(value = 18, message ="Age must be equal or greater than 18")
    int age;

    public User() {
    }

    public User(@Nonnull String firstName, String lastName, String email, String password, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    @Nonnull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Nonnull String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
