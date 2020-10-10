package com.fml.learn.basiclearn.patterns.adapter;

public class AdapterClient {
    public static void main(String[] args) {
        Target target = new ClassAdapter();
        target.request();

        Target target1 = new ObjectAdapter(new Adaptee());
        target1.request();
    }
}
