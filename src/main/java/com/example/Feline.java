package com.example;


import java.util.List;

public class Feline implements Predator {
    private final Animal animal;

    public Feline(Animal animal) {
        this.animal = animal;
    }

    public List<String> eatMeat() throws Exception {
        return animal.getFood("Хищник");
    }

    public String getFamily() {
        return "Кошачьи";
    }

    public int getKittens() {
        return animal.getChildren();
    }

    public int getKittens(int count) {
        return animal.getChildren(count);
    }
}