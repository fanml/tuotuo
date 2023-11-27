package com.fml.learn.algorithm;

import com.fml.learn.algorithm.structure.ListNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewSolutionTest {

  /**
   * 3.给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
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
  public static int lengthOfLongestSubstringTest(String s) {
    int length = s.length();
    int result = 0;
    int k = -1;
    Set<Character> ss = new HashSet<>();
    for (int i = 0; i < length; i++) {
      if (i != 0) {
        ss.remove(s.charAt(i - 1));
      }
      while (k + 1 < length && !ss.contains(s.charAt(k + 1))) {
        ss.add(s.charAt(k + 1));
        k++;
      }
      result = Math.max(result, k - i + 1);
    }
    return result;
  }

  /**
   * 206. 反转链表  双指针 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。 输入：head = [1,2,3,4,5] 输出：[5,4,3,2,1] 输入：head =
   * [1,2] 输出：[2,1] 输入：head = [] 输出：[] 普通方法
   */
  public ListNode reverseList1(ListNode head) {
    ListNode pre = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = pre;
      pre = curr;
      curr = next;
    }
    return pre;
  }

  /**
   * 206. 反转链表 递归
   */
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode newHead = reverseList(head);
    head.next.next = head;
    head.next = null;
    return newHead;
  }

  //  public class LRUCache {
//
//    /**
//     * 算法
//     * <p>
//     * LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
//     * <p>
//     * 双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
//     * <p>
//     * 哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。
//     * <p>
//     * 这样以来，我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在 O(1)的时间内完成 get 或者 put 操作。具体的方法如下：
//     * <p>
//     * 对于 get 操作，首先判断 key 是否存在：
//     * <p>
//     * 如果 key 不存在，则返回 −1；
//     * <p>
//     * 如果 key 存在，则 key 对应的节点是最近被使用的节点。通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
//     * <p>
//     * 对于 put 操作，首先判断 key 是否存在：
//     * <p>
//     * 如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
//     * <p>
//     * 如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为 value，并将该节点移到双向链表的头部。
//     * <p>
//     * 上述各项操作中，访问哈希表的时间复杂度为 O(1)，在双向链表的头部添加节点、在双向链表的尾部删除节点的复杂度也为 O(1)。
//     * 而将一个节点移到双向链表的头部，可以分成「删除该节点」和「在双向链表的头部添加节点」两步操作，都可以在 O(1) 时间内完成。
//     */
//    class DLinkedNode {
//
//      int key;
//      int value;
//      DLinkedNode prev;
//      DLinkedNode next;
//
//      public DLinkedNode() {
//      }
//
//      public DLinkedNode(int _key, int _value) {
//        key = _key;
//        value = _value;
//      }
//    }
//
//    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
//    private int size;
//    private int capacity;
//    private DLinkedNode head, tail;
//
//    public LRUCache(int capacity) {
//      this.size = 0;
//      this.capacity = capacity;
//      head = new DLinkedNode();
//      tail = new DLinkedNode();
//      head.next = tail;
//      tail.prev = head;
//    }
//
//    public int get(int key) {
//      DLinkedNode node = cache.get(key);
//      if (node == null) {
//        return -1;
//      }
//      // 将node放在head后面
//      moveToHead(node);
//      return node.value;
//    }
//
//    public void put(int key, int value) {
//      DLinkedNode node = cache.get(key);
//      if (node == null) {
//        DLinkedNode nodeNew = new DLinkedNode(key, value);
//        cache.put(key, nodeNew);
//        addToHead(nodeNew);
//        size++;
//        if (size > capacity) {
//          DLinkedNode node1 = removeTail();
//          cache.remove(node1.key);
//          --size;
//        }
//      } else {
//        node.value = value;
//        moveToHead(node);
//      }
//    }
//
//    private void addToHead(DLinkedNode node) {
//      node.next = head.next;
//      node.prev = head;
//      head.next = node;
//      head.next.prev = node;
//    }
//
//    private void removeNode(DLinkedNode node) {
//      node.prev.next = node.next;
//      node.next.prev = node.prev;
//    }
//
//    private void moveToHead(DLinkedNode node) {
//      removeNode(node);
//      addToHead(node);
//    }
//
//    private DLinkedNode removeTail() {
//      DLinkedNode node = tail.prev;
//      removeNode(node);
//      return node;
//    }
//  }
//

  /**
   * 15. 三数之和 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足
   * nums[i] + nums[j] + nums[k] == 0 。请 你返回所有和为 0 且不重复的三元组。 注意：答案中不可以包含重复的三元组。 输入：nums =
   * [-1,0,1,2,-1,-4] 输出：[[-1,-1,2],[-1,0,1]] 解释： nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
   * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。 nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1)
   * = 0 。 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。 注意，输出的顺序和三元组的顺序并不重要。
   */
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    // 排序
    Arrays.sort(nums);
    int length = nums.length;
    // 第一层循环
    for (int first = 0; first < length; first++) {
      // 相等的数据跳过
      if (first > 0 && nums[first] == nums[first - 1]) {
        continue;
      }
      // c 对应的指针初始指向数组的最右端
      int third = length - 1;
      int target = -nums[first];
      for (int second = first + 1; second < length; second++) {
        if (second > first + 1 && nums[second] == nums[second - 1]) {
          continue;
        }
        // 移动third
        while (second < third && nums[second] + nums[third] > target) {
          --third;
        }
        if (second == third) {
          break;
        }
        if (nums[second] + nums[third] == target) {
          List<Integer> list = new ArrayList<>();
          list.add(nums[first]);
          list.add(nums[second]);
          list.add(nums[third]);
          result.add(list);
        }
      }
    }
    return result;
  }

  /**
   * 215. 数组中的第K个最大元素 中等 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
   * <p>
   * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
   * <p>
   * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
   * <p>
   * 示例 1: 输入: [3,2,1,5,6,4], k = 2 输出: 5
   * <p>
   * 示例 2: 输入: [3,2,3,1,2,4,5,5,6], k = 4 输出: 4
   */
  static int quickselect(int[] nums, int l, int r, int k) {
    if (l == r) {
      return nums[k];
    }
    int x = nums[l];
    int i = l - 1;
    int j = r + 1;
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

  /**
   * 25. K 个一组翻转链表
   * <p>
   * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。 k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k
   * 的整数倍，那么请将最后剩余的节点保持原有顺序。 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
   */
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode hair = new ListNode(0);
    hair.next = head;
    ListNode pre = hair;
    while (head != null) {
      ListNode tail = pre;
      for (int i = 0; i < k; i++) {
        tail = tail.next;
        if (tail == null) {
          return hair.next;
        }
      }
      ListNode nex = tail.next;
      ListNode[] reverse = myReverse(head, tail);
      head = reverse[0];
      tail = reverse[1];
      pre.next = head;
      tail.next = nex;
      pre = tail;
      head = tail.next;
    }
    return hair.next;
  }

  public ListNode[] myReverse(ListNode head, ListNode tail) {
    ListNode prev = tail.next;
    ListNode p = head;
    while (prev != tail){
      ListNode nex = p.next;
      p.next = prev;
      prev = p;
      p = nex;
    }
    return new ListNode[]{tail, head};
  }


}
