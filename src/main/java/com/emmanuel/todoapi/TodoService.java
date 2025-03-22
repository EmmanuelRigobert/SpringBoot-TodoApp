package com.emmanuel.todoapi;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static int todoCount = 0;
    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(todoCount++, "Emmanuel", "Learn Java1", false, LocalDate.now().plusYears(1)));
        todos.add(new Todo(todoCount++, "Emmanuel", "Learn Spring", false, LocalDate.now().plusYears(1)));
        todos.add(new Todo(todoCount++,"Emmanuel", "Learn Spring Boot", false, LocalDate.now().plusYears(1)));
        todos.add(new Todo(todoCount++,"Emmanuel", "Learn React", false, LocalDate.now().plusYears(2)));
        todos.add(new Todo(todoCount++,"Emmanuel", "Learn Spring Security", false, LocalDate.now().plusYears(2)));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> byUsername = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(byUsername).toList();
    }

    public void addTodo(String username, String description,Boolean done, LocalDate dateline) {
        Todo todo = new Todo(todoCount++, username, description, done, dateline);
        todos.add(todo);
    }

    public void deleteTodoById(int id) {
        todos.removeIf(todor -> todor.getId() == id);
    }

    public Todo findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }

    public void updateTodo(Todo todo) {
        todos.removeIf(todor -> todor.getId() == todo.getId());
        todos.add(todo);
    }
}
