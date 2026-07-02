package fa.training.ASM6.service;

import fa.training.ASM6.entity.Category;
import fa.training.ASM6.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Tracks frequency counts for each category name extracted
 * from the comma-separated category field on Courses
 * Frequencies are updated when courses are created, edited, or deleted
 */
@Service
@RequiredArgsConstructor // Constructor injection
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void updateFrequency(String categories){
        if(categories == null || categories.isBlank()) return;

        String[] parts = categories.split(",");
        for(String part : parts){
            String name = part.trim().toLowerCase();

            if(name.isEmpty())  continue;

            Category category = categoryRepository.findByName(name).orElseGet( () ->{
                Category c = new Category();
                c.setName(name);
                c.setFrequency(0);
                return c;
            });
            category.setFrequency(category.getFrequency()+1);
            categoryRepository.save(category);
        }
    }

    public void decreaseFrequency(String categories) {
        if (categories == null || categories.isBlank()) return;

        String[] parts = categories.split(",");
        for (String part : parts) {
            String name = part.trim().toLowerCase();

            if(name.isEmpty())  continue;

            categoryRepository.findByName(name).ifPresent( category -> {
                int freq = category.getFrequency() - 1;
                if(freq <= 0) categoryRepository.delete(category);
                else{
                    category.setFrequency(freq);
                    categoryRepository.save(category);
                }
            });
        }
    }
}
