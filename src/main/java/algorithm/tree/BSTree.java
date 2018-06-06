package algorithm.tree;

/**
 * Created by mengtian on 2018/6/5
 * <p>
 * 二叉查找树
 * <p>
 * http://www.cnblogs.com/skywang12345/p/3576452.html#a1
 */
public class BSTree<T extends Comparable<T>> {
    private BSTNode<T> root;


    /**
     * 前序遍历
     *
     * @param tree
     */
    private void preOrder(BSTNode<T> tree) {
        if (tree != null) {
            System.out.println(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 中序遍历
     *
     * @param tree
     */
    private void inOrder(BSTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.println(tree.key + " ");
            inOrder(tree.right);
        }
    }

    /**
     * 后序遍历
     *
     * @param tree
     */
    private void postOrder(BSTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.println(tree.key + " ");
        }
    }

    /**
     * 递归查找
     *
     * @param x
     * @param key
     * @return
     */
    private BSTNode<T> search(BSTNode<T> x, T key) {
        if (x == null) {
            return x;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return search(x.left, key);
        } else if (cmp > 0) {
            return search(x.right, key);
        } else return x;
    }

    /**
     * 非递归查找
     *
     * @param x
     * @param key
     * @return
     */
    private BSTNode<T> iterativeSearch(BSTNode<T> x, T key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else return x;
        }
        return x;
    }

    /**
     * 查找最大值
     *
     * @param tree
     * @return
     */
    private BSTNode<T> maximum(BSTNode<T> tree) {
        if (tree == null) {
            return tree;
        }
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree;
    }

    /**
     * 查找最小值
     *
     * @param tree
     * @return
     */
    private BSTNode<T> minimum(BSTNode<T> tree) {
        if (tree == null) {
            return tree;
        }
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    /**
     * 找结点(x)的前驱结点。即，查找"二叉树中数据值小于该结点"的"最大结点"。
     *
     * @param x
     * @return
     */
    public BSTNode<T> predecessor(BSTNode<T> x) {
        // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (x.left != null)
            return maximum(x.left);

        // 如果x没有左孩子。则x有以下两种可能：
        // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
        BSTNode<T> y = x.parent;
        while ((y != null) && (x == y.left)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    /**
     * 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。
     *
     * @param x
     * @return
     */
    public BSTNode<T> successor(BSTNode<T> x) {
        // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (x.right != null)
            return minimum(x.right);

        // 如果x没有右孩子。则x有以下两种可能：
        // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
        // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        BSTNode<T> y = x.parent;
        while ((y != null) && (x == y.right)) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * 将结点node插入到二叉树中
     *
     * @param tree
     * @param node
     */
    private void insert(BSTree<T> tree, BSTNode<T> node) {
        int cmp;
        BSTNode<T> y = null;
        BSTNode<T> x = tree.root;

        //查找node的插入位置
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }
        node.parent = y;
        if (y == null) {
            tree.root = node;
        } else {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0)
                y.left = node;
            else
                y.right = node;
        }
    }

    /**
     * 删除结点(node)，并返回被删除的结点
     *
     * @param tree
     * @param node
     */
    private BSTNode<T> remove(BSTree<T> tree, BSTNode<T> node) {
        BSTNode<T> x = null;
        BSTNode<T> y = null;

        if (node.left == null || node.right == null) {
            y = node;
        } else {
            y = successor(node);
        }

        if (y.left != null) {
            x = y.left;
        } else {
            x = y.right;
        }
        if (x != null) {
            x.parent = y.parent;
        }

        if (y.parent == null)
            tree.root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;

        if (y != node)
            node.key = y.key;

        return y;
    }

    /**
     * 打印"二叉查找树"
     * <p>
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     * -1，表示该节点是它的父结点的左孩子;
     * 1，表示该节点是它的父结点的右孩子。
     */
    private void print(BSTNode<T> tree, T key, int direction) {

        if (tree != null) {

            if (direction == 0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.key, 0);
    }

    public void preOrder() {
        preOrder(root);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void postOrder() {
        postOrder(root);
    }

    public BSTNode<T> search(T key) {
        return search(root, key);
    }

    public BSTNode<T> iterativeSearch(T key) {
        return iterativeSearch(root, key);
    }

    public T maximum() {
        BSTNode<T> p = maximum(root);
        if (p != null)
            return p.key;
        return null;
    }

    public T minimum() {
        BSTNode<T> p = minimum(root);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    public void insert(T key) {
        BSTNode<T> tree = new BSTNode<T>(key, null, null, null);
        //如果新建节点失败，则返回
        if (tree != null) {
            insert(this, tree);
        }
    }

    /*
     * 删除结点(z)，并返回被删除的结点
     *
     * 参数说明：
     *     tree 二叉树的根结点
     *     z 删除的结点
     */
    public void remove(T key) {
        BSTNode<T> z, node;
        if ((z = search(root, key)) != null) {
            if ((node = remove(this, z)) != null)
                node = null;
        }
    }
}
