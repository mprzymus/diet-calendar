package pl.mprm.diet_calendar.model.product_data;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<MacroElement> macroElements = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<MicroElement> microElements = new HashSet<>();

    private Double calories;

    @Column
    private String name;

    @Column
    private Double grams;

    @Column
    private String description;

    @Column
    private Boolean isNutritious;

}