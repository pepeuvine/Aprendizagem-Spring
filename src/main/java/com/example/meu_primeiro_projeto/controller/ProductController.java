package com.example.meu_primeiro_projeto.controller;

import com.example.meu_primeiro_projeto.model.Product;
import com.example.meu_primeiro_projeto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        boolean deleted = productService.deleteProduct(id);
        if(deleted){
            return ResponseEntity.ok(Map.of("message", "Produto deletado com sucesso!"));
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Produto não encontrado!"));
        }
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody String category){
        return productService.updateCategory(id, category);
    }

    @PutMapping("/update-price/{id}")
    public ResponseEntity<?> updatePrice(@PathVariable Long id, @RequestBody Double price){
        return productService.updatePrice(id, price);
    }
}


