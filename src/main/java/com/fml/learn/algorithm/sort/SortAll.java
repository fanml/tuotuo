package com.fml.learn.algorithm.sort;

import java.util.Arrays;

public class SortAll {


  public static void main(String[] args) {
    Integer a[] = {2, 5, 3, 7, 4, 8};
//    bubbleSort(a);
    selectionSort(a);
    System.out.println(Arrays.toString(a));
  }

  /**
   * 冒泡排序 冒泡排序就是把小的元素往前调或者把大的元素往后调，比较是相邻的两个元素比较，交换也发生在这两个元素之间。（类似于气泡上浮过程)
   */
  public static <T extends Comparable<? super T>> void bubbleSort(T[] a) {
    for (int i = 0; i < a.length; i++) { // 外层循环控制排序趟数
      for (int j = 0; j < a.length - i - 1; j++) {// 内层循环交换相邻元素位置 将最大的元素移动到最后
        if (a[j].compareTo(a[j + 1]) > 0) {
          T tmp = a[j];
          a[j] = a[j + 1];
          a[j + 1] = tmp;
        }
      }
    }
  }


  /**
   * 选择排序 从未排序序列中找到最小（最大），放在已排序序列尾部
   */
  public static <T extends Comparable<? super T>> void selectionSort(T[] a) {
    for (int i = 0; i < a.length - 1; i++) { // 外层循环控制排序趟数
      int p = i; // 记录最小值
      for (int j = i + 1; j < a.length; j++) { // 内存循环找到比最小值小的元素 交换
        if (a[j].compareTo(a[p]) < 0) {
          p = j;
        }
      }
      // 将最小元素和未排序部分的第一个元素进行交换
      T tmp = a[p];
      a[p] = a[i];
      a[i] = tmp;
    }
  }

  /**
   * 快速排序
   */
  public static void quickSort(int[] arrays, int left, int right) {
    // 左指针大于右指针 退出递归
    if (left > right) {
      return;
    }
    int l = left; // 左指针
    int r = right; // 右指针
    int pivot = arrays[left]; // 基点
    int temp = 0;
    while (l < r) { //左指针和右指针相等时 推出循环
      while (pivot <= arrays[r] && l < r) { // 基点小于右边节点
        r--;
      }
      while (pivot >= arrays[l] && l < r) { // 基点大于右边节点
        l++;
      }
      if (l <= r) { // 左右指针相遇 交换
        temp = arrays[r];
        arrays[r] = arrays[l];
        arrays[l] = temp;
      }
    }
    // 基点归位
    arrays[left] = arrays[l];
    arrays[l] = pivot;
    quickSort(arrays, left, l - 1);
    quickSort(arrays, l + 1, right);
  }

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
   *
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
