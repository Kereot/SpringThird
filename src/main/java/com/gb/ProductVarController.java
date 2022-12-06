package com.gb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductVarController {
    @Autowired
    private ProductVarRepository productVarRepository;

    @GetMapping("/list2")
    public String list(Model model, Model model2) {
        List<ProductVar> products = productVarRepository.getProducts();
        model.addAttribute("products", products);
        ProductVar productVar = new ProductVar();
        model2.addAttribute("productVar", productVar);
        return "list2";
    }

    @PostMapping("/list2/add")
    public String addProduct(@ModelAttribute ProductVar productVar){
        if (productVar.getId() != null && !productVar.getTitle().isBlank() && productVar.getCost() != null) {
            productVarRepository.add(productVar);
        }
        return "redirect:/list2";
    }

    @PostMapping("/list2/remove")
    public String removeProduct(@ModelAttribute ProductVar productVar){
        if (productVar != null) productVarRepository.remove(productVar.getId());
        return "redirect:/list2";
    }
}