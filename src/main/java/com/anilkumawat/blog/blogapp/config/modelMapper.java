package com.anilkumawat.blog.blogapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class modelMapper {

    @Bean
    public ModelMapper getModel(){
        return new ModelMapper();
    }
}
