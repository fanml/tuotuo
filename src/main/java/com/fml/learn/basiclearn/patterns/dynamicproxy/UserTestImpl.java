package com.fml.learn.basiclearn.patterns.dynamicproxy;

public class UserTestImpl implements UserTest {
    @Override
    public void add() {
        System.out.println("add");
    }

    @Override
    public void delete() {
        System.out.println("delete");
    }
}
