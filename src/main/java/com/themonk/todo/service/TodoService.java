package com.themonk.todo.service;

import com.themonk.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private static List<Todo> todos_all = new ArrayList();

    public static int idCounter = 0;

        public static void init() {
                todos_all.clear();
                todos_all.add(new Todo(++idCounter, "leapdemo", "Learn Angular 1", new Date(), false));
                todos_all.add(new Todo(++idCounter, "leapdemo", "Learn Microservice 1", new Date(), false));
                todos_all.add(new Todo(++idCounter, "leapdemo", "Learn Kafka 1", new Date(), false));

                todos_all.add(new Todo(++idCounter, "leapdemo2", "Learn Angular 2", new Date(), false));
                todos_all.add(new Todo(++idCounter, "leapdemo2", "Learn Microservice 2", new Date(), false));
                todos_all.add(new Todo(++idCounter, "leapdemo2", "Learn Kafka 2", new Date(), false));
        }
        static {
            init();
        }

        public List<Todo> findAll(String username) {
            return todos_all.stream().filter(Todo -> Todo.getUsername().equals(username)).collect(Collectors.toList());
        }

        public Todo deleteById(long id, String username) {
            Todo todo = findById(id, username);
            if(todo == null) return null;
            if(todos_all.remove(todo)){
                return todo;
            }
            return null;
        }

    public Todo findById(long id, String username) {
        for(Todo todo : todos_all){
            if(todo.getId() == id && todo.getUsername().equals(username)) {
                return todo;
            }
        }
        return null;
    }

    public Todo save(String username, Todo todo) {
        if(todo.getId() == 0 || todo.getId() == -1){
//            todo.setId(++this.idCounter);
            todos_all.add(todo);
        }else {
            deleteById(todo.getId(), username);
            todos_all.add(todo);
        }
        return todo;
    }
}
