package pl.mprm.diet_calendar.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Posilek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = DailyMenu.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "jadlospisid")
    private DailyMenu dailyMenu;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "danie")
    private Collection<Skladnik> skladniki = new HashSet<>();

    @Enumerated(value = EnumType.ORDINAL)
    private RodzajPosilku rodzajPosilku;

    @NotNull
    @Min(0)
    @Max(23)
    private Integer godzinaPosilku;

    @NotNull
    @Min(0)
    @Max(59)
    private Integer minutaPosilku;

    @Min(0)
    private Double kalorycznosc;

}