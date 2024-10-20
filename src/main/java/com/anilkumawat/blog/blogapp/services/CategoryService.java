package com.anilkumawat.blog.blogapp.services;

import com.anilkumawat.blog.blogapp.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCat(CategoryDto categoryDto);
    CategoryDto updateCat(CategoryDto categoryDto,Integer id);
    List<CategoryDto> getAllCat();
    CategoryDto getByIdCat(Integer id);
    void deleteById(Integer id);

}
