package com.example.service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product findById(Long id){
        Product product = productRepository.findById(id).orElse(null);
        log.info("find product by id : {}",product);
        return product;
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }
}
