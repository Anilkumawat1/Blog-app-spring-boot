package com.anilkumawat.blog.blogapp.services;

import com.anilkumawat.blog.blogapp.dto.UserDto;
import com.anilkumawat.blog.blogapp.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

public  interface UserService {

     UserDto createUser(UserDto user);
     UserDto updateUser(UserDto user,Integer userId);
     UserDto getUserById(Integer userId);
     List<UserDto> getAllUsers();
     void deleteUser(Integer userId);

}
