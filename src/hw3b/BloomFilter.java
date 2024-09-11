package hw3b;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {
    private BitSet bitSet;
    private int size;
    private int numHashFunctions;

    public BloomFilter(int size, int numHashFunctions) {
        this.size = size;
        this.numHashFunctions = numHashFunctions;
        this.bitSet = new BitSet(size);
    }

    public void add(int x) {
        for (int i = 0; i < numHashFunctions; i++) {
            int hash = hash(x, i);
            bitSet.set(hash % size);
        }
    }

    public boolean contains(int x) {
        for (int i = 0; i < numHashFunctions; i++) {
            int hash = hash(x, i);
            if (!bitSet.get(hash % size)) {
                return false;
            }
        }
        return true;
    }

private int hash(int x, int salt) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(String.valueOf(x + salt).getBytes());
        int result = 0;
        for (byte b : bytes) {
            result += b;
        }
        // Apply bitmask to use only the last few bits
        return Math.abs(result) & (0xFF);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return -1;
    }
}
}
