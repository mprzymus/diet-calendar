package pl.mprm.diet_calendar.controllers.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.mprm.diet_calendar.configurations.MessageConfiguration;
import pl.mprm.diet_calendar.controllers.UserController;
import pl.mprm.diet_calendar.service.ProductService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/dietitian")
public class ProductController {

    private final ProductService productService;
    private final MessageConfiguration messageConfiguration;
    private final UserController userController;

    @GetMapping("/products")
    public String showProducts(Model model) {
        var allProducts = productService.findAllProductAsDTO();
        model = userController.addUserDataToModel(model);
        model.addAttribute("products", allProducts);
        return "dietitian/products";
    }

    @GetMapping("/addProduct/{id}")
    public String addProducts(Model model, @PathVariable String id) {
        ProductDto product;
        try {
            var idAsLong = Long.parseLong(id);
            product = productService.findDtoById(idAsLong);
        } catch (NumberFormatException exception) {
            product = new ProductDto();
        }
        model = userController.addUserDataToModel(model);
        model.addAttribute("product", product);
        return "dietitian/addProduct";
    }

    @PostMapping("/products")
    public RedirectView processEdit(@ModelAttribute("product") @Valid ProductDto product, BindingResult bindingResult, RedirectAttributes attributes) {
        var redirectView = new RedirectView("/dietitian/products", true);
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getAllErrors().toString());
            bindingResult.getFieldErrors()
                    .forEach(error -> attributes.addFlashAttribute(error.getField(), error.getDefaultMessage()));
        } else {
            if (productService.nameExists(product.getId(), product.getName())) {
                attributes.addFlashAttribute("duplicated", messageConfiguration.getDuplicatedMessage());
            } else {
                try {
                    log.info("Saved product id: {}", productService.saveCommand(product).getId().toString());
                } catch (IllegalArgumentException ex) {
                    attributes.addFlashAttribute("invalid_format", messageConfiguration.getInvalidFormat());
                }
            }
        }
        return redirectView;
    }
}
