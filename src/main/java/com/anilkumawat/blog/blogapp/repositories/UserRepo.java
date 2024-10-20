package com.anilkumawat.blog.blogapp.repositories;

import com.anilkumawat.blog.blogapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
