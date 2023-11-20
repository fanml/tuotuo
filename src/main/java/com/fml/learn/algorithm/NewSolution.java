package com.fml.learn.algorithm;

import com.fml.learn.algorithm.structure.ListNode;
import java.util.HashSet;
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

  public static void main(String[] args) {
//    String s = "abcabcbb";
//    String s = "bbbbb";
    String s = "pwwkew";
    System.out.println(lengthOfLongestSubstring(s));


  }

}
