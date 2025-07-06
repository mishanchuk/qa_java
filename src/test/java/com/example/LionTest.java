package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LionTest {
    @Mock
    private Predator predator;

    @Parameterized.Parameters(name = "Пол: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самец"},
                {"Самка"}
        });
    }

    @Parameterized.Parameter
    public String sex;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    // Тест 1: Проверка наличия гривы
    @Test
    public void doesHaveMane_ShouldReturnCorrectForSex() throws Exception {
        Lion lion = new Lion(sex, predator);
        boolean result = lion.doesHaveMane();
        if ("Самец".equals(sex)) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }

    // Тест 2: Проверка вызова eatMeat()
    @Test
    public void getFood_ShouldCallEatMeatOnce() throws Exception {
        Lion lion = new Lion(sex, predator);
        lion.getFood();
        verify(predator, times(1)).eatMeat();
    }

    // Тест 3: Проверка возврата котят
    @Test
    public void getKittens_ShouldCallGetKittensOnce() throws Exception {
        Feline felineMock = mock(Feline.class);
        Lion lion = new Lion(sex, felineMock);
        lion.getKittens();
        verify(felineMock, times(1)).getKittens();
    }
    @Test(expected = Exception.class)
    public void constructor_WithInvalidSex_ShouldThrowException() throws Exception {
        new Lion("Неизвестно", mock(Predator.class));
    }
}