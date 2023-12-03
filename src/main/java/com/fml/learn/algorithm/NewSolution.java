package com.fml.learn.algorithm;

import com.fml.learn.algorithm.structure.ListNode;
import com.fml.learn.algorithm.structure.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
   * 46. 全排列
   * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
   * 输入：nums = [1,2,3]
   * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   *
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
