
public class DLLNode<T> {

    private T element; // element stored in node
    private DLLNode<T> next; // Reference to next Node
    private DLLNode<T> prev; // Reference to prev Node


    // Constructor for DLLNode

    /**
     * @param element
     */
    public DLLNode(T element) {
        setElement(element);
    }

    // Sets the element variable

    /**
     * @param element
     */
    public void setElement(T element) {
        this.element = element;
    }

    // Sets the next variable

    /**
     * @param next
     */
    public void setNext(DLLNode<T> next) {
        this.next = next;
    }

    // Sets the prev variable

    /**
     * @param prev
     */
    public void setPrev(DLLNode<T> prev) {
        this.prev = prev;
    }

    // Get method for Node Element

    /**
     * @return
     */
    public T getElement() {
        return element;
    }

    // Get method for prev variable

    /**
     * @return
     */
    public DLLNode<T> getPrev() {
        return prev;
    }

    // Get method for next variable

    /**
     * @return
     */
    public DLLNode<T> getNext() {
        return next;
    }
}


