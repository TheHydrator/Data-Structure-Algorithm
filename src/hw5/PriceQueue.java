/***************************************************************
 * Below is the Queue code from the textbook, but modified to be
 * a queue of Price objects rather than a generic queue.
 ****************************************************************/

package hw5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class PriceQueue implements Iterable<Price> {
    private Node first;    // beginning of queue
    private Node last;     // end of queue
    private int n;         // number of elements on queue
    private TreeMap<String, Node> map;  // maps a price to its node in the queue
    
    // TODO - Add a TreeMap that maps a price to the node before that price in the queue
    //        and maps the first price (nothing before it) to null.  You will need to
    //        maintain this invariant on the TreeMap.
    //
    // NOTE1: You will use the TreeMap to improve the running time of enqueue and delete
    //        so that they run in logarithmic time.
    //
    // NOTE2: Because you add a new field (the TreeMap), you need to consider how ALL
    //        the methods in the PriceQueue class might need to change in order to 
    //        correctly maintain the invariants on the TreeMap.

    // helper linked list class // changed this helper class to include a prev field
    private static class Node {
        private Price price;
        private Node next;
        private Node prev; // Add prev to easily remove nodes
    }

    /**
     * Initializes an empty queue. // changed this constructor to initialize the TreeMap
     */
    public PriceQueue() {
        first = null;
        last = null;
        n = 0;
        map = new TreeMap<>();
        map.put("", first);
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of Prices in this queue.
     *
     * @return the number of Prices in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the Price least recently added to this queue.
     *
     * @return the Price least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Price peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.price;
    }

    /**
     * Adds a Price to the front of the queue if it is not already present
     * in the queue.
     * 
     * @param price the Price to be added
     * @return {@code true} if the Price was added and {@code false} if it was
     *         not added (it was already present in the queue).
     */
    public boolean enqueue(Price price) { // changed this method to use the TreeMap to improve running time.
        String priceString = price.toString();
        if (map.containsKey(priceString)) {
            return false; // Price is already present in the queue
        }
        Node newNode = new Node();
        newNode.price = price;
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        map.put(priceString, newNode);
        n++;
        return true;
    }


    /**
     * Removes and returns the Price in this queue that was least recently added.
     *
     * @return the Price on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Price dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Price price = first.price;
        if (first.next != null) {
            first.next.prev = null;
        } else {
            last = null; // The queue is now empty
        }
        first = first.next;
        map.remove(price.toString());
        n--;
        return price;
    }
    
    
    /**
     * Deletes a Price from the queue if it was present.
     * 
     * @param price the Price to be deleted.
     * @return {@code true} if the Price was deleted and {@code false} otherwise
     */
    public boolean delete(Price price) { // changed this method to use the TreeMap to improve running time.
        Node nodeToDelete = map.get(price.toString());
        if (nodeToDelete == null) {
            return false; // Price not found
        }
        if (nodeToDelete.prev != null) {
            nodeToDelete.prev.next = nodeToDelete.next;
        } else {
            first = nodeToDelete.next; // The node was the first node
        }
        if (nodeToDelete.next != null) {
            nodeToDelete.next.prev = nodeToDelete.prev;
        } else {
            last = nodeToDelete.prev; // The node was the last node
        }
        map.remove(price.toString());
        n--;
        return true;
    }


    /**
     * Returns an iterator that iterates over the Prices in this queue in FIFO order.
     *
     * @return an iterator that iterates over the Prices in this queue in FIFO order
     */
    public Iterator<Price> iterator()  {
        return new PriceListIterator(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class PriceListIterator implements Iterator<Price> {
        private Node current;

        public PriceListIterator(Node first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Price next() {
            if (!hasNext()) throw new NoSuchElementException();
            Price price = current.price;
            current = current.next; 
            return price;
        }
    }
}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
