package com.quizapp.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    String categoryName;
    Integer numOfQuestion;
    String title;
}
