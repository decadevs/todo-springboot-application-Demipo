package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/todos")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public void todoService(TodoService todoService) {
        this.todoService = todoService;
    }

    //view all todo
    @RequestMapping( method = RequestMethod.GET)
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    //view a particular todo with the id
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Optional<Todo> getTodo(@PathVariable(name = "id") Integer id){
        return todoService.getTodo(id);
    }

    //view a particular status as done/pending/in progress
    @RequestMapping(path = "/status/{status}", method = RequestMethod.GET)
    public List<Todo> getTodoByStatus(@PathVariable(name = "status") String status){
        return todoService.getTodoByStatus(status);
    }

    //create a todo
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Todo saveTodo(@RequestBody Todo todoToSave){
        return todoService.saveTodo(todoToSave);
    }

    //edit title and description fields
    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Todo updateTitleAndDescription(@RequestBody Todo todoToUpdate, @PathVariable(name = "id") Integer id) {
        return todoService.updateTitleAndDescription(todoToUpdate, id);
    }

    //edit title, status and description fields
    @RequestMapping(path = "/status/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Todo updateStatus(@RequestBody Todo todoToUpdate, @PathVariable(name = "id") Integer id) {
        return todoService.updateStatus(todoToUpdate, id);
    }

    //delete a todo by id
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable(name = "id") Integer id){
        todoService.deleteTodo(id);
    }
}

