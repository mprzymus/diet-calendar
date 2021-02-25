package pl.mprm.diet_calendar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mprm.diet_calendar.model.users.Dietitian;
import pl.mprm.diet_calendar.model.users.Patient;
import pl.mprm.diet_calendar.repositories.DietitianRepository;
import pl.mprm.diet_calendar.repositories.ProductRepository;
import pl.mprm.diet_calendar.model.product_data.MicroElement;
import pl.mprm.diet_calendar.model.product_data.Product;
import pl.mprm.diet_calendar.repositories.UserRepository;

@Profile("dev")
@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProductRepository repository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addProducts();
        addUsers();
    }

    private void addUsers() {
        var dietitian = new Dietitian();
        dietitian.setLogin("diet");
        dietitian.setPassword(passwordEncoder.encode("pass"));
        userRepository.save(dietitian);

        var patient = new Patient();
        patient.setLogin("user");
        patient.setPassword(passwordEncoder.encode("pass"));
        userRepository.save(patient);
    }

    private void addProducts() {
        log.info("Adding data to db");
        var product = new Product();
        product.setDescription("desc");
        product.setName("name");
        product.setIsNutritious(true);
        product.setCalories(10.0);
        product.setGrams(10.0);

        var micro = new MicroElement();
        micro.setName("micro");
        micro.setAmount(10.4);
        micro.setProduct(product);
        var micro2 = new MicroElement();
        micro2.setName("micro2");
        micro2.setAmount(1.4);
        micro2.setProduct(product);
        product.getMicroElements().add(micro);
        product.getMicroElements().add(micro2);
        repository.save(product);
    }
}
