package fa.training.service;

import fa.training.model.LmsUser;
import fa.training.model.Student;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class LmsDataService {
    /*
        saves a list of LmsUser objects (this list can
        contain both LmsUser and Instructor types) to a file named users.dat
     */
    public void saveUsers(List<LmsUser> users){
       try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))){
           oos.writeObject(users);
       } catch (IOException e){
           e.printStackTrace();
       }
    }

    // reads the content of users.dat and returns the list of LmsUser objects.
    @SuppressWarnings("unchecked")
    public List<LmsUser> getUsers() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))){
            return (List<LmsUser>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //  that saves a list of Student objects to a file named students.dat
    public void saveStudents(List<Student> students){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))){
            oos.writeObject(students);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //  that reads the content of students.dat and returns the list of Student objects
    @SuppressWarnings("unchecked")
    public List<Student> getStudents(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))){
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
