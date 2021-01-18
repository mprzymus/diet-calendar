package pl.mprm.diet_calendar.controllers.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.mprm.diet_calendar.service.ProductService;
import pl.mprm.diet_calendar.service.UserService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/dietitian")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/products")
    public String showProducts(Model model, @ModelAttribute("error") String error) {
        var allProducts = productService.findAllProductsAsCommand();
        model = userService.addUserToModel(model);
        model.addAttribute("products", allProducts);
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "products";
    }

    @PostMapping("/products")
    public RedirectView processChanges(@ModelAttribute("product") @Valid ProductCommand product, BindingResult bindingResult, RedirectAttributes attributes) {
        var redirectView = new RedirectView("/dietitian/products", true);
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getAllErrors().toString());
            bindingResult.getFieldErrors()
                    .forEach(error -> attributes.addFlashAttribute(error.getField(), error.getDefaultMessage()));
        } else {
            productService.saveCommand(product);
        }
        return redirectView;
    }
}
