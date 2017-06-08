/**
 * A basic implementation of a LinkedList slightly repurposed to be the superclass of a Queue.
 * Note that the generic type `T` is being used here.
 *
 * <p>
 * Author: the one group of 4
 * Date: March 22, 2017
 * Course: CSC630 Data Structures and Algorithms
 */

public class LinkedList<T> {
    private T contents;
    private LinkedList<T> next;

    public LinkedList(T object) {
        contents = object;
        next = null;
    }


    /**
     * Add the given object to the end of the linked list.
     * <i>If the linked list is of size '1' and its only
     * node has no data, the given object will be inserted
     * at index 0, and the LinkedList's size will remain
     * the same.</i>
     *
     * @param o the object to add
     * @return whether the object was added successfully
     */
    public boolean add(T o) {
        /*
         * The following is done when there is no next
         * element, and the the data is null. This situation
         * occurs after .clear()/
         */
        if (getContents() == null && getNext() == null) {
            setContents(o);
            return true;
        }

        LinkedList<T> nextNode = this;
        /*
         * We start at 'next' because there is no scenario in which
         * the first element is empty (it is either 'null' or it
         * has data — that is, if you don't count 'null' as data!)
         */
        while (nextNode.getNext() != null) {
            nextNode = nextNode.getNext();
        }
        nextNode.setNext(new LinkedList<T>(o));
        return true;
        // returning true because if we made it this far, the insert actually happened
    }

    /**
     * Add the given object at the given index.
     * <i>Index '0' would insert the item as the
     * first element in the list.</i>
     *
     * @param index the index at which to insert the object
     * @param o     the object to insert
     * @return whether the insertion was successful
     * @throws IndexOutOfBoundsException if no object is present at the given index
     */
    public boolean add(int index, T o) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot have negative indices!");
        }

        LinkedList<T> node = this;

        if (index == 0) {
            // this is done separately because it is an edge case
            // the contents of the list, to be moved to the right.
            LinkedList<T> nextLinkedList = new LinkedList<>(getContents());
            nextLinkedList.setNext(getNext()); // index of each element += 1
            setContents(o); // set first node to o
            setNext(nextLinkedList); // add nextLinkedList
            return true;
        }

        for (int traversed = 1; traversed < index; traversed++) { // traversed starts at '1', because we have already traversed the first node
            // if index > size()
            if (node.getNext() == null) {
                throw new IndexOutOfBoundsException("The given index is outside the bounds of the LinkedList.");
            }
            node = node.getNext();
        }
        LinkedList<T> nodeToBeInserted = new LinkedList<T>(o);
        nodeToBeInserted.setNext(node.getNext());
        node.setNext(nodeToBeInserted);
        return true;
    }

    /**
     * Get the object at the given index.
     *
     * @param index the index of the requested object
     * @return the requested object
     * @throws IndexOutOfBoundsException if outside the bounds of the LinkedList
     */
    public T get(int index) throws IllegalArgumentException {
        if (index < 0) throw new IndexOutOfBoundsException("No negative indices!");
        LinkedList<T> requestedNode = this;
        for (int traversed = 0; traversed < index; traversed++) {
            if (requestedNode.getNext() == null) {
                throw new IndexOutOfBoundsException("The given index is outside the bounds of the LinkedList.");
            }
            requestedNode = requestedNode.getNext();
        }
        return requestedNode.getContents();
    }

    /**
     * Check whether the linked list contains the given object.
     *
     * @param object the object to search for
     * @return whether or not the given object is in the LinkedList
     */
    public boolean contains(T object) {
        // indexOf returns -1 if T object is not found in this.
        return indexOf(object) != -1;
    }

    /**
     * Return the first index of the given object in the linked list.
     * <p>
     * <i>Node: equivalency is checked using .equals(), allowing for
     * more dynamic checking (i.e. Integer(4) will match non-wrapped '4'). </i>
     *
     * @param object the object to search for
     * @return the index of the given object, returns -1 if not found.
     */
    public int indexOf(T object) {
        int nodeIndex = 0;
        LinkedList<T> currentNode = this;
        while (!nullSafeCheckEquivalence(object, currentNode.getContents())) {
            if (currentNode.getNext() == null) {
                return -1;
            }
            currentNode = currentNode.getNext();
            nodeIndex++;
        }
        return nodeIndex;
    }

    /**
     * A 'null-safe' equivalence checker. Relies on Java's built-in Object.equals() for non-null object1.
     *
     * @param object1 the first object to compare
     * @param object2 the second object to compare
     * @return whether the objects are equivalent
     */
    private boolean nullSafeCheckEquivalence(Object object1, Object object2) {
        if (object1 == null) {
            return object2 == null;
        }
        return object1.equals(object2);
    }

    /**
     * Removes all nodes in the list.
     */
    public void clear() {
        next = null;
        contents = null;
    }

    /**
     * Removes the given node from the LinkedList.
     * Previous and later nodes remain intact, however
     * later nodes indices will drop by 1 (because
     * there is one less node before them).
     *
     * @param index the index of the node to remove.
     * @return the object which was removed.
     */
    public T remove(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("No negative indices!");
        if (index == 0) {
            // done separately because traversal would lead to previousNode = null
            T removed = this.getContents();
            if (getNext() != null) {
                setContents(getNext().getContents());
                setNext(getNext().getNext());
            } else { // accounts for case where list has size 1
                clear();
            }
            return removed;
        }
        LinkedList<T> iteratingNode = this; // this is the node with which we iterate over the chain.
        LinkedList<T> previousNode = null; // the node right before 'iteratingNode'
        int iteratingNodeIndex = 0; // the index of the iterating node in the 'chain'
        while (iteratingNodeIndex < index) {
            if (iteratingNode.getNext() == null) {
                throw new IndexOutOfBoundsException("No such index!");
                // throw a new illegal argument exception, because the LinkedList 'chain' does not have such an node
            }
            previousNode = iteratingNode;
            iteratingNode = iteratingNode.getNext();
            iteratingNodeIndex++;
        }
        LinkedList<T> nodeToReinsert = iteratingNode.getNext(); // the node after
        previousNode.setNext(nodeToReinsert);
        return iteratingNode.getContents();
    }

    /**
     * Returns a String output of the list, suitable for printing.
     * Stylized to print in the same format as an array
     */
    public String toString() {
        String str = "[";
        LinkedList<T> currentNode = this;
        for (int transversed = 0; transversed < this.size(); transversed++) {
            if (currentNode.getContents() != null) {
                str += currentNode.getContents().toString();
            } else {
                str += "<empty>"; // filler for nodes with no contents
            }
            if (transversed != this.size() - 1) str += ", "; // adds a comma between the contents of different nodes
            currentNode = currentNode.getNext();
        }
        str += "]";
        return str;
    }

    /**
     * Get the number of nodes in the LinkedList.
     *
     * @return the number of nodes in the LinkedList
     */
    public int size() {
        int nodes = 1; // start at 1, because the host object will always be a node
        LinkedList<T> currentNode = this;
        while (currentNode.getNext() != null) { // when there is a next node...
            nodes++; // increment the nodes...
            currentNode = currentNode.getNext(); // move to the next node — NOTE: this will never get 'null' because the 'while' loop does a check
        }
        return nodes;
    }


    // variable encapsulation

    /**
     * Get the contents of the current node.
     *
     * @return the contents of the current node
     */
    public T getContents() {
        return contents;
    }

    /**
     * Set the contents of the current node.
     * This does not affect any nodes later in the linked list.
     *
     * @param contents the new contents of the current node
     */
    private void setContents(T contents) {
        this.contents = contents;
    }

    /**
     * Get the next node in the linked list.
     *
     * @return the next node in the linked list
     */
    public LinkedList<T> getNext() {
        return next;
    }

    /**
     * Set the next node in the linked list.
     * <i>This will, if used improperly, remove all future elements!</i>
     *
     * @param next the node to set as the next node in the linked list
     */
    private void setNext(LinkedList<T> next) {
        this.next = next;
    }
}
