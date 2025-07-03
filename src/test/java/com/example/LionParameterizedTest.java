package com.example;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class LionParameterizedTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private final String sex;
    private final List<String> expectedFood;
    private final boolean expectedMane;
    private final Class<? extends Throwable> expectedException;
    private final int expectedKittens;
    private final Predator predator;

    public LionParameterizedTest(String sex,
                                 List<String> expectedFood,
                                 boolean expectedMane,
                                 Class<? extends Throwable> expectedException,
                                 int expectedKittens,
                                 Predator predator) {
        this.sex = sex;
        this.expectedFood = expectedFood;
        this.expectedMane = expectedMane;
        this.expectedException = expectedException;
        this.expectedKittens = expectedKittens;
        this.predator = predator;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() throws Exception {
        Feline feline = mock(Feline.class);
        when(feline.eatMeat()).thenReturn(List.of("Мясо"));
        when(feline.getKittens()).thenReturn(3);

        Predator otherPredator = mock(Predator.class);
        when(otherPredator.eatMeat()).thenReturn(List.of("Рыба"));

        return Arrays.asList(new Object[][]{
                {"Самец", List.of("Мясо"), true, null, 3, feline},
                {"Самка", List.of("Мясо"), false, null, 3, feline},
                {"Другой", null, false, Exception.class, 0, feline},
                {"Самец", List.of("Рыба"), true, null, 0, otherPredator}
        });
    }

    @Before
    public void resetMocks() throws Exception {
        reset(predator);
        if (predator instanceof Feline) {
            Feline feline = (Feline) predator;
            when(feline.eatMeat()).thenReturn(expectedFood);
            when(feline.getKittens()).thenReturn(expectedKittens);
        } else {
            when(predator.eatMeat()).thenReturn(expectedFood);
        }
    }

    @Test
    public void testLionFunctionality() throws Exception {
        if (expectedException != null) {
            exceptionRule.expect(expectedException);
            new Lion(sex, predator);
            return;
        }

        Lion lion = new Lion(sex, predator);

        assertEquals("Грива", expectedMane, lion.doesHaveMane());
        assertEquals("Еда", expectedFood, lion.getFood());
        assertEquals("Котята", expectedKittens, lion.getKittens());

        verify(predator, times(1)).eatMeat();
        if (predator instanceof Feline) {
            verify((Feline) predator, times(1)).getKittens();
        }
    }

    @Test
    public void testMethodCallsOrder() throws Exception {
        if (expectedException != null) return;

        Lion lion = new Lion(sex, predator);
        lion.getFood();
        lion.getKittens();

        InOrder inOrder = inOrder(predator);
        inOrder.verify(predator).eatMeat();
        if (predator instanceof Feline) {
            inOrder.verify((Feline) predator).getKittens();
        }
    }
}