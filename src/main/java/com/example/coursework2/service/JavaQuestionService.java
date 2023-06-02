package com.example.coursework2.service;

import com.example.coursework2.exceptions.QuestionNotFoundException;
import com.example.coursework2.exceptions.QuestionNullPointerException;
import com.example.coursework2.model.Question;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{

    private final Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }
    @PostConstruct
    public void init() {
        questions.add(new Question("Что такое класс?", "Шаблон или описане объекта"));
        questions.add(new Question("Что такое объект?", "экземпляр класса"));
        questions.add(new Question("Какой метод запускает программу на Java?", "метод main"));
        questions.add(new Question("Что такое аргумент метода?", "это те данные, с которыми метод будет работать"));
        questions.add(new Question("Для чего используется оператор NEW?", "для создания экземпляра класса"));
    }

    @Override
    public Question add(String question, String answer) {
        Question question1 = new Question(question, answer);
        isNull(question,answer);
        questions.add(question1);
        return question1;
    }

    @Override
    public Question remove(String question, String answer) {
        Question question1 = new Question(question, answer);
        isNull(question,answer);
        if (questions.contains(question1)) {
            questions.remove(question1);
            return question1;
        }
        throw new QuestionNotFoundException();
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        Question[] array = getAll().toArray(new Question[0]);
        int n = (int) Math. floor(Math. random() * array.length);
        return array[n];
    }

    private void isNull(String question, String answer) {
        if (question == null || answer == null) {
            throw new QuestionNullPointerException();
        }
    }
}
