package com.example.quizapp.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.quizapp.model.QuestionWrapper;
import com.example.quizapp.quizapp.model.Quiz;
import com.example.quizapp.quizapp.model.Response;
import com.example.quizapp.quizapp.service.QuizService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    
    @GetMapping("all")
    public ResponseEntity<List<Quiz>> getAllQuiz(){
            return quizService.getAllQuiz();
    }

    @PostMapping("create")
    public ResponseEntity<String> creatQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getMethodName(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response) {
       return quizService.calculateResult(id, response); 
    }
    
    
    
}
