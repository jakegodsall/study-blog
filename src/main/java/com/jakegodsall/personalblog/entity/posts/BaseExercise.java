package com.jakegodsall.personalblog.entity.posts;

import jakarta.persistence.*;

import java.util.Objects;

@MappedSuperclass
public abstract class BaseExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String solution;

    public BaseExercise() {
    }

    public BaseExercise(Long id,
                        String question,
                        String solution) {
        this.id = id;
        this.question = question;
        this.solution = solution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseExercise that = (BaseExercise) o;
        return Objects.equals(id, that.id) && Objects.equals(question, that.question) && Objects.equals(solution, that.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, solution);
    }

    @Override
    public String toString() {
        return "BaseExercise{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}
