package pl.mprm.diet_calendar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.mprm.diet_calendar.dao.ProductRepository;
import pl.mprm.diet_calendar.model.product_data.Mikroskladnik;
import pl.mprm.diet_calendar.model.product_data.Product;

@Profile("dev")
@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProductRepository repository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Adding data to db");
        var product = new Product();
        product.setOpis("desc");
        product.setNazwa("name");
        product.setCzyPelnowartosciowy(false);
        product.setKalorycznosc(10.0);
        product.setGramatura(10.0);

        var micro = new Mikroskladnik();
        micro.setNazwa("micro");
        micro.setIlosc(10.4);
        micro.setProduct(product);
        product.getMikroskladniki().add(micro);
        repository.save(product);
        product = new Product();
        repository.save(product);
    }
}
