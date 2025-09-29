package com.example.todoapp.controller;

import com.example.todoapp.entity.Todo;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public String listTodos(Model model) {
        model.addAttribute("todos", todoService.getTodosForCurrentUser());
        model.addAttribute("todo", new Todo());
        return "todos";
    }

    @PostMapping("/todos")
    public String addTodo(@ModelAttribute Todo todo) {
        todoService.saveTodo(todo);
        return "redirect:/todos";
    }

    @PostMapping("/todos/{id}/complete")
    public String completeTodo(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            todo.setCompleted(true);
            todoService.saveTodo(todo);
        }
        return "redirect:/todos";
    }

    @PostMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
}