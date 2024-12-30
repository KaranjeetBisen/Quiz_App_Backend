package com.example.quizapp.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.quizapp.dao.QuestionDao;
import com.example.quizapp.quizapp.dao.QuizDao;
import com.example.quizapp.quizapp.model.Question;
import com.example.quizapp.quizapp.model.QuestionWrapper;
import com.example.quizapp.quizapp.model.Quiz;
import com.example.quizapp.quizapp.model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
       
        List<Question> questions = questionDao.findRandQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz Created!" , HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz = quizDao.findById(id);
       List<Question> questionsFromDB = quiz.get().getQuestions();
       List<QuestionWrapper> questionsForUser = new ArrayList<>();

       for(Question q: questionsFromDB){
        QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
        questionsForUser.add(qw);
       }

       return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<String> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions= quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        String totalQuestions = String.valueOf(questions.size());
        String value = String.valueOf(right);
        return new ResponseEntity<>(value+" out of "+ totalQuestions, HttpStatus.OK);
    }

    public ResponseEntity<List<Quiz>> getAllQuiz() {
       List<Quiz> quizs = quizDao.findAll();
       return new ResponseEntity<>(quizs, HttpStatus.OK);
    }
}
