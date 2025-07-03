package com.example;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class CatTest {

    private Predator predatorMock = mock(Predator.class);
    private Feline felineMock = mock(Feline.class);
    private Cat cat = new Cat(felineMock, predatorMock);

    @Test
    public void testCatFood() throws Exception {
        List<String> expectedFood = List.of("Рыба", "Мясо");
        when(predatorMock.eatMeat()).thenReturn(expectedFood);

        List<String> actualFood = cat.getFood();

        assertEquals(expectedFood, actualFood);
        verify(predatorMock).eatMeat();
    }

    @Test
    public void testCatSound() {
        assertEquals("Мяу", cat.getSound());
    }
}
