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

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> allTodos = todoRepository.findAll();
        return allTodos.stream().map((todo) -> modelMapper.map(todo , TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto) {
        Todo todo = todoRepository.findById(todoDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Todo" , "ID" , todoDto.getId())
        );

        if(todoDto.getTitle() != null) todo.setTitle(todoDto.getTitle());
        if(todoDto.getDescription() != null) todo.setDescription(todoDto.getDescription());
        if(todoDto.isCompleted() != todo.isCompleted()) todo.setCompleted(todoDto.isCompleted());

        todoRepository.save(todo);

        return modelMapper.map(todo , TodoDto.class);
    }

    @Override
    public TodoDto deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo" , "ID" , id)
        );

        todoRepository.delete(todo);

        return modelMapper.map(todo , TodoDto.class);
    }


}
