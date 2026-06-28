package fa.training.main;

import fa.training.model.Instructor;
import fa.training.model.LmsUser;
import fa.training.model.Student;
import fa.training.service.LmsDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Test implements CommandLineRunner {
    private final LmsDataService lmsDataService;

    public Test(LmsDataService lmsDataService) {
        this.lmsDataService = lmsDataService;
    }

    @Override
    public void run(String... args){

        // Create a list containing LmsUser and Instructor objects
        List<LmsUser> users = Arrays.asList(
            new LmsUser(1, "John Doe", "john.doe@fpt.com"),
            new Instructor(2, "Jane Smith", "jane.smith@fpt.com", "Engineering", "Spring Framework Expert")
        );

        // save it to the file users.dat
        lmsDataService.saveUsers(users);

        // Create a List of Students object

        List<Student> students = Arrays.asList(
            new Student(101, "Jane Smith", 3.75),
            new Student(102, "Bob Johnson", 3.50)
        );
        // save it to the file students.dat
        lmsDataService.saveStudents(students);

        // Retrieve the saved list of LmsUser objects and call printInfo for each obj
        System.out.println("=== List of LMS Users ===");
        List<LmsUser> loadedUsers = lmsDataService.getUsers();
        for (LmsUser user : loadedUsers) {
            user.printInfo();
        }

        //  Retrieve the saved list of Student objects and call printInfo for each obj
        System.out.println("=== List of Students ===");
        List<Student> loadedStudents = lmsDataService.getStudents();
        for (Student student : loadedStudents) {
            student.printInfo();
        }
    }
}
