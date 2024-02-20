package com.todospringboot.todobackend.service;

import com.todospringboot.todobackend.dto.TodoDto;
import com.todospringboot.todobackend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodo();

    TodoDto updateTodo(TodoDto todoDto);

    TodoDto deleteTodo(Long id);
}
