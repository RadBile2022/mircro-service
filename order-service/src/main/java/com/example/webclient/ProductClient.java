package com.example.webclient;

import com.example.dto.Customer;
import com.example.dto.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProductClient {

    @GetExchange("/api/product/{id}")
    public Product findById(@PathVariable("id") Long id);




}
