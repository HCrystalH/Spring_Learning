package Ex2.fa.training.service;

import Ex2.fa.training.entity.Student;
import Ex2.fa.training.repository.StudentRepository;
import Ex2.fa.training.service.base.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends GenericServiceImpl<Student,Long> {
    public StudentService(StudentRepository studentRepository){
        super(studentRepository);
    }

    // Implement business methods
}
