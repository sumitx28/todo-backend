package com.todospringboot.todobackend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;
    @NotEmpty(message = "title must be provided")
    private String title;
    @NotEmpty(message = "description must be provided")
    private String description;
    private boolean completed;
}
