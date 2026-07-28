package com.cognizant.ormlearn.model.quiz;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "attempt")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "at_us_id")
    private AppUser user;

    @Column(name = "at_date")
    private LocalDate attemptDate;

    @OneToMany(mappedBy = "attempt")
    private List<AttemptQuestion> attemptQuestionList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public LocalDate getAttemptDate() {
        return attemptDate;
    }

    public void setAttemptDate(LocalDate attemptDate) {
        this.attemptDate = attemptDate;
    }

    public List<AttemptQuestion> getAttemptQuestionList() {
        return attemptQuestionList;
    }

    public void setAttemptQuestionList(List<AttemptQuestion> attemptQuestionList) {
        this.attemptQuestionList = attemptQuestionList;
    }

    @Override
    public String toString() {
        return "Attempt [id=" + id + ", attemptDate=" + attemptDate + "]";
    }
}
