package com.todospringboot.todobackend.service;

import com.todospringboot.todobackend.dto.TodoDto;
import com.todospringboot.todobackend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id) throws ResourceNotFoundException;
}
