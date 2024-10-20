package com.anilkumawat.blog.blogapp.repositories;

import com.anilkumawat.blog.blogapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
