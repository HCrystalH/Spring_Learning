package fa.training.ASM6.service;

import fa.training.ASM6.entity.Instructor;
import fa.training.ASM6.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Authentication logic
 * simple username password matching
 */
@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public Instructor login(String username, String password){
        return instructorRepository.findByUsername(username)
                .filter(instructor -> instructor.getPassword().equals(password))
                .orElse(null);
    }

    public Instructor findByUsername(String username){
        return instructorRepository.findByUsername(username).orElse(null);
    }
}
