package com.fml.learn.basiclearn.patterns.builder;

/**
 * 具体建造者(Concrete Builder）
 */
public class CatBuilderOne extends Builder {
    @Override
    public Builder builderErDuo(String a) {
        cat.setErDuo(a);
        System.out.println("builderErDuo" + cat.getErDuo());
        return this;
    }

    @Override
    public Builder builderBiZi(String a) {
        cat.setBiZi(a);
        System.out.println("builderBiZi" + cat.getBiZi());
        return this;
    }

    @Override
    public Builder builderZui(String a) {
        cat.setZui(a);
        System.out.println("builderZui" + cat.getZui());
        return this;
    }
}
