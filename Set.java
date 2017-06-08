/**
 * Created by Ward Bradt on 4/9/17.
 * Revised on 06/08/17.
 */
public class Set<T extends Comparable<T>> extends BinarySearchTree<T> {

    public Set() {
        super();
    }

	/**
	 * This is the same addHelper method as BST, except for the last
	 * else statement. If the item is already in the tree, addHelper
	 * returns false.
	 * Citation: http://algs4.cs.princeton.edu/32bst/BST.java.html
	 *
	 * @param parent The parent Node of the item (i.e whichever Node
	 *               you are testing to see if its left or right == null)
	 * @param item the item being added to the tree.
	 * @return if item was added to the tree.
	 */
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
		// If item is already in the tree, return false.
		else {
			return false;
		}
	}

    /**
     * Find the union of 2 sets.
     * 
     * @param other is the second set that you want to find the union of.
     * @return whether or not the BST is sorted correctly.
     */
    public Set<T> union(Set<T> other) {
    	Set<T> ret = new Set<T>();
    	if (other != null && other.getRoot() != null && other.getRoot().getContents() != null) {
    		for (T i: other.breadthFirst()) {
    			ret.add(i);
    		}
    	}
    	if (this != null && this.getRoot() != null && this.getRoot().getContents() != null) {
    		for (T j: this.breadthFirst()) {
    			ret.add(j);
    		}
    	}
    	return ret;
    }
    
    /**
     * Find the intersection of 2 sets.
     * 
     * @param other is the second set that you want to find the intersection of.
     * @return whether or not the BST is sorted correctly.
     */
    public Set<T> intersection(Set<T> other) {
    	Set<T> ret = new Set<T>();
    	if (other != null && other.getRoot() != null && other.getRoot().getContents() != null && this != null && this.getRoot() != null && this.getRoot().getContents() != null) {
    		for (T i: this.breadthFirst()) {
    			if (other.contains(i))
    				ret.add(i);
    		}
    	}
    	return ret;
    }
}

