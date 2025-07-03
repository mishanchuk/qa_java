package com.example;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterizedAnimalTest {

    // Общие переменные для теста
    private String animalType; // "Lion" или "Cat"
    private String sex; // "Самец" или "Самка" (для Lion)
    private List<String> mockFood; // Мокнутый ответ
    private boolean expectMane; // Для Lion
    private boolean expectException; // Ожидается исключение

    private Predator predatorMock;

    public ParameterizedAnimalTest(String animalType, String sex, List<String> mockFood, boolean expectMane, boolean expectException) {
        this.animalType = animalType;
        this.sex = sex;
        this.mockFood = mockFood;
        this.expectMane = expectMane;
        this.expectException = expectException;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        predatorMock = mock(Predator.class);
        try {
            when(predatorMock.eatMeat()).thenReturn(mockFood);
        } catch (Exception e) {
            // Не ожидается
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Тест для Lion: самец
                { "Lion", "Самец", List.of("Мясо", "Кости"), true, false },
                // Тест для Lion: самка
                { "Lion", "Самка", List.of("Мясо", "Кости"), false, false },
                // Тест для Lion: некорректный пол
                { "Lion", "Другой", null, false, true },
                // Тест для Cat
                { "Cat", null, List.of("Мясо", "Кости"), false, false }
        });
    }

    @Test
    public void testAnimals() throws Exception {
        if ("Lion".equals(animalType)) {
            if (expectException) {
                new Lion(sex, predatorMock);
            } else {
                Lion lion = new Lion(sex, predatorMock);
                assertEquals(expectMane, lion.doesHaveMane());
                // Проверка getFood
                List<String> food = lion.getFood();
                assertEquals(mockFood, food);
                verify(predatorMock).eatMeat();
            }
        } else if ("Cat".equals(animalType)) {
            // Создаем Feline и Cat
            Feline feline = new Feline();
            Cat cat = new Cat(feline, predatorMock);
            // Проверка getFood
            List<String> food = cat.getFood();
            assertEquals(mockFood, food);
            verify(predatorMock).eatMeat();
        }
    }
}