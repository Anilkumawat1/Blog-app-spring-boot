package com.anilkumawat.blog.blogapp.dto;

import com.anilkumawat.blog.blogapp.models.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private String imageUrl;
    @NotEmpty
    private UserDto user;
    @NotEmpty
    private CategoryDto category;
}
