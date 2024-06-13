package com.example.online_shop.services;

import com.example.online_shop.dto.ProductDto;
import com.example.online_shop.models.Product;
import com.example.online_shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
                productDto.getSellerId()
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
                        item.getSellerId()
                ))
                .collect(Collectors.toList());
    }

    public void deleteProduct(Product entity) {
        productRepository.delete(entity);
    }

    public List<Product> findProductsBySellerId(int sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(ProductDto productDto) throws IOException {
        // Fetch the existing product to avoid overwriting important fields
        Product existingProduct = productRepository.findByName(productDto.getName());

//        if (!file.isEmpty()) {
//            existingProduct.setImage(file.getBytes());
//        }

        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setStock(productDto.getStock());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setImagePath(productDto.getImagePath());
        existingProduct.setSellerId(productDto.getSellerId());
        existingProduct.setId(getProductId(productDto.getName()));

        productRepository.save(existingProduct);
    }

    public int getProductId(String name){
        return productRepository.findByName(name).getId();
    }

    public List<Product> findLatestProducts() {
        return productRepository.findTop3ByOrderByCreatedAtDesc();
    }
}
