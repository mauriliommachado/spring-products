package br.com.mauriliomachado.products.controller;

import br.com.mauriliomachado.products.model.Product;
import br.com.mauriliomachado.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity registerProduct(@Valid @RequestBody Product product) throws URISyntaxException {
        product = this.productService.save(product);
        return ResponseEntity.created(new URI(product.getId().toString())).body(null);
    }

    @GetMapping("/products")
    public ResponseEntity findAllProducts() {
        return ResponseEntity.ok(this.productService.findAll());
    }

    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity updateProduct(@PathVariable("id") long productId, @Valid @RequestBody Product product) throws URISyntaxException {
        try {
            Product currentProd = this.productService.findById(productId);
            currentProd.updateProduct(product);
            this.productService.save(currentProd);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getProduct(@PathVariable("id") long productId) throws URISyntaxException {
        try {
            Product p = productService.findById(productId);
            return ResponseEntity.ok(p);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
