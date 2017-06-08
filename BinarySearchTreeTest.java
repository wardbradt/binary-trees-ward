import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Ward Bradt on 4/9/17.
 * Revised on 06/08/17.
 */
public class BinarySearchTreeTest {
    @Test
    public void testAdd() {
        BinarySearchTree<Integer> addTester = new BinarySearchTree<>();
        addTester.add(20);
        // Check that it adds the first item to root's contents.
        assertTrue(addTester.getRoot().getContents().compareTo(20) == 0);
        addTester.add(23);
        // Check that it adds a larger number to the right, not the left.
        assertTrue(addTester.getRoot().getRight().getContents().compareTo(20) > 0);
        try {
            addTester.getRoot().getLeft().getContents();
            assertTrue(false);
        } catch (NullPointerException expected) {
            // good to go
        }

        addTester.clear();
        // addTester's root is now 10.
        addTester.add(10);
        for (int i = 0; i < 8; i++) {
            addTester.add(i);
        }
        for (int i = 18; i > 12; i--) {
            addTester.add(i);
        }

        Node test = addTester.getRoot();
        while (test != null) {
            if (test.getLeft() != null) {
                assertTrue((int)test.getContents() >= (int)test.getLeft().getContents());
                test = test.getLeft();
            }
            else if (test.getRight() != null) {
                assertTrue((int)test.getContents() <= (int)test.getRight().getContents());
                test = test.getRight();
            }
            else break;
        }
        assertTrue(addTester.getRoot().getLeft().getContents() < addTester.getRoot().getContents());

        addTester.clear();
        addTester.add(4);
        assertTrue(addTester.add(4));
    }

    @Test
    public void testRemoveHelper() {
        BinarySearchTree<Integer> test = new BinarySearchTree<>();
        for (int i = 1; i < 8; i++) {
            test.add(i);
        }
        for (int i = 18; i > 12; i--) {
            test.add(i);
        }
        try {
            test.remove(32);
            assertTrue(false);
        } catch (NullPointerException expected) {
        }

        test.remove(4);
        assertFalse(test.contains(4));
        try {
            test.remove(4);
            assertTrue(false);
        } catch (NullPointerException expected) {
            // good to go
        }

        test.remove(7);
        test.remove(15);
        test.remove(13);
        BinarySearchTree<Integer> temp = new BinarySearchTree<>(test.getRoot());
        while (temp.getRoot().getRight() != null) {
            assertTrue(temp.getRoot().getContents().compareTo(temp.getRoot().getRight().getContents()) <= 0);
            temp = new BinarySearchTree<>(temp.getRoot().getRight());
        }
        temp = new BinarySearchTree<>(test.getRoot());
        while (temp.getRoot().getLeft() != null) {
            assertTrue(temp.getRoot().getContents().compareTo(temp.getRoot().getLeft().getContents()) >= 0);
            temp = new BinarySearchTree<>(temp.getRoot().getLeft());
        }
    }

    @Test
    public void testIt() {
        BinarySearchTree<Integer> test = new BinarySearchTree<>();
        for (int i = 0; i < 8; i++) {
            test.add(i);
        }
        for (int i = 18; i > 12; i--) {
            test.add(i);
        }

        try {
            test.remove(32);
            assertTrue(false);
        } catch (NullPointerException expected) {
        }

        test.remove(4);
        assertFalse(test.contains(4));
        try {
            test.remove(4);
            assertTrue(false);
        } catch (NullPointerException expected) {
            // good to go
        }
        test.remove(7);
        test.remove(15);
        test.remove(13);
    }
    
    @Test
    public void testContain() {
    	BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();
    	test.add(2);
    	test.add(3);
    	test.add(5);
    	assertFalse(test.contains(100));
    	assertTrue(test.contains(2));
    	assertTrue(test.contains(3));
    	assertTrue(test.contains(5));
    }
}
