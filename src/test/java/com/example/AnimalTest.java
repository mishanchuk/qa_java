package com.example;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class AnimalTest {
    @Test
    public void getFood_ForHerbivore_ReturnsPlants() throws Exception {
        assertEquals(List.of("Трава", "Различные растения"), new Animal().getFood("Травоядное"));
    }

    @Test(expected = Exception.class)
    public void getFood_InvalidType_ThrowsException() throws Exception {
        new Animal().getFood("Неизвестный тип");
    }
}