package com.example;

import java.util.Arrays;
import java.util.List;

public class Animal {

    public List<String> getFood(String animalKind) throws Exception {
        if ("Травоядное".equals(animalKind)) {
            return Arrays.asList("Трава", "Различные растения");
        } else if ("Хищник".equals(animalKind)) {
            return Arrays.asList("Животные", "Птицы", "Рыба");
        } else {
            throw new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник");
        }
    }

    public String getFamily() {
        return "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
    }
    public int getChildren() {
        return 0; // Базовая реализация
    }

    public int getChildren(int count) {
        return count;
    }
}