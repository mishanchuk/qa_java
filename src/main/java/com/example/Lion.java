package com.example;

import java.util.List;

public class Lion {
    private final boolean hasMane;
    private final Feline feline;

    public Lion(String sex, Feline feline) throws Exception {
        if (feline == null) {
            throw new IllegalArgumentException("Feline cannot be null");
        }

        switch (sex) {
            case "Самец":
                this.hasMane = true;
                break;
            case "Самка":
                this.hasMane = false;
                break;
            default:
                throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }
        this.feline = feline;
    }

    public List<String> getFood() throws Exception {
        return feline.eatMeat();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public int getKittens() {
        return feline.getKittens();
    }
}