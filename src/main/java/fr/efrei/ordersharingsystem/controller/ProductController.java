package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> getProducts(@PathVariable Integer id) {//get product from id
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping("/create")
    public ResponseEntity<List<Product>> createProducts(@PathVariable Integer id) {//create product from body
        return ResponseEntity.ok(new ArrayList<>());
    }
}