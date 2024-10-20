package com.anilkumawat.blog.blogapp.controllers;

import com.anilkumawat.blog.blogapp.dto.ApiResponse;
import com.anilkumawat.blog.blogapp.dto.CategoryDto;
import com.anilkumawat.blog.blogapp.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class categoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCat = categoryService.createCat(categoryDto);
        return new ResponseEntity<>(createdCat, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCat(){
        return new ResponseEntity<>(categoryService.getAllCat(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCatById(@PathVariable Integer id){
        return new ResponseEntity<>(categoryService.getByIdCat(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCat(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer id){
        return new ResponseEntity<>(categoryService.updateCat(categoryDto,id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer id){
        categoryService.deleteById(id);
        return new ResponseEntity<>(new ApiResponse("Deleted Category Successfully",true),HttpStatus.OK);
    }
}
