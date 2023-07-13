package com.gazanfarli.quizapp.service;

import com.gazanfarli.quizapp.dto.QuestionDTO;
import com.gazanfarli.quizapp.model.QuizResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IQuizService {
    ResponseEntity<String> createQuiz(String category, int numQ, String title);

    ResponseEntity<List<QuestionDTO>> getQuiz(Integer id);

    ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses);
}
