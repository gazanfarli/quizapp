package com.gazanfarli.quizapp.dto;

import lombok.Builder;

@Builder
public record QuestionDTO (
    Integer id,
    String questionTitle,
    String option1,
    String option2,
    String option3,
    String option4
) {}
