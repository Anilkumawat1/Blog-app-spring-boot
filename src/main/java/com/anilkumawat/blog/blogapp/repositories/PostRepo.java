package com.anilkumawat.blog.blogapp.repositories;

import com.anilkumawat.blog.blogapp.models.Category;
import com.anilkumawat.blog.blogapp.models.Post;
import com.anilkumawat.blog.blogapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);
}
