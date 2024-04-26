package com.example;

import com.example.webclient.CustomerClient;
import com.example.webclient.ProductClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "TALENT PRODUCT MICRO SERVICE",
        description = "product modul microservice",
        version = "1.0"
))
@EnableDiscoveryClient // gak guna
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
//    @Bean
//    @LoadBalanced // tidak ada berakibat : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://CUSTOMER-SERVICE/api/customer/1": CUSTOMER-SERVICE] with root cause
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    @Autowired
    private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;
    @Bean
    WebClient webClientCustomer(){
        return WebClient.builder()
                .baseUrl("http://customer-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();

    }

    @Bean
    WebClient webClientProduct(){
        return WebClient.builder()
                .baseUrl("http://product-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();

    }

    @Bean
    CustomerClient customerClient(){
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClientCustomer()))
                .build();

        return factory.createClient(CustomerClient.class);
    }

    @Bean
    ProductClient productClient(){
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClientProduct()))
                .build();

        return factory.createClient(ProductClient.class);
    }
}
