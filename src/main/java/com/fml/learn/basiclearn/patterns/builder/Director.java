package com.fml.learn.basiclearn.patterns.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product builder() {
        builder.builderBiZi("大鼻子");
        builder.builderErDuo("小眼睛");
        builder.builderZui("大嘴");
        return builder.getProduct();
    }
}
