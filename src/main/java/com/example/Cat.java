package com.example;

import java.util.List;

public class Cat {

    private Predator predator;

    public Cat(Feline feline, Predator predator) {
        this.predator = predator;
    }

    public String getSound() {
        return "Мяу";
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }
}