import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Ward Bradt and Jimmy Pham on 4/9/17.
 */
public class SetTest {
    @Test
    public void testEmpty(){
        Set<String> test = new Set<>();
        assertTrue(test.empty());

        test = new Set<>();
        test.add("foobar");
        assertFalse(test.empty());
    }

    @Test
    public void testClear() {
        Set<String> test = new Set<>();
        test.add("foobar");
        assertFalse(test.empty());
        test.clear();
        assertTrue(test.empty());
    }

    @Test
    public void testAdd() {
        Set<String> test = new Set<>();
        assertTrue(test.add("foobar"));
        assertFalse(test.add("foobar"));
        test.remove("foobar");
        assertTrue(test.add("foobar"));
        assertTrue(test.add("foobar2"));
        assertTrue(test.contains("foobar2"));
    }

    @Test
    public void testContains() {
        Set<String> test = new Set<>();
        test.add("foobar");
        assertTrue(test.contains("foobar"));
        assertFalse(test.contains("foobar2"));
        test.add("foobar2");
        assertTrue(test.contains("foobar2"));
        test.remove("foobar");
        assertFalse(test.contains("foobar"));
    }

    @Test
    public void testRemove() {
        Set<String> test = new Set<>();
        test.add("foobar");
        test.add("foobar2");
        test.remove("foobar");
    }
    
    @Test
    public void testUnion() {
    	Set<String> test1 = new Set<>();
    	Set<String> test2 = new Set<>();
    	test1.add("foobar");
    	test1.add("foobar2");
    	test2.add("foobar3");
    	test2.add("foobar4");
    	Set<String> test3 = test1.union(test2);
    	assertTrue(test3.contains("foobar"));
    	assertTrue(test3.contains("foobar2"));
    	assertTrue(test3.contains("foobar3"));
    	assertTrue(test3.contains("foobar4"));
    	assertFalse(test3.contains("jimmy"));
    	
    	Set<String> test4 = new Set<String>();
    	test4 = test4.union(test3);
    	assertTrue(test4.contains("foobar"));
    	assertTrue(test4.contains("foobar2"));
    	assertTrue(test4.contains("foobar3"));
    	assertTrue(test4.contains("foobar4"));
    	assertFalse(test4.contains("jimmy"));


    }
    
    @Test
    public void testIntersect() {
    	Set<String> test1 = new Set<>();
    	Set<String> test2 = new Set<>();
    	test1.add("foobar");
    	test1.add("foobar2");
    	test2.add("foobar2");
    	test2.add("foobar4");
    	Set<String> test3 = test1.intersection(test2);
    	assertTrue(test3.contains("foobar2"));
    	assertFalse(test3.contains("foobar"));
    	assertFalse(test3.contains("foobar4"));
    	
    	Set<String> test4 = new Set<String>();
    	
    	Set<String> test5 = test3.intersection(test4);
    	assertTrue(test5.empty());
    	test5 = test4.intersection(test3);
    	assertTrue(test5.empty());

    }
}
