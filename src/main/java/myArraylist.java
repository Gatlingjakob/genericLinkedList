import java.util.*;

/* Models a list of objects using an array as 
   the underlying implementation. */
public class myArraylist {
    private Object[] myElements;
    private int mySize;

    public void MyArrayList() {
        myElements = new Object[10];
        mySize = 0;
    }

    // adds given element at the end
    public void add(Object element) {
        add(size(), element);
    }

    // adds given element at the given index
    public void add(int index, Object element) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        if (size() == myElements.length) {
            resize();
        }

        // slide
        for (int i = size(); i > index; i--)
            myElements[i] = myElements[i - 1];

        // add element
        myElements[index] = element;

        mySize++;
    }

    // removes all elements
    public void clear() {
        mySize = 0;
    }

    // returns true if the list contains this integer
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    // returns the element at the given index;
    // throws IndexOutOfBoundsException if index is bad
    public Object get(int index) {
        checkIndex(index);

        return myElements[index];
    }

    // returns the first index where the given element
    // is found in this list, if present;
    // returns -1 if not found
    public int indexOf(Object element) {
        for (int i = 0; i < size(); i++) {
            if ((element == null && get(i) == null) ||
                    (element != null && get(i).equals(element)))
                return i;
        }

        return -1;
    }

    // returns true if there are no elements in the list
    public boolean isEmpty() {
        return mySize == 0;
    }

    public Iterator iterator() {
        return listIterator();
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }

    public ListIterator listIterator(int index) {
        return new MyArrayListIterator(index);
    }

    // removes and returns the element at the given index;
    // throws IndexOutOfBoundsException if index is bad
    public void remove(int index) {
        checkIndex(index);

        // slide
        for (int i = index; i < size() - 1; i++)
            myElements[i] = myElements[i + 1];

        mySize--;
    }

    // sets the given index to store the given element value;
    // throws IndexOutOfBoundsException if index is bad
    public Object set(int index, Object element) {
        checkIndex(index);

        myElements[index] = element;
        return get(index);
    }

    // returns the number of elements in this list
    public int size() {
        return mySize;
    }

    // returns this list as an array of objects
    public Object[] toArray() {
        Object[] array = new Object[size()];
        System.arraycopy(myElements, 0, array, 0, size());
        return array;
    }

    // returns this list as a string, such as "[12, 5, -7, 3]"
    public String toString() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            result += myElements[i];
            if (i < size() - 1)
                result += ", ";
        }

        return "[" + result + "]";
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
    }

    private void resize() {
        Object[] newArray = new Object[size() * 2];
        System.arraycopy(myElements, 0, newArray, 0, size());
        myElements = newArray;
    }

    private class MyArrayListIterator implements ListIterator {
        private int myIndex;
        private int myLastIndexReturned = -1;

        public MyArrayListIterator(int index) {
            myIndex = index;
        }

        public void add(Object o) {
            myArraylist.this.add(myIndex, o);
            myIndex++;  // adding shifts our current position forward by 1
            myLastIndexReturned = -1;
        }

        public boolean hasNext() {
            return myIndex < size();
        }

        public boolean hasPrevious() {
            return myIndex > 0;
        }

        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();

            myLastIndexReturned = myIndex;
            Object result = myArraylist.this.get(myIndex);
            myIndex++;
            return result;
        }

        public int nextIndex() {
            return myIndex;
        }

        public Object previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();

            myIndex--;
            myLastIndexReturned = myIndex;
            Object result = myArraylist.this.get(myIndex);
            return result;
        }

        public int previousIndex() {
            return myIndex - 1;
        }

        public void remove() {
            if (myLastIndexReturned < 0)
                throw new IllegalStateException();

            myArraylist.this.remove(myLastIndexReturned);

            // removal might have shifted our current index
            if (myLastIndexReturned < myIndex)
                myIndex--;

            myLastIndexReturned = -1;
        }

        public void set(Object o) {
            if (myLastIndexReturned < 0)
                throw new IllegalStateException();

            myArraylist.this.set(myLastIndexReturned, o);
        }
    }
}