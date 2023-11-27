package com.fml.learn.algorithm.sort;

public class SortAll {
    /**
     * 插入排序
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertSort(T[] a) {
        int j;
        for (int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    /**
     * 希尔排序
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] a) {
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                T tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

}
