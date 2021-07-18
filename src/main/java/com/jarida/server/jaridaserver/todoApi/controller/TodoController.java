package com.jarida.server.jaridaserver.todoApi.controller;

import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.todoApi.model.Todo;
import com.jarida.server.jaridaserver.todoApi.service.TodoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v2")
@Validated
@Api(tags = "TODO API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "TODO", description = "This is for getting  TODO Api")
})
public class TodoController {
    TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting list of todos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 409, message = "The resource you were trying to reach exist/conflict"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal server error")
            //@ApiResponse(code = 500, message = "Failure", response = ErrorResource.class)
    }
    )
    public List<Todo> getAllTodos() {
        return todoService.getTodos();
    }


    @GetMapping("/todo/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting a single todo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 409, message = "The resource you were trying to reach exist/conflict"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal server error")
            //@ApiResponse(code = 500, message = "Failure", response = ErrorResource.class)
    }
    )
    public ResponseEntity<Todo> getTodoById(@PathVariable(value = "todoId") Long todoId) {
        todoService.getTodoById(todoId);

        Optional<Todo> todoOptional = Optional.ofNullable(todoService.getTodoById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Todo with id :: " + todoId + " does not exists"
                )));

        return ResponseEntity.of(todoOptional);
    }

    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for posting a todo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 409, message = "The resource you were trying to reach exist/conflict"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal server error")
    }
    )
    public ResponseEntity<Todo> addNote(@Valid @RequestBody Todo todo) {
        todoService.insert(todo);
        return ResponseEntity.ok().body(todo);
    }


    @PutMapping("/todo/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for updating a single todo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 409, message = "The resource you were trying to reach exist/conflict"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal server error")
            //@ApiResponse(code = 500, message = "Failure", response = ErrorResource.class)
    }
    )
    public Todo updateTodo(@PathVariable(value = "todoId") Long todoId,
                                           @Valid  @RequestBody Todo todos) {
       return todoService.updateTodo(todoId, todos);
    }

    @DeleteMapping("/todo/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for deleting a single todo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 409, message = "The resource you were trying to reach exist/conflict"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 500, message = "Internal server error")
            //@ApiResponse(code = 500, message = "Failure", response = ErrorResource.class)
    }
    )

    public Map<String, String> deleteTodo(@PathVariable(value = "todoId") Long todoId) {
        todoService.deleteTodo(todoId);
        Map<String,String> response = new HashMap<>();
        response.put("timestamp", new SimpleDateFormat("dd, MMMM, yyyy - hh:mm aa").format(Calendar.getInstance().getTime()));
        response.put("message","Deleted Successfully");
        return response;
    }



}
