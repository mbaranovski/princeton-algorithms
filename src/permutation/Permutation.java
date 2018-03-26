package permutation;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);      // k

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        if (k > queue.size()) throw new IndexOutOfBoundsException();

        Iterator<String> it = queue.iterator();

        for (int i = 0; i < k; i++) {
            System.out.println(it.next());
        }
    }
}
