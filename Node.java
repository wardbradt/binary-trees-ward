/**
 * Created by Jimmy Pham and Ward Bradt
 * @param <T>
 */
public class Node<T> {
    private T contents;
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    public Node() {
        contents = null;
        left = null;
        right = null;
        parent = null;
    }

    public Node(T item) {
        contents = item;
        left = null;
        right = null;
        parent = null;
    }

    /**
     * Copy constructor
     *
     * @param copiedRoot the Node that is being copied
     */
    public Node(Node<T> copiedRoot) {
        contents = copiedRoot.getContents();
        left = copiedRoot.getLeft();
        right = copiedRoot.getRight();
        parent = copiedRoot.getParent();
    }

    public T getContents() {
        return contents;
    }

    public void setContents(T item) { contents = item; }

    public void setParent(Node<T> p) { parent = p; }

    public Node<T> getParent() { return parent; }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setLeft(Node<T> item) {
        item.setParent(this);
        left = item;
    }

    public void setRight(Node<T> item) {
        item.setParent(this);
        right = item;
    }
}