package com.quizapp.quiz_service.service;

import com.quizapp.quiz_service.dao.QuizDao;
import com.quizapp.quiz_service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numOfQue, String title) {
        List<Integer> questionIds = quizInterface.getQuestionForQuiz(category, numOfQue).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionIds);
        quizDao.save(quiz);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED)
                ;
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer quizId) {

        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Integer> questionsIds = quiz.get().getQuestions();
        ResponseEntity<List<QuestionWrapper>> questionsForUser = quizInterface.getQuestionById(questionsIds);

        return questionsForUser;
    }

    public ResponseEntity<Integer> calculateScore(Integer quizId, List<Response> response) {

        ResponseEntity<Integer> score = quizInterface.getScore(response);

        return  score;
    }
}
