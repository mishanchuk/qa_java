package com.example;

import java.util.List;

public class Feline extends Animal implements Predator {
    @Override
    public List<String> eatMeat() throws Exception {
        return getFood("Хищник");
    }

    public int getKittens() {
        return getKittens(1);
    }

    public int getKittens(int kittensCount) {
        if (kittensCount < 0) {
            throw new IllegalArgumentException("Количество котят не может быть отрицательным");
        }
        return kittensCount;
    }

    @Override
    public String getFamily() {
        return "Кошачьи";
    }
}