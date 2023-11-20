package com.fml.learn.algorithm;

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

}
