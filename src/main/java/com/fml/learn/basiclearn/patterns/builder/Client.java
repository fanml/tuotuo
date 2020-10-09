package com.fml.learn.basiclearn.patterns.builder;

public class Client {
    public static void main(String[] args) {
        Builder builder = new CatBuilderOne();

        Director director = new Director(builder);
        Product cat = director.builder();
        cat.show();

        Builder builder2 = new CatBuilderTwo();
        Product cat2 = builder2.builderBiZi("一只鼻子").builderErDuo("俩只耳朵").builderZui("一张嘴").getProduct();
        cat2.show();
    }
}
