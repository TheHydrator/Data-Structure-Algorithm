package hw3b;

import java.util.Set;
import java.util.TreeSet;



interface Mset{
    void add(int x);
    boolean contains(int x);
}
public class hw5 {
    public static void main(String[] args) {
        Stopwatch sw;
        int n = 50000;
        int size = (int) Math.ceil(n * 8);
        int hash = (int) Math.ceil(0.7 * size / n);

        Mset a1 = new A1();
        Mset a2 = new A2();
        Mset a3 = new A3();
        Mset a4 = new A4(size, hash);
        sw = new Stopwatch();
        for (int i = 0; i < n; i+=2) {
            a1.add(i);
             
        }

        
        System.out.println(sw.elapsedTime());
        sw = new Stopwatch();
        for (int i = 0; i < n; i+=2) {
            a2.add(i);
        }
        System.out.println(sw.elapsedTime());
        sw = new Stopwatch();

        for (int i = 0; i < n; i+=2) {
            a3.add(i);
        }
        System.out.println(sw.elapsedTime());
        sw = new Stopwatch();
        for (int i = 0; i < n; i+=2) {
            a4.add(i);
        }
        System.out.println(sw.elapsedTime());



        sw = new Stopwatch();
        for (int i = 0; i < n; i++) {
            a1.contains(i);
        
        }
        System.out.println("Checking time by Sequentialsearch: "+ sw.elapsedTime());
        

        sw = new Stopwatch();
        for (int i = 0; i < n; i++) {
            a2.contains(i);
        
        }
        System.out.println("Checking time by TreeSet: "+ sw.elapsedTime());
       
        sw = new Stopwatch();
        for (int i = 0; i < n; i++) {
            a3.contains(i);
        
        }
        System.out.println("Checking time by HashSet: "+ sw.elapsedTime());

        sw = new Stopwatch();
        for (int i = 0; i < n; i++) {
            a4.contains(i);
        
        }
        System.out.println("Checking time by BloomFilter: "+ sw.elapsedTime());

        //even number
        sw = new Stopwatch();
        for (int i = 0; i < n; i+=2) {
            a1.contains(i);
        
        }
        System.out.println("Even number by Sequentialsearch: "+ sw.elapsedTime());
        

        sw = new Stopwatch();
        for (int i = 0; i < n; i+=2) {
            a2.contains(i);
        
        }
        System.out.println("Even number by TreeSet: "+ sw.elapsedTime());
       
        sw = new Stopwatch();
        for (int i = 0; i < n; i+=2) {
            a3.contains(i);
        
        }
        System.out.println("Even number by HashSet: "+ sw.elapsedTime());

        sw = new Stopwatch();
        for (int i = 0; i < n; i+=2) {
            a4.contains(i);
        }
        System.out.println("Even number by BloomFilter: "+ sw.elapsedTime());


        // odd number
        sw = new Stopwatch();
        for (int i = 1; i < n; i+=2) {
            a1.contains(i);
        
        }
        System.out.println("Odd number by Sequentialsearch: "+ sw.elapsedTime());
        

        sw = new Stopwatch();
        for (int i = 1; i < n; i+=2) {
            a2.contains(i);
        
        }
        System.out.println("Odd number by TreeSet: "+ sw.elapsedTime());
       
        sw = new Stopwatch();
        for (int i = 1; i < n; i+=2) {
            a3.contains(i);
        
        }
        System.out.println("Odd number by HashSet: "+ sw.elapsedTime());

        sw = new Stopwatch();
        for (int i = 1; i < n; i+=2) {
            a4.contains(i);
        }
        System.out.println("Odd number by BloomFilter: "+ sw.elapsedTime());


        

        // for (int i = 0; i < 1000000; i++) {
        //     if (!a1.contains(i)) {
        //         System.out.println("a1 missing " + i);
        //     }
        //     if (!a2.contains(i)) {
        //         System.out.println("a2 missing " + i);
        //     }
        //     if (!a3.contains(i)) {
        //         System.out.println("a3 missing " + i);
        //     }
        // }
        // System.out.println("Time: " + sw.elapsedTime());
    }
}

    
class A1 implements Mset{
    private SequentialSearchST<Integer, Integer> st = new SequentialSearchST<>();

    @Override
    public void add(int x) {
        st.put(x, 0);
    }

    @Override
    public boolean contains(int x) {
        return st.contains(x);
    }
    
}
class A2 implements Mset{
    private TreeSet<Integer> st = new TreeSet<>();
    @Override
    public void add(int x) {
        st.add(x);
    }

    @Override
    public boolean contains(int x) {
        return st.contains(x);
    }
    
}
class A3 implements Mset{
    private Set<Integer> st = new TreeSet<>();
    @Override
    public void add(int x) {
        st.add(x);
    }
    @Override
    public boolean contains(int x) {
        return st.contains(x);
    }
    
}
class A4 implements Mset {
    private BloomFilter st;

    public A4(int n, int numHashFunctions) {
        st = new BloomFilter(n, numHashFunctions);
    }
    
    @Override
    public void add(int x) {
        st.add(x);
    }

    @Override
    public boolean contains(int x) {
        return st.contains(x);
    }
}

class Stopwatch {

	private final long start;

	/**
	 * Create a stopwatch object.
	 */
	public Stopwatch() {
		start = System.currentTimeMillis();
	}


	/**
	 * Return elapsed time (in seconds) since this object was created.
	 */
	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}

}

