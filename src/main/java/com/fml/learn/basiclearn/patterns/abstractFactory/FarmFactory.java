package com.fml.learn.basiclearn.patterns.abstractFactory;

public interface FarmFactory {
    public Animals newAnimals();

    public Plants newPlants();
}
