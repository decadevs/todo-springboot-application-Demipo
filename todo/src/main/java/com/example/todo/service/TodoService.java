package com.example.todo.service;


import com.example.todo.TodoApplication;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private Logger LOG = LoggerFactory.getLogger(TodoApplication.class);

    @Autowired
    public void todoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //view all todo
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    //view a particular status as done/pending/in progress
    public List<Todo> getTodoByStatus(String status){
        List<Todo> todoList = todoRepository.findAll();
        List<Todo> list = new ArrayList<>();
        for(Todo todo : todoList){
            if(todo.getStatus().equals(status)){
                list.add(todo);
            }
        }
        return list;
    }

    //view a particular todo with the id
    public Optional<Todo> getTodo(Integer id){
        LOG.info("Getting the product with given id:" + id);
        return todoRepository.findById(id);
    }

    public Todo saveTodo(Todo todo){
        Todo todoToSave;
        try{
            LOG.info("saving todo...");
            todoToSave = todoRepository.save(todo);
        }catch(Exception e){
            LOG.error("An error occurred during saving" + e.getMessage());
        }
        return new Todo();
    }

    //edit title and description fields
    public Todo updateTitleAndDescription(Todo todoToUpdate, Integer id){
        Todo foundTodo =  todoRepository.getOne(id);
        try{
            foundTodo.setTitle((todoToUpdate.getTitle()));
            foundTodo.setDescription((todoToUpdate.getDescription()));
            todoRepository.save(foundTodo);
        }catch(Exception e){
            LOG.error("An error occurred during saving" + e.getMessage());
        }
        return todoToUpdate;
    }

    //edit status field
    public Todo updateStatus(Todo todoToUpdate, Integer id){
        Todo foundTodo =  todoRepository.getOne(id);
        try{
            foundTodo.setStatus((todoToUpdate.getStatus()));
            todoRepository.save(foundTodo);
        }catch(Exception e){
            LOG.error("An error occurred during saving" + e.getMessage());
        }
        return todoToUpdate;
    }

    //delete a todo by id
    public void deleteTodo (Integer id){
        try{
            todoRepository.deleteById(id);
        }catch(Exception e){
            LOG.error("An error occurred during saving" + e.getMessage());
        }
    }
}
