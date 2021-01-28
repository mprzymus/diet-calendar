package pl.mprm.diet_calendar.controllers.product;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    private Long id;
    private String makroskladniki;
    private String mikroskladniki;

    @NotNull
    @Min(0)
    private Double kalorycznosc;

    @NotBlank
    @NotEmpty
    private String nazwa;

    @NotNull
    @Min(0)
    private Double gramatura;

    @NotEmpty
    private String opis;

    private Boolean czyPelnowartosciowy;
}
