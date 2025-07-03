package com.example;

import java.util.List;

public class Lion {

    private boolean hasMane;
    private Predator predator;

    public Lion(String sex, Predator predator) throws Exception {
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }
        this.predator = predator;
    }

    public int getKittens() {
        if (predator instanceof Feline) {
            return ((Feline) predator).getKittens();
        }
        return 0;
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }
}