package com.fml.learn.basiclearn.patterns.builder;

/**
 * 抽象建造者（Builder）
 */
abstract class Builder {
    protected Product cat = new Product();

    public abstract Builder builderErDuo(String a);

    public abstract Builder builderBiZi(String a);

    public abstract Builder builderZui(String a);

    public  Product getProduct(){
        return cat;
    }

}
