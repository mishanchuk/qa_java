package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    @Mock
    private Feline feline;

    @Test
    public void getSound_ReturnsMeow() {
        assertEquals("Мяу", new Cat(feline).getSound());
    }

    @Test
    public void getFood_CallsEatMeatOnce() throws Exception {
        new Cat(feline).getFood();
        verify(feline, times(1)).eatMeat();
    }
}