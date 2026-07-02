package fa.training.ASM6.service;

import fa.training.ASM6.entity.Course;
import fa.training.ASM6.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CRUD + pagination
 * category filtering, and status management (draft/published/archived).
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;

    public static final int STATUS_DRAFT = 1;
    public static final int STATUS_PUBLISHED = 2;
    public static final int STATUS_ARCHIVED = 3;

    /*
        Page<Course> contains:
        - list of courses for current page
        - total pages
        - total elements
     */
    public Page<Course> findPublishedCourses(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findByStatus(STATUS_PUBLISHED,pageable);
    }

    public Page<Course> findPublishedByCategory(String category, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return courseRepository.findPublishedByCategory(STATUS_PUBLISHED,category,pageable);
    }

    public Page<Course> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());
        return courseRepository.findAll(pageable);
    }

    public Course findById(Integer courseId){
        return courseRepository.findById(courseId).orElse(null);
    }

    public Course save(Course course){
        return courseRepository.save(course);
    }

    public void delete(Integer courseId){
        courseRepository.deleteById(courseId);
    }

    public long countByStatus(Integer status){
        return courseRepository.countByStatus(status);
    }

    public long getTotalCourses(){
        return courseRepository.count();
    }

    public List<Course> getRecentPublishedCourses(){
        return courseRepository.findByStatusOrderByCreatedAtDesc(STATUS_PUBLISHED);
    }

    public void updateStatus(Integer courseId, Integer status){
        Course course = findById(courseId);

        if(course == null){
            log.info("Course with ID {} not found. Cannot update status.", courseId);
            return;
        }

        course.setStatus(status);
        courseRepository.save(course);
    }

    public long getTotalRecords(){
        return courseRepository.totalRecords();
    }
}
