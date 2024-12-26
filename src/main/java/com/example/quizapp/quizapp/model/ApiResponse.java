package com.example.quizapp.quizapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class ApiResponse {
    String message;
    Question data;

}
