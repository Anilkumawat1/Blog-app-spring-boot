package com.anilkumawat.blog.blogapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {

    private Integer id;
    @NotEmpty
    private String type;
    @NotEmpty
    private String about;
}
