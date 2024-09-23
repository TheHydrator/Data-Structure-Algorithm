package algs11;

import java.util.Deque;
import java.util.LinkedList;

import java.util.Queue;

class Node<T> {
    Node<T> left, right;
    T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value){
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class Tree<T> {
    
    Node<T> root;

    Tree(T value){
        root = new Node<T>(value);
    }

    public int size(){
        return size(root);
    }
    private static <T> int size(Node<T> node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    private static <T> int maxDepth(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
    }

    public String printInorder() {
        return printInorder(root);
    }

    static <T> String printInorder(Node<T> node) {
        if (node == null) return "";
        return printInorder(node.left) + node.value + " " + printInorder(node.right);
    }

    public String printPreorder() {
        return printPreorder(root);
    }

    private static <T> String printPreorder(Node<T> node) {
        if (node == null) return "";
        return node.value + " " + printPreorder(node.left) + printPreorder(node.right);
    }

    public String printPostorder() {
        return printPostorder(root);
    }

    private static <T> String printPostorder(Node<T> node) {
        if (node == null) return "";
        return printPostorder(node.left) + printPostorder(node.right) + node.value + " ";
    }

    public String printBFS() {
        StringBuilder sb = new StringBuilder();
        if (root == null) return sb.toString();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node<T> node = queue.poll();
                sb.append(node.value + " ");
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            sb.append("\n"); // Add newline after each level
        }
        return sb.toString();
    }

    public int countLeaves() {
        return (int)countLeaves(root);
    }

    private <T> int countLeaves(Node<T> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    static int f1(Tree<Integer> node, int sum) {
        return f1(node.root, sum, 0); // Assuming root is stored in node.root
    }

    private static int f1(Node<Integer> node, int sum, int currentSum) {
        if (node == null) return 0;
        currentSum += node.value;
        if (node.left == null && node.right == null && currentSum == sum) return 1;
        return f1(node.left, sum, currentSum) + f1(node.right, sum, currentSum);
    }   

    public String printPaths() {
        StringBuilder sb = new StringBuilder();
        printPaths(root, "", sb);
        return sb.toString().trim(); // Remove trailing spaces
    }

    private void printPaths(Node<T> node, String path, StringBuilder sb) {
        if (node == null) return;
        path += node.value + " -> ";
        if (node.left == null && node.right == null) {
            sb.append(path.substring(0, path.length() - 3)).append("\n"); // Remove " -> " from leaf nodes
        }
        printPaths(node.left, path, sb);
        printPaths(node.right, path, sb);
    }

    public void mirror() {
        mirror(root);
    }

    private void mirror(Node<T> node) {
        if (node == null) return;
        Node<T> temp = node.left;
        node.left = node.right;
        node.right = temp;
        mirror(node.left);
        mirror(node.right);
    }

    public void doubleValues() { // Assuming you meant to double the values
        doubleValues(root);
    }

    private void doubleValues(Node<T> node) {
        if (node == null) return;
        node.value = (T) Double.valueOf((Double) node.value * 2); // Assuming T is a Number type (modify cast if needed)
        doubleValues(node.left);
        doubleValues(node.right);
    }

    public boolean sameTree(Tree<T> other) {
        return sameTree(root, other.root);
    }

    private boolean sameTree(Node<T> node1, Node<T> node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (!node1.value.equals(node2.value)) return false;
        return sameTree(node1.left, node2.left) && sameTree(node1.right, node2.right);
    }

    // FOR THE NEXT ASSIGNMENT  
    public String printIterativePreOrder(){
        StringBuilder sb = new StringBuilder();
        if (root == null) return sb.toString();

        Deque<Node<T>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            sb.append(node.value).append(" ");

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return sb.toString().trim();
    }
    
    public String printIterativePostOrder(){
        StringBuilder sb = new StringBuilder();
        if (root == null) return sb.toString();
    
        Deque<Node<T>> stack1 = new LinkedList<>();
        Deque<Node<T>> stack2 = new LinkedList<>();
        stack1.push(root);
    
        while (!stack1.isEmpty()) {
            Node<T> node = stack1.pop();
            stack2.push(node);
    
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }
    
        while (!stack2.isEmpty()) {
            sb.append(stack2.pop().value).append(" ");
        }
    
        return sb.toString().trim();
    }
    
    public String printIterativeInOrder(){
        StringBuilder sb = new StringBuilder();
        if (root == null) return sb.toString();
    
        Deque<Node<T>> stack = new LinkedList<>();
        Node<T> current = root;
    
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            sb.append(current.value).append(" ");
            current = current.right;
        }
    
        return sb.toString().trim();
    }

    
    public Deque<Node<T>> toCircularDLL() {
        Deque<Node<T>> dll = new LinkedList<>();
        convertToDLL(root, dll);
        
        if (!dll.isEmpty()) {
            // Make the DLL circular
            Node<T> firstNode = dll.getFirst();
            Node<T> lastNode = dll.getLast();
            firstNode.prev = lastNode;
            lastNode.next = firstNode;
        }
        
        return dll;
    }
    
    private void convertToDLL(Node<T> node, Deque<Node<T>> dll) {
        if (node == null) return;
    
        convertToDLL(node.left, dll);
        
        // Create duplicates
        Node<T> newNode = new Node<>(node.value);
        Node<T> nextNode = dll.isEmpty() ? null : (Node<T>) dll.getFirst(); // Explicitly cast the value to Node<T>
        
        if (nextNode != null) {
            newNode.next = nextNode;
            nextNode.prev = newNode;
        }
        
        dll.addLast(newNode); // Add the new node, not just its value
        
        convertToDLL(node.right, dll);
    }
    
    

}
