package com.fml.learn.basiclearn.patterns.Builder;

public class CatBuilderOne extends Builder {
    @Override
    public void builderErDuo() {
        cat.setErDuo("大耳朵");
        System.out.println("builderErDuo" + cat.getErDuo());
    }

    @Override
    public void builderBiZi() {
        cat.setBiZi("小鼻子");
        System.out.println("builderBiZi" + cat.getErDuo());
    }

    @Override
    public void builderZui() {
        // 默认值
        System.out.println("builderZui" + cat.getZui());
    }
}
