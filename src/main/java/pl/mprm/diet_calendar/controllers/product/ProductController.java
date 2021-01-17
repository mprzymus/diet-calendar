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
import pl.mprm.diet_calendar.model.product_data.Product;
import pl.mprm.diet_calendar.service.ProductService;
import pl.mprm.diet_calendar.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/dietitian")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/products")
    public String showProducts(Model model) {
        var allProducts = productService.findAllProductsAsCommand();
        model = userService.addUserToModel(model);
        model.addAttribute("products", allProducts);
        return "products";
    }

    @PostMapping("/products")
    public String processChanges(@ModelAttribute("product") ProductCommand product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            log.error(bindingResult.getAllErrors().toString());
        }
        else {

        }
        return "redirect:/dietitian/products";
    }
}
