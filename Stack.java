import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of the 'Stack' data structure.
 * Each Stack stores a reference to a <code>Stack next</code> which represents the next, or above, node
 * <br>
 * Authors: Tony Tan, Jimmy Pham, Ward Bradt, and Miles McCain
 * Date: Created March 28th
 *       Revised June 8, 2017
 * Course: CSC630 Data Structures and Algorithms
 */

public class Stack<T> implements Iterable<T> {
    private T contents;
    private Stack<T> next;

    /**
     * Create a new <code>Stack</code>, with <code>null</code> at the top.
     * <i>Note: a subsequent 'push' will replace the top object 'null'.</i>
     */
    public Stack() {
        contents = null;
        next = null;
    }
    
    public Stack(T item) {
    	contents = item;
    	next = null;
    }

    /**
     * Check whether the <code>Stack</code> is empty.
     *
     * @return if the <code>Stack</code> is empty
     */
    public boolean empty() {
        return contents == null && next == null;
    }

    /**
     * Get the element at the top of the stack, without removing it.
     *
     * @return the top element of the stack
     */
    public T peek() {
        return contents;
    }

    /**
     * Get the element at the top of the stack, and remove it from the stack.
     *
     * @return the top element of the stack
     */
    public T pop() {
        T popped = contents;
        if (next == null) {
            contents = null;
        } else {
            contents = next.contents;
            next = next.next;
        }
        return popped;
    }

    /**
     * Insert the given element at the top of the stack.
     * <i>If there is only one element in the stack, and the
     * top element is null, the pushed element will
     * <strong>replace</strong> null, not add on top of it.
     * As a result, it is impossible to have 'null' as the bottom
     * element in the stack.</i>
     *
     * @param item the element to insert
     */
    public void push(T item) {
        if (contents == null && next == null) {
            contents = item;
            return;
            // if there is no next element and this is null, we insert element here at position 0
        }
        Stack<T> nextItem = new Stack<T>();
        nextItem.contents = contents;
        nextItem.next = next;
        next = nextItem;
        contents = item;
    }

    /**
     * Find the 1-based index of the given element.
     * <i>Returns -1 if the item is not found in the <code>Stack</code>.</i>
     *
     * @param o the element to search for
     * @return the index of the given element
     */
    public int search(T o) {
        Stack<T> i = this;
        int index = 1;
        while (!o.equals(i.contents)) {
            if (i.next == null) {
                return -1;
                // return -1 (not found) if there is no next item in the stack
            }
            index += 1;
            i = i.next;
        }
        return index;
    }

    /**
     * Get the number of elements in the <code>Stack</code>.
     *
     * @return the number of elements in the <code>Stack</code>.
     */
    public int size() {
        int elements = 1;
        // the host method will ALWAYS be an element, even if null
        Stack traverser = this;
        while (traverser.next != null) {
            traverser = traverser.next;
            elements++;
        }
        return elements;
    }

    /**
     * Create an array from the elements of the <code>Stack</code>, with the top element in
     * position 0.
     * <p>
     * <b>Note:</b> this method is not fully optimized, as it requires the entire traversal of the <code>stack</code>
     * twice (once to get the size, and another time to populate the array).
     * <p>
     * Data conventions note: this is not a perfect 'translation,' so to speak, because
     * a <code>Stack</code> is fundamentally different from an array. Nevertheless, an array is
     * much more suitable for native (for (e : q){}) iteration.
     *
     * @return an array version of the <code>Stack</code>'s elements
     */
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Stack<T> traversed = this;
        for (int i = 0; i < array.length; i++) {
            array[i] = traversed.contents;
            traversed = traversed.next;
        }
        return array;
    }

    /**
     * Creates a printable human-readable <code>String</code> which represents the <code>Stack</code>.
     *
     * @return a printable <code>String</code> which represents the <code>Stack</code>
     */
    public String toString() {
        String serialized = "[";
        Stack<T> element = this;
        while (element != null) {
            if (element.contents == null) {
                serialized += "<empty>, ";
            } else {
                serialized += element.contents.toString() + ", ";
            }
            element = element.next;
        }
        serialized = serialized.substring(0, serialized.length() - 2);
        serialized += ']';
        return serialized;
    }
    
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return next.contents != null;
            }

            @Override
            public T next() {
                if (next.contents == null) throw new NoSuchElementException("No more elements!");
                return next.pop();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
