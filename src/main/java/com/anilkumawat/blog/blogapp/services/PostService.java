package com.anilkumawat.blog.blogapp.services;

import com.anilkumawat.blog.blogapp.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer user_id,Integer category_id);
    PostDto getById(Integer id);
    List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
    void deletePostById(Integer id);
    PostDto updateByPostId(PostDto postDto,Integer id);
    List<PostDto> getByUser(Integer id);
    List<PostDto> getByCategory(Integer id);
    List<PostDto> searchByTitle(String title);
}
