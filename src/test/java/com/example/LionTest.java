package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {


    @Test
    public void constructorWithMaleSexSetsHasManeTrue() throws Exception {
        Feline felineMock = mock(Feline.class);
        Lion lion = new Lion("Самец", felineMock);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void constructorWithFemaleSexSetsHasManeFalse() throws Exception {
        Feline felineMock = mock(Feline.class);
        Lion lion = new Lion("Самка", felineMock);
        assertFalse(lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void constructorWithInvalidSexThrowsException() throws Exception {
        new Lion("Неизвестно", mock(Feline.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullFelineThrowsException() throws Exception {
        new Lion("Самец", null);
    }

    @Test
    public void getFoodShouldReturnPredatorFood() throws Exception {
        Feline felineMock = mock(Feline.class);
        when(felineMock.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        Lion lion = new Lion("Самец", felineMock);
        assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());
    }

    @Test
    public void getKittensShouldReturnOne() throws Exception {
        Feline felineMock = mock(Feline.class);
        when(felineMock.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самка", felineMock);
        assertEquals(1, lion.getKittens());
    }

    @Test
    public void doesHaveManeForMaleReturnsTrue() throws Exception {
        Feline felineMock = mock(Feline.class);
        Lion lion = new Lion("Самец", felineMock);
        assertTrue(lion.doesHaveMane());
    }
}