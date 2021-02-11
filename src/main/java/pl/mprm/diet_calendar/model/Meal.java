package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Meal implements Comparable<Meal> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = DailyMenu.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "daily_menu_id")
    private DailyMenu dailyMenu;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meal", fetch = FetchType.EAGER)
    private Collection<Ingredient> ingredients = new HashSet<>();

    @Enumerated(value = EnumType.ORDINAL)
    private MealType mealType;

    @NotNull
    @Min(0)
    @Max(23)
    private Integer hour;

    @NotNull
    @Min(0)
    @Max(59)
    private Integer minute;

    @Min(0)
    private Double calories;

    @Column
    private String name;

    @Override
    public int compareTo(Meal meal) {
        int result = hour.compareTo(meal.hour);
        if (result != 0) {
            return result;
        }
        result = minute.compareTo(meal.minute);
        if (result != 0) {
            return result;
        }
        return name.compareTo(meal.name);
    }

    public String showTime() {
        var time = LocalTime.of(hour, minute);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return  time.format(dtf);
    }
}