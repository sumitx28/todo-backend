package com.todospringboot.todobackend.service.impl;

import com.todospringboot.todobackend.dto.TodoDto;
import com.todospringboot.todobackend.entity.Todo;
import com.todospringboot.todobackend.exception.ResourceAlreadyExistsException;
import com.todospringboot.todobackend.exception.ResourceNotFoundException;
import com.todospringboot.todobackend.repository.TodoRepository;
import com.todospringboot.todobackend.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        Todo existing = todoRepository.findTodoByTitle(todoDto.getTitle());

        if(existing != null) {
            throw new ResourceAlreadyExistsException("Todo" , "title" , todoDto.getTitle());
        }

        Todo newTodo = todoRepository.save(modelMapper.map(todoDto, Todo.class));
        return modelMapper.map(newTodo, TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo" , "ID" , id)
        );

        return modelMapper.map(todo, TodoDto.class);
    }
}
