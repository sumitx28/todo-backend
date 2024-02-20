package com.todospringboot.todobackend.controller;

import com.todospringboot.todobackend.dto.TodoDto;
import com.todospringboot.todobackend.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping()
    public ResponseEntity<TodoDto> addTodoHandler(@RequestBody @Valid TodoDto todoDto) {
        TodoDto newTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoHandler(@PathVariable("id") Long id) {
        TodoDto savedTodo = todoService.getTodo(id);
        return ResponseEntity.ok(savedTodo);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodoHandler() {
        List<TodoDto> allTodos = todoService.getAllTodo();
        return ResponseEntity.ok(allTodos);
    }

    @PutMapping
    public ResponseEntity<TodoDto> updateTodo( @RequestBody TodoDto todoDto) {
        TodoDto updatedTodo = todoService.updateTodo(todoDto);
        return ResponseEntity.ok(updatedTodo);
    }
}
