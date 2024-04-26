package com.talent6.controller;


import com.talent6.dto.SearchEmailRequest;
import com.talent6.entity.Customer;
import com.talent6.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@RequestMapping("/api/customer")
public class CustomerController {

    @Value("${spring.application.version}")
    private String versionString;

    @GetMapping("/version")
    public String getVersion(){
        return versionString;
    }
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
