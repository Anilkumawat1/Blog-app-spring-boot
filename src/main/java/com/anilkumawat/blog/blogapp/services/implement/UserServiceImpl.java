package com.anilkumawat.blog.blogapp.services.implement;

import com.anilkumawat.blog.blogapp.dto.UserDto;
import com.anilkumawat.blog.blogapp.exceptions.ResourseNotFoundException;
import com.anilkumawat.blog.blogapp.models.User;
import com.anilkumawat.blog.blogapp.repositories.UserRepo;
import com.anilkumawat.blog.blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user) {
        User createdUser = userRepo.save(dtoToUser(user));
        return userToUserDto(createdUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourseNotFoundException("User Id not found","id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepo.save(user);
        return userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourseNotFoundException("User","id",userId));
        return userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users){
            userDtos.add(userToUserDto(user));
        }
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourseNotFoundException("User","id",userId));
        userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }
    private UserDto userToUserDto(User user){
       return modelMapper.map(user,UserDto.class);
    }
}
