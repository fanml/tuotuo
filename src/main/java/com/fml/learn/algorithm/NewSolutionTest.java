package com.fml.learn.algorithm;

import com.fml.learn.algorithm.structure.ListNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

}
