package com.cognizant.ormlearn.model.quiz;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qn_id")
    private int id;

    @Column(name = "qn_text")
    private String text;

    // Eager here since every option under a question, along with its score,
    // is always needed to render the attempt summary
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Option> optionList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", text=" + text + "]";
    }
}
