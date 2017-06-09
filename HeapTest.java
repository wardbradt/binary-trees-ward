import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Ward Bradt on 4/9/17.
 * Revised on 06/08/17.
 */
public class HeapTest {
    @Test
    public void testAdd() {
        Heap<Integer> test = new Heap<>(3);
        assertTrue(test.contains(3));
        assertTrue(test.add(4));
        assertTrue(test.add(5));
        assertTrue(test.contains(5));
        assertTrue(test.contains(4));
        assertTrue(test.contains(5));
        // Test that we can have duplicates
        assertTrue(test.add(4));
    }

    @Test
    public void testSize() {
        Heap<Integer> test = new Heap<>(3);
        assertEquals(1, test.size());

        test.add(4);
        assertEquals(2, test.size());
        test.add(4);
        assertEquals(3, test.size());
    }

    @Test
    public void testContains() {
        Heap<Integer> test = new Heap<>(3);
        assertTrue(test.contains(3));
        assertTrue(test.add(4));
        assertTrue(test.add(5));
        assertFalse(test.contains(2));
        assertTrue(test.contains(5));
        assertTrue(test.contains(4));
        assertTrue(test.contains(5));

        test.clear();
        test.add(20);
        test.add(5);
        test.add(15);
        test.add(8);
        assertTrue(test.contains(8));
        assertFalse(test.contains(4));
    }

    @Test
    public void testExtractMax() {
        Heap<Integer> extTester = new Heap<>();
        extTester.add(12);
        extTester.add(4);
        extTester.add(3);
        extTester.add(6);
        extTester.add(34);
        extTester.add(32);
        assertEquals(new Integer(34), extTester.extractMax());
        assertNotEquals(new Integer(34), extTester.extractMax());
        assertEquals(new Integer(32), extTester.getContents());

        Queue<Integer> depthQueue = extTester.iterate();
        Integer previous = depthQueue.peek();
        // Check that they are still in descending order
        for (Integer i : depthQueue) {
            assertTrue(i.compareTo(previous) <= 0);
            previous = i;
        }

        extTester.clear();
        assertTrue(extTester.empty());
        // Check that when extractMax() is called on a null/empty heap it returns null.
        assertTrue(extTester.extractMax() == null);
    }

    @Test
    public void testDepth(){
        Heap<Integer> depthTester = new Heap<>();
        depthTester.add(1);
        depthTester.add(2);
        depthTester.add(10);
        depthTester.add(45);
        depthTester.add(23);
        depthTester.add(8);
        depthTester.add(7);
        depthTester.add(12);

        Queue<Integer> depthQueue = depthTester.iterate();
        // So that previous is one bigger than the first element in this Queue, add 1.
        Integer previous = depthQueue.peek() + 1;
        // Check that they are in descending order
        while (depthQueue.iterator().hasNext()) {
            assertTrue(depthQueue.peek().compareTo(previous) < 0);
            previous = depthQueue.iterator().next();
        }

        Heap<String> depthTester2 = new Heap<>();
        depthTester2.add("foobar8");
        depthTester2.add("foobar7");
        depthTester2.add("foobar5");
        depthTester2.add("foobar4");
        depthTester2.add("foobar6");
        depthTester2.add("foobar3");

        Queue<String> dQueue = depthTester2.iterate();
        String prev = dQueue.peek();
        dQueue.iterator().next();
        // Check that they are in descending order
        while (dQueue.iterator().hasNext()) {
            assertTrue(dQueue.peek().compareTo(prev) <= 0);
            prev = dQueue.iterator().next();
        }
    }
}
