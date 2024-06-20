package com.example.online_shop.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/upload/**")
                //for danesh
                .addResourceLocations("file:///C:/Users/Danesh/Desktop/online shop/main/e-commerce/online-shop/src/main/resources/static/assets/upload/");

                  //todo for ashkan
//                 .addResourceLocations("file:/Users/ashykng/Projects/e-commerce/online-shop/src/main/resources/static/assets/upload/");

    }
}
