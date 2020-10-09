package com.fml.learn.basiclearn.patterns.abstractFactory;

public class LnFarm implements FarmFactory {
    @Override
    public Animals newAnimals() {
        System.out.println("辽宁农场生了一只狗");
        return new Dog();
    }

    @Override
    public Plants newPlants() {
        System.out.println("辽宁农场种了一朵花");
        return new Flower();
    }
}
