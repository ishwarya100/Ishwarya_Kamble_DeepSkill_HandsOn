package com.cognizant.ormlearn.repository.quiz;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.quiz.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    // Joins user, attempt, attempt_question, question, attempt_option and
    // options in that order, with fetch on every one-to-many relationship
    // used, filtered by userId and attemptId
    @Query("SELECT DISTINCT a FROM Attempt a "
            + "JOIN FETCH a.user u "
            + "JOIN FETCH a.attemptQuestionList aq "
            + "JOIN FETCH aq.question q "
            + "JOIN FETCH aq.attemptOptionList ao "
            + "JOIN FETCH ao.option o "
            + "WHERE u.id = :userId AND a.id = :attemptId")
    Optional<Attempt> getAttempt(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
