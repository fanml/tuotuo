package com.fml.learn.algorithm;

import com.fml.learn.algorithm.structure.ListNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * gogogo 2023-11-20
 */
public class NewSolution {


  /**
   * 3.无重复字符的最长子串 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
   * <p>
   * <p>
   * <p>
   * 示例 1:
   * <p>
   * 输入: s = "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。 示例 2:
   * <p>
   * 输入: s = "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。 示例 3:
   * <p>
   * 输入: s = "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。 请注意，你的答案必须是 子串 的长度，"pwke"
   * 是一个子序列，不是子串。
   */
  public static int lengthOfLongestSubstring(String s) {
    //以 (a)bcabcbb 开始的最长字符串为 (abc)abcbb
    //以 a(b)cabcbb 开始的最长字符串为 a(bca)bcbb
    //以 ab(c)abcbb 开始的最长字符串为 ab(cab)cbb
    //以 abc(a)bcbb开始的最长字符串为 abc(abc)bb
    //以 abca(b)cbb 开始的最长字符串为 abca(bc)bb
    //以 abcab(c)bb开始的最长字符串为 abcab(cb)b
    //以 abcabc(b)b 开始的最长字符串为 abcabc(b)b
    //以 abcabcb(b) 开始的最长字符串为 abcabcb(b)
    // 哈希集合，记录每个字符是否出现过
    Set<Character> occ = new HashSet<Character>();
    int n = s.length();
    // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
    int rk = -1, ans = 0;
    for (int i = 0; i < n; ++i) {
      if (i != 0) {
        // 左指针向右移动一格，移除一个字符
        occ.remove(s.charAt(i - 1));
      }
      while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
        // 不断地移动右指针
        occ.add(s.charAt(rk + 1));
        ++rk;
      }
      // 第 i 到 rk 个字符是一个极长的无重复字符子串
      ans = Math.max(ans, rk - i + 1);
    }
    return ans;
  }

  /**
   * 206. 反转链表  双指针 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。 输入：head = [1,2,3,4,5] 输出：[5,4,3,2,1] 输入：head =
   * [1,2] 输出：[2,1] 输入：head = [] 输出：[] 普通方法
   */
  public ListNode reverseList1(ListNode head) {
    // 前一个节点
    ListNode prev = null;
    // 当前节点
    ListNode curr = head;
    while (curr != null) {
      // 后一个节点
      ListNode next = curr.next;
      // 当前节点指针指向前一个节点
      curr.next = prev;
      // 前一个节点等于当前节点
      prev = curr;
      // 当前节点等于下一个节点
      curr = next;
    }
    return prev;
  }

  /**
   * 206. 反转链表 递归
   */
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }

  /**
   * 146. LRU 缓存 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。 实现 LRUCache 类： LRUCache(int capacity) 以 正整数
   * 作为容量 capacity 初始化 LRU 缓存 int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 void put(int key,
   * int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity
   * ，则应该 逐出 最久未使用的关键字。 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
   */
  public class LRUCache {

    /**
     * 算法
     * <p>
     * LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
     * <p>
     * 双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
     * <p>
     * 哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。
     * <p>
     * 这样以来，我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在 O(1)的时间内完成 get 或者 put 操作。具体的方法如下：
     * <p>
     * 对于 get 操作，首先判断 key 是否存在：
     * <p>
     * 如果 key 不存在，则返回 −1；
     * <p>
     * 如果 key 存在，则 key 对应的节点是最近被使用的节点。通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
     * <p>
     * 对于 put 操作，首先判断 key 是否存在：
     * <p>
     * 如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
     * <p>
     * 如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为 value，并将该节点移到双向链表的头部。
     * <p>
     * 上述各项操作中，访问哈希表的时间复杂度为 O(1)，在双向链表的头部添加节点、在双向链表的尾部删除节点的复杂度也为 O(1)。
     * 而将一个节点移到双向链表的头部，可以分成「删除该节点」和「在双向链表的头部添加节点」两步操作，都可以在 O(1) 时间内完成。
     */
    class DLinkedNode {

      int key;
      int value;
      DLinkedNode prev;
      DLinkedNode next;

      public DLinkedNode() {
      }

      public DLinkedNode(int _key, int _value) {
        key = _key;
        value = _value;
      }
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
      this.size = 0;
      this.capacity = capacity;
      // 使用伪头部和伪尾部节点
      head = new DLinkedNode();
      tail = new DLinkedNode();
      head.next = tail;
      tail.prev = head;
    }

    public int get(int key) {
      DLinkedNode node = cache.get(key);
      if (node == null) {
        return -1;
      }
      // 如果 key 存在，先通过哈希表定位，再移到头部
      moveToHead(node);
      return node.value;
    }

    public void put(int key, int value) {
      DLinkedNode node = cache.get(key);
      if (node == null) {
        // 如果 key 不存在，创建一个新的节点
        DLinkedNode newNode = new DLinkedNode(key, value);
        // 添加进哈希表
        cache.put(key, newNode);
        // 添加至双向链表的头部
        addToHead(newNode);
        ++size;
        if (size > capacity) {
          // 如果超出容量，删除双向链表的尾部节点
          DLinkedNode tail = removeTail();
          // 删除哈希表中对应的项
          cache.remove(tail.key);
          --size;
        }
      } else {
        // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
        node.value = value;
        moveToHead(node);
      }
    }

    private void addToHead(DLinkedNode node) {
      node.prev = head;
      node.next = head.next;
      head.next.prev = node;
      head.next = node;
    }

    private void removeNode(DLinkedNode node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
      removeNode(node);
      addToHead(node);
    }

    private DLinkedNode removeTail() {
      DLinkedNode res = tail.prev;
      removeNode(res);
      return res;
    }
  }


  /**
   * 215. 数组中的第K个最大元素
   * 中等
   * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
   *
   * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
   *
   * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
   *
   * 示例 1:
   * 输入: [3,2,1,5,6,4], k = 2
   * 输出: 5
   *
   * 示例 2:
   * 输入: [3,2,3,1,2,4,5,5,6], k = 4
   * 输出: 4
   *
   */
   static  int quickselect(int[] nums, int l, int r, int k) {
    if (l == r) {
      return nums[k];
    }
    int x = nums[l], i = l - 1, j = r + 1;
    while (i < j) {
      do {
        i++;
      } while (nums[i] < x);
      do {
        j--;
      } while (nums[j] > x);
      if (i < j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
      }
    }
    // 打印
     System.out.println(Arrays.toString(nums));
    if (k <= j) {
      return quickselect(nums, l, j, k);
    } else {
      return quickselect(nums, j + 1, r, k);
    }
  }

  public static int findKthLargest(int[] _nums, int k) {
    int n = _nums.length;
    return quickselect(_nums, 0, n - 1, n - k);
  }




  public static void main(String[] args) {
//    String s = "abcabcbb";
//    String s = "bbbbb";
//    String s = "pwwkew";
//    System.out.println(lengthOfLongestSubstring(s));
    int [] nums ={3,2,1,5,6,4};
    findKthLargest(nums,2);


  }

}
