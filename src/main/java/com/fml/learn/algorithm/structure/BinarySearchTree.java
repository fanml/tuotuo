package com.fml.learn.algorithm.structure;

import java.nio.BufferUnderflowException;

/**
 * 二叉查找树
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }

        return findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {

    }


    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    /**
     * 最小值递归实现
     *
     * @param t
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        } else {
            return findMin(t);
        }
    }

    /**
     * 最大值非递归实现
     *
     * @param t
     * @return
     */
    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t == null) {
            return null;
        }
        while (t.right != null) {
            t = t.right;
        }
        return t.right;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<T>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            ;
        }
        return t;
    }

    /**
     * 1.节点是一片树叶 直接删除
     * 2.节点有一个儿子 该节点可以在其父节点调整自己的链以绕过该节点后被删除
     * 3.节点有俩个儿子 1)用其右子树的最小数据代替该节点的数据 然后递归删除那个节点
     *
     * @param x
     * @param t
     * @return
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return t;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;

    }


    /**
     * 私有BinaryNode类
     *
     * @param <T>
     */
    private static class BinaryNode<T> {
        BinaryNode(T theElement) {
            this(theElement, null, null);
        }

        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            element = theElement;
            lt = left;
            rt = right;
        }

        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
    }
}
