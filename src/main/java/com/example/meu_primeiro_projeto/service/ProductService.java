package com.example.meu_primeiro_projeto.service;

import com.example.meu_primeiro_projeto.dto.ProductDTO;
import com.example.meu_primeiro_projeto.model.Product;
import com.example.meu_primeiro_projeto.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    public Optional<ProductDTO> getProductById(Long id){
             return productRepository.findById(id).map(ProductDTO::new);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<?> updateCategory(Long id, String category){
        Optional<Product> newProduct =  productRepository.findById(id);

        if(newProduct.isPresent()){
            Product product = newProduct.get();
            product.setCategory(category);
            productRepository.save(product);
            return ResponseEntity.ok(Map.of("message","Categoria adicionada a produto!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Produto não encontrado!"));
        }
    }

    public ResponseEntity<?> updatePrice(Long id, Double price){
        Optional<Product> newProduct = productRepository.findById(id);

        if(newProduct.isPresent()){
            Product product = newProduct.get();
            product.setPrice(price);
            productRepository.save(product);
            return ResponseEntity.ok(Map.of("message","Preço atualizado com sucesso!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Produto não encontrado!"));
    }

}

