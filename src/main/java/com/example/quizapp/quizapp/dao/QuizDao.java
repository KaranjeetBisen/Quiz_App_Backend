package com.example.quizapp.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quizapp.quizapp.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
    
}
