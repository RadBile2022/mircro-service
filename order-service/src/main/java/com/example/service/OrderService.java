package com.example.service;

import com.example.dto.Customer;
import com.example.dto.OrderLineResponse;
import com.example.dto.OrderResponse;
import com.example.dto.Product;
import com.example.entity.Order;
import com.example.entity.OrderLine;
import com.example.repository.OrderRepository;
import com.example.webclient.CustomerClient;
import com.example.webclient.ProductClient;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@Transactional // kalau order gagal, otomatis orderline digagalkan
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;
    public Order save(Order order) {
        log.info("save order : {}", order);


        for (OrderLine orderLine : order.getOrderLines()) {
            orderLine.setOrder(order);
        }
        return orderRepository.save(order);
    }

    public OrderResponse findById(Long id) {
        Optional<Order> optOrder = orderRepository.findById(id);
        if (!optOrder.isPresent()) {
            return null;
        }

        Order order = optOrder.get();
        OrderResponse response = OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderDate(order.getOrderDate())
                .customer(customerClient.findById(order.getId()))
                .orderLineResponses(new ArrayList<OrderLineResponse>())
                .build();

        for(OrderLine orderLine: order.getOrderLines()){
            Product product = productClient.findById(orderLine.getProductId());
            response.getOrderLineResponses().add(new OrderLineResponse(orderLine.getId(), product, orderLine.getQuantity(),
                    orderLine.getPrice()));
        }
        log.info("find order by Id : {}", response);

        return response;
    }

    public OrderResponse findByOrderNumber(String  orderNumber) {
        Order orderByNumber = orderRepository.findByOrderNumber(orderNumber);
        if (orderByNumber==null) {
            return null;
        }

        OrderResponse response = OrderResponse.builder()
                .id(orderByNumber.getId())
                .orderNumber(orderByNumber.getOrderNumber())
                .orderDate(orderByNumber.getOrderDate())
                .customer(customerClient.findById(orderByNumber.getId()))
                .orderLineResponses(new ArrayList<OrderLineResponse>())
                .build();

        for(OrderLine orderLine: orderByNumber.getOrderLines()){
            Product product = productClient.findById(orderLine.getProductId());
            response.getOrderLineResponses().add(new OrderLineResponse(orderLine.getId(), product, orderLine.getQuantity(),
                    orderLine.getPrice()));
        }
        log.info("find order by Id : {}", response);

        return response;
    }


//    public Customer findCustomerById(Long id) {
//        return restTemplate.getForObject("http://CUSTOMER-SERVICE/api/customer/" + id, Customer.class);
//    }

//    public Product findProductById(Long id){
//        return restTemplate.getForObject("http://PRODUCT-SERVICE/api/product/" + id, Product.class);
//    }
}