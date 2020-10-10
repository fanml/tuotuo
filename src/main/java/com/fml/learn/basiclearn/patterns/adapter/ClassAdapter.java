package com.fml.learn.basiclearn.patterns.adapter;

/**
 * 类适配器
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.specificRequest();
    }
}
