import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryTreeTest {
    @Test
    public void testEmpty() {
      BinaryTree<Integer> test = new BinaryTree<Integer>();
      assertTrue(test.empty());
      test.add(new Integer(0));
      assertFalse(test.empty());
    }
  
    @Test
    public void testAdd() {
	  BinaryTree<String> test = new BinaryTree<String>();
	  assertTrue(test.add("foobar"));
	  test.add("foobar2");
	  test.add("foobar3");
	  test.add("foobar4");
	  test.add("foobar5");
	  test.add("foobar6");
	  assertEquals("foobar5", test.getRoot().getLeft().getRight().getContents());
	  assertTrue(test.contains("foobar"));
	  assertTrue(test.contains("foobar2"));
    }
    
    @Test
    public void testAddLeft() {
    	BinaryTree<Integer> test = new BinaryTree<Integer>();
    	Integer testInt = 0;
    	Integer testInt2 = 10;
    	test.add(testInt);
    	test.addLeft(test.getRoot(), testInt2);
    	assertTrue(test.getRoot().getLeft().getContents().equals(new Integer (10)));
    	
    }

    @Test
    public void testRemove() {
        BinaryTree<String> removeTester = new BinaryTree<>();
        removeTester.add("foobar");
        assertTrue(removeTester.contains("foobar"));

        removeTester.remove("foobar");
        try {
            removeTester.remove("foobar");
            assertTrue(false);
        } catch (NullPointerException expected) {
        }

        removeTester.add("foobar2");
        try {
            removeTester.remove("foobar");
            assertTrue(false);
        } catch (NullPointerException expected) {
        }

        // Test if it removes the first breadth first traversal.
        removeTester.clear();
        assertTrue(removeTester.empty());
        removeTester.add("foobar");
        removeTester.add("foobarB");
        removeTester.add("foobarC");
        removeTester.add("foobarD");
        removeTester.add("foobarE");
        removeTester.add("foobarF");
        removeTester.add("foobarG");
        removeTester.add("foobarH");
        removeTester.remove("foobarG");

        // The array is of length 7.
        String[] testArr = new String[removeTester.breadthFirst().size()];
        int count = 0;
        for (String i : removeTester.breadthFirst()) {
            testArr[count] = i;
            count++;
        }
        assertNotEquals("foobar", testArr[1]);
        assertNotEquals("foobarG", testArr[6]);
        assertEquals("foobarD", testArr[3]);

        removeTester.remove("foobarD");
        testArr = new String[removeTester.breadthFirst().size()];
        count = 0;
        for (String i : removeTester.breadthFirst()) {
            testArr[count] = i;
            count++;
        }
        assertNotEquals("foobarD", testArr[3]);
    }
    
    @Test
    public void testAddRight() {
    	BinaryTree<Integer> test = new BinaryTree<Integer>();
    	Integer testInt = new Integer(0);
    	Integer testInt2 = new Integer(10);
    	test.add(testInt);
    	test.addRight(test.getRoot(), testInt2);
    	assertTrue(test.getRoot().getRight().getContents().equals(new Integer (10)));
    }
  
  @Test
  public void testClear() {
	  BinaryTree<String> test = new BinaryTree<String>();
	  test.add("foobar");
	  test.add("foobar2");
	  test.addLeft(test.getRoot().getLeft(), "foobar3");
	  assertFalse(test.empty());
	  test.clear();

	  try {
	      test.get("foobar3");
	      assertTrue(false);
      } catch (NullPointerException expected) {
      }

	  assertTrue(test.empty());
  }

    @Test
    public void testContains() {
      BinaryTree<String> containsTester = new BinaryTree<String>();
      containsTester.add("Hello");
      assertTrue(containsTester.contains("Hello"));

      assertFalse(containsTester.contains("hi"));
      containsTester.addLeft(containsTester.getRoot(), "hi");
      assertTrue(containsTester.contains("hi"));
    }

    @Test
    public void testGet() {
        BinaryTree<String> getTester = new BinaryTree<>();
        getTester.add("Hello world!");
        try {
            getTester.get("Hello");
            assertTrue(false);
        } catch (NullPointerException expected){
        }
        // getTester.remove("Hello world!");
        try {
            getTester.get("Hello world!");
        } catch (NullPointerException expected) {
        }
    }

    @Test
    public void testBreadthFirst() {
        BinaryTree<String> breadthTester = new BinaryTree<String>();
        // add method needs to work before this can work.
        breadthTester.add("foobar");
        breadthTester.add("foobar2");
        breadthTester.add("foobar3");
        breadthTester.add("foobar4");
        breadthTester.add("foobar5");
        assertEquals("foobar5", breadthTester.getRoot().getLeft().getRight().getContents());

        String[] str = new String[5];
        int count = 0;
        for (String i : breadthTester.breadthFirst()) {
            str[count] = i;
            count++;
        }

        assertEquals("foobar5", breadthTester.getRoot().getLeft().getRight().getContents());
        assertEquals("foobar", str[0]);
        assertEquals("foobar2", str[1]);
        assertNotEquals("foobar3", str[1]);
        assertEquals("foobar3", str[2]);
        assertNotEquals("foobar", str[4]);
        assertEquals("foobar4", str[3]);
    }

    @Test
    public void testDepthFirst() {
       	BinaryTree<String> depthTester = new BinaryTree<String>();
       	depthTester.add("foobar");
       	depthTester.add("foobar2");
       	depthTester.add("foobar3");
        depthTester.add("foobar4");
        depthTester.add("foobar5");
        depthTester.add("foobar6");
        depthTester.add("foobar7");
        depthTester.add("foobar8");

        String[] str = new String[10];
        int count = 0;
        for (String i : depthTester.depthFirst()) {
            str[count] = i;
            count++;
           }
        assertEquals("foobar", str[0]);
        assertEquals("foobar2", str[1]);
        assertEquals("foobar4", str[2]);
        assertEquals("foobar8", str[3]);
        assertEquals("foobar5", str[4]);
        assertEquals("foobar3", str[5]);
        assertEquals("foobar6", str[6]);
        assertEquals("foobar7", str[7]);
    }
}