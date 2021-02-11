package pl.mprm.diet_calendar.controllers.product;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    private Long id;
    private String macroElements;
    private String microElements;

    @NotNull
    @Min(0)
    private Double calories;

    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    @Min(0)
    private Double grams;

    @NotEmpty
    private String description;

    private Boolean isNutritious;
}
