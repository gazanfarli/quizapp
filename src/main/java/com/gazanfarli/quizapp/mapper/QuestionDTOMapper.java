package com.gazanfarli.quizapp.mapper;

import com.gazanfarli.quizapp.dto.QuestionDTO;
import com.gazanfarli.quizapp.model.Question;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class QuestionDTOMapper implements Function<Question, QuestionDTO> {
    @Override
    public QuestionDTO apply(Question question) {
        return new QuestionDTO(
                question.getId(),
                question.getQuestionTitle(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4()
        );
    }
}
