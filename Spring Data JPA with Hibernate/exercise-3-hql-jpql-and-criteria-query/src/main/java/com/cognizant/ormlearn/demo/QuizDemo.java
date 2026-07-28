package com.cognizant.ormlearn.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.ormlearn.model.quiz.Attempt;
import com.cognizant.ormlearn.model.quiz.AttemptQuestion;
import com.cognizant.ormlearn.model.quiz.Option;
import com.cognizant.ormlearn.service.quiz.AttemptService;

@Component
public class QuizDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuizDemo.class);

    @Autowired
    private AttemptService attemptService;

    public void runDemo() {
        testGetAttemptDetail();
    }

    // fetch quiz attempt details using HQL and display
    // username, attempt date, each question, its options with score and
    // whether that option was the one selected by the user
    private void testGetAttemptDetail() {
        LOGGER.info("Start");
        Attempt attempt = attemptService.getAttempt(1, 1);
        LOGGER.debug("User:{}", attempt.getUser().getName());
        LOGGER.debug("Attempt Date:{}", attempt.getAttemptDate());
        for (AttemptQuestion attemptQuestion : attempt.getAttemptQuestionList()) {
            LOGGER.debug(attemptQuestion.getQuestion().getText());
            for (Option option : attemptQuestion.getQuestion().getOptionList()) {
                boolean selected = attemptQuestion.getAttemptOptionList().stream()
                        .anyMatch(attemptOption -> attemptOption.getOption().getId() == option.getId());
                LOGGER.debug(" {}\t{}\t{}", option.getText(), option.getScore(), selected);
            }
        }
        LOGGER.info("End");
    }
}
