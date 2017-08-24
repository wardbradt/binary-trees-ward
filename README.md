# Binary Trees
##### Created April 5, 2017
##### Author: Ward Bradt

This project works with binary trees and the multitude of data structures that are related to or associated with them. It explores creative modifications to many of them, such as depth-first additions in the <code>BinarySearchTree</code> class and a custom sorting convention similar to a <code>PriorityQueue</code> in the <code>Heap</code> class.
### Classes Included
2. `BinaryTree`
3. `BinarySearchTree` and `Set`
4. `Heap` and `PriorityQueue`


### Helper Classes
1. `Stack`
1. `Node`
1. `LinkedList`
1. `Queue`

## `Stack` class
This class was written as part of a group project by Tony Tan, Jimmy Pham, Ward Bradt, and Miles McCain.
<br>
This `Stack` class contains the following features:
* A no-args constructor
* `public boolean empty()` -- Tests if this stack is empty.
* `public T peek()` -- Looks at the object at the top of this stack without removing it from the stack.
* `public T pop()` -- Removes the object at the top of this stack and returns that object as the value of this function.
* `public T push(T item)` -- Pushes an item onto the top of this stack.
* `public int search(Object o)` -- Returns the 1-based position where an object is on this stack. If the object o occurs as an item in this stack, this method returns the distance from the top of the stack of the occurrence nearest the top of the stack; the topmost item on the stack is considered to be at distance 1. The equals method is used to compare o to the items in this stack.
* `public String toString()` -- Returns an attractive printout of the whole stack.  (Note that this cannot be completed with the public API, unless you make a copy of the stack.  No need to stick to only public methods for this implementation!)

## `Node` class
A `Node` object is very similar to a `BinaryTree` and lays out the foundation for many binary tree classes. Each `Node` object holds a reference to a `T contents`, a `Node<T> parent`, a `Node<T> left`, and a `Node<T> right`.

## `LinkedList` class
This is an implementation of a basic linked list data structure.

This `LinkedList` class contains:
* A "root contents" constructor
* `boolean add(T o)` -- Adds the element `o` at the end
* `boolean add(int index, T o)` -- Adds the element `o` at the specified position.
* `T get(int index)` -- Returns the element at the specified position in this list.
* `boolean contains(T o)` -- returns `true` if `o` is the list.
* `int indexOf(T o)` -- returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
* `int size()` -- Returns the number of elements in this list.
* `void clear()` -- Removes all elements of the list.
* `T remove(int index)` -- Removes and returns the element at the specified position in this list.
* `String toString()` -- Returns a String output of the list, suitable for printing.

## `Queue` class
An object of type `Queue` follows a "first-in first-out" convention. Each `Queue` holds a reference to a `Queue` object `next`, thus providing the connections for this data structure.

This `Queue` class contains:
* A no-args constructor
* A "root contents" constructor
* `public Iterator<T> iterator()` -- Returns an iterator over the `Queue` object from front to back
* `public int size()` -- Returns the number of elements currently in the queue.
* `public boolean isEmpty()` -- Returns if the queue contains any elements.
* `public boolean add(T o)` -- Adds `o` to the end of the queue.
* `public T peek()` -- Returns the front-most element.
* `public boolean addQueue(Queue<T> other)` -- Appends `other` to the end of the queue.


## `BinaryTree` class
An object of type `BinaryTree` constitutes an entire binary tree. A node of this binary tree will use a helper class `Node`. A binary tree is a data structure where every node has two _child_ nodes, usually called `left` and `right`.  The node that other classes have access to is usually called the `root` node.

This `BinaryTree` class contains:
* A no-args constructor
* `public boolean empty()` -- Test if this tree is empty.
* `public void clear()` -- Empty the tree.
* `public boolean add(T item)` -- Add `item` to the _first available_ spot in a breadth-first traversal of the tree. Returns whether `item` was successfully added (for now, always `true`).
* `public boolean addLeft(Node<T> n, T item)` -- Add `item` as the `left` child of `n`, so long as `n.left` is currently `null`.  Returns whether `item` was successfully added.
* `public boolean addRight(Node<T> n, T item)` -- Add `item` as the `right` child of `n`, so long as `n.right` is currently `null`.  Returns whether `item` was successfully added.
* `public void remove(T item) throws NullPointerException` -- Remove from the tree the _first occurrence_ of `item` in a breadth-first traversal of the tree.  Return
* `public boolean contains(T item)` -- Determines if `item` is in the tree.  Throws a `NullPointerException` if no nodes in the tree contain `item`.
* `public Node<T> get(T item) throws NullPointerException` -- Returns (a reference to) the first `Node` in the tree (under a breadth-first search) containing `item`.  Throws a `NullPointerException` if no nodes in the tree contain `item`.
* `public java.util.Iterator<T> breadthFirst()` -- Return an iterator over the tree in a breadth-first order, skipping `null` nodes.
* `public java.util.Iterator<T> depthFirst()` -- Return an iterator over the tree in a depth-first order, skipping `null` nodes.
* Any other methods you need in order to make the below classes work!

## `BinarySearchTree` class
A _binary search tree_ is a binary tree that is ordered (so it's contents `T` must be `Comparable`) such that any `Node n` satisfies the property: `n.left` and any of its children compare to smaller than `n`, and `n.right` and any of its children compare to larger than `n`.

This `BinarySearchTree` class contains:
* A no-args constructor
* `public boolean empty()` -- Test if this tree is empty.
* `public void clear()` -- Empty the tree.
* `public boolean add(T item)` -- Add `item` in a valid spot.  Returns whether `item` was successfully added (for now, always `true`).
* `public boolean remove(T item)` -- Remove from the tree _one occurrence_ of `item` in the tree,  _without_ removing anything else (Don't lop off entire limbs).
* `public boolean contains(T item)` -- Determines if `item` is in the tree.
* `public java.util.Iterator<T> sorted(boolean reversed)` -- Return an iterator over the tree in sorted order, possibly `reversed`.

## `Set` class
A set is a container that contains at most one of any element.  Thus, your contents `T` should have a valid `equals` method.  A `Set` should use a `BinarySearchTree` to perform its functionality.

Your `Set` class should contain:
* A no-args constructor
* `public boolean empty()` -- Test if this set is empty.
* `public void clear()` -- Empty the set.
* `public boolean add(T item)` -- Add `item`.  Returns whether `item` was successfully added, which is `false` precisely when another copy of `item` is already in the set.
* `public void remove(T item)` -- Remove `item` from the set.
* `public boolean contains(T item)` -- Determines if `item` is in the set.
* `public Set union(Set other)` -- performs the _union_ of `this` with `other`, _i.e._ the set containing all the elements of `this` or `other`.
* `public Set intersection(Set other)` -- performs the _intersection_ of `this` with `other`, _i.e._ the set containing all the elements present in **both** `this` and `other`.
* **Fun Aside**: Create a `main` method for `Set` that takes in the name of a text file via command-line argument and creates an ordered dictionary of words in the text, output as another text file with one word per line.

## `Heap` class
This implementation of a binary search tree follows a custom ordering resembling that of a priority queue.

A _heap_ is a tree that satisfies the _heap_ property: every node `n` has the property that both `n.left` (and all its children) and `n.right` (and all its children) compare to being _less than_ `n` (This is sometimes referred to as the _max-heap_ property, and their are similarly _min-heaps_).  Note again that `T` must be `Comparable` to make this happen.

Your `Heap` class should contain:
* A no-args constructor
* `public boolean add(T item)` -- Add `item` to the heap in a valid spot.  Returns whether `item` was successfully added (always `true`).
* `public T	extractMax()` -- Returns the maximum object, then removes that object from the heap.  Note that the maximum object is the root, so this method should reset the `root`.
* `public T	max()` -- Returns the maximum object
* `public boolean contains(T item)` -- Determines if `item` is in the heap.
* `public boolean empty()` -- Test if this heap is empty.
*  `public java.util.Iterator<T> iterate()` -- Returns an iterator over its elements in order of largest to smallest.
* `public int size()` -- Returns the number of elements currently in the heap.
* `public void clear()` -- Empty the heap.

## `PriorityQueue` class
A Priority Queue is a queue (first-in, first-out, otherwise known as a line) where items are ordered by their natural comparisons, and instead of taking from the "front of the line" you take the highest priority element.  A `PriorityQueue` should use a `Heap` to perform its functionality.

* A no-args constructor
* `public boolean add(T item)` -- Add `item` to the "back" of the queue, then propagate the item to the proper place in the queue.  Returns whether `item` was successfully added (always `true`).
* `public T next()` -- Return the highest priority item from the queue, removing it in the process.
* `public T peekNext()` -- Return the highest priority item from the queue.
* `public boolean contains(T item)` -- Determines if `item` is in the queue.
* `public int size()` -- Returns the number of elements currently in the queue.

## Acknowledgements/ Citations
* All of these classes were written as part of the coursework in my "Data Structures and Algorithms" class. Nicholas Zufelt, the teacher of that class, wrote the descriptions for some of these data structures' methods.
* http://algs4.cs.princeton.edu/32bst/BST.java.html
* http://stackoverflow.com/questions/5262308/how-do-implement-a-breadth-first-traversal