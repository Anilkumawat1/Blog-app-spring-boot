package com.anilkumawat.blog.blogapp.controllers;

import com.anilkumawat.blog.blogapp.dto.ApiResponse;
import com.anilkumawat.blog.blogapp.dto.PostDto;
import com.anilkumawat.blog.blogapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class postController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{user_id}/category/{category_id}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer user_id,@PathVariable Integer category_id){
        PostDto createdPost = postService.createPost(postDto,user_id,category_id);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id){
        return ResponseEntity.ok(postService.getById(id));
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostDto>> getAllPostDto(@RequestParam(defaultValue = "1",required = false) Integer pageNumber,
                                                       @RequestParam(defaultValue = "5",required = false) Integer pageSize){
        return ResponseEntity.ok(postService.getAllPost(pageNumber,pageSize));
    }

    @PutMapping("/post")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,Integer id){
        return ResponseEntity.ok(postService.updateByPostId(postDto,id));
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer id){
        postService.deletePostById(id);
        return new ResponseEntity<>(new ApiResponse("Deleted Post Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/user/post/{id}")
    public ResponseEntity<List<PostDto>> getPostOfUser(@PathVariable Integer id){
        return ResponseEntity.ok(postService.getByUser(id));
    }

    @GetMapping("/category/post/{id}")
    public ResponseEntity<List<PostDto>> getPostOfCategory(@PathVariable Integer id){
        return ResponseEntity.ok(postService.getByCategory(id));
    }

    @GetMapping("/post/search/{title}")
    public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String title){
        return ResponseEntity.ok(postService.searchByTitle(title));
    }

}
