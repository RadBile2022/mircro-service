package com.talent6.controller;


import com.talent6.dto.SearchEmailRequest;
import com.talent6.entity.Customer;
import com.talent6.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") Long id){
        return customerService.findById(id);
    }

    @GetMapping
    public Iterable<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping("/search-by-email")
    public Customer findByEmail(@RequestBody SearchEmailRequest form){
        return customerService.findByEmail(form.getEmail());
    }
}
