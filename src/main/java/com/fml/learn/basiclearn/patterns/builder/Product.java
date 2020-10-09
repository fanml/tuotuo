package com.fml.learn.basiclearn.patterns.builder;

public class Product {
    private String erDuo = "耳朵";
    private String biZi = "鼻子";
    private String zui = "嘴";

    public void show() {
        System.out.println("productCat " + this.erDuo + this.biZi + this.zui);
    }

    public String getErDuo() {
        return erDuo;
    }

    public void setErDuo(String erDuo) {
        this.erDuo = erDuo;
    }

    public String getBiZi() {
        return biZi;
    }

    public void setBiZi(String biZi) {
        this.biZi = biZi;
    }

    public String getZui() {
        return zui;
    }

    public void setZui(String zui) {
        this.zui = zui;
    }
}
