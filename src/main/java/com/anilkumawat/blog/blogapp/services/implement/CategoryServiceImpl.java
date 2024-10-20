package com.anilkumawat.blog.blogapp.services.implement;

import com.anilkumawat.blog.blogapp.dto.CategoryDto;
import com.anilkumawat.blog.blogapp.exceptions.ResourseNotFoundException;
import com.anilkumawat.blog.blogapp.models.Category;
import com.anilkumawat.blog.blogapp.repositories.CategoryRepo;
import com.anilkumawat.blog.blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCat(CategoryDto categoryDto) {
        Category category = categoryRepo.save(CatDtoToCat(categoryDto));
        return CatToCatDto(category);
    }

    @Override
    public CategoryDto updateCat(CategoryDto categoryDto , Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Category id not found","id",id));
        category.setType(categoryDto.getType());
        category.setAbout(categoryDto.getAbout());
        Category updatedCat = categoryRepo.save(category);
        return CatToCatDto(updatedCat);
    }

    @Override
    public List<CategoryDto> getAllCat() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category cat : categories){
            categoryDtos.add(CatToCatDto(cat));
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto getByIdCat(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Category id not found","id",id));
        return CatToCatDto(category);
    }

    @Override
    public void deleteById(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Category id not found","id",id));
        categoryRepo.delete(category);
    }
    private CategoryDto CatToCatDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }
    private Category CatDtoToCat(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }
}
