package Ex1.fa.training.controller;


import Ex1.fa.training.controller.base.GenericController;
import Ex1.fa.training.entity.Student;
import Ex1.fa.training.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController extends GenericController<Student,Long> {
    public StudentController(StudentService studentService){
        super(studentService, "students", "student");
    }

    @Override
    protected Class<Student> getEntityClass() {
        return Student.class;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student entity) {
        service.save(entity);
        return "redirect:/students";
    }
}
