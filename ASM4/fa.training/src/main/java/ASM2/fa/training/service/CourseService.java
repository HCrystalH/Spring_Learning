package ASM2.fa.training.service;

import ASM2.fa.training.model.Course;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private static final String FILE_NAME = "courses.dat";

    /*
        Save the list of courses passed in the param to a file named courses.dat
        Use ObjectOutputStream for Serialization
     */
    public void saveCourse(List<Course> courses) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            out.writeObject(courses);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Read the courses.dat file and return its content as a List<Course>
        Use ObjectInputStream
     */
    @SuppressWarnings("unchecked")
    public List<Course> getCourses() throws IOException, ClassNotFoundException {
        List<Course> courses = new ArrayList<>();
        File file = new File(FILE_NAME);

        if(!file.exists()){
            return courses;
        }
;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            courses = (List<Course>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading courses: " + e.getMessage(),e);
        }

        return courses;
    }
}
