package com.example.coursework2.service;

import com.example.coursework2.exceptions.QuestionsAlreadyBoundException;
import com.example.coursework2.model.Question;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        if (questionService.getAll().size() < amount) {
            throw new QuestionsAlreadyBoundException();
        }
        Collection<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
           questions.add(questionService.getRandomQuestion());
        }

       return questions;
    }
}
