/**
 * Authors: Jimmy Pham, Ward Bradt
 * Finish Date: April 12, 2017
 *
 */

public class BinaryTree<T>{
    private Node<T> root;

    public BinaryTree() {
        root = null;
    }

    /**
     * Copy constructor
     *
     * @param r is root
     */
    public BinaryTree(Node<T> r) { root = r; }
    
    public Node<T> getRoot() {
    	return root;
    }

    public void setRoot(Node<T> node) { root = node; }

    /**
     * Tells if the <code>BinaryTree</code> has any items in it.
     *
     * @return if the <code>BinaryTree</code>'s <code>contents == null</code>
     */
    public boolean empty() {
    	return root == null;
    }

    public void clear() {
    	root = null;
    }

    /**
     * Add <code>T item</code> to the <code>BinaryTree</code>. At every <code>Node</code>, first checks if the left
     * node == null then checks the right node == null. If neither are, it
     * goes to the right and checks again.
     *
     * @param item the T item to be added to the tree
     * @return true if T item is added.
     */
    public boolean add(T item) {
        Queue<Node<T>> nodes = new Queue<>(root);
        if (nodes.peek() == null) {
            root = new Node<T>(item);
            return true;
        }

        while (true) {
            // First check if the left node == null
            if (nodes.peek().getLeft() == null) {
                return addLeft(nodes.peek(), item);
            }
            // Then check the right node
            else nodes.add(nodes.peek().getLeft());
            if (nodes.peek().getRight() == null) {
                return addRight(nodes.peek(), item);
            }
            // Else go the right and repeat (with a DFS), checking left then right nodes.
            else nodes.add(nodes.peek().getRight());
            nodes.iterator().next();
        }
    }

    public boolean addLeft(Node<T> n, T item) {
    	if (n.getLeft() == null) {
    		n.setLeft(new Node<T>(item));
    		return true;
    	}
    	else 
    		return false;
    }

    public boolean addRight(Node<T>n, T item) {
    	if (n.getRight() == null) {
    		n.setRight(new Node<T>(item));
    		return true;
    	}
    	else 
    		return false;
    }

    /**
     * Removes the first instance of <code>T item</code> in a breadth-first traversal.
     *
     * @param item is the item to be removed
     * @throws NullPointerException if item is null or if item is not in the binary tree.
     */
    public void remove(T item) throws NullPointerException {
        if (item == null) throw new NullPointerException("param item cannot be null!");
        Queue<Node<T>> nodes = new Queue<>(root);
        while (!nodes.isEmpty()) {
            if (nodes.peek().getContents() == item) {
                removeHelper(nodes.peek());
                return;
            }
            if (nodes.peek().getLeft() != null)
                nodes.add(nodes.peek().getLeft());
            if (nodes.peek().getRight() != null) {
                nodes.add(nodes.peek().getRight());
            }
            nodes.iterator().next();
        }
        // throws exception if tree does not contain item
        throw new NullPointerException("item is not in the BinaryTree.");
    }

    /**
     * Finds the <code>Node</code> to swap <code>removed</code> with and swaps those two <code>Node</code>s.
     *
     * @param removed the <code>Node</code> to remove from the tree
     */
    public void removeHelper(Node<T> removed) {
        Node<T> swapped = new Node<T>();
        // If not at the end of a branch, find the end of a branch.
        if (removed.getLeft() != null || removed.getRight() != null) {
            swapped = removed.getLeft();
            // swapped must be at the end of a branch.
            while (swapped != null) {
                // If swapped is not at the end of a branch
                if (swapped.getLeft() == null || swapped.getRight() == null) {
                    swapped = swapped.getLeft();
                }
            }

            // Swap swapped and removed.
            try {
                swapped.setRight(removed.getRight());
            } catch (NullPointerException ifNull) {
            }
            try {
                swapped.setLeft(removed.getLeft());
            } catch (NullPointerException ifNull) {
            }
        }

        if (removed.getParent() != null && swapped != null) {
            if (removed.getParent().getLeft() == removed) {
                removed.getParent().setLeft(swapped);
            }
            else if (removed.getParent().getRight() == removed) {
                removed.getParent().setRight(swapped);
            }
        }
        removed.setContents(null);
    }

    /**
     * Determines if any <code>Node</code>s in the tree contain a specified <code>Object</code>.
     *
     * @param item is the item the method searches for.
     * @return if <code>item</code> is in the <code>BinaryTree</code>
     */
    public boolean contains(T item) {
        if (root.getContents() != item) {
            // create binary tree from left and right then create a branched recursion
            // to check if each subbranch contains item.
            if (root.getLeft() != null) {
                BinaryTree<T> left = new BinaryTree<>(root.getLeft());
                if (left.contains(item)) return true;
            }
            if (root.getRight() != null) {
                BinaryTree<T> right = new BinaryTree<>(root.getRight());
                if (right.contains(item)) return true;
            }
            return false;
        }
        return true;
    }

    /**
     * <code>get(T item)</code> searches for an <code>Object</code> in the <code>BinaryTree</code>.
     * Received help from
     * http://stackoverflow.com/questions/5262308/how-do-implement-a-breadth-first-traversal
     *
     * @param item is what is being searched for in the <code>BinaryTree</code>
     * @return the first breadth-first reference to T item if it is in the BinaryTree
     * @throws NullPointerException if item == null or if item is not in the BinaryTree
     */
    public Node<T> get(T item) throws NullPointerException {
        if (item == null) throw new NullPointerException("parameters cannot be null!");
        Queue<Node<T>> nodes = new Queue<>(root);
        while (!nodes.isEmpty()) {
            if (nodes.peek().getContents() == item)
                return nodes.iterator().next();
            if (nodes.peek().getLeft() != null)
                nodes.add(nodes.peek().getLeft());
            if (nodes.peek().getRight() != null)
                nodes.add(nodes.peek().getRight());
            nodes.iterator().next();
        }
        throw new NullPointerException("item is not in the BinaryTree.");
    }

    /**
     * Returns a breadth-first <code>Iterable Object</code> of the <code>BinaryTree</code>.
     * <code>Queue</code> class implements Iterable<T> using <code>java.util.Iterator</code>
     *
     * @return a breadth-first iterable object of the tree.
     */
    public Queue<T> breadthFirst() {
        Queue<T> breadth = new Queue<T>();
        Queue<Node<T>> nodes = new Queue<>(root);
        // A breadth first traversal in this form never looks at root.
        while (!nodes.isEmpty()) {
            if (nodes.peek().getContents() != null) {
                if (nodes.peek().getLeft() != null) {
                    nodes.add(nodes.peek().getLeft());
                }
                if (nodes.peek().getRight() != null)
                    nodes.add(nodes.peek().getRight());
                breadth.add(nodes.iterator().next().getContents());
            }
            else {
                nodes.iterator().next();
            }
        }
        return breadth;
    }
    
    /**
     * Returns a depth-first iterable object of the branch tree.
     * <code>Queue</code> class implements <code>Iterable<T></code> using <code>java.util.Iterator</code>
     *
     * @return a breadth-first <code>Iterable</code> object of the tree.
     */
    public Queue<T> depthFirst() {
        Stack<Node<T>> nodes = new Stack<Node<T>>(getRoot());
        Queue<T> depth = new Queue<T>(root.getContents());
        if (nodes.peek().getLeft() != null) {
            depth.addQueue(new BinaryTree<>(nodes.peek().getLeft()).depthFirst());
        }
        if (nodes.peek().getRight() != null) {
            depth.addQueue(new BinaryTree<>(nodes.peek().getRight()).depthFirst());
        }
        nodes.pop();
        return depth;
    }
}
