package com.cognizant.ormlearn.service.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.quiz.Attempt;
import com.cognizant.ormlearn.repository.quiz.AttemptRepository;

@Service
public class AttemptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttemptService.class);

    @Autowired
    private AttemptRepository attemptRepository;

    @Transactional
    public Attempt getAttempt(int userId, int attemptId) {
        LOGGER.info("Start");
        return attemptRepository.getAttempt(userId, attemptId).get();
    }
}
