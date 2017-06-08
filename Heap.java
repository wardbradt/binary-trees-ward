/**
 * Created by Ward Bradt on 4/9/17.
 * Revised on 06/08/17.
 *
 * A <code>Heap</code> with some elements of a <code>PriorityQueue</code>. This data structure does not automatically
 * balance when elements are removed/ added. Each <code>Heap</code> has two child <code>Heap</code>s.
 * <br>
 * This data structure follows the following convention:
 * For any <code>Heap n</code>, <code>n.contents</code> > <code>n.right</code>'s contents and all of
 * <code>n.right</code>'s children's contents > <code>n.left</code>'s contents and all of <code>n.left</code>'s
 * children's contents.
 *
 */
public class Heap<T extends Comparable<T>>{
    private T contents;
    private Heap<T> left, right;

    public Heap() {
        contents = null;
        left = null;
        right = null;
    }

    public Heap(T item) {
        contents = item;
        left = null;
        right = null;
    }

    /**
     * Copy constructor for <code>Heap</code>
     *
     * @param rootHeap the Heap that is being copied.
     */
    public Heap(Heap<T> rootHeap) {
        contents = rootHeap.contents;
        left = rootHeap.left;
        right = rootHeap.right;
    }

    public Heap<T> getLeft() {
        return left;
    }

    public Heap<T> getRight() {
        return right;
    }

    public T getContents() {
        return contents;
    }

    // We want the contents of the left heap to always be smaller.

    /**
     * Add T item to the <code>heap</code>. This add method follows the convention that
     * <code>n.contents</code> > <code>n.right</code> and all of its children > <code>n.left</code> and all of its children.
     * Uses recursive helper method <code>addHelper(Heap<T> parent, T item)</code>.
     *
     * @param item the item that is to be added.
     * @return true if item is added
     */
    public boolean add(T item) {
        if (item == null) {
            throw new NullPointerException("param item cannot be null!");
        }

        if (contents == null) {
            contents = item;
            return true;
        }

        if (item.compareTo(contents) > 0) {
            right = new Heap<T>(this);
            left = null;
            contents = item;
            return true;
        }

        // parent is the node that will be compared.
        return addHelper(this, item);
    }

    /**
     * Recursive helper method for <code>add(T item)</code>. This method is only reached if
     * neither <code>contents</code>, <code>left</code>, or <code>right == null</code>.
     *
     * @param parent The "root" <code>Heap</code> of the item that is to be added. On the first
     *               iteration parent is <code>this</code> - the root <code>Heap</code>.
     * @param item the item that it to be added.
     * @return if <code>item</code> is added.
     */
    public boolean addHelper(Heap<T> parent, T item) {
        // If either of the parent's right or left branches are null
        if (parent.left == null && parent.right == null) {
            parent.right = new Heap<T>(item);
            return true;
        }

        // If it gets here, parent.left != null and parent.right == null
        if (parent.right == null) {
            // If item is smaller than left's contents, then we need to swap them
            // because bigger items are always on the right.
            if (item.compareTo(parent.left.contents) < 0) {
                parent.right = new Heap<T>(parent.left);
                parent.left = new Heap<T>(item);
                return true;
            }
            parent.right = new Heap<T>(item);
            return true;
        }

        // If it gets here, left == null and right != null
        if (parent.left == null) {
            // If item is larger than right's contents, need to swap.
            if (item.compareTo(parent.right.contents) > 0) {
                parent.left = new Heap<T>(parent.right);
                parent.right = new Heap<T>(item);
                return true;
            }
            parent.left = new Heap<T>(item);
            return true;
        }

        // parent.left is always smaller than parent.right (contents)
        // If it is smaller than the parent's left branch
        if (item.compareTo(parent.left.contents) < 0) {
            return addHelper(parent.left, item);
        }

        // If item is bigger than parent.left and smaller than parent.right
        else if (item.compareTo(parent.right.contents) < 0) {
            // This is keeping mind that with a Heap x, x.contents > right.contents > left.contents
            Heap<T> newMid = new Heap<T>(item);
            // Set the new top branch's left to the right of whatever it is replacing.
            if (parent.left.right != null) newMid.left = parent.left.right;
            if (parent.left.left != null) newMid.left.right = parent.left.left;
            // Set the new top branch's right as a new Heap with the contents of parent's left.
            newMid.right = new Heap<T>(parent.left.contents);
            parent.left = newMid;
            return true;
        }

        // If item is bigger than both the left and right branches
        else if (item.compareTo(parent.right.contents) > 0) {
            Heap<T> newMid = new Heap<T>(item);
            newMid.left = parent.left;
            newMid.right = parent.right;
            // Put newMid on the right branch of parent
            parent.right = newMid;
            parent.left = null;
            return true;
        }

        // Only if item == both the parent's left and right branches
        return true;
    }

    /**
     * Return the <code>contents</code>, or the greatest object, of this <code>Heap</code> and reformat
     * the <code>Heap</code> with <code>contents</code> removed.
     * Returns <code>null</code> if <code>contents == null</code>.
     *
     * @return the greatest object in the <code>Heap</code>
     */
    public T extractMax() {
        T tempCon = contents;
        if (left == null || right == null) {
            if (right != null) setRoot(right);
            // Only if left != null or both are null
            else setRoot(left);
        }
        // Only gets here if neither the left or right branch is null.
        else {
            contents = right.contents;
            right = new Heap<T>(right.extractMax());
        }
        return tempCon;
    }

    /**
     * Only to be used if user would like to completely clear the heap.
     * Sort of a copy constructor, setting <code>this</code> to <code>newRoot</code>.
     */
    public void setRoot(Heap<T> newRoot) {
        if (newRoot != null) {
            contents = newRoot.contents;
            left = newRoot.left;
            right = newRoot.right;
        }
    }

    /**
     * Removes all elements from the <code>Heap</code>.
     */
    public void clear() {
        contents = null;
        right = null;
        left = null;
    }

    /**
     * Returns an iterable object of the <code>Heap</code> in descending order
     *
     * @return a Que
     */
    public Queue<T> iterate() {
        Stack<Heap<T>> nodes = new Stack<Heap<T>>(this);
        Queue<T> depth = new Queue<T>(contents);
        if (nodes.peek().getRight() != null) {
            depth.addQueue(nodes.peek().getRight().iterate());
        }
        if (nodes.peek().getLeft() != null) {
            depth.addQueue(nodes.peek().getLeft().iterate());
        }
        nodes.pop();
        return depth;
    }

    /**
     * Returns the maximum Object
     * @return the highest-valued <code>Object</code> in this <code>Heap</code>
     */
    public T max() {
        return contents;
    }

    public boolean empty() { return contents == null; }

    /**
     * Tests if an item is in this <code>Heap</code>.
     * @param item an Object
     * @return if <code>item</code> is in this <code>Heap</code>
     */
    public boolean contains (T item) {
        int cmp = item.compareTo(contents);
        if (cmp == 0) return true;
        if (cmp > 0) {
            return false;
        }
        if (left != null) {
            if (left.contains(item)) return true;
        }
        if (right != null) {
            if (right.contains(item)) return true;
        }
        return false;
    }

    /**
     * Recursively count how many <code>Heap</code>s, or <code>Object</code>s, are in the root <code>Heap</code>.
     * This is done through a depth-first recursive iteration.
     * @return how many <code>Heap</code>s, or <code>Object</code>s, are in the root <code>Heap</code>
     */
    public int size() {
        int count = 0;

        if (contents != null) count++;

        if (right != null) {
            count+= right.size();
        }

        if (left != null) {
            count += left.size();
        }

        return count;
    }
}
