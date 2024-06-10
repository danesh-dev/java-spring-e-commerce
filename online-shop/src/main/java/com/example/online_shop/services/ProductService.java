package com.example.online_shop.services;

import com.example.online_shop.dto.ProductDto;
import com.example.online_shop.models.Product;
import com.example.online_shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void create(ProductDto productDto) throws Exception {
        if ((productRepository.existsByNameAndCategory(productDto.getName(), productDto.getCategory())) && (productDto.getStock() > 0)) {
            throw new Exception("Product is already in Sell");
        }

        Product product = new Product(
                productDto.getName(),
                productDto.getImagePath(),
                productDto.getPrice(),
                productDto.getStock(),
                productDto.getCategory(),
                productDto.getSeller()
        );

        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(item -> new ProductDto(
                        item.getName(),
                        item.getImagePath(),
                        item.getPrice(),
                        item.getStock(),
                        item.getCategory(),
                        item.getSeller()
                ))
                .collect(Collectors.toList());
    }
}