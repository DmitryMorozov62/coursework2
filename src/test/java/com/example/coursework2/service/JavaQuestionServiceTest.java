package com.example.coursework2.service;

import com.example.coursework2.exceptions.QuestionNotFoundException;
import com.example.coursework2.exceptions.QuestionNullPointerException;
import com.example.coursework2.model.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class JavaQuestionServiceTest {
    private JavaQuestionService out = new JavaQuestionService();
    @BeforeEach
    public void setUp() {
        out.add("Что такое класс?", "Шаблон или описане объекта");
        out.add("Что такое объект?", "экземпляр класса");
    }

    public static Stream<Arguments> paramsForAddQuestion() {
        return Stream.of(
                Arguments.of(new Question("Для чего используется ключевое слово «this»?", "указатель на текущий объект класса внутри самого класса"),
                        "Для чего используется ключевое слово «this»?", "указатель на текущий объект класса внутри самого класса"),
                Arguments.of(new Question("Как называется оператор увеличения, который увеличивает значение переменной на единицу?"
                        , "инкремент"),"Как называется оператор увеличения, который увеличивает значение переменной на единицу?"
                        , "инкремент")
        );
    }
    @ParameterizedTest
    @MethodSource("paramsForAddQuestion")
    void addTest(Question result, String question, String answer) {
        assertEquals(result,out.add(question,answer));
    }

    @Test
    void addTestWithNullQuestion() {
        assertThrows(QuestionNullPointerException.class, () -> out.add(null, "пусто"));
    }

    @Test
    void addTestWithNullAnswer() {
        assertThrows(QuestionNullPointerException.class, () -> out.add("Есть ответ?", null));
    }

    public static Stream<Arguments> paramsForRemoveQuestion() {
        return Stream.of(Arguments.of(new Question("Что такое класс?", "Шаблон или описане объекта"),
                                                               "Что такое класс?", "Шаблон или описане объекта"),
                         Arguments.of(new Question("Что такое объект?", "экземпляр класса"),
                                                                "Что такое объект?", "экземпляр класса"));

    }

    @ParameterizedTest
    @MethodSource("paramsForRemoveQuestion")
    void removeTest(Question result, String question, String answer) {
        assertEquals(result,out.remove(question,answer));
    }

    @Test
    void removeTestIsNotFound() {
        assertThrows(QuestionNotFoundException.class,()-> out.remove("Такого вопроса нет", "точно нет"));
    }

    @Test
    void getAll() {
        Assertions.assertThat(out.getAll()).
                containsExactlyInAnyOrder(new Question("Что такое класс?", "Шаблон или описане объекта"),
                                          new Question("Что такое объект?", "экземпляр класса"));
    }

}