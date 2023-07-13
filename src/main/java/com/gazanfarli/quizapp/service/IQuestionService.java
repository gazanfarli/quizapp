package com.gazanfarli.quizapp.service;

import com.gazanfarli.quizapp.model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IQuestionService {
    ResponseEntity<List<Question>> getAllQuestions();
    ResponseEntity<List<Question>> getQuestionsByCategory(String category);
    ResponseEntity<String> addQuestion(Question question);
}
