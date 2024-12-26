package com.example.quizapp.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.quizapp.dao.QuestionDao;
import com.example.quizapp.quizapp.model.ApiResponse;
import com.example.quizapp.quizapp.model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<ApiResponse> addQuestion(Question question) {
        ApiResponse response = new ApiResponse();
        questionDao.save(question);
        response.setMessage("Question added successfully!!");
        response.setData(question);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> delQuestion(Integer id) {
        ApiResponse response = new ApiResponse();
        try {
            Question question = questionDao.findById(id).get();
            questionDao.deleteById(id);
            response.setMessage("Question deleted successfully!!");
            response.setData(question);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Failed to delete question. Error: " + e.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ApiResponse> updQuestion(Integer id, Question question) {
        ApiResponse response = new ApiResponse();

        Question existingQuestion = questionDao.findById(id).get();
        existingQuestion.setCategory(question.getCategory());
        existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
        existingQuestion.setQuestionTitle(question.getQuestionTitle());
        existingQuestion.setOption1(question.getOption1());
        existingQuestion.setOption2(question.getOption2());
        existingQuestion.setOption3(question.getOption3());
        existingQuestion.setOption4(question.getOption4());
        existingQuestion.setRightAnswer(question.getRightAnswer());

        questionDao.save(existingQuestion);

        response.setMessage("Question updated by id: "+ id);
        response.setData(question);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Question> getQuestionById(Integer id) {
        Question question = questionDao.findById(id).get();
        return new ResponseEntity(question, HttpStatus.OK);
    }
}
