package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Mock
    private Animal animalMock;

    // Тестируемый объект создаем вручную
    private Feline createFeline() {
        return new Feline() {
            // Переопределяем метод, чтобы подменить родительскую реализацию
            @Override
            public List<String> getFood(String animalKind) throws Exception {
                return animalMock.getFood(animalKind);
            }
        };
    }

    // Тест 1: getKittens() без параметров
    @Test
    public void getKittensNoArgumentsReturnsOne() {
        Feline feline = new Feline(); // Тестируем реальный объект
        assertEquals(1, feline.getKittens());
    }

    // Тест 2: getKittens(int) с валидным значением
    @Test
    public void getKittensWithPositiveNumberReturnsSameValue() {
        Feline feline = new Feline();
        assertEquals(3, feline.getKittens(3));
    }

    // Тест 3: getKittens(int) с отрицательным значением
    @Test(expected = IllegalArgumentException.class)
    public void getKittensWithNegativeNumberThrowsException() {
        new Feline().getKittens(-1);
    }

    // Тест 4: getFamily()
    @Test
    public void getFamilyReturnsCatFamily() {
        assertEquals("Кошачьи", new Feline().getFamily());
    }

    // Тест 5: eatMeat() вызывает getFood("Хищник")
    @Test
    public void eatMeatCallsGetFoodWithPredatorType() throws Exception {
        Feline feline = createFeline();
        feline.eatMeat();
        verify(animalMock, times(1)).getFood("Хищник");
    }

    // Тест 6: eatMeat() возвращает значение от getFood()
    @Test
    public void eatMeatReturnsValueFromGetFood() throws Exception {
        Feline feline = createFeline();
        when(animalMock.getFood("Хищник")).thenReturn(List.of("Мясо"));
        assertEquals(List.of("Мясо"), feline.eatMeat());
    }
}