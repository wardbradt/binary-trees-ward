/**
 * An object of type `Queue` follows a "first-in first-out" convention:
 * The element that has been in the <code>Queue</code> for the longest duration will be at the front of the
 * <code>Queue</code> and the newest element will be at the back.
 * Each `Queue` holds a reference to a `Queue` object `next`, thus providing the connections for this data structure.
 *
 * Created by Ward Bradt on 04/06/17.
 * Revised on 06/08/17.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {
    private T contents;
    private Queue<T> next;

    public Queue() {
        contents = null;
        next = null;
    }

    public Queue(T object) {
        contents = object;
        next = null;
    }
    
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return contents != null;
            }

            /**
             * Get the element at the front of the <code>Queue</code>, and remove it from the <code>Queue</code>.
             *
             * @return the front element of the <code>Queue</code>
             */
            @Override
            public T next() {
                if (contents == null) throw new NoSuchElementException("No more elements!");
                T popped = contents;
                if (next == null) {
                    contents = null;
                } else {
                    contents = next.contents;
                    next = next.next;
                }
                return popped;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    /**
     * Gets the number of <code>Node</code>s, or elements, in the <code>Queue</code>
     *
     * @return the number of <code>Node</code>s in the <code>Queue</code>
     */
    public int size() {
        // start at 1, because the host object will always be a node
        int nodes = 0;
        Queue<T> currentNode = this;
        // when there is a next node...
        while (currentNode != null && currentNode.peek() != null) {
            // increment the nodes...
            nodes++;
            // move to the next node — NOTE: this will never get 'null' because the 'while' loop does a check
            currentNode = currentNode.getNext();
        }
        return nodes;
    }

    /**
     * Tests if the <code>Queue</code> has elements.
     *
     * @return if the <code>Queue</code> is empty.
     */
    public boolean isEmpty() {
        return contents == null && next == null;
    }

    /**
     * Add <code>T o</code> to the end of the <code>Queue</code>.
     *
     * @param o the <code>T object</code> to be added to the <code>Queue</code>
     * @return if <code>o</code> was added
     */
    public boolean add(T o) {
        /*
         * The following is done when there is no next
         * element, and the the data is null. This situation
         * occurs after .clear()/
         */
        if (contents == null && next == null) {
            contents = o;
            return true;
        }

        Queue<T> nextNode = this;
        /*
         * We start at 'next' because there is no scenario in which
         * the first element is empty (it is either 'null' or it
         * has data — that is, if you don't count 'null' as data!)
         */
        while (nextNode.getNext() != null) {
            nextNode = nextNode.getNext();
        }
        nextNode.setNext(new Queue<T>(o));
        return true;
    }

    /**
     * Get the element at the front of the <code>Queue</code> without removing it.
     * A replacement for a <code>getContents()</code>
     *
     * @return the front element of the <code>Queue</code>
     */
    public T peek() {
        return contents;
    }

    public Queue<T> getNext() {
        return next;
    }

    /**
     * Set the next <code>Node</code> in the queue.
     * <b>Warning:</b>This will, if used improperly, remove all future elements!
     *
     * @param next the <code>Node</code> to set as the next node in the <code>Queue</code>
     */
    private void setNext(Queue<T> next) {
        this.next = next;
    }
    
    /**
     * Append a <code>Queue</code> to the end of another <code>Queue</code>.
     *
     * @param other the <code>Queue</code> to append at the end of the original <code>Queue</code>.
     */
    public boolean addQueue(Queue<T> other) {
    	while (other.contents != null)
    		this.add(other.iterator().next());
    	return true;
    }
}
