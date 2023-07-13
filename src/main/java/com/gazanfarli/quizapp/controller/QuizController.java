package com.gazanfarli.quizapp.controller;

import com.gazanfarli.quizapp.dto.QuestionDTO;
import com.gazanfarli.quizapp.model.QuizResponse;
import com.gazanfarli.quizapp.service.Impl.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(
            @RequestParam("category") String category,
            @RequestParam("numQ") int numQ,
            @RequestParam("title") String title
            ) {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuiz(@PathVariable Integer id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(
            @PathVariable Integer id,
            @RequestBody List<QuizResponse> responses
    ) {
        return quizService.calculateResult(id, responses);
    }
}
