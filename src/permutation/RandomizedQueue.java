package permutation;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int n;

    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }              // construct an empty randomized queue

    public boolean isEmpty() {
        return n == 0;
    }              // is the randomized queue empty?

    public int size() {
        return n;
    }              // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (a.length == n) {
            resize(a.length * 2);
        }

        a[n++] = item;
    }        // add the item

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");

        int randomIndex = StdRandom.uniform(n);
        Item lastItem = a[n - 1];
        Item item = a[randomIndex];
        a[n - 1] = null;                              // to avoid loitering
        a[randomIndex] = lastItem;                    // to avoid nulls inside array
        n--;

        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4) resize(a.length / 2);
        return item;
    }               // remove and return a random item

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");

        return a[StdRandom.uniform(n)];
    }               // return a random item (but do not remove it)

    public Iterator<Item> iterator() {

        Item[] iterableArray = cloneArray();
        StdRandom.shuffle(iterableArray, 0, n);

        Iterator<Item> it = new Iterator<Item>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < n;
            }

            @Override
            public Item next() {
                if (!this.hasNext()) throw new NoSuchElementException();
                return iterableArray[i++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        return it;
    }        // return an independent iterator over items in random order

    private void resize(int capacity) {
        assert capacity >= n;

        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    private Item[] cloneArray() {
        Item[] cloned = (Item[]) new Object[n];
        for (int i = 0; i < n; i++) {
            cloned[i] = a[i];
        }
        return cloned;
    }

    public static void main(String[] args) {

/*        RandomizedQueue queue = new RandomizedQueue();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);



        Iterator it = queue.iterator();
        Iterator it2 = queue.iterator();

        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());

        System.out.println("---");

        System.out.println(it2.next());
        System.out.println(it2.next());
        System.out.println(it2.next());*/

    }  // unit testing (optional)
}
