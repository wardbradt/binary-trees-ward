/**
 * Created by Ward Bradt on 04/09/17.
 * Revised on 06/08/17.
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(Node<T> root) {
        super(root);
    }

    /**
     * Adds <code>T item</code> to the <code>BinarySearchTree</code> in the correct position,
     * following the condition that for any position/ root x,
     * the right branch is always bigger than the left branch.
     * Uses the recursive helper method addHelper() when the
     * root != null
     *
     * @param item
     * @return
     */
    public boolean add(T item) {
        if (item == null) {
            throw new NullPointerException("param item cannot be null!");
        }

        if (getRoot() == null) {
            setRoot(new Node<T>(item));
            return true;
        }

        // parent is the node that will be compared.
        Node<T> parent = getRoot();
        return addHelper(parent, item);
    }

    // Citations:
    // http://algs4.cs.princeton.edu/32bst/BST.java.html
    public boolean addHelper(Node<T> parent, T item) {
        int cmp = item.compareTo(parent.getContents());
        // If item is smaller comparatively, add it to the left.
        if (cmp < 0) {
            // If at the end of a branch.
            if (parent.getLeft() == null) {
                parent.setLeft(new Node<T>(item));
                return true;
            }
            // If there is an item to its left
            return addHelper(parent.getLeft(), item);
        }
        // If item is bigger, add it to the right.
        else if (cmp > 0) {
            // If at the end of a branch.
            if (parent.getRight() == null) {
                parent.setRight(new Node<T>(item));
                return true;
            }
            // If there is an item to its right
            return addHelper(parent.getRight(), item);
        }
        // If item is already in the tree, add to the left..
        else {
            // If at the end of a branch.
            if (parent.getLeft() == null) {
                parent.setLeft(new Node<T>(item));
                return true;
            }
            // If there is an item to its left
            return addHelper(parent.getLeft(), item);
        }
    }

    /**
     * Overwrites the <code>removeHelper</code> method from <code>BinaryTree</code>. Makes
     * <code>root.right</code> the new root and reformats the tree so it is still sorted.
     *
     * @param removed the Node to remove from the tree
     */
    public void removeHelper(Node<T> removed) {
        Node<T> swapped;
        if (removed.getRight() == null || removed.getLeft() == null) {
            if (removed.getLeft() != null) {
                swapped = removed.getLeft();
            }
            else if (removed.getRight() != null) swapped = removed.getRight();
            // If both left and right are null
            else swapped = null;
            if (removed.getParent() == null) setRoot(swapped);
        }

        else {
            swapped = removed.getRight();
            swapped.setLeft(removed.getLeft());
            swapped.setRight(removed.getRight().getRight());
            Node<T> rightIter = new Node<T>(swapped.getRight());
            while (rightIter.getLeft() != null) {
                rightIter = rightIter.getLeft();
            }
            rightIter.getParent().setLeft(removed.getRight().getLeft());
        }

        if (removed.getParent() == null) {
            setRoot(swapped);
        }
        else if (removed.getParent().getLeft() == removed) {
            if (swapped != null) removed.getParent().setLeft(swapped);
            else removed.getParent().setLeft(new Node<T>());
        }
        // else is if removed.getParent().getRight() == removed.
        else {
            if (swapped != null) removed.getParent().setRight(swapped);
            else removed.getParent().setRight(new Node<T>());
        }
    }
    
    @Override
    public boolean contains (T item) {
        if (getRoot().getContents() == null) return false;
    	Node<T> cur = getRoot();
    	while (cur.getContents() != null) {
    		if (cur.getContents().equals(item))
    			return true;
    		else if (cur.getContents().compareTo(item) < 0 && cur.getRight() != null)
    			cur = cur.getRight();
    		else if (cur.getLeft() != null)
    			cur = cur.getLeft();
    		else return false;
    	}
    	return false;
    }
}
