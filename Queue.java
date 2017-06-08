/**
 * Created by wardbradt on 4/6/17.
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
             * Get the element at the front of the queue, and remove it from the queue.
             *
             * @return the top element of the stack
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
     * Get the number of nodes in the queue.
     *
     * @return the number of nodes in the queue
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
     * Tests if the Queue has no elements.
     *
     * @return if the queue is empty.
     */
    public boolean isEmpty() {
        return contents == null && next == null;
    }

    /**
     * Add T o to the end of the queue.
     *
     * @param o the T object to be added to the queue
     * @return true if o is added.
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
     * Get the element at the front of the queue, without removing it.
     *
     * @return the front element of the stack
     */
    public T peek() {
        return contents;
    }

    /**
     * Get the next node in the queue.
     *
     * @return the next node in the queue.
     */
    public Queue<T> getNext() {
        return next;
    }

    public T getContents() {
        return contents;
    }

    /**
     * Set the next node in the queue.
     * <i>This will, if used improperly, remove all future elements!</i>
     *
     * @param next the node to set as the next node in the queue.
     */
    private void setNext(Queue<T> next) {
        this.next = next;
    }
    
    /**
     * Append a queue to the end of another queue.
     *
     * @param other is the queue to append at the end of the original queue.
     */
    public boolean addQueue(Queue<T> other) {
    	while (other.contents != null)
    		this.add(other.iterator().next());
    	return true;
    }
}
