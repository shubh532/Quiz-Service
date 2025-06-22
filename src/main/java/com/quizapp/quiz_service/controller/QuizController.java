package com.quizapp.quiz_service.controller;

import com.quizapp.quiz_service.model.QuestionWrapper;
import com.quizapp.quiz_service.model.QuizDto;
import com.quizapp.quiz_service.model.Response;
import com.quizapp.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumOfQuestion(), quizDto.getTitle());
    }

    @GetMapping("getQuiz/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable Integer quizId){
        return quizService.getQuestions(quizId);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId, @RequestBody List<Response> response){
        return quizService.calculateScore(quizId,response);
    }
}