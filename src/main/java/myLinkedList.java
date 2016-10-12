/**
 * Created by Jakob on 13-09-2016.
 */

import java.util.*;

public class myLinkedList<E> implements Iterable<E> {


    public myLinkedList() {
        clear();
    }

    // O(1) //
    public void clear() {
        beginMarker = new Node<E>(null, null, null);
        endMarker = new Node<E>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
    }

    // O(n) //
    public int size() {
        return theSize;
    }

    // O(n) //
    public boolean isEmpty() {
        return size() == 0;
    }

    // O(n) //
    public boolean add(E x) {
        add(size(), x);
        return true;
    }

    // O(n) //
    public void add(int idx, E x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    // O(n) //
    private void addBefore(Node<E> p, E x) {
        Node<E> newNode = new Node<E>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
    }

    // O(n) //
    public E get(int idx) {

        return getNode(idx).data;
    }

    // O(1) //
    public E set(int idx, E newVal) {
        Node<E> p = getNode(idx);
        E oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    // O(n) //
    private Node<E> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    // O(n) //
    private Node<E> getNode(int idx, int lower, int upper) {
        Node<E> p;

        if (idx < lower || idx > upper){
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());
    }

        if(idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        }
        else {
            p = endMarker;
            for(int i = size( ); i > idx; i--)
                p = p.prev;
        }

        return p;
    }
    // O(1) //
    public E remove(int idx) {
        return remove(getNode(idx));
    }
    // O(1) //
    private E remove(Node<E> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;

        return p.data;
    }
    // O(n) //
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");

        for(E x : this)
            builder.append(x + " ");
        builder.append("]");

        return new String(builder);
    }

    // O(n) //
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements java.util.Iterator<E> {

        private Node<E> current = beginMarker.next;
        private boolean okToRemove = false;

        // O(n) //
        public boolean hasNext() {
            return current != endMarker;
        }

        // O(n) //
        public E next() {
            if(!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            E nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        // O(1) //
        public void remove() {
            if(!okToRemove) {
                throw new IllegalStateException();
            }
            myLinkedList.this.remove(current.prev);
            okToRemove = false;
        }
    }

    private static class Node<E>
    {
        public Node( E d, Node<E> p, Node<E> n ) {
            data = d; prev = p; next = n;
        }

        public E data;
        public Node<E> prev;
        public Node<E> next;
    }

    private int theSize;
    private Node<E> beginMarker;
    private Node<E> endMarker;

}