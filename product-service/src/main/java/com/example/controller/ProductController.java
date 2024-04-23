package com.example.controller;

import com.example.entity.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id){
        return productService.findById(id);
    }


    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }
}
