package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Spy
    @InjectMocks
    private Feline felineSpy;

    // Тест 1: getKittens() без параметров вызывает getKittens(1)
    @Test
    public void getKittens_NoArguments_CallsWithDefaultOne() {
        felineSpy.getKittens();
        verify(felineSpy, times(1)).getKittens(1);
    }

    // Тест 2: getKittens(int) возвращает переданное значение
    @Test
    public void getKittens_WithArgument_ReturnsSameValue() {
        assertEquals(3, felineSpy.getKittens(3));
    }

    // Тест 3: Отрицательное количество котят вызывает исключение
    @Test(expected = IllegalArgumentException.class)
    public void getKittens_NegativeNumber_ThrowsException() {
        felineSpy.getKittens(-1);
    }

    // Тест 4: getFamily() возвращает правильное значение
    @Test
    public void getFamily_ReturnsCatFamily() {
        assertEquals("Кошачьи", felineSpy.getFamily());
    }

    // Тест 5: eatMeat() вызывает getFood("Хищник")
    @Test
    public void eatMeat_CallsGetFoodWithPredatorType() throws Exception {
        // Подменяем только getFood(), оставляя остальную логику
        doReturn(List.of("Мясо")).when(felineSpy).getFood("Хищник");

        felineSpy.eatMeat();

        verify(felineSpy, times(1)).getFood("Хищник");
    }

    // Тест 6: eatMeat() возвращает правильный список еды
    @Test
    public void eatMeat_ReturnsCorrectFoodList() throws Exception {
        // Подменяем возвращаемое значение getFood()
        doReturn(List.of("Мясо")).when(felineSpy).getFood("Хищник");

        List<String> result = felineSpy.eatMeat();
        assertEquals(List.of("Мясо"), result);
    }
}