import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jakob on 04-10-2016.
 */
public class myLinkedListTest {

    myLinkedList<Integer> list = new myLinkedList<Integer>();
    @Before
    public void setUp() throws Exception {
    list.add(1);
        list.add(2);
    }

    @Test
    public void clear() throws Exception {
list.clear();int i = list.size();
        assertEquals(i, 0);
    }

    @Test
    public void size() throws Exception {
    int i = list.size();
        assertEquals(i, 2);
    }

    @Test
    public void isEmpty() throws Exception {

        list.clear();
        boolean i= list.isEmpty();

        assertEquals(i , true);
    }

    @Test
    public void add() throws Exception {
        list.add(3);
        int i= list.get(2);

        assertEquals(i , 3);

    }

    @Test
    public void get() throws Exception {
    int i = list.get(1);
        assertEquals(i, 2);
    }

    @Test
    public void set() throws Exception {
    list.set(1, 3);
        int i = list.get(1);
        assertEquals(i, 3);


    }

    @Test
    public void remove() throws Exception {

        myLinkedList<Integer> list = new myLinkedList<Integer>();
        list.add(1);
        list.add(2);
        int i= 0;
        list.remove(1);
        i=list.size();


        assertEquals(i , 1);
    }

    @Test
    public void iterator() throws Exception {

    }

}