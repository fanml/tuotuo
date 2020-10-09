package com.fml.learn.basiclearn.patterns.builder;

public class Client {
    public static void main(String[] args) {
        Builder builder = new CatBuilderOne();
        Director director = new Director(builder);
        Product cat = director.builder();
        cat.show();

    }
}
