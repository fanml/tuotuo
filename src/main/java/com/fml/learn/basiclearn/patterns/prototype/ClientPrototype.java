package com.fml.learn.basiclearn.patterns.prototype;

public class ClientPrototype {
    public static void main(String[] args) throws CloneNotSupportedException {
        ZhuPrototype zhu1 = new ZhuPrototype("zuy", "11");


        ZhuPrototype cloneZhu = (ZhuPrototype) zhu1.clone();
        cloneZhu.setAge("18");
        System.out.println("zhu1 " + zhu1);
        System.out.println("zhu1 hash " + zhu1.hashCode());
        System.out.println("cloneZhu " + cloneZhu);
        System.out.println("cloneZhu hash " + cloneZhu.hashCode());
    }
}
