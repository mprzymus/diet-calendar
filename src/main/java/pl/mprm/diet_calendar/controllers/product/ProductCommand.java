package pl.mprm.diet_calendar.controllers.product;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProductCommand {
    private Long id;
    private String makroskladniki;
    private String mikroskladniki;

    private Double kalorycznosc;
    private String nazwa;
    private Double gramatura;
    private String opis;
    private Boolean czyPelnowartosciowy;
}
