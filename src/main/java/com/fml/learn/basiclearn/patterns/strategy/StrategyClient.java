package com.fml.learn.basiclearn.patterns.strategy;

public class StrategyClient {
    public static void main(String[] args) {
        Context c = new Context();
        Strategy s = new ConcreteStrategyB();
        c.setStrategy(s);
        c.strategyMethod();
    }
}
