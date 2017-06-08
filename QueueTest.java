/**
 * Created by Ward Bradt on 4/9/17.
 * Revised on 06/08/17.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    @Test
    public void testIsEmpty() {
        Queue<String> emptyTester = new Queue<>("foobar");
        assertFalse(emptyTester.isEmpty());
        emptyTester.iterator().next();
        assertTrue(emptyTester.isEmpty());
    }

    @Test
    public void testNext() {
        Queue<String> popTester = new Queue<>("foobar");
        assertEquals("foobar", popTester.iterator().next());
        assertTrue(popTester.isEmpty());
        popTester.add("foobar2");
        popTester.add("foobar3");
        popTester.add("foobar4");
        assertNotEquals("foobar3", popTester.iterator().next());
        assertEquals("foobar3", popTester.iterator().next());
    }

    @Test
    public void testAdd() {
        Queue<String> addTester = new Queue<>("foobar");
        assertTrue(addTester.add("foobar2"));
        assertEquals("foobar", addTester.iterator().next());
        assertNotEquals("foobar", addTester.peek());
        assertEquals("foobar2", addTester.peek());
    }

    @Test
    public void testIterator(){
        Queue<String> iterTester = new Queue<>("foobar");
        iterTester.add("foobar1");
        iterTester.add("foobar2");
        iterTester.add("foobar3");
        String[] test = new String[4];
        int count = 0;
        for (String i : iterTester) {
            test[count] = i;
            count++;
        }
        assertEquals("foobar", test[0]);
        assertEquals("foobar1", test[1]);
        assertEquals("foobar2", test[2]);
        assertEquals("foobar3", test[3]);
    }

    @Test
    public void testSize() {
        Queue<String> sizeTester = new Queue<>();
        sizeTester.add("foobar1");
        sizeTester.add("foobar2");
        sizeTester.add("foobar3");
        assertEquals(3, sizeTester.size());

        sizeTester.iterator().next();
        assertEquals(2, sizeTester.size());

        Queue<String> sizeTester2 = new Queue<>();
        assertEquals(0, sizeTester2.size());
    }
    
    @Test
    public void testAddQueue() {
    	Queue<String> q1 = new Queue<String>();
    	Queue<String> q2 = new Queue<String>();
    	q1.add("jimmy");
    	q1.add("jimmy2");
    	q2.add("foobar");
    	q1.addQueue(q2);
    	q1.iterator().next();
    	q1.iterator().next();
    	assertTrue(q1.iterator().next().equals("foobar"));
    }
}
