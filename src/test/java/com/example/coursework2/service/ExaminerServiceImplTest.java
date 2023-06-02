package com.example.coursework2.service;

import com.example.coursework2.exceptions.QuestionsAlreadyBoundException;
import com.example.coursework2.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private Collection<Question> questions;

    @BeforeEach
    public void setUp() {
        questions = Set.of(new Question("Что такое класс?", "Шаблон или описане объекта"),
                           new Question("Что такое объект?", "экземпляр класса"),
                           new Question("Какой метод запускает программу на Java?", "метод main"),
                           new Question("Что такое аргумент метода?", "это те данные, с которыми метод будет работать"),
                           new Question("Для чего используется оператор NEW?", "для создания экземпляра класса"));
    }

    @Test
    void getQuestionTest() {
        Mockito.when(javaQuestionService.getRandomQuestion()).
                thenReturn(new Question("Что такое класс?", "Шаблон или описане объекта"),
                           new Question("Что такое класс?", "Шаблон или описане объекта"),
                           new Question("Что такое объект?", "экземпляр класса"));
        Collection<Question> expected = Set.of((new Question("Что такое класс?", "Шаблон или описане объекта")),
                                                new Question("Что такое объект?", "экземпляр класса"));
        assertEquals(expected,examinerService.getQuestion(2));
    }

    @Test
    void getQuestionTestNegative() {
        Mockito.when(javaQuestionService.getAll()).
                thenReturn(Set.of(new Question("Что такое класс?", "Шаблон или описане объекта"),
                        new Question("Что такое объект?", "экземпляр класса"),
                        new Question("Какой метод запускает программу на Java?", "метод main"),
                        new Question("Что такое аргумент метода?", "это те данные, с которыми метод будет работать"),
                        new Question("Для чего используется оператор NEW?", "для создания экземпляра класса")));
        assertThrows(QuestionsAlreadyBoundException.class, ()-> examinerService.getQuestion(6));
    }

}