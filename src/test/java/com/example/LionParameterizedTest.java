package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LionParameterizedTest {

    @Parameterized.Parameters(name = "Пол: {0}, Ожидаемая грива: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самец", true},
                {"Самка", false}
        });
    }

    @Parameterized.Parameter
    public String sex;

    @Parameterized.Parameter(1)
    public boolean expectedHasMane;

    @Test
    public void doesHaveManeShouldMatchExpectedForSex() throws Exception {
        Feline felineMock = mock(Feline.class);
        Lion lion = new Lion(sex, felineMock);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @Test
    public void getFoodShouldCallEatMeatOnFeline() throws Exception {
        Feline felineMock = mock(Feline.class);
        when(felineMock.eatMeat()).thenReturn(List.of("Мясо"));

        Lion lion = new Lion(sex, felineMock);
        List<String> food = lion.getFood();

        assertEquals(List.of("Мясо"), food);
        verify(felineMock, times(1)).eatMeat();
    }
}