package com.fml.learn.algorithm;

import com.fml.learn.algorithm.structure.ListNode;
import java.util.HashSet;
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

}
