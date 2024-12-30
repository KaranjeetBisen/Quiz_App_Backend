package com.example.quizapp.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.quizapp.quizapp.model.ApiResponse;
import com.example.quizapp.quizapp.model.Question;
import com.example.quizapp.quizapp.service.QuestionService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    // @RequestMapping
    // public String showPage() {
    // return "quizApplication";
    // }
    // Crud Questions Controller
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/allCategories")
    public ResponseEntity<List<String>> getAllCategories() {
        return questionService.getAllCategories();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<ApiResponse> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("del/{id}")
    public ResponseEntity<ApiResponse> addQuestion(@PathVariable Integer id) {
        return questionService.delQuestion(id);
    }

    @PutMapping("upd/{id}")
    public ResponseEntity<ApiResponse> updQuestion(@PathVariable Integer id, @RequestBody Question question) {
        return questionService.updQuestion(id, question);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Question> getQuestionByid(@PathVariable Integer id) {
        return questionService.getQuestionById(id);
    }

}
