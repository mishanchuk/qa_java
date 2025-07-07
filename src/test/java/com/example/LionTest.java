package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Feline felineMock;

    @Test
    public void getFoodShouldCallEatMeatOnFeline() throws Exception {
        // Задаем поведение мока
        when(felineMock.eatMeat()).thenReturn(List.of("Мясо", "Птица"));

        Lion lion = new Lion("Самец", felineMock);
        List<String> food = lion.getFood();

        assertEquals(List.of("Мясо", "Птица"), food);
        verify(felineMock, times(1)).eatMeat();
    }

    @Test
    public void getKittensShouldCallGetKittensOnFeline() throws Exception {
        when(felineMock.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самка", felineMock);
        int kittens = lion.getKittens();

        assertEquals(3, kittens);
        verify(felineMock, times(1)).getKittens();
    }

    @Test
    public void maleLion_HasMane() throws Exception {
        Lion lion = new Lion("Самец", felineMock);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void femaleLionHasNoMane() throws Exception {
        Lion lion = new Lion("Самка", felineMock);
        assertFalse(lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void invalidSexThrowsException() throws Exception {
        new Lion("Неизвестно", felineMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullFelineThrowsException() throws Exception {
        new Lion("Самец", null);
    }
}