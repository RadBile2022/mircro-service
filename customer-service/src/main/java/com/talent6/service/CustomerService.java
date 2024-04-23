package com.talent6.service;

import com.talent6.entity.Customer;
import com.talent6.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer findById(Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        log.info("find customer by id : {}" , customer);
        return customer;
    }

    public Iterable<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findByEmail(String email){
        return customerRepository.findByEmail(email);
    }
}
