package pl.mprm.diet_calendar.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mprm.diet_calendar.service.ProductService;
import pl.mprm.diet_calendar.service.UserService;

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
        return "products";
    }
}
