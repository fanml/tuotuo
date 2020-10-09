package com.fml.learn.basiclearn.patterns.abstractFactory;

public class BjFarm implements FarmFactory {
    @Override
    public Animals newAnimals() {
        System.out.println("北京农场生了一直猫");
        return new Cat();
    }

    @Override
    public Plants newPlants() {
        System.out.println("北京农场种了一颗树");
        return new Tree();
    }
}
