package com.emmanuel.todoapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TodoControllerJpa {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private TodoRepository todoRepository;


    public TodoControllerJpa( TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){

        List<Todo> todos = todoRepository.findByUsername(username);

        return todos;
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodoById(@PathVariable String username, @PathVariable int id){
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable int id){
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); //This returns a 204 status code for successful deletion. (No content to return)
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo){ //ResponseEntity here is used to return the updated ttodo
        todo.setUsername(username);
        todoRepository.save(todo);
        return ResponseEntity.ok(todo);
    }

    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Void> createTodoById(@PathVariable String username, @RequestBody Todo todo){
        todo.setUsername(username);
        System.out.println("Todo: " + todo);
        todoRepository.save(todo);
        return ResponseEntity.noContent().build();
    }





    /*
    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap model) {
        //We pass an obj of ttodo to the view so that the form can bind to it and send it back to the server. This is called form backing object which is an alternative to using @RequestParam
        String username = getLoggedInUsername(model);
        //logger.info("Username: {}", username);
        Todo todo = new Todo(0, username, "", false, LocalDate.now());
        model.put("todo", todo);//The jsp form will bind to this object with the name todo
        return "addTodo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodoPost(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "addTodo";
        }

        //String username = "emmanuel";
        //TODO pass username from session
        String username = getLoggedInUsername(model);
        todo.setUsername(username);
//        logger.info("Username: {}", username);
        todoRepository.save(todo);
//        todoService.addTodo(username,ttodo.getDescription(),ttodo.isDone(), ttodo.getDateline());


        return "redirect:/todos";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:/todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String updateTodo(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.put("todo", todo);
        return "updateTodo";
    }
    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodoPost(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "updateTodo";
        }
        String username = getLoggedInUsername(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:/todos";
    }
    */

}
