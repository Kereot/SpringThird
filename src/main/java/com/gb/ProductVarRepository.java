package com.gb;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductVarRepository {
    @Getter
    private List<ProductVar> products;
    @Getter
    private final int MAX_PROD = 5;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        for (int i = 0; i < MAX_PROD; i++) {
            ProductVar product = new ProductVar();
            product.setId(i + 1);
            product.setTitle("Product #" + (i + 1));
            product.setCost((float) Math.random() * (1000 - 10 + 1) + 10);
            products.add(product);
        }
    }

    public ProductVar getById(int id) {
        return products.stream().
                filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public void add(ProductVar product) {
        products.add(product);
    }

    public void remove(Integer id) {
        products.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .ifPresent(products::remove);
    }
}