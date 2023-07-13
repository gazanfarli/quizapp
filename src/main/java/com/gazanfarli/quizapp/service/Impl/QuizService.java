package com.gazanfarli.quizapp.service.Impl;

import com.gazanfarli.quizapp.model.QuizResponse;
import com.gazanfarli.quizapp.repository.QuestionRepository;
import com.gazanfarli.quizapp.repository.QuizRepository;
import com.gazanfarli.quizapp.dto.QuestionDTO;
import com.gazanfarli.quizapp.mapper.QuestionDTOMapper;
import com.gazanfarli.quizapp.model.Question;
import com.gazanfarli.quizapp.model.Quiz;
import com.gazanfarli.quizapp.service.IQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService implements IQuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionDao;
    private final QuestionDTOMapper questionDTOMapper;

    @Override
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        // get random questions from db
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        // set quiz attributes
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        // save quiz to db
        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<QuestionDTO>> getQuiz(Integer id) {
        List<QuestionDTO> questionDTOS = quizRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException())
                .getQuestions().stream()
                .map(questionDTOMapper)
                .toList();

        return new ResponseEntity<>(questionDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses) {
        List<Question> questions = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException())
                .getQuestions();
        int count = 0;
        int i = 0;
        for(QuizResponse response: responses) {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                count++;

            i++;
        }

        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
