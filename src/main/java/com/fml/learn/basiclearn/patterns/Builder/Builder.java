package com.fml.learn.basiclearn.patterns.Builder;

abstract class Builder {
    protected Product cat = new Product();

    public abstract void builderErDuo();

    public abstract void builderBiZi();

    public abstract void builderZui();

    public Product getBuilder() {
        return cat;
    }

}
