package com.fml.learn.algorithm;

import com.fml.learn.algorithm.structure.ListNode;
import com.fml.learn.algorithm.structure.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
   * 92. 反转链表 II
   * <p>
   * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
   * 输入：head = [1,2,3,4,5], left = 2, right = 4 输出：[1,4,3,2,5]
   */
  public ListNode reverseBetween(ListNode head, int left, int right) {
    // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
    ListNode dummyNode = new ListNode(-1);
    dummyNode.next = head;

    ListNode pre = dummyNode;
    // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
    // 建议写在 for 循环里，语义清晰
    for (int i = 0; i < left - 1; i++) {
      pre = pre.next;
    }

    // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
    ListNode rightNode = pre;
    for (int i = 0; i < right - left + 1; i++) {
      rightNode = rightNode.next;
    }

    // 第 3 步：切断出一个子链表（截取链表）
    ListNode leftNode = pre.next;
    ListNode curr = rightNode.next;

    // 注意：切断链接
    pre.next = null;
    rightNode.next = null;

    // 第 4 步：同第 206 题，反转链表的子区间
    reverseList(leftNode);

    // 第 5 步：接回到原来的链表中
    pre.next = rightNode;
    leftNode.next = curr;
    return dummyNode.next;
  }


  /**
   * 不耗用额外内存
   */
  public ListNode reverseBetween2(ListNode head, int left, int right) {
    // 设置 dummyNode 是这一类问题的一般做法
    ListNode dummyNode = new ListNode(-1);
    dummyNode.next = head;
    ListNode pre = dummyNode;
    for (int i = 0; i < left - 1; i++) {
      pre = pre.next;
    }
    ListNode cur = pre.next;
    ListNode next;
    for (int i = 0; i < right - left; i++) {
      next = cur.next;
      cur.next = next.next;
      next.next = pre.next;
      pre.next = next;
    }
    return dummyNode.next;
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
      // 查看剩余部分长度是否大于等于 k
      for (int i = 0; i < k; ++i) {
        tail = tail.next;
        if (tail == null) {
          return hair.next;
        }
      }
      ListNode nex = tail.next;
      ListNode[] reverse = myReverse(head, tail);
      head = reverse[0];
      tail = reverse[1];
      // 把子链表重新接回原链表
      pre.next = head;
      tail.next = nex;
      pre = tail;
      head = tail.next;
    }

    return hair.next;
  }

  public ListNode[] myReverse(ListNode head, ListNode tail) {
    // tail的下一个节点
    ListNode prev = tail.next;
    // head节点
    ListNode p = head;
    while (prev != tail) {
      ListNode nex = p.next;
      p.next = prev;
      prev = p;
      p = nex;
    }
    return new ListNode[]{tail, head};
  }

  /**
   * 15. 三数之和 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足
   * nums[i] + nums[j] + nums[k] == 0 。请 你返回所有和为 0 且不重复的三元组。 注意：答案中不可以包含重复的三元组。 输入：nums =
   * [-1,0,1,2,-1,-4] 输出：[[-1,-1,2],[-1,0,1]] 解释： nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
   * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。 nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1)
   * = 0 。 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。 注意，输出的顺序和三元组的顺序并不重要。
   */
  public List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    // 枚举 a
    for (int first = 0; first < n; ++first) {
      // 需要和上一次枚举的数不相同
      if (first > 0 && nums[first] == nums[first - 1]) {
        continue;
      }
      // c 对应的指针初始指向数组的最右端
      int third = n - 1;
      int target = -nums[first];
      // 枚举 b
      for (int second = first + 1; second < n; ++second) {
        // 需要和上一次枚举的数不相同
        if (second > first + 1 && nums[second] == nums[second - 1]) {
          continue;
        }
        // 需要保证 b 的指针在 c 的指针的左侧
        while (second < third && nums[second] + nums[third] > target) {
          --third;
        }
        // 如果指针重合，随着 b 后续的增加
        // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
        if (second == third) {
          break;
        }
        if (nums[second] + nums[third] == target) {
          List<Integer> list = new ArrayList<Integer>();
          list.add(nums[first]);
          list.add(nums[second]);
          list.add(nums[third]);
          ans.add(list);
        }
      }
    }
    return ans;
  }

  /**
   * 53. 最大子数组和
   * <p>
   * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 子数组 是数组中的一个连续部分。
   * <p>
   * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4] 输出：6 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
   * <p>
   * 动态规划
   */
  public int maxSubArray(int[] nums) {
    int pre = 0, maxAns = nums[0];
    for (int x : nums) {
      pre = Math.max(pre + x, x);
      maxAns = Math.max(maxAns, pre);
    }
    return maxAns;
  }

  /**
   * 21. 合并两个有序链表 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的 输入：l1 = [1,2,4], l2 = [1,3,4]
   * 输出：[1,1,2,3,4,4]
   */
  public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else if (l1.val < l2.val) {
      l1.next = mergeTwoLists1(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists1(l1, l2.next);
      return l2;
    }
  }

  /**
   * 21. 合并两个有序链表 非递归
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // 定义一个哨兵节点
    ListNode prehead = new ListNode(-1);
    // 维护一个prev指针
    ListNode prev = prehead;
    while (l1 != null && l2 != null) {
      // 指针指向小的节点
      if (l1.val <= l2.val) {
        prev.next = l1;
        // 移动子链表头
        l1 = l1.next;
      } else {
        prev.next = l2;
        l2 = l2.next;
      }
      // 移动指针
      prev = prev.next;
    }
    // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
    prev.next = l1 == null ? l2 : l1;
    return prehead.next;
  }

  /**
   * 23. 合并 K 个升序链表
   * <p>
   * 给你一个链表数组，每个链表都已经按升序排列。 请你将所有链表合并到一个升序链表中，返回合并后的链表。
   * <p>
   * 顺序合并
   */
  public ListNode mergeKLists(ListNode[] lists) {
    ListNode ans = null;
    for (int i = 0; i < lists.length; i++) {
      mergeTwoLists(ans, lists[i]);
    }
    return ans;
  }

  /**
   * 23. 合并 K 个升序链表 分治 俩俩合并
   */
  public ListNode mergeKLists2(ListNode[] lists) {
    return merge(lists, 0, lists.length - 1);
  }

  public ListNode merge(ListNode[] lists, int l, int r) {
    if (l == r) {
      return lists[l];
    }
    if (l > r) {
      return null;
    }
    int mid = (l + r) / 2;
    return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
  }

  /**
   * 1. 两数之和 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
   * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。 你可以按任意顺序返回答案。 输入：nums = [2,7,11,15], target = 9
   * 输出：[0,1] 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
   */
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        return new int[]{map.get(nums[i]), i};
      } else {
        map.put(target - nums[i], i);
      }
    }
    return null;
  }

  /**
   * 5. 最长回文子串 给你一个字符串 s，找到 s 中最长的回文子串。 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
   * <p>
   * 示例 1： 输入：s = "babad" 输出："bab" 解释："aba" 同样是符合题意的答案。 暴力解法
   */
  public String longestPalindrome1(String s) {
    int len = s.length();
    if (len < 2) {
      return s;
    }
    int maxLen = 1;
    int begin = 0;
    char[] charArray = s.toCharArray();
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        // 暴力解法优化
        // 截取所有子串，如果截取的子串小于等于之前 遍历过的最大回文串，直接跳过。因为截取的子串即使是回文串也不可能是最大的，所以不需要判断
        if (j - i < len) {
          continue;
        }
        if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
          maxLen = j - i + 1;
          begin = i;
        }
      }
    }
    return s.substring(begin, begin + maxLen);

  }

  /**
   * 判断回文子串
   *
   * @param charArray
   * @param left
   * @param right
   * @return
   */
  private boolean validPalindromic(char[] charArray, int left, int right) {
    while (left < right) {
      if (charArray[left] != charArray[right]) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  /**
   * 5. 最长回文子串 中心扩散算法
   */
  public static String longestPalindrome(String s) {
    if (s == null || s.length() < 1) {
      return "";
    }
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
      int len1 = expandAroundCenter(s, i, i);
      int len2 = expandAroundCenter(s, i, i + 1);
      int len = Math.max(len1, len2);
      if (len > end - start) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  public static int expandAroundCenter(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      --left;
      ++right;
    }
    return right - left - 1;
  }

  /**
   * 102.二叉树的层序遍历 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。 输入：root =
   * [3,9,20,null,null,15,7] 输出：[[3],[9,20],[15,7]] 队列
   * <p>
   * 广度优先搜索
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      List<Integer> list = new ArrayList<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        list.add(node.val);
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      result.add(list);
    }
    return result;
  }

  /**
   * 103. 二叉树的锯齿形层序遍历 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
   * <p>
   * 输入：root = [3,9,20,null,null,15,7] 输出：[[3],[20,9],[15,7]]
   * <p>
   * 广度优先搜索
   */
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new LinkedList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    nodeQueue.offer(root);
    boolean isOrder = true;
    while (!nodeQueue.isEmpty()) {
      Deque<Integer> list = new LinkedList<>();
      int size = nodeQueue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = nodeQueue.poll();
        if (isOrder) {
          list.offerLast(node.val);
        } else {
          list.offerFirst(node.val);
        }
        if (node.left != null) {
          nodeQueue.offer(node.left);
        }
        if (node.right != null) {
          nodeQueue.offer(node.right);
        }
      }
      isOrder = !isOrder;
      result.add(new LinkedList<>(list));
    }
    return result;
  }

  /**
   * 236. 二叉树的最近公共祖先 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点
   * x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
   */
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left == null && right == null) {
      return null; // 1.
    }
    if (left == null) {
      return right; // 3.
    }
    if (right == null) {
      return left; // 4.
    }
    return root; // 2. if(left != null and right != null)
  }

  /**
   * 33. 搜索旋转排序数组
   * <p>
   * <p>
   * 整数数组 nums 按升序排列，数组中的值 互不相同 。 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为
   * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如，
   * [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums
   * 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
   * <p>
   * 输入：nums = [4,5,6,7,0,1,2], target = 0 输出：4 二分查找
   */
  public int search(int[] nums, int target) {
    if (nums == null) {
      return -1;
    }
    int length = nums.length;
    if (nums.length == 1) {
      return nums[0] == target ? nums[0] : -1;
    }
    if (nums[0] == target) {
      return 0;
    }
    if (nums[length - 1] == target) {
      return length - 1;
    }
    int r = 0, l = length - 1;
    int mid = 0;
    while (r <= l) {
      mid = (r + l) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[0] < nums[mid]) {
        if (nums[0] < target && target < nums[mid]) {
          l = mid - 1;
        } else {
          r = mid + 1;
        }

      } else {
        if (nums[mid] < target && target < nums[length - 1]) {
          r = mid + 1;
        } else {
          l = mid - 1;
        }
      }
    }
    return -1;
  }


  /**
   * 704. 二分查找
   * <p>
   * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
   * <p>
   * 示例 1:
   * <p>
   * 输入: nums = [-1,0,3,5,9,12], target = 9 输出: 4 解释: 9 出现在 nums 中并且下标为 4 示例 2:
   */
  public int searchTwo(int[] nums, int target) {
    int l = 0;
    int r = nums.length - 1;
    while (l <= r) {
      int mid = (r - l) / 2 + l;
      if (target == nums[mid]) {
        return mid;
      } else if (nums[mid] > target) {
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return -1;
  }

  /**
   * 121. 买卖股票的最佳时机 示例 2： 输入：[7,1,5,3,6,4] 输出：5 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润
   * = 6-1 = 5 。 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。 示例 2： 输入：prices = [7,6,4,3,1] 输出：0
   * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
   * <p>
   * 动态规划
   */
  public int maxProfit(int[] prices) {
    int min = Integer.MAX_VALUE;
    int max = 0;
    for (int i = 0; i < prices.length; i++) {
      if (min > prices[i]) {
        min = prices[i];
      } else if (prices[i] - min > max) {
        max = prices[i] - min;
      }
    }
    return max;
  }

  /**
   * 141. 环形链表 给你一个链表的头节点 head ，判断链表中是否有环。 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
   * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。 如果链表中存在环
   * ，则返回 true 。 否则，返回 false 。 快慢双指针
   */
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (slow != fast) {
      if (fast == null || fast.next == null) {
        return false;
      }
      fast = fast.next.next;
      slow = slow.next;
    }
    return true;

  }


  /**
   * 142. 环形链表 II
   * <p>
   * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
   * <p>
   * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0
   * 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
   * <p>
   * 不允许修改 链表
   * <p>
   * 输入：head = [3,2,0,-4], pos = 1 输出：返回索引为 1 的链表节点 解释：链表中有一个环，其尾部连接到第二个节点。
   * <p>
   * 双指针
   */
  public ListNode detectCycle(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    // 第一次相遇
    while (true) {
      if (fast == null || fast.next == null) {
        return null;
      }
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        break;
      }
    }
    // 快节点返回头节点
    fast = head;
    while (fast != slow) {
      slow = slow.next;
      fast = fast.next;
    }
    return fast;
  }


  /**
   * 20. 有效的括号 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 有效字符串需满足： 左括号必须用相同类型的右括号闭合。
   * 左括号必须以正确的顺序闭合。 每个右括号都有一个对应的相同类型的左括号。
   */
  public boolean isValid(String s) {
    // 长度是奇数时返回false
    int length = s.length();
    if (length % 2 == 1) {
      return false;
    }
    Map<Character, Character> map = new HashMap<>();
    map.put('}', '{');
    map.put(']', '[');
    map.put(')', '(');
    Deque<Character> stack = new LinkedList<Character>();
    for (int i = 0; i < length; i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        if (stack.isEmpty() || !stack.peek().equals(map.get(c))) {
          return false;
        }
        stack.pop();
      } else {
        stack.push(c);
      }
    }
    return stack.isEmpty();
  }

  /**
   * 200. 岛屿数量 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
   * 此外，你可以假设该网格的四条边均被水包围。 深度优先搜索
   */
  public int numIslandsDfs(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int rn = grid.length;
    int cn = grid[0].length;
    int result = 0;
    for (int r = 0; r < rn; r++) {
      for (int c = 0; c < cn; c++) {
        if (grid[r][c] == '1') {
          result++;
          dfs(grid, r, c);
        }
      }
    }
    return result;
  }

  void dfs(char[][] grid, int r, int c) {
    int rn = grid.length;
    int cn = grid[0].length;
    if (r < 0 || r >= rn || c < 0 || c >= cn || grid[r][c] == '0') {
      return;
    }
    grid[r][c] = '0';
    dfs(grid, r + 1, c);
    dfs(grid, r - 1, c);
    dfs(grid, r, c + 1);
    dfs(grid, r, c - 1);
  }

  /**
   * 200. 岛屿数量 广度优先搜索BFS
   */
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int nr = grid.length;
    int nc = grid[0].length;
    int result = 0;
    for (int r = 0; r < nr; r++) {
      for (int c = 0; c < nc; c++) {
        if (grid[r][c] == '1') {
          result++;
          grid[r][c] = '0';
          Queue<Integer> neighbors = new LinkedList<>();
          neighbors.add(r * nc + c);
          while (!neighbors.isEmpty()) {
            int id = neighbors.remove();
            int row = id / nc;
            int col = id % nc;
            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
              neighbors.add((row - 1) * nc + col);
              grid[row - 1][col] = '0';
            }
            if (row + 1 < nr && grid[row + 1][col] == '1') {
              neighbors.add((row + 1) * nc + col);
              grid[row + 1][col] = '0';
            }
            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
              neighbors.add(row * nc + col - 1);
              grid[row][col - 1] = '0';
            }
            if (col + 1 < nc && grid[row][col + 1] == '1') {
              neighbors.add(row * nc + col + 1);
              grid[row][col + 1] = '0';
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * 88. 合并两个有序数组
   * <p>
   * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。 请你 合并 nums2 到 nums1
   * 中，使合并后的数组同样按 非递减顺序 排列。 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m
   * 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n
   * = 3 输出：[1,2,2,3,5,6] 解释：需要合并 [1,2,3] 和 [2,5,6] 。 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
   * 逆向双指针
   */
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // 尾指针
    int tail = m + n - 1;
    int p1 = m - 1;
    int p2 = n - 1;
    // 值
    int cur;
    while (p1 > -1 || p2 > -1) {
      if (p1 == -1) {
        cur = nums2[p2--];
      } else if (p2 == -1) {
        cur = nums1[p1--];
      } else if (nums1[p1] < nums2[p2]) {
        cur = nums2[p2--];
      } else {
        cur = nums1[p1--];
      }
      nums1[tail--] = cur;
    }

  }

  /**
   * 912.快速排序
   */
  public static void quickSort(int[] arrays, int left, int right) {
    if (left > right) {
      return;
    }
    int l = left;
    int r = right;
    int tmp = 0;
    int pivot = arrays[left];
    while (l < r) {
      while (pivot <= arrays[r] && l < r) {
        r--;
      }
      while (pivot >= arrays[l] && l < r) {
        l++;
      }
      if (l <= r) {
        tmp = arrays[r];
        arrays[r] = arrays[l];
        arrays[l] = tmp;
      }
    }

    arrays[left] = arrays[l];
    arrays[l] = pivot;
    quickSort(arrays, left, l - 1);
    quickSort(arrays, l + 1, right);
  }

  /**
   * 46. 全排列 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案 输入：nums = [1,2,3]
   * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   * <p>
   * 回溯
   */
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    List<Integer> output = new ArrayList<Integer>();
    for (int num : nums) {
      output.add(num);
    }

    int n = nums.length;
    backtrack(n, output, res, 0);
    return res;
  }

  public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
    // 所有数都填完了
    if (first == n) {
      res.add(new ArrayList<Integer>(output));
    }
    for (int i = first; i < n; i++) {
      // 动态维护数组
      Collections.swap(output, first, i);
      // 继续递归填下一个数
      backtrack(n, output, res, first + 1);
      // 撤销操作
      Collections.swap(output, first, i);
    }
  }

  /**
   * 54. 螺旋矩阵 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
   * <p>
   * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]] 输出：[1,2,3,6,9,8,7,4,5]
   * <p>
   * 按层模拟
   */
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> order = new ArrayList<Integer>();
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return order;
    }
    int rows = matrix.length, columns = matrix[0].length;
    int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
    while (left <= right && top <= bottom) {
      // 从左取到右
      for (int column = left; column <= right; column++) {
        order.add(matrix[top][column]);
      }
      // 从上去到下
      for (int row = top + 1; row <= bottom; row++) {
        order.add(matrix[row][right]);
      }
      // 没重合
      if (left < right && top < bottom) {
        // 从右取到左
        for (int column = right - 1; column > left; column--) {
          order.add(matrix[bottom][column]);
        }
        // 从下取到上
        for (int row = bottom; row > top; row--) {
          order.add(matrix[row][left]);
        }
      }
      // 缩小范围
      left++;
      right--;
      top++;
      bottom--;
    }
    return order;
  }

  /**
   * 160. 相交链表
   * <p>
   * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
   * <p>
   * hash集合解法
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    Set<ListNode> mySet = new HashSet<>();
    while (headA != null) {
      mySet.add(headA);
      headA = headA.next;
    }
    while (headB != null) {
      if (mySet.contains(headB)) {
        return headB;
      }
      headB = headB.next;
    }
    return null;
  }

  /**
   * 160. 相交链表
   * <p>
   * 双指针
   */
  public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    ListNode pA = headA;
    ListNode pB = headB;
    while (pA != pB) {
      pA = pA == null ? headB : pA.next;
      pB = pB == null ? headA : pB.next;
    }
    return pA;

  }

  /**
   * 415. 字符串相加
   * <p>
   * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
   * <p>
   * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
   */
  public String addStrings(String num1, String num2) {
    int i = num1.length() - 1, j = num2.length() - 1, add = 0;
    StringBuffer ans = new StringBuffer();
    while (i >= 0 || j >= 0 || add != 0) {
      int x = i >= 0 ? num1.charAt(i) - '0' : 0;
      int y = j >= 0 ? num2.charAt(j) - '0' : 0;
      int result = x + y + add;
      ans.append(result % 10);
      add = result / 10;
      i--;
      j--;
    }
    // 计算完以后的答案需要翻转过来
    ans.reverse();
    return ans.toString();
  }

  /**
   * 2. 两数相加 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
   * <p>
   * 请你将两个数相加，并以相同形式返回一个表示和的链表。
   * <p>
   * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
   * <p>
   * 输入：l1 = [2,4,3], l2 = [5,6,4] 输出：[7,0,8] 解释：342 + 465 = 807.
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = null, tail = null;
    int add = 0;
    while (l1 != null || l2 != null || add != 0) {
      int a = l1 == null ? 0 : l1.val;
      int b = l2 == null ? 0 : l2.val;
      int temp = a + b + add;
      int val = (temp) % 10;
      if (head == null) {
        head = tail = new ListNode(val);
      } else {
        tail.next = new ListNode(val);
        tail = tail.next;
      }
      add = temp / 10;
      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
    }
    return head;
  }

  /**
   * 300. 最长递增子序列 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
   * <p>
   * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
   * <p>
   * 示例 1：
   * <p>
   * 输入：nums = [10,9,2,5,3,7,101,18] 输出：4 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
   * <p>
   * 动态规划
   */
  public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int[] dp = new int[nums.length];
    dp[0] = 1;
    int maxans = 1;
    for (int i = 1; i < nums.length; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      maxans = Math.max(maxans, dp[i]);
    }
    return maxans;
  }

  /**
   * 300. 最长递增子序列
   * <p>
   * 贪心+二分 没学会抄一遍吧
   */
  public int lengthOfLIS1(int[] nums) {
    int len = 1, n = nums.length;
    if (n == 0) {
      return 0;
    }
    int[] d = new int[n + 1];
    d[len] = nums[0];
    for (int i = 1; i < n; ++i) {
      if (nums[i] > d[len]) {
        d[++len] = nums[i];
      } else {
        int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
        while (l <= r) {
          int mid = (l + r) >> 1;
          if (d[mid] < nums[i]) {
            pos = mid;
            l = mid + 1;
          } else {
            r = mid - 1;
          }
        }
        d[pos + 1] = nums[i];
      }
    }
    return len;
  }

  /**
   * 876. 链表的中间结点 给你单链表的头结点 head ，请你找出并返回链表的中间结点。 如果有两个中间结点，则返回第二个中间结点。
   * <p>
   * 输入：head = [1,2,3,4,5] 输出：[3,4,5] 解释：链表只有一个中间结点，值为 3 。
   * <p>
   * 输入：head = [1,2,3,4,5,6] 输出：[4,5,6] 解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
   * <p>
   * <p>
   * 快慢指针
   */
  public ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  /**
   * 143. 重排链表
   * <p>
   * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
   * <p>
   * L0 → L1 → … → Ln - 1 → Ln 请将其重新排列后变为：
   * <p>
   * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → … 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
   * <p>
   * 输入：head = [1,2,3,4] 输出：[1,4,2,3]
   * <p>
   * 输入：head = [1,2,3,4,5] 输出：[1,5,2,4,3]
   * <p>
   * <p>
   * 寻找链表中点 + 链表逆序 + 合并链表
   */
  public void reorderList(ListNode head) {
    ListNode midNode = middleNode(head);
    ListNode l1 = head;
    ListNode l2 = midNode.next;
    midNode.next = null;
    l2 = reverseList(l2);
    mergeList(l1, l2);
  }

  public void mergeList(ListNode l1, ListNode l2) {
    ListNode l1_tmp;
    ListNode l2_tmp;
    while (l1 != null && l2 != null) {
      l1_tmp = l1.next;
      l2_tmp = l2.next;
      l1.next = l2;
      l1 = l1_tmp;
      l2.next = l1;
      l2 = l2_tmp;
    }
  }

  /**
   * 42. 接雨水
   * <p>
   * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
   * <p>
   * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1] 输出：6 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1]
   * 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
   * <p>
   * 双指针 这题没怎么会 还得看
   */
  public int trap(int[] height) {
    int ans = 0;
    int left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0;
    while (left < right) {
      leftMax = Math.max(leftMax, height[left]);
      rightMax = Math.max(rightMax, height[right]);
      if (height[left] < height[right]) {
        ans += leftMax - height[left];
        ++left;
      } else {
        ans += rightMax - height[right];
        --right;
      }
    }
    return ans;
  }

  /**
   * 19. 删除链表的倒数第 N 个结点
   * <p>
   * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
   * <p>
   * 双指针
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return head;
    }
    ListNode dummnyNode = new ListNode(-1);
    dummnyNode.next = head;
    ListNode slow = dummnyNode;
    ListNode fast = dummnyNode;
    for (int i = 0; i < n + 1; i++) {
      fast = fast.next;
    }
    while (fast != null) {
      fast = fast.next;
      slow = slow.next;
    }
    slow.next = slow.next.next;
    return dummnyNode.next;
  }

  /**
   * 56. 合并区间
   * <p>
   * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回
   * 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
   * <p>
   * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]] 输出：[[1,6],[8,10],[15,18]] 解释：区间 [1,3] 和 [2,6] 重叠,
   * 将它们合并为 [1,6]
   * <p>
   * 排序
   */
  public int[][] merge(int[][] intervals) {
    // 按照子集左元素排序
    // 第一个子集近结果集，比较后边子集的右元素和结果集中的子集的左元素 大于加入结果集
    // 小于则将右元素替换为左元素
    if (intervals.length == 0) {
      return new int[0][2];
    }
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] interval1, int[] interval2) {
        return interval1[0] - interval2[0];
      }
    });
    List<int[]> merged = new ArrayList<int[]>();
    for (int i = 0; i < intervals.length; i++) {
      int l = intervals[i][0];
      int r = intervals[i][1];
      if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < l) {
        merged.add(new int[]{l, r});
      } else {
        merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], r);
      }
    }
    return merged.toArray(new int[merged.size()][]);
  }


  /**
   * 124. 二叉树中的最大路径和
   * <p>
   * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
   * <p>
   * 路径和 是路径中各节点值的总和。
   * <p>
   * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
   * <p>
   * 输入：root = [-10,9,20,null,null,15,7] 输出：42 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
   */
  public int maxPathSum(TreeNode root) {
    maxGain(root);
    return maxSum;
  }

  int maxSum = Integer.MIN_VALUE;

  public int maxGain(TreeNode node) {
    if (node == null) {
      return 0;
    }

    // 递归计算左右子节点的最大贡献值
    // 只有在最大贡献值大于 0 时，才会选取对应子节点
    int leftGain = Math.max(maxGain(node.left), 0);
    int rightGain = Math.max(maxGain(node.right), 0);

    // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
    int priceNewpath = node.val + leftGain + rightGain;

    // 更新答案
    maxSum = Math.max(maxSum, priceNewpath);

    // 返回节点的最大贡献值
    return node.val + Math.max(leftGain, rightGain);
  }

  /**
   * 94. 二叉树的中序遍历
   *
   * <p>
   * 输入：root = [1,null,2,3] 输出：[1,3,2]
   * <p>
   * 递归
   */
  public List<Integer> inorderTraversal(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<Integer> ans = new ArrayList<>();
    inorder(root, ans);
    return ans;
  }

  private void inorder(TreeNode root, List<Integer> ans) {
    if (root == null) {
      return;
    }
    inorder(root.left, ans);
    ans.add(root.val);
    inorder(root.right, ans);
  }

  /**
   * 94. 二叉树的中序遍历
   * <p>
   * Morris
   */
  public List<Integer> inorderTraversal2(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    // 首先把根节点赋值给cur
    TreeNode cur = root;
    // 如果cur不为空就继续遍历
    while (cur != null) {
      if (cur.left == null) {
        // 如果当前节点cur的左子节点为空，就访问当前节点cur
        // 接着让当前节点cur指向他的右子节点
        ans.add(cur.val);
        cur = cur.right;
      } else {
        TreeNode pre = cur.left;
        // 查找pre节点，注意这里有个判断就是pre的右子节点不能等于cur
        while (pre.right != null && pre.right != cur) {
          pre = pre.right;
        }
        // 如果pre节点的右指针指向空，我们就让他指向当前节点cur
        // 然后当前节点cur指向他的左子节点
        if (pre.right == null) {
          pre.right = cur;
          cur = cur.left;
        } else {
          // 如果pre节点的右指针不为空，那么他肯定是指向cur的
          // 表示cur的子节点都遍历完了，我们需要让pre的右指针指向null，目的是把树还原，然后再访问当前节点cur
          // 最后再让当前节点cur指向他的右子节点
          pre.right = null;
          ans.add(cur.val);
          cur = cur.right;
        }

      }
    }
    return ans;
  }

  public List<Integer> inorderTraversal3(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null) {
      return ans;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode node = root;
    while (!deque.isEmpty() || node != null) {
      while (node != null) {
        deque.push(node);
        node = node.left;
      }
      node = deque.pop();
      ans.add(node.val);
      node = node.right;
    }
    return ans;
  }

  /**
   * 144. 二叉树的前序遍历 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
   */
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    preorder(root, ans);
    return ans;
  }

  private void preorder(TreeNode root, List<Integer> ans) {
    if (root == null) {
      return;
    }
    ans.add(root.val);
    preorder(root.left, ans);
    preorder(root.right, ans);
  }

  public List<Integer> preorderTraversal2(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null) {
      return ans;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode node = root;
    while (!deque.isEmpty() || node != null) {
      while (node != null) {
        ans.add(node.val);
        deque.push(node);
        node = node.left;
      }
      node = deque.pop();
      node = node.right;
    }
    return ans;
  }

  /**
   * 129. 求根节点到叶节点数字之和 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。 每条从根节点到叶节点的路径都代表一个数字：
   * <p>
   * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。 计算从根节点到叶节点生成的 所有数字之和 。
   * <p>
   * 叶节点 是指没有子节点的节点。 输入：root = [1,2,3] 输出：25 解释： 从根到叶子节点路径 1->2 代表数字 12 从根到叶子节点路径 1->3 代表数字 13
   * 因此，数字总和 = 12 + 13 = 25
   */
  public int sumNumbers(TreeNode root) {
    return dfs(root, 0);
  }

  public int dfs(TreeNode root, int prevSum) {
    if (root == null) {
      return 0;
    }
    int sum = prevSum * 10 + root.val;
    if (root.left == null && root.right == null) {
      return sum;
    } else {
      return dfs(root.left, sum) + dfs(root.right, sum);
    }
  }

  /**
   * 最长公共子串
   * <p>
   * 有两个字符串，s1="people"和s2="eplm"，我们要求他俩最长的公共子串。我们一眼就能看 出他们的最长公共子串是"pl"，长度是2
   */
  public int longestCommonSubString(String text1, String text2) {
    if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
      return 0;
    }
    int max = 0;
    // 边界值加1
    int[][] dp = new int[text1.length() + 1][text2.length() + 1];
    for (int i = 1; i <= text1.length(); i++) {
      for (int j = 1; j <= text2.length(); j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1; // 边界值加1原因 如果不加1 dp[-1][-1]越界
        } else {
          dp[i][j] = 0;
        }
        max = Math.max(dp[i][j], max);
      }
    }
    return max;
  }


  /**
   * 1143. 最长公共子序列
   * <p>
   * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
   * <p>
   * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
   * <p>
   * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 示例 1：
   * <p>
   * 输入：text1 = "abcde", text2 = "ace" 输出：3 解释：最长公共子序列是 "ace" ，它的长度为 3 。
   * <p>
   * <p>
   * 动态规划
   */
  public int longestCommonSubsequence(String text1, String text2) {
    if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
      return 0;
    }
    int max = 0;
    int[][] dp = new int[text1.length() + 1][text2.length() + 1];
    for (int i = 1; i <= text1.length(); i++) {
      for (int j = 1; j <= text2.length(); j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        }
        max = Math.max(dp[i][j], max);
      }
    }
    return max;
  }

  /**
   * 199. 二叉树的右视图
   * <p>
   * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
   * <p>
   * 输入: [1,2,3,null,5,null,4] 输出: [1,3,4]
   * <p>
   * DFS
   */
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    rightSideViewDFS(root, 0, ans);
    return ans;
  }

  private void rightSideViewDFS(TreeNode root, int level, List<Integer> ans) {
    if (root == null) {
      return;
    }
    // 只取第一个元素
    if (level == ans.size()) {
      ans.add(root.val);
    }
    rightSideViewDFS(root.right, level + 1, ans);
    rightSideViewDFS(root.left, level + 1, ans);
  }

  /**
   * 82. 删除排序链表中的重复元素 II
   * <p>
   * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
   * <p>
   * 输入：head = [1,2,3,3,4,4,5] 输出：[1,2,5]
   */
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return head;
    }

    ListNode dummy = new ListNode(0, head);

    ListNode cur = dummy;
    while (cur.next != null && cur.next.next != null) {
      if (cur.next.val == cur.next.next.val) {
        int x = cur.next.val;
        while (cur.next != null && cur.next.val == x) {
          cur.next = cur.next.next;
        }
      } else {
        cur = cur.next;
      }
    }

    return dummy.next;
  }

  /**
   * 69. x 的平方根
   * <p>
   * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
   * <p>
   * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
   * <p>
   * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
   * <p>
   * 示例 1：
   * <p>
   * 输入：x = 4 输出：2 示例 2：
   * <p>
   * 输入：x = 8 输出：2
   */
  public int mySqrt(int x) {
    int l = 0, r = x, ans = -1;
    while (l <= r) {
      int mid = (r - l) / 2 + l;
      if (mid * mid <= x) {
        ans = mid;
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return ans;
  }

  /**
   * 70. 爬楼梯 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
   * <p>
   * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
   */
  public int climbStairs(int n) {
    // f(n) = f(n-1)+f(n+1);
    int p = 0, q = 0, r = 1;
    for (int i = 1; i <= n; i++) {
      p = q;
      q = r;
      r = p + q;
    }
    return r;
  }

  /**
   * 148. 排序链表
   * <p>
   * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 归并排序 没会
   */
  public ListNode sortList(ListNode head) {
    return sortList(head, null);
  }

  public ListNode sortList(ListNode head, ListNode tail) {
    if (head == null) {
      return head;
    }
    if (head.next == tail) {
      head.next = null;
      return head;
    }
    ListNode slow = head, fast = head;
    while (fast != tail) {
      slow = slow.next;
      fast = fast.next;
      if (fast != tail) {
        fast = fast.next;
      }
    }
    ListNode mid = slow;
    ListNode list1 = sortList(head, mid);
    ListNode list2 = sortList(mid, tail);
    ListNode sorted = merge(list1, list2);
    return sorted;
  }

  public ListNode merge(ListNode head1, ListNode head2) {
    ListNode dummyHead = new ListNode(0);
    ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
    while (temp1 != null && temp2 != null) {
      if (temp1.val <= temp2.val) {
        temp.next = temp1;
        temp1 = temp1.next;
      } else {
        temp.next = temp2;
        temp2 = temp2.next;
      }
      temp = temp.next;
    }
    if (temp1 != null) {
      temp.next = temp1;
    } else if (temp2 != null) {
      temp.next = temp2;
    }
    return dummyHead.next;
  }

  /**
   * 31. 下一个排列 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
   * <p>
   * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。 整数数组的 下一个排列
   * 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
   * <p>
   * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。 而 arr = [3,2,1] 的下一个排列是
   * [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。 给你一个整数数组 nums ，找出 nums 的下一个排列。
   * <p>
   * 必须 原地 修改，只允许使用额外常数空间。
   */
  public void nextPermutation(int[] nums) {
    // 找到第一个比右边小的数字 较小数 尽可能靠右
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }
    if (i >= 0) {
      // 找到较大数 尽可能小
      int j = nums.length - 1;
      while (j >= 0 && nums[i] >= nums[j]) {
        j--;
      }
      //  较小数与较大数交换
      swap(nums, i, j);
    }
    // 使得后边的变为升序
    reverse(nums, i + 1);
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public void reverse(int[] nums, int start) {
    int left = start, right = nums.length - 1;
    while (left < right) {
      swap(nums, left, right);
      left++;
      right--;
    }
  }

  /**
   * 8. 字符串转换整数 (atoi)
   * <p>
   * 输入：s = "   -42" 输出：-42
   */
  public int myAtoi(String str) {
    int length = str.length();
    if (length == 0) {
      return 0;
    }
    int i = 0;
    boolean flag = true;
    int ans = 0;
    while (i < length && str.charAt(i) == ' ') {
      i++;
    }
    if (i != 0 && i == length - 1) {
      return 0;
    }
    if (i < length && str.charAt(i) == '-') {
      flag = false;
    }
    if (i < length && str.charAt(i) == '-' || i < length && str.charAt(i) == '+') {
      i++;
    }

    while (i < length && Character.isDigit(str.charAt(i))) {
      int r = str.charAt(i) - '0';
      if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && r > 7)) {
        return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      ans = ans * 10 + r;
      i++;
    }
    return flag ? ans : -ans;
  }

  /**
   * 239. 滑动窗口最大值
   * <p>
   * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
   * <p>
   * 返回 滑动窗口中的最大值 。
   * <p>
   * 示例 1：
   * <p>
   * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3 输出：[3,3,5,5,6,7] 解释： 滑动窗口的位置                最大值
   * ---------------               ----- [1  3  -1] -3  5  3  6  7       3 1 [3  -1  -3] 5  3  6  7
   * 3 1  3 [-1  -3  5] 3  6  7       5 1  3  -1 [-3  5  3] 6  7       5 1  3  -1  -3 [5  3  6] 7 6
   * 1  3  -1  -3  5 [3  6  7]      7
   */
  public int[] maxSlidingWindow(int[] nums, int k) {
    //边界条件的判断
    if (nums == null || k <= 0) {
      return new int[0];
    }
    int[] res = new int[nums.length - k + 1];
    int index = 0;
    //双端队列，就是两边都可以插入和删除数据的队列，注意这里存储
    //的是元素在数组中的下标，不是元素的值
    Deque<Integer> qeque = new ArrayDeque<>();
    for (int i = 0; i < nums.length; i++) {
      //如果队列中队头元素和当前元素位置相差i-k，相当于队头元素要
      //出窗口了，就把队头元素给移除，注意队列中存储
      //的是元素的下标（函数peekFirst()表示的是获取队头的下标，函数
      //pollFirst()表示的是移除队头元素的下标）
      if (!qeque.isEmpty() && qeque.peekFirst() <= i - k) {
        qeque.pollFirst();
      }
      //在添加一个值之前，前面比他小的都要被移除掉，并且还要保证窗口
      //中队列头部元素永远是队列中最大的
      while (!qeque.isEmpty() && nums[qeque.peekLast()] < nums[i]) {
        qeque.pollLast();
      }
      //当前元素的下标加入到队列的尾部
      qeque.addLast(i);
      //当窗口的长度大于等于k个的时候才开始计算（注意这里的i是从0开始的）
      if (i >= k - 1) {
        //队头元素是队列中最大的，把队列头部的元素加入到数组中
        res[index++] = nums[qeque.peekFirst()];
      }
    }
    return res;
  }

  /**
   * 165. 比较版本号
   * <p>
   * 示例 1：
   * <p>
   * 输入：version1 = "1.01", version2 = "1.001" 输出：0 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1" 示例 2：
   * <p>
   * 输入：version1 = "1.0", version2 = "1.0.0" 输出：0 解释：version1 没有指定下标为 2 的修订号，即视为 "0" 示例 3：
   * <p>
   * 输入：version1 = "0.1", version2 = "1.1" 输出：-1 解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是
   * "1" 。0 < 1，所以 version1 < version2
   */
  public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");
    for (int i = 0; i < v1.length || i < v2.length; i++) {
      int x = 0, y = 0;
      if (i < v1.length) {
        x = Integer.parseInt(v1[i]);
      }
      if (i < v2.length) {
        y = Integer.parseInt(v2[i]);
      }
      if (x > y) {
        return 1;
      }
      if (x < y) {
        return -1;
      }
    }
    return 0;
  }

  /**
   * LCR 140. 训练计划 II
   * <p>
   * 给定一个头节点为 head 的链表用于记录一系列核心肌群训练项目编号，请查找并返回倒数第 cnt 个训练项目编号。
   * <p>
   * <p>
   * <p>
   * 示例 1：
   * <p>
   * 输入：head = [2,4,7,8], cnt = 1 输出：8
   * <p>
   * 双指针
   */
  public ListNode trainingPlan(ListNode head, int cnt) {
    ListNode slow = head;
    ListNode fast = head;
    for (int i = 0; i < cnt; i++) {
      fast = fast.next;
    }
    while (fast != null) {
      fast = fast.next;
      slow = slow.next;
    }
    return slow;
  }

  /**
   * 41. 缺失的第一个正数 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
   * <p>
   * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
   * <p>
   * 示例 1：
   * <p>
   * 输入：nums = [1,2,0] 输出：3 示例 2：
   * <p>
   * 输入：nums = [3,4,-1,1] 输出：2 示例 3：
   * <p>
   * 输入：nums = [7,8,9,11,12] 输出：1
   * <p>
   * 把数组看成hash表
   */
  public int firstMissingPositive(int[] nums) {
    int len = nums.length;

    for (int i = 0; i < len; i++) {
      while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
        // 满足在指定范围内、并且没有放在正确的位置上，才交换
        // 例如：数值 3 应该放在索引 2 的位置上
        swap(nums, nums[i] - 1, i);
      }
    }

    // [1, -1, 3, 4]
    for (int i = 0; i < len; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    // 都正确则返回数组长度 + 1
    return len + 1;
  }

//  /**
//   * 155. 最小栈
//   * <p>
//   * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
//   * <p>
//   * 实现 MinStack 类:
//   * <p>
//   * MinStack() 初始化堆栈对象。 void push(int val) 将元素val推入堆栈。 void pop() 删除堆栈顶部的元素。 int top() 获取堆栈顶部的元素。
//   * int getMin() 获取堆栈中的最小元素。
//   */
//  class MinStack {
//
//    Deque<Integer> xStack;
//    Deque<Integer> minStack;
//
//    public MinStack() {
//      xStack = new LinkedList<Integer>();
//      minStack = new LinkedList<Integer>();
//      minStack.push(Integer.MAX_VALUE);
//    }
//
//    public void push(int x) {
//      xStack.push(x);
//      minStack.push(Math.min(minStack.peek(), x));
//    }
//
//    public void pop() {
//      xStack.pop();
//      minStack.pop();
//    }
//
//    public int top() {
//      return xStack.peek();
//    }
//
//    public int getMin() {
//      return minStack.peek();
//    }
//  }

  /**
   * 78. 子集
   * <p>
   * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
   * <p>
   * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 示例 1：
   * <p>
   * 输入：nums = [1,2,3] 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
   */
  public List<List<Integer>> subsets(int[] nums) {
    // 结果集长度为 nums长度左移一位
    List<List<Integer>> res = new ArrayList<>(1 << nums.length);
    res.add(new ArrayList<>());
    for (int num : nums) {
      int j = res.size();
      // 每遍历一个元素就在之前子集中的每个集合追加这个元素，让他变成新的子集
      for (int i = 0; i < j; i++) {
        List<Integer> list = new ArrayList<>(res.get(i));
        list.add(num);
        res.add(list);
      }
    }
    return res;
  }


  /**
   * 76. 最小覆盖子串
   * <p>
   * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
   * <p>
   * 注意：
   * <p>
   * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 如果 s 中存在这样的子串，我们保证它是唯一的答案。
   * <p>
   * 示例 1：
   * <p>
   * 输入：s = "ADOBECODEBANC", t = "ABC" 输出："BANC" 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
   */
  public String minWindow(String s, String t) {
    //把t中的字符全部放到map中
    Map<Character, Integer> map = new HashMap<>();
    for (char ch : t.toCharArray()) {
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }

    int left = 0;//窗口的左边界
    int right = 0;//窗口的右边界

    //满足条件的窗口开始位置
    int strStart = 0;
    //满足条件的窗口的长度
    int windowLength = Integer.MAX_VALUE;

    while (right < s.length()) {
      //记录右指针扫描过的字符
      char rightChar = s.charAt(right);
      //如果右指针扫描的字符存在于map中，就减1
      if (map.containsKey(rightChar)) {
        map.put(rightChar, map.getOrDefault(rightChar, 0) - 1);
      }
      //记录之后右指针要往右移
      right++;

      //检查窗口是否把t中字符全部覆盖了，如果覆盖了，要移动窗口的左边界
      //找到最小的能全部覆盖的窗口
      while (check(map)) {
        //如果现在窗口比之前保存的还要小，就更新窗口的长度
        //以及窗口的起始位置
        if (right - left < windowLength) {
          windowLength = right - left;
          strStart = left;
        }
        //移除窗口最左边的元素，也就是缩小窗口
        char leftChar = s.charAt(left);
        if (map.containsKey(leftChar)) {
          map.put(leftChar, map.getOrDefault(leftChar, 0) + 1);
        }
        //左指针往右移
        left++;
      }
    }
    //如果找到合适的窗口就截取，否则就返回空
    if (windowLength != Integer.MAX_VALUE) {
      return s.substring(strStart, strStart + windowLength);
    }
    return "";
  }

  //检查窗口是否把字符串t中的所有字符都覆盖了，如果map中所有
  // value的值都不大于0，则表示全部覆盖
  private boolean check(Map<Character, Integer> map) {
    for (int value : map.values()) {
      //注意这里的value是可以为负数的，为负数的情况就是，相同的字符右
      // 指针扫描的要比t中的多，比如t是"ABC"，窗口中的字符是"ABBC"
      if (value > 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * 104. 二叉树的最大深度
   * <p>
   * 给定一个二叉树 root ，返回其最大深度。
   * <p>
   * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
   */
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    } else {
      int left = maxDepth(root.left);
      int right = maxDepth(root.right);
      return Math.max(left, right) + 1;
    }
  }

  /**
   * 110. 平衡二叉树
   * <p>
   * 给定一个二叉树，判断它是否是高度平衡的二叉树。
   * <p>
   * 本题中，一棵高度平衡二叉树定义为：
   * <p>
   * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
   */
  public boolean isBalanced(TreeNode root) {
    return height(root) >= 0;
  }

  public int height(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftHeight = height(root.left);
    int rightHeight = height(root.right);
    if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
      return -1;
    } else {
      return Math.max(leftHeight, rightHeight) + 1;
    }
  }

  int ans;

  /**
   * 543. 二叉树的直径 给你一棵二叉树的根节点，返回该树的 直径 。
   * <p>
   * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
   * <p>
   * 两节点之间路径的 长度 由它们之间边数表示。
   * <p>
   * 输入：root = [1,2,3,4,5] 输出：3 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
   */
  public int diameterOfBinaryTree(TreeNode root) {
    ans = 1;
    depth(root);
    return ans - 1;
  }

  public int depth(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int l = depth(node.left);
    int r = depth(node.right);
    ans = Math.max(l + r + 1, ans);
    return Math.max(l, r) + 1;
  }

  /**
   * 101. 对称二叉树 给你一个二叉树的根节点 root ， 检查它是否轴对称。
   */
  public boolean isSymmetric(TreeNode root) {
    return check(root, root);
  }

  public boolean check(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }
    return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
  }

  /**
   * 39. 组合总和 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合
   * ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
   * <p>
   * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
   * <p>
   * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
   * <p>
   * 示例 1：
   * <p>
   * 输入：candidates = [2,3,6,7], target = 7 输出：[[2,2,3],[7]] 解释： 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2
   * 可以使用多次。 7 也是一个候选， 7 = 7 。 仅有这两种组合。
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    int len = candidates.length;
    List<List<Integer>> res = new ArrayList<>();
    if (len == 0) {
      return res;
    }

    Deque<Integer> path = new ArrayDeque<>();
    dfs(candidates, 0, len, target, path, res);
    return res;
  }

  /**
   * @param candidates 候选数组
   * @param begin      搜索起点
   * @param len        冗余变量，是 candidates 里的属性，可以不传
   * @param target     每减去一个元素，目标值变小
   * @param path       从根结点到叶子结点的路径，是一个栈
   * @param res        结果集列表
   */
  private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path,
      List<List<Integer>> res) {
    // target 为负数和 0 的时候不再产生新的孩子结点
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(path));
      return;
    }

    // 重点理解这里从 begin 开始搜索的语意
    for (int i = begin; i < len; i++) {
      path.addLast(candidates[i]);

      // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
      dfs(candidates, i, len, target - candidates[i], path, res);

      // 状态重置
      path.removeLast();
    }
  }

  /**
   * 98. 验证二叉搜索树
   * <p>
   * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
   * <p>
   * 有效 二叉搜索树定义如下：
   * <p>
   * 节点的左子树只包含 小于 当前节点的数。 节点的右子树只包含 大于 当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树。
   */
  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public boolean isValidBST(TreeNode node, long lower, long upper) {
    if (node == null) {
      return true;
    }
    if (node.val <= lower || node.val >= upper) {
      return false;
    }
    return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
  }

  /**
   * 48. 旋转图像 中等 1.8K 相关企业 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
   * <p>
   * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
   * <p>
   * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]] 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
   */
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n / 2; ++i) {
      for (int j = 0; j < (n + 1) / 2; ++j) {
        // +1 处理奇数的情况
        int temp = matrix[i][j];
        matrix[i][j] = matrix[n - j - 1][i];
        matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
        matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
        matrix[j][n - i - 1] = temp;
      }
    }
  }

  /**
   * 64. 最小路径和 相关企业 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
   * <p>
   * 说明：每次只能向下或者向右移动一步。 动态规划
   */
  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int rows = grid.length, columns = grid[0].length;
    int[][] dp = new int[rows][columns];
    dp[0][0] = grid[0][0];
    for (int i = 1; i < rows; i++) {
      dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    for (int j = 1; j < columns; j++) {
      dp[0][j] = dp[0][j - 1] + grid[0][j];
    }
    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < columns; j++) {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
      }
    }
    return dp[rows - 1][columns - 1];
  }


  /**
   * 113. 路径总和 II
   *
   * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
   *
   * 叶子节点 是指没有子节点的节点。
   *
   * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
   * 输出：[[5,4,11,2],[5,8,4,5]]
   *
   * 深度优先遍历 + 回溯
   *
   */
  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    dfsPathSum(root, targetSum);
    return ret;
  }
  List<List<Integer>> ret = new LinkedList<List<Integer>>();
  Deque<Integer> path = new LinkedList<Integer>();

  public void dfsPathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return;
    }
    path.offerLast(root.val);
    targetSum -= root.val;
    if (root.left == null && root.right == null && targetSum == 0) {
      ret.add(new LinkedList<Integer>(path));
    }
    dfsPathSum(root.left, targetSum);
    dfsPathSum(root.right, targetSum);
    path.pollLast();
  }


  public static void main(String[] args) {
//    String s = "abcabcbb";
//    String s = "bbbbb";
//    String s = "pwwkew";
//    System.out.println(lengthOfLongestSubstring(s));
//    int[] nums = {3, 2, 1, 5, 6, 4};
//    findKthLargest(nums, 2);

    longestPalindrome("babad");

  }

}
