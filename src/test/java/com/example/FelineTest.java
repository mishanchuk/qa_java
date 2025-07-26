package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Spy
    private Feline felineSpy;

    @Test
    public void eatMeatShouldReturnCorrectPredatorFood() throws Exception {
        doReturn(List.of("Животные", "Птицы", "Рыба"))
                .when(felineSpy).getFood("Хищник");
        List<String> result = felineSpy.eatMeat();
        assertEquals(List.of("Животные", "Птицы", "Рыба"), result);
    }

    @Test
    public void eatMeatShouldCallGetFoodWithPredatorArgument() throws Exception {
        doReturn(List.of("Животные", "Птицы", "Рыба"))
                .when(felineSpy).getFood("Хищник");
        felineSpy.eatMeat();
        verify(felineSpy, times(1)).getFood("Хищник");
    }

    @Test
    public void getKittensNoArgumentsShouldReturnOne() {
        assertEquals(1, new Feline().getKittens());
    }

    @Test
    public void getKittensNoArgumentsShouldCallWithDefaultValue() {
        Feline spy = spy(new Feline());
        spy.getKittens();
        verify(spy, times(1)).getKittens(1);
    }

    @Test
    public void getKittensWithArgumentShouldReturnSameValue() {
        assertEquals(3, new Feline().getKittens(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getKittensNegativeCountShouldThrowException() {
        new Feline().getKittens(-1);
    }

    @Test
    public void getFamilyShouldReturnCatFamily() {
        assertEquals("Кошачьи", new Feline().getFamily());
    }

    @Test(expected = Exception.class)
    public void eatMeatWhenGetFoodThrowsExceptionShouldPropagateIt() throws Exception {
        doThrow(new Exception("Ошибка")).when(felineSpy).getFood("Хищник");
        felineSpy.eatMeat();
    }
}