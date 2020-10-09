package com.fml.learn.basiclearn.patterns.abstractFactory;

public class Flower implements Plants {
    @Override
    public void show() {
        System.out.println("花");
    }
}
