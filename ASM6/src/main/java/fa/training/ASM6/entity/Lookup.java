package fa.training.ASM6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic lookup entity for storing reference data
 * Used to map status codes to human-readable labels
 * (e.g., type="course_status", lookupKey="1", value="Draft")
 */
@Entity
@Table(name="tbl_lookup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lookup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String type;

    @Column(name="lookup_key", nullable = false)
    private String lookupKey;

    @Column(nullable = false, name="lookup_value")
    private String value;
}
