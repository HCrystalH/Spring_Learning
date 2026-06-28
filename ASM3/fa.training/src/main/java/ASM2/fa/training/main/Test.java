package ASM2.fa.training.main;

import ASM2.fa.training.model.Course;
import ASM2.fa.training.service.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Test implements CommandLineRunner {
    private final CourseService courseService;

    public Test(CourseService courseService) {
        this.courseService = courseService;
    }

    /*
        Spring automatically executes it after startup
        run once
     */
    @Override
    public void run(String... agrs) throws Exception{
        // Create a List of Course objects (e.g:, 2-3 courses)
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Spring Framework", "John Doe",
                "Learn Spring Core, MVC, and Boot", 40));
        courses.add(new Course("Java Programming", "John Wick",
                "Master OOP, Collections, and Streams", 30));
        courses.add(new Course("Database Design", "Hoang Hoang",
                "Understand SQL, Normalization, and Indexing", 25));

        // Call the saveCourses() from courseService to save this list to the file
        System.out.println("Saving courses to file...");
        courseService.saveCourse(courses);

        // Call the getCourses() from  courseService to get the list of saved courses
        System.out.println("Loading courses from file...");
        List<Course> loadedCourses = courseService.getCourses();

        // Loop through the retrieved list and call the displayInfo() method for each course
        System.out.println("=== Retrieved Courses ===");
        for(Course course : loadedCourses){
            course.displayInfo();
        }
    }
}
