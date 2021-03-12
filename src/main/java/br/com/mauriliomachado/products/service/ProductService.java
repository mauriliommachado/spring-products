package br.com.mauriliomachado.products.service;

import br.com.mauriliomachado.products.model.Product;
import br.com.mauriliomachado.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product findById(long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public boolean existsById(long id) {
        return this.productRepository.existsById(id);
    }
}
