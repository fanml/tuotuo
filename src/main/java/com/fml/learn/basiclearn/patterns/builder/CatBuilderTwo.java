package com.fml.learn.basiclearn.patterns.builder;

public class CatBuilderTwo extends Builder {
    @Override
    public Builder builderErDuo(String a) {
        cat.setErDuo(a);
        System.out.println("builderErDuo2" + cat.getErDuo());
        return this;
    }

    @Override
    public Builder builderBiZi(String a) {
        cat.setBiZi(a);
        System.out.println("builderBiZi2" + cat.getBiZi());
        return this;
    }

    @Override
    public Builder builderZui(String a) {
        cat.setZui(a);
        System.out.println("builderZui2" + cat.getZui());
        return this;
    }

}
