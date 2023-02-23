package com.example.testrequestmapping;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class WebController {
    List<Todo> todoList = new CopyOnWriteArrayList<>();
    @GetMapping("/listTodo")
    public String index(Model model, @RequestParam(value = "limit", required = false) Integer limit) {
        model.addAttribute("todoList", limit != null ? todoList.subList(0, limit) : todoList);
//        nếu limit khác null thì sẽ trả về số 'limit' dữ liệu , nếu để trống thì sẽ trả về tất cả
        return "listTodo";
    }
    @GetMapping("/addTodo")
    public String addTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "addTodo";
    }
    @PostMapping("/addTodo")
    public String addTodo(@Valid @ModelAttribute Todo todo,  BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
//            throw new Exception("Invalid Todo object: "+bindingResult.getAllErrors());
            return "addTodo";
        }
        else {
            todoList.add(todo);
            return "success";
        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> InvalidAddTodo(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    }




