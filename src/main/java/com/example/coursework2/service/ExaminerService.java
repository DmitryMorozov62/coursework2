package com.example.coursework2.service;

import com.example.coursework2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    public Collection<Question> getQuestion(int amount);
}
