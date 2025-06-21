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
        List<Integer> questions = quizInterface.getQuestionForQuiz(category, numOfQue).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED)
                ;
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer quizId) {

        Optional<Quiz> quiz = quizDao.findById(quizId);
//        List<Integer> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
//
//        for (Integer q : questionsFromDB) {
//            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
//            questionsForUser.add(qw);
//        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(Integer quizId, List<Response> response) {

//        Quiz quiz = quizDao.findById(quizId).get();
//        List<Integer> questions=quiz.getQuestions();
        int right =0;
//        int i =0;
//
//        for (Response res : response){
//            if(res.getResponse().equals(questions.get(i).getAnswer())){
//                right++;
//            }
//            i++;
//        }

        return  new ResponseEntity<>(right, HttpStatus.OK);
    }
}
