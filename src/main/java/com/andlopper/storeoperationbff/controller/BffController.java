package com.andlopper.storeoperationbff.controller;

import com.andlopper.storeoperationbff.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/bff")
public class BffController {

    private final RestTemplate restTemplate;

    public BffController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        String productApiUrl = "http://localhost:8081/products"; // Substitua pela URL da product-api
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity(productApiUrl, Product[].class);
        Product[] products = responseEntity.getBody();

        if (products == null || products.length == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{productId}")
    public String getProductById(@PathVariable Long productId) {
        String productApiUrl = "http://localhost:8081/products/" + productId;
        return restTemplate.getForObject(productApiUrl, String.class);
    }

    @GetMapping("/pdv")
    public String getPDVData() {
        String pdvApiUrl = "http://pdv-api/api/pdvs"; // Substitua pela URL da pdv-api
        return restTemplate.getForObject(pdvApiUrl, String.class);
    }

    @GetMapping("/sales/{saleId}")
    public String getSaleById(@PathVariable Long saleId) {
        String saleApiUrl = "http://localhost:8082/sales/" + saleId;
        return restTemplate.getForObject(saleApiUrl, String.class);
    }

    @GetMapping("/customers/{customerId}")
    public String getCustomerById(@PathVariable Long customerId){
        String customerApiUrl = "http://localhost:8083/customers/" + customerId;
        return restTemplate.getForObject(customerApiUrl, String.class);
    }

    @GetMapping("/customers")
    public String getCustomerData() {
        String customerApiUrl = "http://customer-api/api/customers"; // Substitua pela URL da customer-api
        return restTemplate.getForObject(customerApiUrl, String.class);
    }
}