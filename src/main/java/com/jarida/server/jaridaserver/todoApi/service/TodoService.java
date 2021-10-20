package com.jarida.server.jaridaserver.todoApi.service;

import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.todoApi.model.Todo;
import com.jarida.server.jaridaserver.todoApi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    TodoRepository todoRepository;


    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo insert(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todo) {

        return todoRepository.findById(id).map(todos -> {

            todos.setTitle(todo.getTitle());
            todos.setDescription(todo.getDescription());
            todos.setTodoStatus(todo.getTodoStatus());

            return todoRepository.save(todos);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Todo with " + id + " not found"
        ));

    }

    public void deleteTodo(Long todoId) {
        boolean exists = todoRepository.existsById(todoId);

        if (!exists) {
            throw new ResourceNotFoundException(
                    "todo with id " + todoId + " does not exists"
            );
        }

        todoRepository.deleteById(todoId);
    }


}
