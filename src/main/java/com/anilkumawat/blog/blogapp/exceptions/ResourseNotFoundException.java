package com.anilkumawat.blog.blogapp.exceptions;

public class ResourseNotFoundException extends RuntimeException{

    public ResourseNotFoundException(String resourceName, String fieldName, Integer id) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,id));
    }
}
