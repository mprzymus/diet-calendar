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
import pl.mprm.diet_calendar.service.UserService;

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
        var allProducts = productService.findAllProductsAsCommand();
        model = userController.addUserDataToModel(model);
        model.addAttribute("products", allProducts);
        return "dietitian/products";
    }

    @GetMapping("/addProduct/{id}")
    public String addProducts(Model model, @PathVariable String id) {
        var allProducts = productService.findAllProductsAsCommand();
        model = userController.addUserDataToModel(model);
        model.addAttribute("products", allProducts);
        return "dietitian/addProduct";
    }

    @PostMapping("/addProduct")
    public RedirectView processChanges(@ModelAttribute("product") @Valid ProductDto product, BindingResult bindingResult, RedirectAttributes attributes) {
        var redirectView = new RedirectView("/dietitian/products", true);
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getAllErrors().toString());
            bindingResult.getFieldErrors()
                    .forEach(error -> attributes.addFlashAttribute(error.getField(), error.getDefaultMessage()));
        } else {
            if (product.getId() != null && productService.nameExists(product.getNazwa())) {
                attributes.addFlashAttribute("duplicated", messageConfiguration.getDuplicatedMessage());
            }
            else {
                productService.saveCommand(product);
            }
        }
        return redirectView;
    }
}
