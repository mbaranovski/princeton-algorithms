package permutation;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first = null;
    private Node last = null;

    private class Node {
        private final Item item;
        private Node next = null;
        private Node previous = null;

        private Node(Item item) {
            this.item = item;
        }

    }

    public Deque() {

    }                         // construct an empty deque

    public boolean isEmpty() {
        return this.size() == 0;
    }              // is the deque empty?

    public int size() {
        return this.size;
    }                 // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();

        Node newfirst = new Node(item);

        if (this.isEmpty()) {
            this.first = newfirst;
            this.last = this.first;
        } else {
            Node oldFirst = this.first;

            this.first = newfirst;
            oldFirst.next = this.first;
            this.first.previous = oldFirst;
        }

        this.size++;
    }        // add the item to the front

    public void addLast(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();

        Node last = new Node(item);

        if (this.isEmpty()) {
            this.last = last;
            this.first = this.last;
        } else {
            Node oldLast = this.last;

            this.last = last;
            oldLast.previous = this.last; // reference to previous
            this.last.next = oldLast;
        }

        this.size++;
    }         // add the item to the end

    public Item removeFirst() {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();

        Node oldFirst = this.first;
        this.first = this.first.previous;
        if (this.first != null) {
            this.first.next = null;
        } else {
            this.last = this.first;
        }

        this.size--;
        return oldFirst.item;
    }          // remove and return the item from the front

    public Item removeLast() {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();

        Node oldLast = this.last;
        this.last = this.last.next;
        if (this.last != null) {
            this.last.previous = null;
        } else {
            this.first = this.last;
        }

        this.size--;

        return oldLast.item;
    }           // remove and return the item from the end

    public Iterator<Item> iterator() {
        Iterator<Item> it = new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (current == null) throw new java.util.NoSuchElementException();
                Item item = current.item;
                current = current.previous;

                return item;
            }

            public void remove() {
                throw new java.lang.UnsupportedOperationException();
            }
        };

        return it;
    }        // return an iterator over items in order from front to end

    public static void main(String[] args) {

/*        Deque<Integer> deque = new Deque<>();

        deque.addLast(1);
        deque.removeFirst();
        System.out.println(deque.size());

        Iterator<Integer> it = deque.iterator();

        System.out.println(it.next());*/
        //   System.out.println(deque.size());


      /*  Iterator it = deck.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());*/

    }  // unit testing (optional)
}
