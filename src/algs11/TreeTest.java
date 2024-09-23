package algs11;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void testSize(){
        // Test case 1: Tree with only root node
        Tree<Integer> tree1 = new Tree<Integer>(5);
        assertEquals(1, tree1.size());

        // Test case 2: Tree with left and right subtrees
        Tree<Integer> tree2 = new Tree<Integer>(5);
        tree2.root.left = new Node<Integer>(3);
        tree2.root.right = new Node<Integer>(7);
        assertEquals(3, tree2.size());

        // Add more test cases as needed
    }

    @Test
    public void testMaxDepth(){
        // Test case 1: Tree with only root node
        Tree<Integer> tree1 = new Tree<Integer>(5);
        assertEquals(1, tree1.maxDepth());

        // Test case 2: Tree with left and right subtrees of different depths
        Tree<Integer> tree2 = new Tree<Integer>(5);
        tree2.root.left = new Node<Integer>(3);
        tree2.root.right = new Node<Integer>(7);
        tree2.root.right.right = new Node<Integer>(8);
        assertEquals(3, tree2.maxDepth());

    }

        @Test
        public void testMirror(){
            // Test case 1: Tree with only root node
            Tree<Integer> tree1 = new Tree<Integer>(5);
            tree1.mirror();
            assertEquals("5 ", tree1.printInorder());
    
            // Test case 2: Tree with left and right subtrees
            Tree<Integer> tree2 = new Tree<Integer>(5);
            tree2.root.left = new Node<Integer>(3);
            tree2.root.right = new Node<Integer>(7);
            tree2.mirror();
            assertEquals("7 5 3 ", tree2.printInorder());
    
            // Test case 3: Tree with multiple levels
            Tree<Integer> tree3 = new Tree<Integer>(5);
            tree3.root.left = new Node<Integer>(3);
            tree3.root.right = new Node<Integer>(7);
            tree3.root.left.left = new Node<Integer>(2);
            tree3.root.left.right = new Node<Integer>(4);
            tree3.root.right.left = new Node<Integer>(6);
            tree3.root.right.right = new Node<Integer>(8);
            tree3.mirror();
            assertEquals("8 7 6 5 4 3 2 ", tree3.printInorder());
        }

    @Test
    public void testSameTree(){

        // Test case 1: Two trees with only root node
        Tree<Integer> tree3 = new Tree<Integer>(5);
        Tree<Integer> tree4 = new Tree<Integer>(5);
        assertTrue(tree3.sameTree(tree4));

        // Test case 2: Two identical trees
        Tree<Integer> tree5 = new Tree<Integer>(5);
        tree5.root.left = new Node<Integer>(3);
        tree5.root.right = new Node<Integer>(7);
        Tree<Integer> tree6 = new Tree<Integer>(5);
        tree6.root.left = new Node<Integer>(3);
        tree6.root.right = new Node<Integer>(7);
        assertTrue(tree5.sameTree(tree6));

        // Test case 3: Two different trees
        Tree<Integer> tree7 = new Tree<Integer>(5);
        tree7.root.left = new Node<Integer>(3);
        tree7.root.right = new Node<Integer>(7);
        Tree<Integer> tree8 = new Tree<Integer>(5);
        tree8.root.left = new Node<Integer>(3);
        tree8.root.right = new Node<Integer>(8); // Different value
        assertFalse(tree7.sameTree(tree8));

        
    }
    
}
