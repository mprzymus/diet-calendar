package pl.mprm.diet_calendar.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mprm.diet_calendar.model.Product;
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
        var allProducts = productService.findAllProducts();
        model = userService.addUserToModel(model);
        model.addAttribute("products", allProducts);
        model.addAttribute("productToTest", allProducts.get(0));
        return "products";
    }

    @PostMapping("/products")
    public String processChanges(@ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            log.error("Coś się wyjebało!");
        }
        log.debug("Post");
        return "redirect:/dietitian/products";
    }
}
