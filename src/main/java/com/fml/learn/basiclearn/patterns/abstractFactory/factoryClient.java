package com.fml.learn.basiclearn.patterns.abstractFactory;

public class factoryClient {
    public static void main(String[] args) {
        BjFarm bjFarm = new BjFarm();
        Animals animals = bjFarm.newAnimals();
        animals.show();
        Plants plants = bjFarm.newPlants();
        plants.show();
        LnFarm lnFarm = new LnFarm();
        lnFarm.newAnimals().show();
        lnFarm.newPlants().show();
    }
}
