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
   * 堆排序
   *
   * @param
   * @return
   * @description 本方法只有一个参数，那就是待排序的array
   * @author
   */
  public static void heapSort(int[] array) {
    // 按照完全二叉树的特点，从最后一个非叶子节点开始，对于整棵树进行大根堆的调整
    // 也就是说，是按照自下而上，每一层都是自右向左来进行调整的
    // 注意，这里元素的索引是从0开始的
    // 另一件需要注意的事情，这里的建堆，是用堆调整的方式来做的
    // 堆调整的逻辑在建堆和后续排序过程中复用的
    for (int i = array.length / 2 - 1; i >= 0; i--) {
      adjustHeap(array, i, array.length);
    }
    // 上述逻辑，建堆结束
    // 下面，开始排序逻辑
    for (int j = array.length - 1; j > 0; j--) {
      // 元素交换
      // 说是交换，其实质就是把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
      swap(array, 0, j);
      // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
      // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
      // 而这里，实质上是自上而下，自左向右进行调整的
      adjustHeap(array, 0, j);
    }
  }

  /**
   * @param
   * @return
   * @description 这里，是整个堆排序最关键的地方，正是因为把这个方法抽取出来，才更好理解了堆排序的精髓，会尽可能仔细讲解
   * @author
   * @time 2018年3月9日 下午2:54:38
   */
  public static void adjustHeap(int[] array, int i, int length) {
    // 先把当前元素取出来，因为当前元素可能要一直移动
    int temp = array[i];
    // 可以参照sort中的调用逻辑，在堆建成，且完成第一次交换之后，实质上i=0；也就是说，是从根所在的最小子树开始调整的
    // 接下来的讲解，都是按照i的初始值为0来讲述的
    // 这一段很好理解，如果i=0；则k=1；k+1=2
    // 实质上，就是根节点和其左右子节点记性比较，让k指向这个不超过三个节点的子树中最大的值
    // 这里，必须要说下为什么k值是跳跃性的。
    // 首先，举个例子，如果a[0] > a[1]&&a[0]>a[2],说明0,1,2这棵树不需要调整，那么，下一步该到哪个节点了呢？肯定是a[1]所在的子树了，
    // 也就是说，是以本节点的左子节点为根的那棵小的子树
    // 而如果a[0}<a[2]呢，那就调整a[0]和a[2]的位置，然后继续调整以a[2]为根节点的那棵子树，而且肯定是从左子树开始调整的
    // 所以，这里面的用意就在于，自上而下，自左向右一点点调整整棵树的部分，直到每一颗小子树都满足大根堆的规律为止
    for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
      // 让k先指向子节点中最大的节点
      if (k + 1 < length && array[k] < array[k + 1]) {
        k++;
      }
      // 如果发现子节点更大，则进行值的交换
      if (array[k] > temp) {
        swap(array, i, k);
        // 下面就是非常关键的一步了
        // 如果子节点更换了，那么，以子节点为根的子树会不会受到影响呢？
        // 所以，循环对子节点所在的树继续进行判断
        i = k;
        // 如果不用交换，那么，就直接终止循环了
      } else {
        break;
      }
    }
  }

  /**
   * 交换元素
   *
   * @param arr
   * @param a   元素的下标
   * @param b   元素的下标
   */
  public static void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  /**
   * 小顶堆
   */
  private static void heapSortMin(int[] array) {
    for (int i = array.length / 2 - 1; i >= 0; i--) {
      adjustHeapMin(array, i, array.length);
    }
    for (int j = array.length - 1; j >= 0; j--) {
      int temp = array[0];
      array[0] = array[j];
      array[j] = temp;
      adjustHeapMin(array, 0, j);
    }
  }

  private static void adjustHeapMin(int[] array, int i, int len) {
    int temp = array[i];
    for (int k = i * 2 + 1; i < len; k = 2 * k + 1) {
      if (k + 1 < len && array[k] > array[k + 1]) {
        k++;
      }
      if (k < len && array[k] < temp) {
        array[i] = array[k];
        i = k;
      } else {
        break;
      }
    }
    array[i] = temp;
  }

  /**
   * 插入排序 把最小的插入到最前边
   *
   * @param a
   * @param <T>
   */
  public static <T extends Comparable<? super T>> void insertSort(T[] a) {
    int j;
    for (int p = 1; p < a.length; p++) { //从第2个元素开始每次都要和他前面的进行比较
      T tmp = a[p];
      for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {  //只要num[j-1]<tmp就终止循环，因为前面的是有序的，也一定是<，不用再比较了
        a[j] = a[j - 1]; //往后挪位置，腾出位置让tmp插入
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


  /**
   * 归并排序  拆分成n个子数组排序后再合并
   *
   * @param arr
   * @param low
   * @param high
   * @param tmp
   */
  public static void mergeSort(int[] arr, int low, int high, int[] tmp) {
    if (low < high) {
      // 求中间位置，用于将数组拆分成两部分
      int mid = (low + high) / 2;
      //对左边序列递归划分
      mergeSort(arr, low, mid, tmp);
      //对右边序列进行递归划分
      mergeSort(arr, mid + 1, high, tmp);
      //合并两个有序序列
      merge(arr, low, mid, high, tmp);
    }
  }

  public static void merge(int[] arr, int low, int mid, int high, int[] tmp) {
    // 用于遍历 tmp 数组的指针
    int i = 0;
    //左边序列和右边序列起始索引
    int j = low, k = mid + 1;
    // 比较左右两个有序数组的元素，并按大小依次放入 tmp 数组中
    while (j <= mid && k <= high) {
      //左半区第一个元素小于右半区第一个元素
      if (arr[j] < arr[k]) {
        //接着往后继续比
        tmp[i++] = arr[j++];
      }
      //右半区第一个元素更小，先放右半区第一个元素
      else {
        tmp[i++] = arr[k++];
      }
//            // 输出排序过程中数组 arr 的变化
      System.out.println(Arrays.toString(arr));
    }
    //若左边序列还有剩余，则将其全部拷贝进tmp[]中
    while (j <= mid) {
      tmp[i++] = arr[j++];
    }

    while (k <= high) {
      tmp[i++] = arr[k++];
    }
    // 将排好序的 tmp 数组复制到原数组 arr 中

    for (int t = 0; t < i; t++) {
      arr[low + t] = tmp[t];
    }
  }

}
