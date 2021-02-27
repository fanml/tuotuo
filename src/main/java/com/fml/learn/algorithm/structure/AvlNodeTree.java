package com.fml.learn.algorithm.structure;

import java.nio.BufferUnderflowException;

/**
 * 平衡二叉树
 */
public class AvlNodeTree<T extends Comparable<? super T>> {

    private static final int ALLOWED_IMBLANE = 1;

    private AvlNode<T> root;

    public AvlNodeTree() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public int height() {
        return height(root);
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
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

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    private AvlNode<T> insert(T x, AvlNode<T> t) {
        if (t == null) {
            return new AvlNode<>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            ;
        }
        return balance(t);
    }

    private boolean contains(T x, AvlNode<T> t) {
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


    private AvlNode<T> remove(T x, AvlNode<T> t) {
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
        return balance(t);
    }

    /**
     * 最小值递归实现
     *
     * @param t
     * @return
     */
    private AvlNode<T> findMin(AvlNode<T> t) {
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
    private AvlNode<T> findMax(AvlNode<T> t) {
        if (t == null) {
            return null;
        }
        while (t.right != null) {
            t = t.right;
        }
        return t.right;
    }

    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null) {
            return t;
        }
        if (height(t.left) - height(t.right) > ALLOWED_IMBLANE) {
            if (height(t.left.left) >= height(t.left.right)) {
                t = rotateWithLeftChild(t);
            } else {
                t = doubleWithLeftXChild(t);
            }
        } else {
            if (height(t.right) - height(t.left) > ALLOWED_IMBLANE) {
                if (height(t.right.right) >= height(t.right.left)) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithLeftXChild(t);
                }
            }
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * 单旋转
     *
     * @param k2
     * @return
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * 双循环
     *
     * @param k3
     * @return
     */
    private AvlNode<T> doubleWithLeftXChild(AvlNode<T> k3) {
        k3.left = rotateWithLeftChild(k3.left);
        return rotateWithLeftChild(k3);
    }


    private static class AvlNode<T> {
        AvlNode(T theElement, AvlNode<T> lf, AvlNode<T> rt) {
            element = theElement;
            left = lf;
            right = rt;
            height = 0;
        }

        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;
    }
}
