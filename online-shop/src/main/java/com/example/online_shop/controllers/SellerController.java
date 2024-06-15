package com.example.online_shop.controllers;

import com.example.online_shop.dto.CategoryDto;
import com.example.online_shop.dto.ProductDto;
import com.example.online_shop.models.Product;
import com.example.online_shop.services.CategoryService;
import com.example.online_shop.services.CustomUserDetail;
import com.example.online_shop.services.ProductService;
import com.example.online_shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/seller")
public class SellerController{
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping()
    public String index(Model model){
        String sellerName = getSellerName();
        List<Product> products = productService.findProductsBySellerId(userService.getUserId(sellerName));

        model.addAttribute("sellerName", sellerName);
        model.addAttribute("products", products);
        return "seller/index";

    }

    //create product
    @GetMapping("/add-product")
    public String showAddProductPage(Model model){
        String sellerName = getSellerName();

        ProductDto product = new ProductDto();
        List<CategoryDto> categories = categoryService.getAllCategories();

        model.addAttribute("product",product);
        model.addAttribute("categories", categories);
        model.addAttribute("sellerName", sellerName);

        return "seller/add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") ProductDto productDto,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             Model model) throws Exception {
        String sellerName = getSellerName();
        int sellerId = userService.getUserId(sellerName);


        productDto.setImagePath(saveImage(imageFile));
        productDto.setSellerId(sellerId);

        // Create the product
        productService.create(productDto);

        model.addAttribute("sellerName", sellerName);
        return "redirect:/seller/my-products";
    }


    //my products
    @GetMapping("/my-products")
    public String showMyProducts(Model model){
        String sellerName = getSellerName();
        List<Product> products = productService.findProductsBySellerId(userService.getUserId(sellerName));

        System.out.println(products.size());
        model.addAttribute("products", products);
        model.addAttribute("sellerName", sellerName);

        return "seller/my-products";
    }


    //update
    @GetMapping("/products/update/{name}")
    public String showUpdatePage(@PathVariable("name") String name, Model model){
        List<CategoryDto> categories = categoryService.getAllCategories();

        ProductDto product = new ProductDto();
        Product chosen_product = productService.findByName(name);
        product.setName(chosen_product.getName());
        product.setPrice(chosen_product.getPrice());
        product.setStock(chosen_product.getStock());
        product.setSellerId(chosen_product.getSellerId());
        product.setCategory(chosen_product.getCategory());
        product.setDescription(chosen_product.getDescription());

        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "seller/edit-product";
    }
    @PostMapping("/products/update")
    public String updateProduct(@ModelAttribute("product") ProductDto productDto, Model model, @RequestParam("imageFile") MultipartFile imageFile){
        try {
            if(imageFile != null)
                productDto.setImagePath(saveImage(imageFile));
            else
                productDto.setImagePath("/assets/upload/default-image.jpeg");

            productService.updateProduct(productDto);
            model.addAttribute("message", "محصول با موفقیت ویرایش شد");

        }catch (IOException e){
            model.addAttribute("message", e.getMessage());
        }

        return "redirect:/seller/my-products";
    }


    //delete
    @DeleteMapping("products/delete/{name}")
    public String deleteProduct(@PathVariable String name){
        productService.deleteProduct(productService.findByName(name));

        return "redirect:/seller/my-product";
    }



    //---------------------------------------------------------
    private String getSellerName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        return userDetails.getName();
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        // Create the directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save the file to the upload directory
        String filePath = uploadDir + File.separator + fileName;
        File destFile = new File(filePath);
        imageFile.transferTo(destFile);

        return "/assets/upload/" + fileName;
    }
}
