package Ex2.fa.training.utils;

import Ex2.fa.training.entity.CourseId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StringToCourseIdConverter implements Converter<String, CourseId> {

    @Override
    public CourseId convert(String source) {
        String[] parts = source.split("_");
        return new CourseId(parts[0], LocalDate.parse(parts[1]));
    }
}
