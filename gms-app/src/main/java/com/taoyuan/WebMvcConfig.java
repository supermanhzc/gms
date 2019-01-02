//package com.taoyuan;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Slf4j
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Value("${image.path}")
//    private String imagePath;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        log.info("image.path={}",imagePath);
//        registry.addResourceHandler("/img/**").addResourceLocations("file:/"+imagePath);
//    }
//}
