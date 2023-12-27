package com.fml.learn.algorithm.structure;

import java.util.Arrays;
import java.util.Comparator;

public class MyHeap<E> {

  private Object[] data;//数据存放区
  private int size;//堆的大小
  private Comparator<? super E> comparator;//比较器

  public MyHeap(int initialCapacity) {
    this(initialCapacity, null);
  }

  public MyHeap(int initialCapacity, Comparator<? super E> comparator) {
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("堆的大小必须大于0");
    }
    this.data = new Object[initialCapacity];
    this.comparator = comparator;
  }

  /**
   * @param e 向堆中添加元素
   * @return
   */
  public boolean add(E e) {
    if (e == null)//不能为空
    {
      throw new NullPointerException();
    }
    if (size >= data.length)//如果堆的空间不够了就扩容，这里是扩大2倍
    {
      data = Arrays.copyOf(data, data.length << 1);
    }
    if (size == 0)//如果堆是空的，直接添加就可以了，不需要调整，因为就一个没发调整
    {
      data[0] = e;
    } else//如果堆不是空的，就要往上调整。
    {
      siftUp(e);
    }
    size++;//添加完之后size要加1
    return true;
  }

  public int getSize() {
    return size;
  }

  //删除堆顶元素
  public E remove() {
    if (size == 0) {
      return null;
    }
    size--;
    E result = (E) data[0];//获取堆顶的元素
    E x = (E) data[size];//取出数组的最后一个元素
    data[size] = null;//然后把最后一个元素的位置置空
    if (size != 0) {
      siftDown(x);//这里实际上是把数组的最后一个元素取出放到栈顶，然后再往下调整。
    }
    return result;
  }

  //访问堆顶元素，不删除
  public E peek() {
    return (size == 0) ? null : (E) data[0];
  }

  /**
   * 返回数组的值
   *
   * @param a
   * @param <T>
   * @return
   */
  public <T> T[] toArray(T[] a) {
    if (a.length < size) {
      return (T[]) Arrays.copyOf(data, size, a.getClass());
    }
    System.arraycopy(data, 0, a, 0, size);
    if (a.length > size) {
      a[size] = null;
    }
    return a;
  }

  /**
   * 往上调整，往上调整只需要和父节点比较即可，如果比父节点大就不需要在调整
   *
   * @param e
   */
  private void siftUp(E e) {
    int s = size;
    while (s > 0) {
      int parent = (s - 1) >>> 1;//根据子节点的位置可以找到父节点的位置
      Object pData = data[parent];
      //和父节点比较，如果比父节点大就退出循环不再调整
      if (comparator != null) {
        if (comparator.compare(e, (E) pData) >= 0) {
          break;
        }
      } else {
        if (((Comparable<? super E>) e).compareTo((E) pData) >= 0) {
          break;
        }
      }
      //如果比父节点小，就和父节点交换，然后再继续往上调整
      data[s] = pData;
      s = parent;
    }
    //通过上面的往上调整，找到合适的位置，再把e放进去
    data[s] = e;
  }

  /**
   * 往下调整，往下调整需要和他的两个子节点（如果有两个子节点）都要比较，哪个最小就和哪 个交换，如果比两个子节点都小就不用再交换
   *
   * @param e
   */
  private void siftDown(E e) {
    int half = size >>> 1;
    int index = 0;
    while (index < half) {
      int min = (index << 1) + 1;//根据父节点的位置可以找到左子节点的位置
      Object minChild = data[min];
      int right = min + 1;//根据左子节点找到右子节点的位置
      if (right < size) {//如果有右子节点就执行这里的代码
        //如果有右子节点，肯定会有左子节点。那么就需要左右两个子节点比较，把小的赋值给minChild
        if (comparator != null) {
          if (comparator.compare((E) minChild, (E) data[right]) > 0) {
            minChild = data[min = right];
          }
        } else {
          if (((Comparable<? super E>) minChild).compareTo((E) data[right]) > 0) {
            minChild = data[min = right];
          }
        }
      }
      //用节点e和他的最小的子节点比较，如果小于他最小的子节点就退出循环，不再往下调整了，
      if (comparator != null) {
        if (comparator.compare(e, (E) minChild) <= 0) {
          break;
        }
      } else {
        if (((Comparable<? super E>) e).compareTo((E) minChild) <= 0) {
          break;
        }
      }
      //如果e比它的最小的子节点小，就用最小的子节点和e交换位置，然后再继续往下调整。
      data[index] = minChild;
      index = min;
    }
    data[index] = e;
  }
}
