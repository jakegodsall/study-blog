package com.jakegodsall.personalblog.entity.posts;

public class CodingExercise extends BaseExercise {
    public CodingExercise() {
    }

    public CodingExercise(Long id,
                          String exercise,
                          String solution) {
        super(id, exercise, solution);
    }
}
