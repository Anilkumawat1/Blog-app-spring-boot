package com.anilkumawat.blog.blogapp.services.implement;

import com.anilkumawat.blog.blogapp.dto.PostDto;

import com.anilkumawat.blog.blogapp.exceptions.ResourseNotFoundException;
import com.anilkumawat.blog.blogapp.models.Category;
import com.anilkumawat.blog.blogapp.models.Post;
import com.anilkumawat.blog.blogapp.models.User;
import com.anilkumawat.blog.blogapp.repositories.CategoryRepo;
import com.anilkumawat.blog.blogapp.repositories.PostRepo;
import com.anilkumawat.blog.blogapp.repositories.UserRepo;
import com.anilkumawat.blog.blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer user_id,Integer category_id) {
        User user = userRepo.findById(user_id).orElseThrow(()->new ResourseNotFoundException("User not found","id",user_id));
        Category category = categoryRepo.findById(category_id).orElseThrow(()->new ResourseNotFoundException("Category not found","id",category_id));
        Post post = DtoToPost(postDto);
        post.setUser(user);
        post.setCategory(category);
        Post createdpost = postRepo.save(post);
//        System.out.println(createdpost);
        return PostToDto(createdpost);
    }

    @Override
    public PostDto getById(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Post not found","id",id));
        return PostToDto(post);
    }

    @Override
    public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page pages = postRepo.findAll(page);
        List<Post> posts = pages.getContent();
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts){
            postDtos.add(PostToDto(post));
        }
        return postDtos;
    }

    @Override
    public void deletePostById(Integer id) {
        Post post = postRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Post not found","id",id));
        postRepo.delete(post);
    }

    @Override
    public PostDto updateByPostId(PostDto postDto, Integer id) {
        Post post = postRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Post not found","id",id));
        Integer user_id = postDto.getUser().getId();
        Integer category_id = postDto.getCategory().getId();
        User user = userRepo.findById(user_id).orElseThrow(()->new ResourseNotFoundException("User not found","id",user_id));
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Category not found","id",category_id));
        Post updatPost = DtoToPost(postDto);
        updatPost.setCategory(category);
        updatPost.setUser(user);
        Post updatedPost = postRepo.save(updatPost);
        return PostToDto(updatedPost);
    }

    @Override
    public List<PostDto> getByUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("User","id",id));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts){
            postDtos.add(PostToDto(post));
        }
        return postDtos;
    }

    @Override
    public List<PostDto> getByCategory(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("Category","id",id));
        List<Post> posts = postRepo.findByCategory(category);
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts){
            postDtos.add(PostToDto(post));
        }
        return postDtos;
    }

    @Override
    public List<PostDto> searchByTitle(String title) {
        List<Post> posts = postRepo.searchByTitle("%"+title+"%");
        return posts.stream().map(this::PostToDto).collect(Collectors.toList());
    }

    private PostDto PostToDto(Post post){
        return modelMapper.map(post,PostDto.class);
    }
    private Post DtoToPost(PostDto postDto){
        return modelMapper.map(postDto,Post.class);
    }
}
