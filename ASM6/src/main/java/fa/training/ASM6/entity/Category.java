package fa.training.ASM6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


/**
 * Entity tracking category frequency for the category cloud display
 * Each category name is unique;
 * frequency indicates how many courses are tagged with this category
 */
@Entity
@Table(name="tbl_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Category name is required")
    private String name;

    // The # of courses associated with this category
    @Column(nullable = false)
    @NotNull(message = "Frequency is required")
    private Integer frequency;
}
