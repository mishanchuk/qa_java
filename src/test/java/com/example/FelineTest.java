package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Mock
    private Animal animalMock;

    @InjectMocks
    private Feline feline;

    @Before
    public void setup() {
        // Настраиваем моки для методов по умолчанию
        when(animalMock.getChildren()).thenReturn(1);
        when(animalMock.getChildren(anyInt())).thenAnswer(invocation -> {
            int count = invocation.getArgument(0);
            return count;
        });
    }

    @Test
    public void testEatMeatWithMock() throws Exception {
        // Настраиваем мок
        when(animalMock.getFood("Хищник")).thenReturn(List.of("Мясо", "Кости"));

        List<String> result = feline.eatMeat();

        verify(animalMock).getFood("Хищник");
        assertEquals(List.of("Мясо", "Кости"), result);
    }

    @Test
    public void testEatMeatCallsGetFoodWithPredator() throws Exception {
        // Используем реальную реализацию Animal
        Feline realFeline = new Feline(new Animal());
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");

        List<String> actualFood = realFeline.eatMeat();

        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFamilyReturnsCorrectValue() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittensWithoutParameters() {
        assertEquals(1, feline.getKittens());
        verify(animalMock).getChildren();
    }

    @Test
    public void testGetKittensWithParameter() {
        int expectedCount = 5;
        assertEquals(expectedCount, feline.getKittens(expectedCount));
        verify(animalMock).getChildren(expectedCount);
    }

    @Test
    public void testGetKittensUsesAnimalImplementation() {
        // Тест с реальной реализацией Animal
        Feline realFeline = new Feline(new Animal());
        assertTrue("Количество котят должно быть >= 0", realFeline.getKittens(3) >= 0);
    }
}