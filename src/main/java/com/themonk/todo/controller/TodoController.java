package com.themonk.todo.controller;

import com.themonk.todo.model.Todo;
import com.themonk.todo.repository.TodoRepository;
import com.themonk.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@CrossOrigin(origins="http://localhost:3200")
@CrossOrigin(origins="*")
@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    private static List<Todo> todos_all = new ArrayList();
    public static int idCounter = 0;
    public static void init() {
        todos_all.clear();
        todos_all.add(new Todo(++idCounter, "leapdemo", "Learn Java", new Date(), false));
        todos_all.add(new Todo(++idCounter, "leapdemo", "Learn Java Script", new Date(), false));
        todos_all.add(new Todo(++idCounter, "leapdemo", "Learn Microservices", new Date(), false));

        todos_all.add(new Todo(++idCounter, "leapdemo2", "Learn Java", new Date(), false));
        todos_all.add(new Todo(++idCounter, "leapdemo2", "Learn Java Script", new Date(), false));
        todos_all.add(new Todo(++idCounter, "leapdemo2", "Learn Microservices", new Date(), false));
    }

    @GetMapping("/resetDB")
    public void resestDB() {
        todoRepository.deleteAll();
        init();
        todoRepository.saveAll(todos_all);
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }

    @DeleteMapping("/users/{username}/todo/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/users/{username}/todo/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        return todoRepository.findById(id).get();
    }

    @PutMapping("/users/{username}/todo/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo) {
        Todo updatedTodo = todoRepository.save(todo);
        return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/todo")
    public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo createdTodo = todoRepository.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
