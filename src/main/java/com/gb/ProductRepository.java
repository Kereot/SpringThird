package com.gb;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    @Getter
    private List<Product> products;
    @Getter
    private final int MAX_PROD = 5;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        for (int i = 0; i < MAX_PROD; i++) {
            products.add(new Product(
                    i + 1,
                    "Product #" + (i + 1),
                    (float) Math.random() * (1000 - 10 + 1) + 10));
        }
    }

    public Product getById(int id) {
        return products.stream().
                filter(p -> p.id().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public void add(Integer id, String title, Float cost) {
        products.add(new Product(id, title, cost));
    }

    public void remove(Integer id) {
        products.stream()
                .filter(x -> x.id().equals(id))
                .findFirst()
                .ifPresent(products::remove);
    }
}