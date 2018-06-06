package algorithm.tree;

/**
 * Created by mengtian on 2018/6/5
 */
public class BSTNode<T extends Comparable<T>> {
    T key;
    BSTNode left;
    BSTNode right;
    BSTNode parent;

    public BSTNode(T key, BSTNode left, BSTNode right, BSTNode parent) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
