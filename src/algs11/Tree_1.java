// package algs11;

// import stdlib.*;

// class Node<T> {
//     Node<T> left, right;
//     T value;
//     public Node(T value){
//         this.value = value;
//     }
//     public Node(T value, Node<T> left, Node<T> right){
//         this.value = value;
//         this.left = left;
//         this.right = right;
//     }

//     public boolean equals(Object o){
//         if (o == null) return false;
//         if (o == this) return true;
//         if (!(o instanceof Node)) return false;
//         Node other = (Node) o;
//         return this.value == other.value && this.left.equals(other.left) && this.right.equals(other.right);
//     }

//     public void insert(int value){
//         insert(this,value);
//     }

//     public Node insert(Node node, int value){
//         if (node == null) return new Node(value);
//         if (value < node.value) 
//             insert(node.left, value);
//         else node.right = insert(node.right, value);
//         return node;
//     }

//     public int size(){
//         return size(this);
//     }

//     private static int size(Node node) {
//         if (node == null) return 0;
//         return 1 + size(node.left) + size(node.right);
//     }

//     // THIS DOES NOT WORK   ( SEE LECTURE>  8:14)
//     private static int size1(Node node){
//         if ((node.left == null) && (node.right == null)) return 1;
//         return 1 + size(node.left) + size(node.right);
//     }

//     public void printInorder(){
//         printInorder(this);
//     }

//     private static void printInorder(Node node) {
//         // if node == null return;
//      if (node !=null){
//             printInorder(node.left);
//             System.out.println(node.value);
//             printInorder(node.right);
//         }
//     }

//     public void mirror() {
        
//        }

//     private static void mirror(Node node) {
//         if (node!=null){
            
//             mirror(node.left);
//             mirror(node.right);
//             Node temp = node.left;
//             node.left = node.right;
//             node.right = temp;
//         }
        
//        }

//     public boolean sameTree(Node node){ return true;}

  
//     public boolean isBST(){
//         return isBST(this);
//     }
//     public static boolean isBST(Node node){
//         if (node == null) return true;
//         int x =1;
//         return isBST(node.left)
//          && isBST(node.right)
//           && (node.value >= gmax(node.left))
//            && (node.value <= gmin(node.right));
//     }

//     public boolean isBST2(){
//         return isBST2(this, Integer.MIN_VALUE, Integer.MAX_VALUE);
//     }

//     public static boolean isBST2(Node node, int min, int max){
//         if (node == null) return true;
//         return isBST2(node.left, min, node.value) && isBST2(node.right, node.value, max) && (node.value >= min) && (node.value <= max);
//     }

//     public static int gmin(Node node){
//         if (node == null) return Integer.MAX_VALUE;
//         return Math.min(node.value, Math.min(gmin(node.left), gmin(node.right)));
//     }

//     public static int gmax(Node node){
//         if (node == null) return Integer.MAX_VALUE;
//         return Math.min(node.value, Math.max(gmax(node.left), gmax(node.right)));
//     }

//     public static Node create(int N){
//         // create a BST with N nodes, left slanted
//         if (N < 0) { // check for negative size
//             throw new IllegalArgumentException("Tree size cannot be negative");
//           }
//         Node root=null;
//         if (N!=0) {
//         root = new Node(N);
//         Node curr = root;
//         for (int i = N-1; i >0; i--) {
//             curr.left = new  Node (i);
//             curr = curr.left;
//         }
//        }
//         return root;
//     }

//     public static double timeTrial(int N) {
//         // measure the amount of time taken to check isBST for a BST with N nodes
		
// 		int T = 10; // number of tests
// 		double sum = 0;
// 		for (int t = 0; t < T; t++) {
//             Node r = create(N);
// 			Stopwatch s = new Stopwatch();
// 			r.isBST();
// 			sum +=  s.elapsedTime();
// 		}
// 		return sum/T;
// 	}
        
// }



// public class Tree_1 {

//     private static final int MIN = 64;
	
// 	//private static final int MAX = 32768000;
// 	// private static final int MAX = 1024000;
// 	 private static final int MAX = 64000;
// 	public static void main(String[] args) {

//         Node r = Node.create(2);
//         int x = Node.gmax(r);
//         int y = Node.gmin(r);

//         r.isBST();


// 		double prev = Node.timeTrial(MIN);
// 		for (int N = MIN*2; N<=MAX; N += N) {
// 			double time = Node.timeTrial(N);
// 			StdOut.format("%,13d %10.3f %10.3f\n", N, time, time/prev);
// 			prev = time;
// 		}
// 	}


//     public static void main1(String[] args){
//         Node root = new Node(5, new Node(3), new Node(7));
//         root.printInorder();
      
        
        
//     }
    
// }
