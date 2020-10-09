package com.fml.learn.basiclearn.patterns.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product builder() {
        builder.builderBiZi();
        builder.builderErDuo();
        builder.builderZui();
        return builder.getBuilder();
    }
}
