package algs11;

public class TreeComparable<T extends Comparable<T>> extends Tree<T> {
    
    TreeComparable(T value) {
        super(value);
    }
    
    public Node<T> insert(T v) {
        return insert(root, v);
    }
    

    private Node<T> insert(Node<T> node, T v) {
        if (node == null) {
            return new Node<T>(node.value);
        }
        if (v.compareTo(node.value) < 0) {
            node.left = insert(node.left, v);
        } else {
            node.right = insert(node.right, v);
        }
        return node;
    }

    public boolean lookup(T v) {
        return lookup(root, v);
    }

    private boolean lookup(Node<T> node, T v) {
        if (node == null) {
            return false;
        }
        if (v.compareTo(node.value) == 0) {
            return true;
        } else if (v.compareTo(node.value) < 0) {
            return lookup(node.left, v);
        } else {
            return lookup(node.right, v);
        }
    }

    public T min() {
        if (root == null) {
            return null;
        }
        return min(root);
    }

    private T min(Node<T> node) {
        if (node.left == null) {
            return node.value;
        }
        return min(node.left);
    }

    public T max() {
        if (this.root == null) {
            return null;
        }
        return max(this.root);
    }

    private T max(Node<T> node) {
        if (node.right == null) {
            return node.value;
        }
        return max(node.right);
    }

    public static boolean isBST1(TreeComparable<Integer> node) {
        return helperBST1(node.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private static boolean helperBST1(Node<Integer> node, Integer minVal, Integer maxVal) {
        if (node == null) {
            return true;
        }
        if ((minVal != null && node.value.compareTo(minVal) < 0) || (maxVal != null && node.value.compareTo(maxVal) > 0)) {
            return false;
        }
        return helperBST1(node.left, minVal, node.value) && helperBST1(node.right, node.value, maxVal);
    }
    

    public boolean isBST(TreeComparable<Integer> node) {
        return helper(node.root, null, null);
    }
    
    private boolean helper(Node<Integer> node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
    
        // Check if the current node's value is within the valid range
        if ((min != null && node.value <= min) || (max != null && node.value >= max)) {
            return false;
        }
    
        // Recursively check the left and right subtrees with updated min and max values
        return helper(node.left, min, node.value) && helper(node.right, node.value, max);
    }
    
    
}
